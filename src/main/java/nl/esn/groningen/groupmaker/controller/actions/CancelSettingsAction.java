package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.view.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code CancelSettingsAction} class represents an action that disposes of a
 * {@link SettingsDialog} when triggered. This action is typically used to cancel
 * changes in the settings dialog without applying them.
 *
 * <p>This class extends {@link AbstractAction} and provides functionality to close
 * the settings dialog when the user decides not to save their changes.</p>
 *
 * @see AbstractAction
 * @see SettingsDialog
 */
public class CancelSettingsAction extends AbstractAction {
    private final SettingsDialog settingsDialog;

    /**
     * Constructs a {@code CancelSettingsAction} with the specified settings dialog.
     *
     * <p>This constructor initializes the action with a name ("Cancel") and associates
     * it with the provided {@link SettingsDialog}. This allows the action to close
     * the dialog when triggered.</p>
     *
     * @param settingsDialog the {@link SettingsDialog} to be disposed of when the action is performed
     */
    public CancelSettingsAction(SettingsDialog settingsDialog) {
        super("Cancel");
        this.settingsDialog = settingsDialog;
    }

    /**
     * Performs the action of disposing of the settings dialog.
     *
     * <p>This method is called when the action is triggered (e.g., when a cancel button is clicked).
     * It closes the associated {@link SettingsDialog} without saving any changes made by the user.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        settingsDialog.dispose();
    }
}