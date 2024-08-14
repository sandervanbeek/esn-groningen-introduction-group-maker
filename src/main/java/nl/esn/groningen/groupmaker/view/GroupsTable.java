package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.Group;
import nl.esn.groningen.groupmaker.model.GroupingModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The {@code GroupsTable} class represents a JTable component that displays
 * information about groups from a {@link GroupingModel}. It provides a visual
 * representation of the groups, including their associated attributes.
 *
 * <p>This class initializes the table with data from the grouping model,
 * setting up the column headers and populating the rows with group details.</p>
 *
 * @see JTable
 * @see GroupingModel
 * @see Group
 */
public class GroupsTable extends JTable {

    /**
     * Constructs a {@code GroupsTable} instance for the specified grouping model.
     *
     * <p>This constructor creates the table model and sets it as the model for
     * the JTable. It also configures visual aspects of the table, such as header
     * font and grid color.</p>
     *
     * @param groupingModel the {@link GroupingModel} containing the groups to be displayed
     */
    public GroupsTable(GroupingModel groupingModel) {
        // Generate and set the table model
        DefaultTableModel tableModel = generateTableModel(groupingModel);
        setModel(tableModel);

        // Configure table appearance
        getTableHeader().setFont(getFont().deriveFont(Font.BOLD));
        setGridColor(Color.LIGHT_GRAY);
        setShowVerticalLines(false);
        setEnabled(false);
    }

    /**
     * Generates a table model based on the provided grouping model.
     *
     * <p>This method creates a {@link DefaultTableModel} with predefined column
     * names and populates it with data from the groups in the grouping model.</p>
     *
     * @param groupingModel the {@link GroupingModel} containing the groups
     * @return a {@link DefaultTableModel} populated with group data
     */
    private DefaultTableModel generateTableModel(GroupingModel groupingModel) {
        // Set the header row and initialize the table
        String[] columnNames = {"Group", "University", "Study duration", "Alcohol-free", "Theme"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with group data
        for (Group group : groupingModel.getGroups()) {
            Object[] row = {
                    "Group " + group.getGroupNumber(),
                    group.getUniversityType(),
                    group.getStudyDurationType(),
                    group.getAlcoholType(),
                    group.getTheme()
            };
            tableModel.addRow(row);
        }

        // Return the fully populated table model
        return tableModel;
    }
}