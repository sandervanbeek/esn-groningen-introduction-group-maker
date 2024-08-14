package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.util.DialogHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * The {@code ImportParticipantsAction} class represents an action that allows users to
 * import a file containing participants, which will then be added to a
 * {@link GroupingModel}. This action is typically associated with a button in the user
 * interface to facilitate the selection of a participants file.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to prompt the
 * user for a file selection, updating the grouping model with the selected participants file.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see DialogHandler
 */
public class ImportParticipantsAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code ImportParticipantsAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with a name ("Import participants")
     * and associates it with the provided {@link GroupingModel}. This allows the action to
     * update the model with the participants file when triggered.</p>
     *
     * @param groupingModel the {@link GroupingModel} to which the participants file will
     *                      be added
     */
    public ImportParticipantsAction(GroupingModel groupingModel) {
        super("Import participants");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of importing participants from a selected file.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It prompts the user to select a file and, if a file is selected, updates the
     * {@link GroupingModel} with the selected participants file.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile = showFileDialog();
        if (selectedFile != null) {
            groupingModel.setParticipants(selectedFile);
        }
    }

    /**
     * Prompts the user to select a file using a file dialog.
     *
     * <p>This method utilizes the {@link DialogHandler} to show a file dialog that
     * filters for CSV files, specifically allowing the user to select a file containing
     * participants.</p>
     *
     * @return the selected {@link File}, or null if no file was selected
     */
    private File showFileDialog() {
        return DialogHandler.showOpenCSVFileDialog();
    }
}