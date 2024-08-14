package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.controller.buttons.OpenGroupsTableButton;
import nl.esn.groningen.groupmaker.controller.buttons.OpenLogsButton;
import nl.esn.groningen.groupmaker.controller.buttons.OpenParticipantsTableButton;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The {@code MainPanel} class represents a panel in the user interface that
 * displays information and controls related to the grouping model.
 *
 * <p>This panel shows the paths for guides, participants, themes, templates,
 * and the output folder. It also includes buttons for displaying matched participants,
 * groups, and logs, updating dynamically based on the state of the grouping model.</p>
 *
 * @see GroupingModel
 * @see OpenParticipantsTableButton
 * @see OpenGroupsTableButton
 * @see OpenLogsButton
 */
public class MainPanel extends JPanel implements Observer {
    private final GroupingModel groupingModel;
    private final JLabel guidesLabel;
    private final JLabel participantsLabel;
    private final JLabel themesLabel;
    private final JLabel templateLabel;
    private final JLabel outputLabel;
    private final JLabel summaryLabel;
    private final JButton matchesButton;
    private final JButton groupsLabel;
    private final JButton logsLabel;

    /**
     * Constructs a {@code MainPanel} with the specified grouping model.
     *
     * <p>This constructor initializes the layout of the panel, creates
     * labels and buttons to display relevant information and actions,
     * and sets the grouping model as an observer to receive updates.</p>
     *
     * @param groupingModel The {@link GroupingModel} used to retrieve and
     *                      display data in the panel.
     */
    public MainPanel(GroupingModel groupingModel) {
        this.groupingModel = groupingModel;

        // Set the layout manager to GridBagLayout for flexible component placement
        setLayout(new GridBagLayout());

        // Create GridBagConstraints for positioning components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 5, 10);

        // Initialize labels to display file paths
        guidesLabel = new JLabel();
        participantsLabel = new JLabel();
        themesLabel = new JLabel();
        templateLabel = new JLabel();
        outputLabel = new JLabel();
        summaryLabel = new JLabel("(Total: 0 guides, 0 guide clusters, 0 participants, 0 groups, 0 themes)");

        // Adjust the font size of the summary label
        Font font = new Font(summaryLabel.getFont().getFontName(), summaryLabel.getFont().getStyle(), 12); // 10 is the new font size
        summaryLabel.setFont(font);

        // Initialize buttons to display tables and logs
        matchesButton = new OpenParticipantsTableButton(groupingModel, "Show matched participants");
        groupsLabel = new OpenGroupsTableButton(groupingModel, "Show groups");
        logsLabel = new OpenLogsButton(groupingModel, "Show logs");

        // Add the components to the panel using the specified layout constraints
        gbc.gridy = 0; // Start at the first column
        gbc.gridx = 0;
        add(new JLabel("Guides:"), gbc); // Add label for guides
        gbc.gridx++;
        gbc.gridwidth = 3;
        add(guidesLabel, gbc); // Add the guides file path label

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Participants:"), gbc); // Add label for participants
        gbc.gridx++;
        gbc.gridwidth = 3;
        add(participantsLabel, gbc); // Add the participants file path label

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Themes:"), gbc); // Add label for themes
        gbc.gridx++;
        gbc.gridwidth = 3;
        add(themesLabel, gbc); // Add the themes file path label

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Email template:"), gbc); // Add label for email template
        gbc.gridx++;
        gbc.gridwidth = 3;
        add(templateLabel, gbc); // Add the email template file path label

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Output folder:"), gbc); // Add label for output folder
        gbc.gridx++;
        gbc.gridwidth = 3;
        add(outputLabel, gbc); // Add the output folder path label

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(-6, 10, 0, 10);
        add(new JLabel("Results:"), gbc);  // Add label for results
        gbc.gridx++;
        add(matchesButton, gbc); // Add button for matched participants
        gbc.gridx++;
        add(groupsLabel, gbc); // Add button for groups
        gbc.gridx++;
        add(logsLabel, gbc); // Add button for logs

        gbc.gridy++; // Move to the next column
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(summaryLabel, gbc);  // Add label for results

//        gbc.gridy++; // Move to the next column
//        gbc.gridx = 0;
//        gbc.insets = new Insets(20, 10, 0, 10);
//        add(new JLabel("Input summary:"), gbc);  // Add label for results
//        gbc.gridx++;
//        gbc.gridwidth = 3;
//        add(new JLabel("0 guide clusters, 0 guides, 0 groups, 0 participants"), gbc);  // Add label for results


        // Add this panel as an observer of the grouping model
        groupingModel.addObserver(this);
    }

    /**
     * Updates the displayed links and button states based on the current
     * data in the grouping model.
     *
     * <p>This method retrieves the file paths from the grouping model,
     * truncates them if they exceed a certain length, and updates the
     * corresponding labels. It also configures the visibility of result
     * buttons based on whether the grouping model is solved.</p>
     */
    private void updateLinks() {
        // Retrieve and truncate file paths from the grouping model
        int maxFilePathLength = 70;
        String guidesText = truncateFilePath(groupingModel.getGuidesFilePath(), maxFilePathLength);
        String participantsText = truncateFilePath(groupingModel.getParticipantsFilePath(), maxFilePathLength);
        String themesText = truncateFilePath(groupingModel.getThemesFilePath(), maxFilePathLength);
        String templateText = null;
        if (groupingModel.getEmailTemplate() != null) templateText = truncateFilePath(groupingModel.getEmailTemplate().getAbsolutePath(), maxFilePathLength);
        String outputText = truncateFilePath(groupingModel.getOutputFolderPath(), maxFilePathLength);

        // Update labels with the truncated file paths
        guidesLabel.setText(guidesText);
        participantsLabel.setText(participantsText);
        themesLabel.setText(themesText);
        templateLabel.setText(templateText);
        outputLabel.setText(outputText);

        // Update button states based on whether the grouping model is solved
        if (groupingModel.isSolved()) {
            matchesButton.setText("<html><a href=''>Participants</a></html>");
            groupsLabel.setText("<html><a href=''>Groups</a></html>");
            logsLabel.setText("<html><a href=''>Logs</a></html>");
        } else {
            matchesButton.setText(null);
            groupsLabel.setText(null);
            logsLabel.setText(null);
        }
    }

    /**
     * Updates the summary information displayed in the panel.
     *
     * <p>This method calculates the total number of guides, guide clusters,
     * participants, groups, and themes from the {@link GroupingModel}.
     * It then updates the {@code summaryLabel} with this information in a formatted string.</p>
     *
     * <p>The number of guides is derived from the sum of all guides in each guide cluster.
     * The number of groups is calculated based on the total participants and the group size.</p>
     */
    private void updateSummary() {
        // Retrieve the number of guides, guide clusters, participants, groups and themes
        int themes = groupingModel.getThemes() != null ? groupingModel.getThemes().length : 0;
        int guideClusters = groupingModel.getGuideClusters() != null ? groupingModel.getGuideClusters().size() : 0;
        int guides = guideClusters != 0 ? groupingModel.getGuideClusters().stream()
                        .filter(cluster -> cluster.getGuides() != null) // Ensure guides list in each cluster is not null
                        .mapToInt(cluster -> cluster.getGuides().size())
                        .sum() : 0;
        int participants = (groupingModel.getParticipants() != null) ? groupingModel.getParticipants().size() : 0;
        int groupSize = groupingModel.getSettings().getGroupSize();
        int groups = (groupSize > 0) ? (participants + groupSize - 1) / groupSize : 0;

        // Update the label
        String text = "(Total: " + guides + " guides, " + guideClusters + " guide clusters, " + participants + " participants, " + groups + " groups, " + themes + " themes)";
        summaryLabel.setText(text);
    }

    /**
     * Truncates a file path to a specified maximum length.
     *
     * <p>This method ensures that the file path is displayed in a user-friendly
     * way by truncating it and adding "..." if it exceeds the maximum length.</p>
     *
     * @param filePath The file path to be truncated.
     * @param maxLength The maximum length of the displayed file path.
     * @return A truncated version of the file path.
     */
    private static String truncateFilePath(String filePath, int maxLength) {
        if (filePath == null || filePath.length() <= maxLength) {
            return filePath;
        }

        // Split the file path into parts and truncate from the beginning if necessary
        String[] parts = filePath.split("/");
        StringBuilder truncatedPath = new StringBuilder();

        for (int i = parts.length - 1; i >= 0; i--) {
            if (truncatedPath.length() + parts[i].length() + 1 > maxLength) {
                break;
            }
            if (truncatedPath.length() > 0) {
                truncatedPath.insert(0, "/");
            }
            truncatedPath.insert(0, parts[i]);
        }

        // Handle cases where the truncation results in a file path of length 0
        if (truncatedPath.length() == 0) {
            return filePath.substring(filePath.length() - maxLength);
        }

        // Add an ellipsis if the path was truncated
        if (truncatedPath.length() < filePath.length()) {
            truncatedPath.insert(0, ".../");
        }

        return truncatedPath.toString();
    }

    /**
     * Updates the panel when changes occur in the observed {@link GroupingModel}.
     *
     * <p>This method is called whenever the grouping model is updated,
     * triggering a refresh of displayed information in the panel.</p>
     *
     * @param o The observable object (grouping model).
     * @param arg An optional argument (not used in this implementation).
     */
    public void update(Observable o, Object arg) {
        updateLinks();
        updateSummary();
        repaint();
    }
}