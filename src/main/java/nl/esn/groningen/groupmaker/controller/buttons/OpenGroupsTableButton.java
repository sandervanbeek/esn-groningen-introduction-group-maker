package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.OpenGroupsTableAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code OpenGroupsTableButton} class is a button that triggers the display of a groups table
 * when activated. This button is linked to a specific {@link GroupingModel}, which provides the
 * necessary data to generate the table. The action to be performed when the button is clicked
 * is defined by the {@link OpenGroupsTableAction} class.
 *
 * <p>This class extends the {@link LinkButton} to provide a hyperlink-like appearance and behavior.
 * It is designed to be initialized with a tooltip that appears on hover, enhancing user experience.
 * The button's enabled state is controlled based on the status of the associated {@link GroupingModel}.</p>
 *
 * @see LinkButton
 * @see OpenGroupsTableAction
 * @see GroupingModel
 */
public class OpenGroupsTableButton extends LinkButton implements Observer {
    private final GroupingModel groupingModel;

    /**
     * Constructs an {@code OpenGroupsTableButton} with the specified {@link GroupingModel} and tooltip text.
     *
     * <p>Upon instantiation, this button is associated with the provided {@link GroupingModel}, which is
     * responsible for managing group data. The tooltip parameter is used to set the text displayed when
     * the user hovers over the button. The button starts in a disabled state and listens for changes
     * in the grouping model's status, enabling or disabling itself as appropriate.</p>
     *
     * @param groupingModel the {@link GroupingModel} linked to this button, which contains the data for
     *                      groupings and manages access to the groups table
     * @param tooltip a string that defines the tooltip text for this button, providing additional context
     *                to the user about its function
     */
    public OpenGroupsTableButton(GroupingModel groupingModel, String tooltip) {
        super(new OpenGroupsTableAction(groupingModel), tooltip);
        this.groupingModel = groupingModel;
        setEnabled(false);
        groupingModel.addObserver(this);
    }

    /**
     * Updates the button's enabled state based on the current state of the {@link GroupingModel}.
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