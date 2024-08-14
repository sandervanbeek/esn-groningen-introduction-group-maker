package nl.esn.groningen.groupmaker.controller.buttons;

import nl.esn.groningen.groupmaker.controller.actions.HelpAction;

/**
 * The {@code HelpButton} class represents a button that, when clicked, triggers an action
 * to open the help documentation in the user's default web browser.
 *
 * <p>This class extends the {@link Button} class, providing a straightforward way to include
 * a help button in the user interface that links directly to the documentation. Unlike other buttons
 * in the application, the {@code HelpButton} does not depend on any external state or model and is
 * always enabled, offering users immediate access to support resources.</p>
 *
 * @see Button
 * @see HelpAction
 */
public class HelpButton extends Button {

    /**
     * Constructs a {@code HelpButton} with the specified tooltip.
     *
     * <p>This constructor initializes the button by associating it with the {@link HelpAction},
     * which handles opening the help link. The button is configured with a tooltip to provide
     * additional information when the user hovers over it, enhancing the user experience.</p>
     *
     * @param tooltip a string representing the tooltip text for this button.
     */
    public HelpButton(String tooltip) {
        super(new HelpAction(), tooltip);
    }
}