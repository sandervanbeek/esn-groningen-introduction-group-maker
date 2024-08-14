package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.model.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SettingsDialog} class represents a modal dialog that allows
 * users to view and modify the settings of the grouping model.
 *
 * <p>This dialog contains a settings panel for adjusting parameters
 * such as group size and dietary preferences, along with buttons
 * to apply, restore, or cancel settings changes.</p>
 *
 * @see GroupingModel
 * @see Settings
 * @see SettingsPanel
 * @see SettingsButtonBar
 */
public class SettingsDialog extends JDialog {
    private final GroupingModel groupingModel; // Model containing group settings
    private final SettingsPanel settingsPanel; // Panel for settings input

    /**
     * Constructs a {@code SettingsDialog} with the specified grouping model.
     *
     * <p>This constructor initializes the dialog, sets its title, and
     * configures its layout. It creates a settings panel and a button bar
     * for user interaction, and displays the dialog modally.</p>
     *
     * @param groupingModel The grouping model whose settings are to be modified.
     */
    public SettingsDialog(GroupingModel groupingModel) {
        super(null, "Settings", Dialog.ModalityType.APPLICATION_MODAL);

        this.groupingModel = groupingModel;
        this.settingsPanel = new SettingsPanel(groupingModel.getSettings());

        // Add components to the dialog
        add(settingsPanel, BorderLayout.PAGE_START);
        add(new SettingsButtonBar(this));

        // Configure dialog appearance and behavior
        setLocationRelativeTo(MainFrame.getInstance());
        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Applies the settings from the settings panel to the grouping model.
     *
     * <p>This method retrieves the current settings from the settings panel
     * and updates the grouping model with these values.</p>
     */
    public void applySettings() {
        Settings settings = settingsPanel.readSettings();
        groupingModel.setSettings(settings);
    }

    /**
     * Restores the settings displayed in the settings panel
     * from the grouping model.
     *
     * <p>This method updates the settings panel fields with the
     * current settings from the grouping model.</p>
     */
    public void restoreSettings() {
        Settings settings = groupingModel.getSettings();
        settingsPanel.setSettingFields(settings);
    }
}