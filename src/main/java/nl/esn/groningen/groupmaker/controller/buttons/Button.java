package nl.esn.groningen.groupmaker.controller.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code Button} class is an abstract base class for creating buttons in the application.
 * It extends {@link JButton} and provides a constructor that associates an {@link AbstractAction}
 * with the button, as well as sets a tooltip text and centers the button's alignment.
 *
 * <p>This class is intended to be extended by specific button implementations that define
 * particular actions and behaviors.</p>
 *
 * @see JButton
 * @see AbstractAction
 */
public abstract class Button extends JButton {

    /**
     * Constructs a {@code Button} with the specified action and tooltip.
     *
     * <p>This constructor initializes the button by setting the action to be performed when the
     * button is clicked, centers the alignment of the button, and sets the tooltip text.</p>
     *
     * @param action the {@link AbstractAction} associated with this button, defining its behavior
     * @param tooltip a string representing the tooltip text for this button
     */
    public Button(AbstractAction action, String tooltip) {
        super(action);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
    }
}