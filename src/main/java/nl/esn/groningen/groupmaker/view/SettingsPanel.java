package nl.esn.groningen.groupmaker.view;

import nl.esn.groningen.groupmaker.model.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SettingsPanel} class represents a panel that allows users
 * to change the settings of the grouping model through a series of
 * configurable parameters displayed as spinners.
 *
 * <p>This panel provides options to set the maximum group size,
 * the maximum number of plant-based eaters, the maximum number of
 * individuals from the same nationality, and the maximum percentage
 * of the same gender within a group.</p>
 *
 * @see JPanel
 * @see Settings
 */
public class SettingsPanel extends JPanel {
    private final JSpinner groupSizeSpinner;
    private final JSpinner dietSpinner;
    private final JSpinner nationalitySpinner;
    private final JSpinner genderProportionSpinner;

    /**
     * Constructs a {@code SettingsPanel} with the specified settings.
     *
     * <p>This constructor initializes the spinners based on the provided
     * settings and sets up the layout of the panel. Each spinner is
     * configured to not be editable via keyboard input and ensures
     * consistent width across all spinners.</p>
     *
     * @param settings The settings object used to initialize the panel's spinners.
     */
    public SettingsPanel(Settings settings) {
        // Create a SpinnerNumberModel for each of the settings
        SpinnerNumberModel model1 = new SpinnerNumberModel(settings.getGroupSize(), 1, 40, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(settings.getPlantBasedGroupMaximum(), 1, 40, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(settings.getSameNationalityGroupMaximum(), 1, 40, 1);
        SpinnerNumberModel model4 = new SpinnerNumberModel(settings.getSameGenderPercentageLimit(), 50, 100, 1);

        // Create a JSpinner for each SpinnerNumberModel
        groupSizeSpinner = new JSpinner(model1);
        dietSpinner = new JSpinner(model2);
        nationalitySpinner = new JSpinner(model3);
        genderProportionSpinner = new JSpinner(model4);

        // Change the JSpinners to not be editable with a keyboard
        ((JSpinner.DefaultEditor) groupSizeSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) dietSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) nationalitySpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) genderProportionSpinner.getEditor()).getTextField().setEditable(false);

        // Set all JSpinners to equal width
        int preferredWidth = genderProportionSpinner.getPreferredSize().width;
        groupSizeSpinner.setPreferredSize(new Dimension(preferredWidth, groupSizeSpinner.getPreferredSize().height));
        dietSpinner.setPreferredSize(new Dimension(preferredWidth, dietSpinner.getPreferredSize().height));
        nationalitySpinner.setPreferredSize(new Dimension(preferredWidth, nationalitySpinner.getPreferredSize().height));

        // Set the layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 0, 10);

        // Add all elements of the panel to the grid
        gbc.gridy = 0; // Start at the first column
        gbc.gridx = 0;
        add(new JLabel("Maximum group size:"), gbc);
        gbc.gridx++;
        add(groupSizeSpinner, gbc);

        gbc.gridy++; // Move to the next row
        gbc.gridx = 0;
        add(new JLabel("Maximum number of plant-based eaters per group:"), gbc);
        gbc.gridx++;
        add(dietSpinner, gbc);

        gbc.gridy++; // Move to the next row
        gbc.gridx = 0;
        add(new JLabel("Maximum number of people of same nationality per group:"), gbc);
        gbc.gridx++;
        add(nationalitySpinner, gbc);

        gbc.gridy++; // Move to the next row
        gbc.gridx = 0;
        add(new JLabel("Maximum percentage of same gender per group:"), gbc);
        gbc.gridx++;
        add(genderProportionSpinner, gbc);
    }

    /**
     * Creates and returns a {@code Settings} object based on the current
     * values of the spinners in this panel.
     *
     * @return The settings object containing the current values of the spinners.
     */
    public Settings readSettings() {
        return new Settings(
                (Integer) groupSizeSpinner.getValue(),
                (Integer) dietSpinner.getValue(),
                (Integer) nationalitySpinner.getValue(),
                (Integer) genderProportionSpinner.getValue()
        );
    }

    /**
     * Sets the values of the spinners based on the provided settings object.
     *
     * @param settings The settings object whose values will be used to update the spinners.
     */
    public void setSettingFields(Settings settings) {
        groupSizeSpinner.setValue(settings.getGroupSize());
        dietSpinner.setValue(settings.getPlantBasedGroupMaximum());
        nationalitySpinner.setValue(settings.getSameNationalityGroupMaximum());
        genderProportionSpinner.setValue(settings.getSameGenderPercentageLimit());
    }
}