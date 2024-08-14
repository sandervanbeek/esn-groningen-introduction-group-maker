package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.view.GroupsTableFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code OpenGroupsTableAction} class represents an action that allows users to
 * open a new window displaying the groups in a {@link GroupingModel}. This action is
 * typically associated with a button in the user interface to facilitate
 * viewing and managing groups.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to open
 * the GroupsTableFrame when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see GroupsTableFrame
 */
public class OpenGroupsTableAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenGroupsTableAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action without a name or icon
     * (super(null)), allowing the action to open the groups table frame
     * associated with the provided {@link GroupingModel} when triggered.</p>
     *
     * @param groupingModel the {@link GroupingModel} whose groups will be displayed
     *                      in the GroupsTableFrame
     */
    public OpenGroupsTableAction(GroupingModel groupingModel) {
        super(null);
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of opening the groups table frame.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It creates a new instance of {@link GroupsTableFrame}, passing the
     * {@link GroupingModel} to display the current groups.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new GroupsTableFrame(groupingModel);
    }
}