package nl.esn.groningen.groupmaker.util;

import nl.esn.groningen.groupmaker.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

import org.apache.poi.xwpf.usermodel.*;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.XmlCursor;

/**
 * Utility class for generating various output documents, including CSV files
 * and customized DOCX files, based on the data from a {@link GroupingModel}.
 *
 * <p>This class provides static methods to generate CSV files for guides, participants,
 * and groups, as well as DOCX files with customized content for each group based on
 * a provided email template.</p>
 */
public class GenerateOutputDocuments {

    /**
     * Generates output documents including CSV files and DOCX files for the given
     * {@link GroupingModel}. If an email template is provided, DOCX files are generated
     * for each group.
     *
     * @param groupingModel The model containing the guides, participants, groups, and output settings.
     */
    public static void generate(GroupingModel groupingModel) {
        List<GuideCluster> guideClusters = groupingModel.getGuideClusters();
        List<Participant> participants = groupingModel.getParticipants();
        List<Group> groups = groupingModel.getGroups();

        File emailTemplate = groupingModel.getEmailTemplate();
        String outputFolderPath = groupingModel.getOutputFolderPath();

        try {
            // Generate CSV files for guides, participants, and groups
            generateGuidesCSV(guideClusters, outputFolderPath);
            generateParticipantsCSV(participants, outputFolderPath);
            generateGroupsCSV(groups, outputFolderPath);

            // Generate customized DOCX files for each group if an email template is provided
            if (emailTemplate != null) {
                for (Group group : groups) {
                    generateMails(group, emailTemplate, outputFolderPath);
                }
            }
        } catch (IOException e) {
            // Show an error dialog if an I/O error occurs during the generation process
            DialogHandler.showExportError();
        }
    }

    /**
     * Generates a DOCX file for a group by replacing placeholders in the email template
     * with group-specific data.
     *
     * @param group The group for which to generate the DOCX file.
     * @param emailTemplate The template file to use for generating the DOCX.
     * @param outputFolderPath The directory where the generated file should be saved.
     * @throws IOException If an error occurs while reading or writing the file.
     */
    private static void generateMails(Group group, File emailTemplate, String outputFolderPath) throws IOException {
        String outputFilePath = outputFolderPath + "/group" + group.getGroupNumber() + ".docx";  // Path to save the modified .docx file

        try {
            // Open the DOCX template document
            FileInputStream fis = new FileInputStream(emailTemplate);
            XWPFDocument document = new XWPFDocument(fis);

            // Iterate over all paragraphs in the document to replace placeholders with actual data
            for (int p = 0; p < document.getParagraphs().size(); p++) {
                XWPFParagraph paragraph = document.getParagraphs().get(p);
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (int i = 0; i < runs.size(); i++) {
                        XWPFRun run = runs.get(i);
                        String text = run.getText(0);

                        if (text != null) {
                            // Replace placeholders with actual group data
                            text = text.replace("[group number]", Integer.toString(group.getGroupNumber()));
                            text = text.replace("[theme]", group.getTheme());
                            run.setText(text, 0);

                            // Insert a table with guides or participants if their placeholders are found
                            if (text.contains("[guides]") || text.contains("[participants]")) {
                                XmlCursor cursor = paragraph.getCTP().newCursor();
                                XWPFTable table = document.insertNewTbl(cursor);
                                table.setWidth("100%");

                                if (text.contains("[guides]")) {
                                    List<Guide> guides = group.getGuideCluster().getGuides();
                                    fillGuidesTable(table, guides);
                                } else {
                                    List<Participant> participants = group.getParticipants();
                                    fillParticipantsTable(table, participants);
                                }

                                // Remove the paragraph after the table has been inserted
                                document.removeBodyElement(document.getPosOfParagraph(paragraph));
                                p--;
                                break;
                            }
                        }
                    }
                }
            }

            // Save the modified document to the specified file
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            document.write(fos);
            fos.close();

            // Close the document streams
            document.close();
            fis.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Fills a table in the DOCX document with the guide data of a group.
     *
     * @param table The table to be filled with guide data.
     * @param guides The list of guides in the group.
     */
    private static void fillGuidesTable(XWPFTable table, List<Guide> guides) {
        // Create and format the table header row
        XWPFTableRow tableHeaderRow = table.getRow(0);
        tableHeaderRow.getCell(0).setText("Name");
        tableHeaderRow.addNewTableCell().setText("Email");
        tableHeaderRow.addNewTableCell().setText("Phone number");

        // Make the header text bold
        tableHeaderRow.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
        tableHeaderRow.getCell(1).getParagraphs().get(0).getRuns().get(0).setBold(true);
        tableHeaderRow.getCell(2).getParagraphs().get(0).getRuns().get(0).setBold(true);

        // If there are no guides, return early
        if (guides == null) return;

        // Add a row for each guide in the group
        for (Guide guide : guides) {
            XWPFTableRow newRow = table.createRow();
            newRow.getCell(0).setText(guide.getFirstName() + " " + guide.getLastName());
            newRow.addNewTableCell().setText(guide.getEmail());
            newRow.addNewTableCell().setText(guide.getPhoneNumber());
        }
    }

    /**
     * Fills a table in the DOCX document with the participant data of a group.
     *
     * @param table The table to be filled with participant data.
     * @param participants The list of participants in the group.
     */
    private static void fillParticipantsTable(XWPFTable table, List<Participant> participants) {
        // Create and format the table header row
        XWPFTableRow tableHeaderRow = table.getRow(0);
        tableHeaderRow.getCell(0).setText("Name");
        tableHeaderRow.addNewTableCell().setText("Email");
        tableHeaderRow.addNewTableCell().setText("Phone number");
        tableHeaderRow.addNewTableCell().setText("Nationality");
        tableHeaderRow.addNewTableCell().setText("Alcohol-free");
        tableHeaderRow.addNewTableCell().setText("Dietary preferences");
        tableHeaderRow.addNewTableCell().setText("Other preferences / allergies");

        // Make the header text bold
        for (int i = 0; i < 7; i++) {
            tableHeaderRow.getCell(i).getParagraphs().get(0).getRuns().get(0).setBold(true);
        }

        // If there are no participants, return early
        if (participants == null) return;

        // Add a row for each participant in the group
        for (Participant participant : participants) {
            XWPFTableRow newRow = table.createRow();
            newRow.getCell(0).setText(participant.getFirstName() + " " + participant.getLastName());
            newRow.getCell(1).setText(participant.getEmail());
            newRow.getCell(2).setText(participant.getPhoneNumber());
            newRow.getCell(3).setText(participant.getNationality());
            newRow.getCell(4).setText(participant.getAlcoholFree());
            newRow.getCell(5).setText(participant.getDiet());
            newRow.getCell(6).setText(participant.getAllergies());
        }
    }

    /**
     * Generates a CSV file containing information about all guides.
     *
     * @param guideClusters The list of guide clusters to include in the CSV.
     * @param outputFolderPath The directory where the CSV file should be saved.
     * @throws IOException If an error occurs while writing the file.
     */
    private static void generateGuidesCSV(List<GuideCluster> guideClusters, String outputFolderPath) throws IOException {
        if (guideClusters == null) return;

        String csvFile = outputFolderPath + "/guides-matched.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write the header row
            writer.append("Group number,First name,Last name,Email,Phone number\n");

            // Write each guide's data as a new row
            for (GuideCluster guideCluster : guideClusters) {
                List<Guide> guides = guideCluster.getGuides();
                if (guides == null) continue;
                for (Guide guide : guides) {
                    writer.append(Integer.toString(guide.getGroupNumber())).append(',')
                            .append(guide.getFirstName()).append(',')
                            .append(guide.getLastName()).append(',')
                            .append(guide.getEmail()).append(',')
                            .append(guide.getPhoneNumber()).append(',')
                            .append(guide.getUniversity()).append(',')
                            .append(guide.getDiet()).append(',')
                            .append(guide.getAllergies()).append(',')
                            .append(guide.getAlcoholFree()).append('\n');
                }
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Generates a CSV file containing information about all participants.
     *
     * @param participants The list of participants to include in the CSV.
     * @param outputFolderPath The directory where the CSV file should be saved.
     * @throws IOException If an error occurs while writing the file.
     */
    private static void generateParticipantsCSV(List<Participant> participants, String outputFolderPath) throws IOException {
        if (participants == null) return;

        String csvFile = outputFolderPath + "/participants-matched.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write the header row
            writer.append("Group number,First name,Last name,Email,Phone number,Gender,Nationality,Date of birth,University,Study duration,Diet,Diet (additional),Alcohol-free,Requests Introduction Guide,Group leader\n");

            // Write each participant's data as a new row
            for (Participant participant : participants) {
                writer.append(Integer.toString(participant.getGroupNumber())).append(',')
                        .append(participant.getFirstName()).append(',')
                        .append(participant.getLastName()).append(',')
                        .append(participant.getEmail()).append(',')
                        .append(participant.getPhoneNumber()).append(',')
                        .append(participant.getGender()).append(',')
                        .append(participant.getNationality()).append(',')
                        .append(participant.getBirthDate()).append(',')
                        .append(participant.getUniversity()).append(',')
                        .append(participant.getStudyDuration()).append(',')
                        .append(participant.getDiet()).append(',')
                        .append(participant.getAllergies()).append(',')
                        .append(participant.getAlcoholFree()).append(',')
                        .append(participant.getRequestsGuide()).append(',')
                        .append(participant.getCanGuide()).append('\n');
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Generates a CSV file containing information about all groups.
     *
     * @param groups The list of groups to include in the CSV.
     * @param outputFolderPath The directory where the CSV file should be saved.
     * @throws IOException If an error occurs while writing the file.
     */
    private static void generateGroupsCSV(List<Group> groups, String outputFolderPath) throws IOException {
        if (groups == null) return;

        String csvFile = outputFolderPath + "/groups.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write the header row
            writer.append("Group number,University,Study duration,Alcohol-free\n");

            // Write each group's data as a new row
            for (Group group : groups) {
                writer.append(Integer.toString(group.getGroupNumber())).append(',')
                        .append(group.getUniversityType()).append(',')
                        .append(group.getStudyDurationType()).append(',')
                        .append(group.getAlcoholType()).append('\n');
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}