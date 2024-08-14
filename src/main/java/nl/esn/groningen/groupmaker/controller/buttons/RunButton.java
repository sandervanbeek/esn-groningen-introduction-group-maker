package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.RunAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code RunButton} class represents a button that triggers the execution of a run action
 * when clicked. It observes a {@link GroupingModel} and enables or disables itself based on
 * the model's state, specifically the presence of a participants file path.
 *
 * <p>This class extends the {@link Button} class and implements the {@link Observer} interface
 * to monitor changes in the {@link GroupingModel}. The button is enabled only when a valid
 * participants file path is provided in the model.</p>
 *
 * @see Button
 * @see RunAction
 * @see GroupingModel
 * @see Observer
 */
public class RunButton extends Button implements Observer {
    private final GroupingModel groupingModel;

    /**
     * Constructs a {@code RunButton} with the specified grouping model and tooltip.
     *
     * <p>This constructor initializes the button by associating it with the given
     * {@link GroupingModel} and setting the tooltip text. The button starts in a disabled state
     * and is only enabled when the grouping model has a valid participants file path. The button
     * also registers itself as an observer of the grouping model to update its state accordingly.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which manages
     *                      the data for groupings
     * @param tooltip a string representing the tooltip text for this button
     */
    public RunButton(GroupingModel groupingModel, String tooltip) {
        super(new RunAction(groupingModel), tooltip);
        this.groupingModel = groupingModel;
        setEnabled(false);
        groupingModel.addObserver(this);
    }

    /**
     * Updates the enabled state of the button based on the state of the observed {@link GroupingModel}.
     *
     * <p>This method checks if the participants file path in the grouping model is valid. The button
     * is enabled if the participants file path is not null; otherwise, it remains disabled. This method
     * is called automatically when the observed object changes.</p>
     *
     * @param o   the observable object (should be the {@link GroupingModel} in this context)
     * @param arg an optional argument passed to the {@link Observable} (not used here)
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(groupingModel.getParticipantsFilePath() != null);
    }
}