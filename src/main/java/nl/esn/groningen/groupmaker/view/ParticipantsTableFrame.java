package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code ParticipantsTableFrame} class represents a frame that displays
 * a table of participants associated with a given grouping model.
 *
 * <p>This frame contains a scrollable table that lists participants and
 * their details. It is designed to be displayed as a modal dialog, allowing
 * users to view participant information while interacting with the application.</p>
 *
 * @see JFrame
 * @see GroupingModel
 * @see ParticipantsTable
 */
public class ParticipantsTableFrame extends JFrame {

    /**
     * Constructs a {@code ParticipantsTableFrame} with the specified grouping model.
     *
     * <p>This constructor initializes the frame, creates a table to display
     * participants from the provided grouping model, and sets up the layout
     * for the frame. The frame is displayed at a fixed size with a close
     * operation that disposes of the frame when closed.</p>
     *
     * @param groupingModel The {@link GroupingModel} that contains the participants' data to be displayed.
     */
    public ParticipantsTableFrame(GroupingModel groupingModel) {
        super("Participants");

        // Create a table for participants and add it to a scroll pane
        JTable table = new ParticipantsTable(groupingModel);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // Set up frame properties
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }
}