package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.GroupingModel;
import nl.esn.groningen.groupmaker.model.Participant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The {@code ParticipantsTable} class represents a JTable that displays
 * participant information from a given grouping model.
 *
 * <p>This table is initialized with data from the {@link GroupingModel}
 * and presents various attributes of participants such as email, phone
 * number, name, gender, and dietary preferences in a structured format.</p>
 *
 * @see JTable
 * @see GroupingModel
 * @see Participant
 */
public class ParticipantsTable extends JTable {

    /**
     * Constructs a {@code ParticipantsTable} with the specified grouping model.
     *
     * <p>This constructor initializes the table with a model that contains
     * participant data, sets the header font to bold, and configures
     * table appearance settings such as grid color and vertical lines.</p>
     *
     * @param groupingModel The {@link GroupingModel} that contains the participants' data to be displayed in the table.
     */
    public ParticipantsTable(GroupingModel groupingModel) {
        // Initialize table model with participant data
        DefaultTableModel tableModel = generateTableModel(groupingModel);
        setModel(tableModel);

        // Configure table appearance
        getTableHeader().setFont(getFont().deriveFont(Font.BOLD));
        setGridColor(Color.LIGHT_GRAY);
        setShowVerticalLines(false);
        setEnabled(false);
    }

    /**
     * Generates a {@code DefaultTableModel} based on the participant data
     * from the specified grouping model.
     *
     * <p>This method creates column names for the table and populates it
     * with rows corresponding to each participant's attributes.</p>
     *
     * @param groupingModel The {@link GroupingModel} used to retrieve participant data.
     * @return A {@code DefaultTableModel} containing participant information.
     */
    private DefaultTableModel generateTableModel(GroupingModel groupingModel) {
        // Set the header row and initialize the table
        String[] columnNames = {
                "Email", "Phone number", "First name", "Last name", "Gender",
                "Nationality", "University", "Study duration", "Diet",
                "Diet (additional)", "Alcohol-free", "Group"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with participant data
        for (Participant participant : groupingModel.getParticipants()) {
            Object[] row = {
                    participant.getEmail(),
                    participant.getPhoneNumber(),
                    participant.getFirstName(),
                    participant.getLastName(),
                    participant.getGender(),
                    participant.getNationality(),
                    participant.getUniversity(),
                    participant.getStudyDuration(),
                    participant.getDiet(),
                    participant.getAllergies(),
                    participant.getAlcoholFree(),
                    participant.getGroupNumber()
            };
            tableModel.addRow(row);
        }

        // Return the fully populated table model
        return tableModel;
    }
}