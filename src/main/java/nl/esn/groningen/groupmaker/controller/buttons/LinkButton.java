package nl.esn.groningen.groupmaker.controller.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code LinkButton} class is an abstract base class for creating hyperlink-style buttons
 * in the application. It extends {@link JButton} and provides a constructor that associates an
 * {@link AbstractAction} with the button, as well as configures the button to appear like a hyperlink.
 *
 * <p>This class customizes the button's appearance by removing borders, content area, and focus
 * painting, making it resemble a link rather than a standard button. The alignment is centered,
 * and a tooltip can be provided.</p>
 *
 * <p>This class is intended to be extended by specific link button implementations that define
 * particular actions and behaviors.</p>
 *
 * @see JButton
 * @see AbstractAction
 */
public abstract class LinkButton extends JButton {

    /**
     * Constructs a {@code LinkButton} with the specified action and tooltip.
     *
     * <p>This constructor initializes the button by setting the action to be performed when the
     * button is clicked, centers the alignment of the button, and sets the tooltip text. Additionally,
     * it customizes the appearance to make the button look like a hyperlink by removing borders,
     * focus painting, and content area.</p>
     *
     * @param action the {@link AbstractAction} associated with this button, defining its behavior
     * @param tooltip a string representing the tooltip text for this button
     */
    public LinkButton(AbstractAction action, String tooltip) {
        super(action);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
    }
}