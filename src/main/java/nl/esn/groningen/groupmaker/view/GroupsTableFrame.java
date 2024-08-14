package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code GroupsTableFrame} class provides a user interface for displaying a
 * table of groups sourced from a {@link GroupingModel}. This frame serves as a
 * dedicated window that allows users to view, scroll through, and interact with
 * group information in a structured manner.
 *
 * <p>The frame contains a {@link GroupsTable}, which is populated with data
 * from the provided grouping model, allowing users to see details about
 * each group clearly and efficiently.</p>
 *
 * <p>This class extends {@link JFrame}, making it a standard Swing window that
 * can be displayed independently of other components. The frame is designed to
 * be user-friendly and visually accessible, with essential features for
 * managing group information.</p>
 *
 * @see JFrame
 * @see GroupingModel
 * @see GroupsTable
 */
public class GroupsTableFrame extends JFrame {

    /**
     * Constructs a {@code GroupsTableFrame} with the specified grouping model.
     *
     * <p>This constructor initializes the frame with the title "Groups",
     * sets up the layout to include a scrollable table of groups, and
     * configures various frame properties such as size and close behavior.
     * It positions the frame relative to the main application window.</p>
     *
     * @param groupingModel the {@link GroupingModel} containing the groups to be displayed
     *                     in the table. This model provides the necessary data
     *                     for populating the table view.
     */
    public GroupsTableFrame(GroupingModel groupingModel) {
        super("Groups");

        // Create and add groups table
        JTable table = new GroupsTable(groupingModel);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // Set frame properties
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }
}