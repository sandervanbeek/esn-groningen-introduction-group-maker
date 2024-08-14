package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.util.DialogHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * The {@code ImportEmailTemplateAction} class represents an action that allows users to
 * import a file containing an email template, which will then be added to a
 * {@link GroupingModel}. This action is typically associated with a button in the user
 * interface to facilitate the selection of a template file.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to prompt the
 * user for a file selection, updating the grouping model with the selected email template
 * file path.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see DialogHandler
 */
public class ImportEmailTemplateAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code ImportEmailTemplateAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with a name ("Import email template")
     * and associates it with the provided {@link GroupingModel}. This allows the action to
     * update the model with the email template file path when triggered.</p>
     *
     * @param groupingModel the {@link GroupingModel} to which the email template file will
     *                      be added
     */
    public ImportEmailTemplateAction(GroupingModel groupingModel) {
        super("Import email template");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of importing an email template file.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It prompts the user to select a file and, if a file is selected, updates the
     * {@link GroupingModel} with the file path of the selected template.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile = showFileDialog();
        if (selectedFile != null) {
            groupingModel.setEmailTemplate(selectedFile);
        }
    }

    /**
     * Prompts the user to select a file using a file dialog.
     *
     * <p>This method utilizes the {@link DialogHandler} to show a file dialog that
     * filters for document files, specifically allowing the user to select a .docx
     * file for the email template.</p>
     *
     * @return the selected {@link File}, or null if no file was selected
     */
    private File showFileDialog() {
        return DialogHandler.showOpenDocxFileDialog();
    }
}