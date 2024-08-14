package nl.esn.groningen.groupmaker.model;

/**
 * The {@code Settings} class encapsulates the configuration parameters used for
 * creating groups in the GroupMaker application.
 *
 * <p>This class holds settings such as the maximum group size, the maximum number
 * of participants with plant-based diets, the maximum number of participants of
 * the same nationality allowed in a group, and the percentage limit for participants
 * of the same gender within a group.</p>
 */
public class Settings {
    private final int groupSize;
    private final int plantBasedGroupMaximum;
    private final int sameNationalityGroupMaximum;
    private final int sameGenderPercentageLimit;

    /**
     * Default constructor that initializes the settings with standard values.
     *
     * <p>Defaults:
     * <ul>
     *   <li>Group size: 16 participants</li>
     *   <li>Plant-based participants: Maximum of 5 per group</li>
     *   <li>Same nationality participants: Maximum of 4 per group</li>
     *   <li>Same gender percentage limit: 75%</li>
     * </ul>
     * </p>
     */
    public Settings() {
        this(16, 5, 4, 75);
    }

    /**
     * Constructs a {@code Settings} object with the specified values.
     *
     * @param groupSize The maximum number of participants allowed in a group.
     * @param plantBasedMaximum The maximum number of plant-based participants allowed in a group.
     * @param maximumSameNationalityMaximum The maximum number of participants of the same nationality allowed in a group.
     * @param maximumAcceptedGenderRatio The maximum allowed percentage of participants of the same gender in a group.
     */
    public Settings(int groupSize, int plantBasedMaximum, int maximumSameNationalityMaximum, int maximumAcceptedGenderRatio) {
        this.groupSize = groupSize;
        this.plantBasedGroupMaximum = plantBasedMaximum;
        this.sameNationalityGroupMaximum = maximumSameNationalityMaximum;
        this.sameGenderPercentageLimit = maximumAcceptedGenderRatio;
    }

    /**
     * Returns the maximum number of participants allowed in a group.
     *
     * @return The group size limit.
     */
    public int getGroupSize() {
        return groupSize;
    }

    /**
     * Returns the maximum number of plant-based participants allowed in a group.
     *
     * @return The plant-based group maximum.
     */
    public int getPlantBasedGroupMaximum() {
        return plantBasedGroupMaximum;
    }

    /**
     * Returns the maximum number of participants of the same nationality allowed in a group.
     *
     * @return The same nationality group maximum.
     */
    public int getSameNationalityGroupMaximum() {
        return sameNationalityGroupMaximum;
    }

    /**
     * Returns the maximum allowed percentage of participants of the same gender in a group.
     *
     * @return The same gender percentage limit.
     */
    public int getSameGenderPercentageLimit() {
        return sameGenderPercentageLimit;
    }
}