package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.util.DialogHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * The {@code SetOutputFolderAction} class represents an action that allows users to
 * select a folder path where the output of a {@link GroupingModel} will be exported.
 * This action is typically associated with a button in the user interface, enabling
 * users to specify the desired output location.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to set the
 * output folder when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see DialogHandler
 */
public class SetOutputFolderAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs a {@code SetOutputFolderAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with the name "Set output folder",
     * allowing it to be associated with a button in the user interface for setting
     * the output folder.</p>
     *
     * @param groupingModel the {@link GroupingModel} to set the output file path for
     */
    public SetOutputFolderAction(GroupingModel groupingModel) {
        super("Set output folder");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of setting the output folder in the grouping model.
     *
     * <p>This method is called when the action is triggered (e.g., when the set
     * output folder button is clicked). It prompts the user to select a folder
     * and updates the grouping model with the selected path.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile = showFolderDialog();
        if (selectedFile != null) {
            groupingModel.setOutputFolderPath(selectedFile);
        }
    }

    /**
     * Displays a dialog for the user to select an output folder.
     *
     * <p>This method utilizes the {@link DialogHandler} to show a folder selection
     * dialog and returns the selected folder as a {@link File} object.</p>
     *
     * @return the selected folder, or {@code null} if the selection was canceled
     */
    private File showFolderDialog() {
        return DialogHandler.showOpenFolderDialog();
    }
}