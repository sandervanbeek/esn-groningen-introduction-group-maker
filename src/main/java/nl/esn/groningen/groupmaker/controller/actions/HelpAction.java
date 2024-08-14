package nl.esn.groningen.groupmaker.controller.actions;

import nl.esn.groningen.groupmaker.util.DialogHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;

/**
 * The {@code HelpAction} class represents an action that opens the help documentation
 * in the user's default web browser. This action is typically associated with a "Help" button
 * in the user interface, providing users with easy access to the project's documentation.

 * <p>This class extends {@link AbstractAction} and handles the logic for launching the
 * documentation URL when the action is triggered. If the system does not support desktop
 * browsing, or if an error occurs while trying to open the link, a dialog is shown
 * to inform the user of the failure.</p>
 *
 * @see AbstractAction
 * @see DialogHandler
 */
public class HelpAction extends AbstractAction {

    /**
     * Constructs a {@code HelpAction} with the default name "Help".
     *
     * <p>This constructor initializes the action with the name "Help",
     * making it suitable for association with a "Help" button or menu item
     * in the user interface.</p>
     */
    public HelpAction() {
        super("Help");
    }

    /**
     * Performs the action of opening the help documentation URL in the default web browser.
     *
     * <p>This method is called when the action is triggered (e.g., when the Help button
     * is clicked). It attempts to open the specified documentation URL in the user's
     * default web browser. If the operation fails due to unsupported desktop features
     * or other errors, a dialog is shown to notify the user.</p>
     *
     * @param e the {@link ActionEvent} that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI("https://github.com/sandervanbeek/esn-groningen-introduction-group-maker/blob/main/README.md"));
            } catch (Exception ex) {
                DialogHandler.showHelpError();
            }
        } else {
            DialogHandler.showHelpError();
        }
    }
}