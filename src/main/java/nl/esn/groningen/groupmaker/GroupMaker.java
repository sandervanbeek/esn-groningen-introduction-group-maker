package nl.esn.groningen.groupmaker;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.view.MainFrame;

/**
 * The {@code GroupMaker} class serves as the entry point for the GroupMaker application.
 * It initializes the application by creating a new instance of the main application frame
 * and setting up the grouping model required for its operation.
 */
public class GroupMaker {

    /**
     * The main method that serves as the entry point for the application.
     *
     * <p>This method initializes the application by creating a new instance of
     * {@link MainFrame}, passing a newly created {@link GroupingModel} to it.
     * This sets up the GUI and the underlying data model needed for the application to function.</p>
     *
     * @param args command-line arguments passed to the application (not used)
     */
    public static void main(String[] args) {
        new MainFrame(new GroupingModel());
    }
}