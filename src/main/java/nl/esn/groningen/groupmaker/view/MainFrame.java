package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MainFrame} class represents the main application window for the
 * ESN Groningen Introduction Group Maker.
 *
 * <p>This frame serves as the primary user interface container, holding the
 * main button bar and the main panel, which displays various functionalities
 * related to participant grouping.</p>
 *
 * @see JPanel
 * @see MainButtonBar
 * @see MainPanel
 * @see GroupingModel
 */
public class MainFrame extends JFrame {
    private static MainFrame instance;

    /**
     * Constructs a {@code MainFrame} with the specified grouping model.
     *
     * <p>This constructor initializes the main application window, sets the
     * title, adds the main button bar and the main panel, and configures the
     * frame's appearance and behavior.</p>
     *
     * @param groupingModel The {@link GroupingModel} used to provide data
     *                      and functionality to the main panel and buttons.
     */
    public MainFrame(GroupingModel groupingModel) {
        super("ESN Groningen - Introduction Group Maker");
        instance = this;

        // Add the main button bar and main panel to the frame
        add(new MainButtonBar(groupingModel), BorderLayout.PAGE_START);
        JPanel leftAlignedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftAlignedPanel.add(new MainPanel(groupingModel));
        add(leftAlignedPanel, BorderLayout.CENTER);

        // Set the application icon
        Image icon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
        setIconImage(icon);

        // Uncomment to set the dock icon on supported OS
        /*
        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            try {
                taskbar.setIconImage(icon);
            } catch (UnsupportedOperationException | SecurityException e) {
                System.out.println("Unable to set dock icon: " + e.getMessage());
            }
        }
        */

        // Configure the frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Returns the singleton instance of the {@code MainFrame}.
     *
     * <p>This method provides access to the main frame instance, allowing
     * other parts of the application to reference it.</p>
     *
     * @return The singleton instance of {@code MainFrame}.
     */
    public static MainFrame getInstance() {
        return instance;
    }
}