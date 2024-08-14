package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.view.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code RestoreSettingsAction} class represents an action that allows users to
 * restore the settings of a {@link SettingsDialog}. This action is typically
 * associated with a button in the settings dialog for reverting any changes made
 * to the settings.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to restore
 * the settings when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see SettingsDialog
 */
public class RestoreSettingsAction extends AbstractAction {
    private final SettingsDialog settingsDialog;

    /**
     * Constructs a {@code RestoreSettingsAction} with the specified settings dialog.
     *
     * <p>This constructor initializes the action with the name "Restore", allowing it
     * to be associated with a button in the settings dialog.</p>
     *
     * @param settingsDialog the {@link SettingsDialog} whose settings will be restored
     */
    public RestoreSettingsAction(SettingsDialog settingsDialog) {
        super("Restore");
        this.settingsDialog = settingsDialog;
    }

    /**
     * Performs the action of restoring the settings in the settings dialog.
     *
     * <p>This method is called when the action is triggered (e.g., when the restore
     * button is clicked). It invokes the {@link SettingsDialog#restoreSettings()}
     * method to revert any changes made to the settings.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        settingsDialog.restoreSettings();
    }
}