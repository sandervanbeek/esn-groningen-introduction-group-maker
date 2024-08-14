package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.view.ParticipantsTableFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The {@code OpenParticipantsTableAction} class represents an action that allows users to
 * open a {@link ParticipantsTableFrame}, which displays details about the participants
 * in a {@link GroupingModel}. This action is typically associated with a button in
 * the user interface for viewing participant details.
 *
 * <p>This class extends {@link AbstractAction} and implements the logic to open
 * the participants table frame when the action is triggered.</p>
 *
 * @see AbstractAction
 * @see GroupingModel
 * @see ParticipantsTableFrame
 */
public class OpenParticipantsTableAction extends AbstractAction {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenParticipantsTableAction} with the specified grouping model.
     *
     * <p>This constructor initializes the action without a name or icon
     * (using {@code super(null)}), allowing the action to open the participants table
     * frame associated with the provided {@link GroupingModel} when triggered.</p>
     *
     * @param groupingModel the {@link GroupingModel} for which the participants will be displayed
     *                      in the {@link ParticipantsTableFrame}
     */
    public OpenParticipantsTableAction(GroupingModel groupingModel) {
        super(null);
        this.groupingModel = groupingModel;
    }

    /**
     * Performs the action of opening the participants table frame.
     *
     * <p>This method is called when the action is triggered (e.g., when a button is clicked).
     * It creates a new instance of {@link ParticipantsTableFrame}, passing the
     * {@link GroupingModel} to display the relevant participant details.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new ParticipantsTableFrame(groupingModel);
    }
}