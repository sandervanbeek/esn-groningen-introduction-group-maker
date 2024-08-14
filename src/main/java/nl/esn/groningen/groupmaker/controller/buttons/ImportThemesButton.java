package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.ImportIntroductionGuidesAction;
import nl.esn.groningen.groupmaker.controller.actions.ImportThemesAction;
import nl.esn.groningen.groupmaker.model.GroupingModel;

/**
 * The {@code ImportThemesButton} class represents a button that triggers the import
 * of themes when clicked. This button is associated with a {@link GroupingModel}
 * and performs an action defined by the {@link ImportThemesAction}.
 *
 * <p>This class extends the {@link Button} class and provides a specific implementation for
 * importing themes into the application. The button is initialized with a tooltip
 * and is linked to a {@link GroupingModel} which manages the data related to grouping.</p>
 *
 * @see Button
 * @see ImportThemesAction
 * @see GroupingModel
 */
public class ImportThemesButton extends Button {

    /**
     * Constructs an {@code ImportThemesButton} with the specified grouping model
     * and tooltip.
     *
     * @param groupingModel the {@link GroupingModel} associated with this button, which manages
     *                      the data for groupings
     * @param tooltip a string representing the tooltip text for this button
     */
    public ImportThemesButton(GroupingModel groupingModel, String tooltip) {
        super(new ImportThemesAction(groupingModel), tooltip);
    }
}
