package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.OpenParticipantsTableAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code OpenParticipantsTableButton} class represents a hyperlink-style button that opens
 * a participants table when activated. This button is linked to a specific {@link GroupingModel}
 * and performs an action defined by the {@link OpenParticipantsTableAction} when clicked.
 *
 * <p>This class extends the {@link LinkButton}, inheriting its visual style and interactive behavior.
 * It is initialized with a tooltip to provide users with additional context, and it is associated
 * with a {@link GroupingModel}, which allows access to the necessary data for displaying the
 * participants table.</p>
 *
 * @see LinkButton
 * @see OpenParticipantsTableAction
 * @see GroupingModel
 */
public class OpenParticipantsTableButton extends LinkButton implements Observer {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenParticipantsTableButton} with the specified {@link GroupingModel} and tooltip text.
     *
     * <p>This constructor initializes the button by linking it with the provided {@link GroupingModel},
     * which manages the data for participants. The tooltip parameter sets the text displayed when the
     * user hovers over the button, providing guidance on its function. The button starts in a disabled
     * state and listens for changes in the grouping model's status to update its enabled state accordingly.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which contains
     *                      the data for groupings and manages access to the participants table
     * @param tooltip a string representing the tooltip text for this button, helping users understand
     *                its purpose
     */
    public OpenParticipantsTableButton(GroupingModel groupingModel, String tooltip) {
        super(new OpenParticipantsTableAction(groupingModel), tooltip);
        this.groupingModel = groupingModel;
        setEnabled(false);
        groupingModel.addObserver(this);
    }

    /**
     * Updates the button's enabled state based on the current status of the {@link GroupingModel}.
     *
     * <p>This method is called whenever the observed {@link GroupingModel} changes. It checks whether
     * the grouping model is solved and enables or disables the button accordingly.</p>
     *
     * @param o the observable object that triggered the update (expected to be the grouping model)
     * @param arg an optional argument passed by the observable (not used in this implementation)
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(groupingModel.isSolved());
    }
}