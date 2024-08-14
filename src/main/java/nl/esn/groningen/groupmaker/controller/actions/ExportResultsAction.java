package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code ExportResultsAction} class represents an action that exports the results
 * of a solved {@link GroupingModel} when triggered. This action is typically associated
 * with a button in the user interface to allow users to export their grouping results.
 *
 * <p>This class extends {@link AbstractAction} and overrides the
 * {@link #actionPerformed(ActionEvent)} method to specify the behavior that occurs
 * when the action is performed.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 */
public class ExportResultsAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code ExportResultsAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with a name ("Export") and associates
     * it with the provided {@link GroupingModel}. This allows the action to export the
     * results when triggered, provided that the model is solved.</p>
     *
     * @param groupingModel the {@link GroupingModel} whose results will be exported
     *                      when the action is performed
     */
    public ExportResultsAction(GroupingModel groupingModel) {
        super("Export");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of exporting the results from the grouping model.
     *
     * <p>This method is called when the action is triggered (e.g., when an export button
     * is clicked). It checks if the grouping model is solved and, if so, calls the
     * {@link GroupingModel#export()} method to perform the export.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (groupingModel.isSolved()) {
            groupingModel.export();
        }
    }
}