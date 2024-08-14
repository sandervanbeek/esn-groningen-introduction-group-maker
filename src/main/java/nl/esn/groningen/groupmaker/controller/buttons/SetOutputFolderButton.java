package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.SetOutputFolderAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

/**
 * The {@code SetOutputFolderButton} class represents a button that allows the user to set
 * the output folder for the grouping model when clicked. It is associated with a
 * {@link GroupingModel} and performs an action defined by the {@link SetOutputFolderAction}.
 *
 * <p>This class extends the {@link Button} class and provides a specific implementation
 * for setting the output folder within the application. The button is initialized with a
 * tooltip and is linked to a {@link GroupingModel}, allowing it to update the model's output
 * folder path.</p>
 *
 * @see Button
 * @see SetOutputFolderAction
 * @see GroupingModel
 */
public class SetOutputFolderButton extends Button {

    /**
     * Constructs a {@code SetOutputFolderButton} with the specified grouping model and tooltip.
     *
     * <p>This constructor initializes the button by associating it with the given
     * {@link GroupingModel} and setting the tooltip text. When the button is clicked, it
     * will trigger the action to set the output folder for the grouping model.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which manages
     *                      the data for groupings and holds the output folder path
     * @param tooltip a string representing the tooltip text for this button
     */
    public SetOutputFolderButton(GroupingModel groupingModel, String tooltip) {
        super(new SetOutputFolderAction(groupingModel), tooltip);
    }
}