package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.ApplySettingsAction;
import nl.esn.groningen.groupmaker.controller.actions.RestoreSettingsAction;
import nl.esn.groningen.groupmaker.view.SettingsDialog;

/**
 * The {@code RestoreSettingsButton} class represents a button that restores settings when clicked.
 * This button is associated with a {@link SettingsDialog} and performs an action defined by
 * the {@link RestoreSettingsAction}.
 *
 * <p>This class extends the {@link Button} class and provides a specific implementation for
 * restoring settings in the application. The button is initialized with a tooltip and is
 * linked to a {@link SettingsDialog} where the settings can be modified.</p>
 *
 * @see Button
 * @see RestoreSettingsAction
 * @see SettingsDialog
 */
public class RestoreSettingsButton extends Button {

    /**
     * Constructs an {@code RestoreSettingsButton} with the specified settings dialog and tooltip.
     *
     * @param settingsDialog the {@link SettingsDialog} associated with this button
     * @param tooltip a string representing the tooltip text for this button
     */
    public RestoreSettingsButton(SettingsDialog settingsDialog, String tooltip) {
        super(new RestoreSettingsAction(settingsDialog), tooltip);
    }
}
