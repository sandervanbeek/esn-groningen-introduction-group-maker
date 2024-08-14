package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.controller.buttons.*;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MainButtonBar} class represents a panel containing a collection
 * of action buttons for interacting with the {@link GroupingModel}.
 * It serves as a control bar within the main user interface of the application.
 *
 * <p>This button bar is constructed using a {@link GridBagLayout},
 * allowing for flexible positioning of buttons. Each button corresponds
 * to a specific action, such as importing data or exporting results.</p>
 *
 * @see JPanel
 * @see GroupingModel
 * @see ImportIntroductionGuidesButton
 * @see ImportParticipantsButton
 * @see ImportThemesButton
 * @see ImportEmailTemplateButton
 * @see SetOutputFolderButton
 * @see OpenSettingsButton
 * @see RunButton
 * @see ExportResultsButton
 * @see HelpButton
 */
public class MainButtonBar extends JPanel {

    /**
     * Constructs a {@code MainButtonBar} with the specified grouping model.
     *
     * <p>This constructor initializes the layout of the button bar and
     * adds various action buttons, including options to import guides,
     * participants, themes, and templates, as well as to set the output
     * folder, open settings, run the grouping process, export results,
     * and access help documentation.</p>
     *
     * @param groupingModel the {@link GroupingModel} used for button actions
     */
    public MainButtonBar(GroupingModel groupingModel) {
        // Set the layout manager to GridBagLayout for flexible component placement
        setLayout(new GridBagLayout());

        // Create GridBagConstraints for positioning components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align components to the west
        gbc.fill = GridBagConstraints.BOTH;   // Allow components to expand
        gbc.weightx = 1.0;                    // Allow components to grow horizontally

        // Add buttons to the button bar with specific constraints
        gbc.gridx = 0; // Start at the first column
        gbc.gridy = 0; // First row
        gbc.insets = new Insets(5, 5, 0, -3); // Set margins around the button
        add(new ImportIntroductionGuidesButton(groupingModel, "Import Introduction Guides from a file"), gbc);

        gbc.gridx++; // Move to the next column
        gbc.insets = new Insets(5, -2, 0, -3); // Adjust margins for the next button
        add(new ImportParticipantsButton(groupingModel, "Import participant data from a file"), gbc);

        gbc.gridx++; // Move to the next column
        add(new ImportThemesButton(groupingModel, "Import themes from a file"), gbc);

        gbc.gridx++; // Move to the next column
        add(new ImportEmailTemplateButton(groupingModel, "Import an email template"), gbc);

        gbc.gridx++; // Move to the next column
        add(new SetOutputFolderButton(groupingModel, "Set the folder where output files will be saved"), gbc);

        gbc.gridx++; // Move to the next column
        add(new OpenSettingsButton(groupingModel, "Open application settings"), gbc);

        gbc.gridx++; // Move to the next column
        add(new RunButton(groupingModel, "Run the grouping process"), gbc);

        gbc.gridx++; // Move to the next column
        add(new ExportResultsButton(groupingModel, "Export the results to a file"), gbc);

        gbc.gridx++; // Move to the next column
        gbc.insets = new Insets(5, -2, 0, 5); // Adjust margins for the last button
        add(new HelpButton("Open the help documentation in your web browser"), gbc);
    }
}