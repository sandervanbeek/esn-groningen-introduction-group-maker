package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.view.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code OpenSettingsAction} class represents an action that allows users to
 * open a {@link SettingsDialog} for configuring settings related to a
 * {@link GroupingModel}. This action is typically associated with a button in
 * the settings dialog for accessing the settings.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to open
 * the settings dialog when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see SettingsDialog
 */
public class OpenSettingsAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenSettingsAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with the name "Settings",
     * allowing it to be associated with a button or menu item in the user interface.</p>
     *
     * @param groupingModel the {@link GroupingModel} for which the settings will be configured
     */
    public OpenSettingsAction(GroupingModel groupingModel) {
        super("Settings");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of opening the settings dialog.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It creates a new instance of {@link SettingsDialog}, passing the
     * {@link GroupingModel} to allow the user to configure settings.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new SettingsDialog(groupingModel);
    }
}