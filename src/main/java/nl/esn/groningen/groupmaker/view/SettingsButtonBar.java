package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.controller.buttons.ApplySettingsButton;
import nl.esn.groningen.groupmaker.controller.buttons.CancelSettingsButton;
import nl.esn.groningen.groupmaker.controller.buttons.RestoreSettingsButton;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SettingsButtonBar} class represents a panel that contains
 * buttons for applying, restoring, and canceling settings changes
 * within a settings dialog.
 *
 * <p>This panel is typically used in conjunction with a settings dialog
 * to provide the user with options to apply their changes, revert to
 * previously saved settings, or cancel any modifications they made.</p>
 *
 * @see JPanel
 * @see SettingsDialog
 * @see ApplySettingsButton
 * @see RestoreSettingsButton
 * @see CancelSettingsButton
 */
public class SettingsButtonBar extends JPanel {

    /**
     * Constructs a {@code SettingsButtonBar} with the specified settings dialog.
     *
     * <p>This constructor sets up the layout of the button bar and adds
     * buttons for applying settings, restoring default settings, and
     * canceling any changes.</p>
     *
     * @param settingsDialog The settings dialog associated with this button bar.
     */
    public SettingsButtonBar(SettingsDialog settingsDialog) {
        // Set the layout manager for the button bar
        setLayout(new GridBagLayout());

        // Initialize GridBagConstraints for button placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align buttons to the left
        gbc.fill = GridBagConstraints.BOTH;   // Allow buttons to expand
        gbc.weightx = 1.0;                    // Equal weight for button resizing

        // Add the Apply button
        gbc.gridx = 0; // Start at the first column
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, -3); // Set margins
        add(new ApplySettingsButton(settingsDialog, "Apply changes to settings"), gbc);

        // Add the Restore button
        gbc.gridx++; // Move to the next column
        gbc.insets = new Insets(5, -2, 5, -3); // Set margins
        add(new RestoreSettingsButton(settingsDialog, "Restore settings to saved values"), gbc);

        // Add the Cancel button
        gbc.gridx++; // Move to the next column
        gbc.insets = new Insets(5, -2, 5, 5); // Set margins
        add(new CancelSettingsButton(settingsDialog, "Cancel changes and close dialog"), gbc);
    }
}