package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.util.GroupingAlgorithm;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code RunAction} class represents an action that triggers the creation
 * of groups within a {@link GroupingModel}. This action is typically associated
 * with a button in the user interface, allowing users to initiate the grouping
 * process based on the specified algorithm.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to
 * create groups when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see GroupingAlgorithm
 */
public class RunAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs a {@code RunAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action with the name "Run", allowing it
     * to be associated with a button in the user interface for executing the grouping
     * operation.</p>
     *
     * @param groupingModel the {@link GroupingModel} to create groups for
     */
    public RunAction(GroupingModel groupingModel) {
        super("Run");
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of creating groups in the grouping model.
     *
     * <p>This method is called when the action is triggered (e.g., when the run
     * button is clicked). It utilizes the {@link GroupingAlgorithm} to form
     * groups based on the data in the grouping model.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GroupingAlgorithm.formGroups(groupingModel);
    }
}