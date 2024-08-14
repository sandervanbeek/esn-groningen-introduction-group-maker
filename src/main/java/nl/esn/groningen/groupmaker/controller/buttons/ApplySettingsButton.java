package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.ApplySettingsAction;
import nl.esn.groningen.groupmaker.view.SettingsDialog;

/**
 * The {@code ApplySettingsButton} class represents a button that applies settings when clicked.
 * This button is associated with a {@link SettingsDialog} and performs an action defined by
 * the {@link ApplySettingsAction}.
 *
 * <p>This class extends the {@link Button} class and provides a specific implementation for
 * applying settings in the application. The button is initialized with a tooltip and is
 * linked to a {@link SettingsDialog} where the settings can be modified.</p>
 *
 * @see Button
 * @see ApplySettingsAction
 * @see SettingsDialog
 */
public class ApplySettingsButton extends Button {

    /**
     * Constructs an {@code ApplySettingsButton} with the specified settings dialog and tooltip.
     *
     * @param settingsDialog the {@link SettingsDialog} associated with this button
     * @param tooltip a string representing the tooltip text for this button
     */
    public ApplySettingsButton(SettingsDialog settingsDialog, String tooltip) {
        super(new ApplySettingsAction(settingsDialog), tooltip);
    }
}