package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.ExportResultsAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code ExportResultsButton} class represents a button that triggers the export of results
 * when clicked. It observes a {@link GroupingModel} and enables or disables itself based on
 * the model's state, ensuring that results can only be exported when the model is solved
 * and the output folder path is set.
 *
 * <p>This class extends the {@link Button} class and implements the {@link Observer} interface
 * to monitor changes in the {@link GroupingModel}. When the model's state changes, the button
 * is enabled or disabled accordingly.</p>
 *
 * @see Button
 * @see ExportResultsAction
 * @see GroupingModel
 * @see Observer
 */
public class ExportResultsButton extends Button implements Observer {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code ExportResultsButton} with the specified grouping model and tooltip.
     *
     * <p>This constructor initializes the button by associating it with the given
     * {@link GroupingModel} and setting the tooltip text. The button starts in a disabled state
     * and is only enabled when the grouping model is solved and has a valid output folder path.
     * The button also registers itself as an observer of the grouping model to update its state
     * accordingly.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which manages
     *                      the data for groupings
     * @param tooltip a string representing the tooltip text for this button
     */
    public ExportResultsButton(GroupingModel groupingModel, String tooltip) {
        super(new ExportResultsAction(groupingModel), tooltip);
        this.groupingModel = groupingModel;
        setEnabled(false);
        groupingModel.addObserver(this);
    }

    /**
     * Updates the enabled state of the button based on the state of the observed {@link GroupingModel}.
     *
     * <p>The button is enabled if the grouping model is solved and has a valid output folder path;
     * otherwise, it is disabled. This method is called automatically when the observed object is
     * changed.</p>
     *
     * @param o   the observable object (should be the {@link GroupingModel} in this context)
     * @param arg an optional argument passed to the {@link Observable} (not used here)
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(groupingModel.isSolved() && groupingModel.getOutputFolderPath() != null);
    }
}