package nl.esn.groningen.groupmaker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Group} class represents a collection of participants and guides, organized under
 * a specific theme and group number. It provides methods to access and analyze details about the participants,
 * such as their university affiliation, study duration, dietary preferences, and demographics.
 *
 * <p>This class encapsulates the data related to a group, including its unique identifier (group number),
 * the guides associated with the group, the participants in the group, and the theme under which the group
 * is organized. It offers various utility methods to analyze the participants' attributes and generate
 * statistical insights about the group's composition.</p>
 *
 *  @see GuideCluster
 *  @see Participant
 */
public class Group {
    private final int groupNumber;
    private GuideCluster guideCluster;
    private final List<Participant> participants;
    private String theme;

    /**
     * Constructs a {@code Group} with the specified group number and list of participants.
     * The guides and theme can be set separately using the provided setter methods.
     *
     * @param groupNumber the unique identifier for this group
     * @param participants the list of {@link Participant} objects associated with this group
     */
    public Group(int groupNumber, List<Participant> participants) {
        this.groupNumber = groupNumber;
        this.participants = participants;
    }

    /**
     * Sets the {@link GuideCluster} associated with this group.
     *
     * @param guideCluster the {@link GuideCluster} object representing the guides for this group
     */
    public void setGuideCluster(GuideCluster guideCluster) {
        this.guideCluster = guideCluster;
    }

    /**
     * Sets the theme associated with this group.
     *
     * @param theme the theme to be associated with this group
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Returns the unique identifier for this group.
     *
     * @return the group number
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Returns the {@link GuideCluster} associated with this group.
     *
     * @return the {@link GuideCluster} object or {@code null} if no guides are assigned
     */
    public GuideCluster getGuideCluster() {
        return guideCluster;
    }

    /**
     * Returns the list of participants in this group.
     *
     * @return a list of {@link Participant} objects or an empty list if no participants are assigned
     */
    public List<Participant> getParticipants() {
        return participants != null ? participants : new ArrayList<>();
    }

    /**
     * Returns the theme associated with this group.
     *
     * @return the theme of the group, or an empty string if no theme is set
     */
    public String getTheme() {
        return theme != null ? theme : "";
    }

    /**
     * Determines the type of university that participants belong to.
     *
     * @return the university type of the group, "Mixed" if participants come from different universities,
     *         or {@code null} if there are no participants
     */
    public String getUniversityType() {
        if (participants == null || participants.isEmpty()) return null; // Return null if no participants

        String university = participants.get(0).getUniversity(); // Get the university of the first participant

        // Check if all participants belong to the same university
        for (Participant participant : participants) {
            if (!participant.getUniversity().equals(university)) {
                return "Mixed"; // Return "Mixed" if universities differ
            }
        }

        return university; // Return the university type
    }

    /**
     * Determines the study duration type of the participants in the group.
     *
     * @return the study duration type, "Mixed" if participants have different study durations,
     *         or {@code null} if there are no participants
     */
    public String getStudyDurationType() {
        if (participants == null || participants.isEmpty()) return null; // Return null if no participants

        String studyDuration = participants.get(0).getStudyDuration(); // Get study duration of the first participant

        // Check if all participants have the same study duration
        for (Participant participant : participants) {
            if (!participant.getStudyDuration().equals(studyDuration)) {
                return "Mixed"; // Return "Mixed" if study durations differ
            }
        }

        return studyDuration; // Return study duration type
    }

    /**
     * Determines the alcohol preference type of the participants in the group.
     *
     * @return the alcohol type ("Alcohol Free" or "Alcohol"), "Mixed" if participants have different preferences,
     *         or {@code null} if there are no participants
     */
    public String getAlcoholType() {
        if (participants == null || participants.isEmpty()) return null; // Return null if no participants

        String alcoholType = participants.get(0).getAlcoholFree(); // Get alcohol preference of the first participant

        // Check if all participants have the same alcohol preference
        for (Participant participant : participants) {
            if (!participant.getAlcoholFree().equals(alcoholType)) {
                return "Mixed"; // Return "Mixed" if preferences differ
            }
        }

        return alcoholType; // Return alcohol preference type
    }

    /**
     * Counts the number of participants who have dietary preferences other than "None".
     *
     * @return the number of participants with dietary preferences
     */
    public int getNumberOfPlantBasedEaters() {
        if (participants == null) return 0;

        int number = 0;
        for (Participant participant : participants) {
            if (!participant.getDiet().equals("None")) {
                number++;
            }
        }

        return number;
    }

    /**
     * Calculates the maximum percentage of participants of the same gender in the group.
     *
     * @return the maximum percentage of participants sharing the same gender, or 0 if there are no participants
     */
    public int getMaxSameGenderPercentage() {
        if (participants == null || participants.isEmpty()) return 0;

        int male = 0, female = 0, other = 0;
        for (Participant participant : participants) {
            switch (participant.getGender()) {
                case "Male":
                    male++;
                    break;
                case "Female":
                    female++;
                    break;
                default:
                    other++;
                    break;
            }
        }

        float max = Math.max(Math.max(male, female), other);
        return Math.round(max / (male + female + other) * 100);
    }

    /**
     * Calculates the maximum count of participants sharing the same nationality.
     *
     * @return the highest number of participants from the same nationality, or 0 if there are no participants
     */
    public int getMaxSameNationality() {
        if (participants == null || participants.isEmpty()) return 0;

        Map<String, Integer> nationalityCount = new HashMap<>();

        // Count occurrences of each nationality
        for (Participant participant : participants) {
            String nationality = participant.getNationality();
            nationalityCount.put(nationality, nationalityCount.getOrDefault(nationality, 0) + 1);
        }

        // Find the maximum count of any nationality
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : nationalityCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }

        return maxCount;
    }

    /**
     * Identifies the most common nationality among participants in the group.
     *
     * @return the nationality that appears most frequently among participants,
     *         or {@code null} if there are no participants
     */
    public String getMostCommonNationality() {
        if (participants == null || participants.isEmpty()) return null;

        Map<String, Integer> nationalityCount = new HashMap<>(); // Map to count nationalities

        // Count occurrences of each nationality
        for (Participant participant : participants) {
            String nationality = participant.getNationality();
            nationalityCount.put(nationality, nationalityCount.getOrDefault(nationality, 0) + 1);
        }

        // Find the most common nationality
        String mostCommonNationality = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : nationalityCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonNationality = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostCommonNationality;
    }
}