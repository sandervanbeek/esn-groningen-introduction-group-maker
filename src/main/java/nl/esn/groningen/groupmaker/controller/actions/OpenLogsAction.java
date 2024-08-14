package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.view.LogsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code OpenLogsAction} class represents an action that allows users to
 * open a {@link LogsFrame}, which displays logs related to the
 * {@link GroupingModel}. This action is typically associated with a button in
 * the user interface for viewing logs.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to open
 * the logs frame when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see LogsFrame
 */
public class OpenLogsAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenLogsAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action without a name or icon
     * (using {@code super(null)}), allowing the action to open the logs frame
     * associated with the provided {@link GroupingModel} when triggered.</p>
     *
     * @param groupingModel the {@link GroupingModel} for which the logs will be displayed
     *                      in the {@link LogsFrame}
     */
    public OpenLogsAction(GroupingModel groupingModel) {
        super(null);
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of opening the logs frame.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It creates a new instance of {@link LogsFrame}, passing the
     * {@link GroupingModel} to display the relevant logs.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new LogsFrame(groupingModel);
    }
}