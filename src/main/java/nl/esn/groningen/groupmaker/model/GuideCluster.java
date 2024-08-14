package nl.esn.groningen.groupmaker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GuideCluster} class represents a collection of {@link Guide} objects.
 * It provides methods to manage a group of guides, allowing for the addition of
 * guides to the cluster and retrieval of the entire guide list.
 *
 * <p>This class facilitates the organization of multiple guides, which is useful
 * for managing group activities or participant assistance during events. Additionally,
 * it includes methods to assess certain attributes of the guides, such as their alcohol preferences.</p>
 *
 * @see Guide
 */
public class GuideCluster {
    private int groupNumber;
    private final List<Guide> guides;

    /**
     * Constructs a {@code GuideCluster} instance.
     *
     * <p>This constructor initializes an empty list of guides. After instantiation,
     * guides can be added to the cluster using the {@link #addGuide(Guide)} method.</p>
     */
    public GuideCluster() {
        this.guides = new ArrayList<>();
    }

    /**
     * Sets the group number associated with this guide cluster.
     *
     * @param groupNumber the unique identifier for the group to which this guide cluster belongs
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Adds a {@link Guide} to the cluster.
     *
     * @param guide the {@code Guide} to be added to the cluster
     */
    public void addGuide(Guide guide) {
        guides.add(guide);
    }

    /**
     * Returns the list of guides in this cluster.
     *
     * @return a list of {@link Guide} objects, or an empty list if no guides have been added
     */
    public List<Guide> getGuides() {
        return guides != null ? guides : new ArrayList<>();
    }

    /**
     * Returns the group number associated with this guide cluster.
     *
     * @return the group number
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Determines the alcohol preference type of the guides in the cluster.
     *
     * @return the alcohol type ("Alcohol Free" or "Alcohol"), "Mixed" if guides have different preferences,
     *         or {@code null} if there are no guides in the cluster
     */
    public String getAlcoholType() {
        if (guides == null || guides.isEmpty()) return null; // Return null if no guides

        String alcoholType = guides.get(0).getAlcoholFree(); // Get alcohol preference of the first guide

        // Check if all guides have the same alcohol preference
        for (Guide guide : guides) {
            if (!guide.getAlcoholFree().equals(alcoholType)) {
                return "Mixed"; // Return "Mixed" if preferences differ
            }
        }

        return alcoholType; // Return alcohol preference type
    }
}