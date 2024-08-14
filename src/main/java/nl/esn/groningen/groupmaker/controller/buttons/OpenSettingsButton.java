package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.OpenGroupsTableAction;
import nl.esn.groningen.groupmaker.controller.actions.OpenSettingsAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

/**
 * The {@code OpenSettingsButton} class represents a hyperlink-style button that opens
 * the settings dialog when clicked. It is associated with a {@link GroupingModel} and performs
 * an action defined by the {@link OpenSettingsAction}.
 *
 * <p>This class extends the {@link LinkButton} class, inheriting its appearance and behavior
 * as a hyperlink. The button is initialized with a tooltip and is linked to a
 * {@link GroupingModel}, allowing it to access the necessary data for displaying the groups table.</p>
 *
 * @see LinkButton
 * @see OpenSettingsAction
 * @see GroupingModel
 */
public class OpenSettingsButton extends Button {

    /**
     * Constructs an {@code OpenGroupsTableButton} with the specified grouping model and tooltip.
     *
     * <p>This constructor initializes the button by associating it with the given
     * {@link GroupingModel} and setting the tooltip text. When the button is clicked, it
     * triggers the action to open the settings menu for the specified grouping model.</p>
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which manages
     *                      the data for groupings and provides access to the groups table
     * @param tooltip a string representing the tooltip text for this button
     */
    public OpenSettingsButton(GroupingModel groupingModel, String tooltip) {
        super(new OpenSettingsAction(groupingModel), tooltip);
    }
}
