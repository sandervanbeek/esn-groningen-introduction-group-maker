package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.Group;
import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.model.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The {@code LogsPane} class is a custom {@link JEditorPane} that displays
 * logs related to group formation based on a {@link GroupingModel}. This pane
 * presents log messages in an HTML format, allowing for rich text display
 * of warnings and information regarding group configurations.
 *
 * <p>The logs include warnings about dietary restrictions, nationality
 * distribution, and gender balance within groups. It aims to provide users
 * with insights into the group composition and any potential issues.</p>
 *
 * @see GroupingModel
 * @see Group
 * @see Settings
 */
public class LogsPane extends JEditorPane {

    /**
     * Constructs a {@code LogsPane} with the specified grouping model.
     *
     * <p>This constructor initializes the pane to display HTML content and
     * generates the log messages based on the provided grouping model. It
     * also sets visual properties such as margin and font style, and
     * disables editing of the pane.</p>
     *
     * @param groupingModel the {@link GroupingModel} containing groups whose
     *                      configurations will be analyzed for log generation.
     */
    public LogsPane(GroupingModel groupingModel) {
        // Set the content type to HTML for rendering formatted text
        setContentType("text/html");

        // Generate and set the text for the pane based on the grouping model
        setText(generateLogs(groupingModel));

        // Set margin for the pane
        EmptyBorder margin = new EmptyBorder(10, 10, 10, 10);
        setBorder(margin);

        // Enable display properties and set font
        putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        setFont((new JTextArea("").getFont())); // Use JTextArea font for consistency
        setEditable(false);
    }

    /**
     * Generates log messages based on the provided grouping model.
     *
     * <p>This method analyzes the groups in the grouping model to check
     * for warnings regarding dietary restrictions, nationality distribution,
     * and gender balance. It constructs a summary of any warnings found.</p>
     *
     * @param groupingModel the {@link GroupingModel} used to gather
     *                      information about the groups.
     * @return a string containing HTML formatted log messages.
     */
    private String generateLogs(GroupingModel groupingModel) {
        // Flags to track the presence of different types of warnings
        boolean dietWarnings = false;
        boolean nationalityWarnings = false;
        boolean genderWarnings = false;

        // Initialize logs with a success message
        StringBuilder logs = new StringBuilder("<b>Run successful!</b>");
        Settings settings = groupingModel.getSettings();

        // Check for diet warnings
        StringBuilder dietWarningsText = new StringBuilder();
        for (Group group : groupingModel.getGroups()) {
            int plantBasedEaters = group.getNumberOfPlantBasedEaters();
            if (plantBasedEaters > settings.getPlantBasedGroupMaximum()) {
                dietWarningsText.append("<br>").append(plantBasedEaters).append(" participants in Group ")
                        .append(group.getGroupNumber()).append(" eat plant-based");
                dietWarnings = true;
            }
        }

        // Check for nationality warnings
        StringBuilder nationalitiesWarningsText = new StringBuilder();
        for (Group group : groupingModel.getGroups()) {
            int maxSameNationalities = group.getMaxSameNationality();
            if (maxSameNationalities > settings.getSameNationalityGroupMaximum()) {
                nationalitiesWarningsText.append("<br>").append(maxSameNationalities).append(" participants in Group ")
                        .append(group.getGroupNumber()).append(" are from ")
                        .append(group.getMostCommonNationality());
                nationalityWarnings = true;
            }
        }

        // Check for gender warnings
        StringBuilder genderWarningsText = new StringBuilder();
        for (Group group : groupingModel.getGroups()) {
            int genderRatio = group.getMaxSameGenderPercentage();
            if (genderRatio > settings.getSameGenderPercentageLimit()) {
                genderWarningsText.append("<br>").append(genderRatio).append("% of participants in Group ")
                        .append(group.getGroupNumber()).append(" are of the same gender");
                genderWarnings = true;
            }
        }

        // Append warning summaries to logs
        if (!dietWarnings && !nationalityWarnings && !genderWarnings) {
            logs.append("<br><br>There were no warnings.");
        } else {
            logs.append("<br><br>There are warnings that you should take note of.");

            if (dietWarnings) {
                logs.append("<br><br><b>Warnings about too many plant-based eaters in groups:</b>")
                        .append(dietWarningsText);
            }

            if (nationalityWarnings) {
                logs.append("<br><br><b>Warnings about too many participants of the same nationality in groups:</b>")
                        .append(nationalitiesWarningsText);
            }

            if (genderWarnings) {
                logs.append("<br><br><b>Warnings about gender imbalances in groups:</b>")
                        .append(genderWarningsText);
            }
        }

        // Return the constructed log messages as a string
        return logs.toString();
    }
}