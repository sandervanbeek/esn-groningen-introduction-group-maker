package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.OpenLogsAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code OpenLogsButton} class represents a hyperlink-style button that opens
 * a logs frame when activated. It is linked to a specific {@link GroupingModel} and
 * triggers an action defined by the {@link OpenLogsAction} when clicked.
 *
 * <p>This class extends the {@link LinkButton}, inheriting its visual style and
 * interaction capabilities. The button is initialized with a tooltip for enhanced
 * user guidance and is bound to a {@link GroupingModel}, allowing it to manage
 * access to relevant log data.</p>
 *
 * @see LinkButton
 * @see OpenLogsAction
 * @see GroupingModel
 */
public class OpenLogsButton extends LinkButton implements Observer {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenLogsButton} with the specified {@link GroupingModel} and tooltip text.
     *
     * <p>This constructor initializes the button by linking it with the provided {@link GroupingModel},
     * which manages the log data. The tooltip parameter sets the text displayed on hover, providing
     * additional context about the button's function. Initially, the button is disabled, and it will
     * listen for changes in the grouping model to update its enabled state accordingly.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which contains
     *                      the data for groupings and manages access to the logs
     * @param tooltip a string representing the tooltip text for this button, guiding the user
     *                on its purpose
     */
    public OpenLogsButton(GroupingModel groupingModel, String tooltip) {
        super(new OpenLogsAction(groupingModel), tooltip);
        this.groupingModel = groupingModel;
        setEnabled(false);
        groupingModel.addObserver(this);
    }

    /**
     * Updates the button's enabled state based on the current status of the {@link GroupingModel}.
     *
     * <p>This method is invoked when the observed {@link GroupingModel} changes. It checks whether
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