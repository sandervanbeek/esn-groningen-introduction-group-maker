package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code LogsFrame} class provides a user interface for displaying logs
 * related to the group management process. This frame is designed to present
 * log messages in a readable format, allowing users to review the history
 * of actions taken within the application.
 *
 * <p>The frame features a {@link LogsPane}, which is an {@link JEditorPane}
 * used to render the log content. Users can scroll through the logs to
 * view detailed information about group management activities.</p>
 *
 * <p>This class extends {@link JFrame}, making it a standard Swing window
 * that can be shown independently of other components. It is designed for
 * easy visibility and access to log information.</p>
 *
 * @see JFrame
 * @see GroupingModel
 * @see LogsPane
 */
public class LogsFrame extends JFrame {

    /**
     * Constructs a {@code LogsFrame} with the specified grouping model.
     *
     * <p>This constructor initializes the frame with the title "Logs",
     * sets up a {@link LogsPane} to display log messages, and configures
     * the layout to include a scrollable view of the logs. The frame's
     * close operation and position relative to the main application window
     * are also set.</p>
     *
     * @param groupingModel the {@link GroupingModel} providing context for
     *                      the logs displayed. This model contains relevant
     *                      information that informs the log messages shown
     *                      in the {@link LogsPane}.
     */
    public LogsFrame(GroupingModel groupingModel) {
        super("Logs");

        // Create and add logs pane
        JEditorPane logsField = new LogsPane(groupingModel);
        add(logsField);

        // Create scroll pane for logs
        JScrollPane scrollPane = new JScrollPane(logsField);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(MainFrame.getInstance());
        pack();
        setVisible(true);
    }
}