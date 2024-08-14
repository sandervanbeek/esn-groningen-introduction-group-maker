package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.view.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code ApplySettingsAction} class represents an action that applies the settings
 * defined in a {@link SettingsDialog} when triggered. This action is typically associated
 * with a button in the user interface to execute the settings application logic.
 *
 * <p>This class extends {@link AbstractAction} and overrides the {@link #actionPerformed(ActionEvent)}
 * method to specify the behavior that occurs when the action is performed. Specifically, it applies
 * the settings from the dialog and then closes the dialog.</p>
 *
 * @see AbstractAction
 * @see SettingsDialog
 */
public class ApplySettingsAction extends AbstractAction {
    private final SettingsDialog settingsDialog;

    /**
     * Constructs an {@code ApplySettingsAction} with the specified settings dialog.
     *
     * <p>This constructor initializes the action with a name ("Apply") and associates it with
     * the provided {@link SettingsDialog}. This allows the action to perform operations related
     * to the settings dialog when triggered.</p>
     *
     * @param settingsDialog the {@link SettingsDialog} associated with this action, from which
     *                       the settings will be applied
     */
    public ApplySettingsAction(SettingsDialog settingsDialog) {
        super("Apply");
        this.settingsDialog = settingsDialog;
    }

    /**
     * Performs the action of applying settings from the settings dialog.
     *
     * <p>This method is called when the action is triggered. It applies the settings
     * defined in the associated {@link SettingsDialog} and then disposes of the dialog.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        settingsDialog.applySettings();
        settingsDialog.dispose();
    }
}