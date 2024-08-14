package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.ApplySettingsAction;
import nl.esn.groningen.groupmaker.controller.actions.CancelSettingsAction;
import nl.esn.groningen.groupmaker.view.SettingsDialog;

/**
 * The {@code CancelSettingsButton} class represents a button that disposes of a settings dialog when clicked.
 * This button is associated with a {@link SettingsDialog} and performs an action defined by
 * the {@link CancelSettingsAction}.
 *
 * <p>This class extends the {@link Button} class and provides a specific implementation for
 * disposing of a settings dialog in the application. The button is initialized with a tooltip and is
 * linked to a {@link SettingsDialog} where the settings can be modified.</p>
 *
 * @see Button
 * @see CancelSettingsAction
 * @see SettingsDialog
 */
public class CancelSettingsButton extends Button {

    /**
     * Constructs an {@code CancelSettingsButton} with the specified settings dialog and tooltip.
     *
     * @param settingsDialog the {@link SettingsDialog} associated with this button
     * @param tooltip a string representing the tooltip text for this button
     */
    public CancelSettingsButton(SettingsDialog settingsDialog, String tooltip) {
        super(new CancelSettingsAction(settingsDialog), tooltip);
    }
}
