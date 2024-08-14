package nl.esn.groningen.groupmaker.util;

import nl.esn.groningen.groupmaker.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * The {@code GroupingAlgorithm} class provides methods to form groups of participants
 * and assign them a guide cluster and theme if possible. It leverages
 * multithreading to optimize participant assignment, aiming to create groups that
 * are diverse yet cohesive.
 *
 * <p>This class is designed to work with a {@link GroupingModel} to automatically
 * generate groups of participants and assign guide clusters and themes. The
 * core algorithm attempts to balance group sizes and match participants with
 * similar attributes where beneficial, while also ensuring diversity.</p>
 *
 * @see Group
 * @see GuideCluster
 * @see GroupingModel
 */
public class GroupingAlgorithm {

    /**
     * This is the main method responsible for forming groups of participants.
     *
     * The method iterates through all participants and divides them into groups
     * according to the specified group size. It handles edge cases where the total
     * number of participants does not perfectly divide by the group size by managing
     * leftover participants.
     *
     * Each group is assigned a unique number, and guide clusters and themes are
     * assigned where applicable. After processing, the method marks the grouping
     * model as "solved" and assigns the generated groups to it.
     *
     * @param groupingModel The {@link GroupingModel} containing participants, guide clusters, themes, and settings.
     */
    public static void formGroups(GroupingModel groupingModel) {
        List<Group> groups = new ArrayList<>();
        List<GuideCluster> guideClusters = groupingModel.getGuideClusters();
        List<Participant> participants = groupingModel.getParticipants();
        String[] themes = groupingModel.getThemes();

        int groupSize = groupingModel.getSettings().getGroupSize();
        int leftover = groupSize - participants.size() % groupSize;

        int groupNumber = 0;
        int assignedParticipants = 0;

        // Loop until all participants are assigned to a group.
        while (assignedParticipants < participants.size()) {
            groupNumber++;

            int effectiveGroupSize = groupSize;
            if (leftover > 0) {
                effectiveGroupSize--;  // Adjust group size for leftover participants
                leftover--;
            }

            effectiveGroupSize = Math.min(effectiveGroupSize, groupSize);

            // Assign participants to the current group
            List<Participant> groupMembers = assignParticipantsToGroup(groupNumber, effectiveGroupSize, participants);
            Group group = new Group(groupNumber, groupMembers);

            // Assign a guide cluster to the group if available
            if (guideClusters != null) {
                GuideCluster guideCluster = assignGuideClusterToGroup(group, guideClusters);
                group.setGuideCluster(guideCluster);
            }

            // Assign a theme to the group if available
            if (themes != null && themes.length >= groupNumber) {
                group.setTheme(themes[groupNumber - 1]);
            }

            groups.add(group);
            assignedParticipants += effectiveGroupSize;
        }

        // Mark the grouping model as solved and assign the generated groups
        groupingModel.setSolved(true);
        groupingModel.setGroups(groups);
    }

    /**
     * This method is responsible for assigning participants to a group.
     *
     * The method uses a ForkJoinPool to parallelize the process of finding the best
     * participant to add to a group. The selection is based on a balance between
     * similarity and dissimilarity scores, ensuring that groups are both cohesive
     * (shared attributes) and diverse (differences in attributes).
     *
     * @param groupNumber The unique identifier for the group being formed.
     * @param groupSize The desired number of participants in the group.
     * @param participants The list of available participants to choose from.
     * @return A list of participants assigned to the group.
     */
    private static List<Participant> assignParticipantsToGroup(int groupNumber, int groupSize, List<Participant> participants) {
        List<Participant> groupMembers = new ArrayList<>();

        ForkJoinPool pool = new ForkJoinPool();
        while (groupMembers.size() < groupSize && !participants.isEmpty()) {
            BestParticipantTask task = new BestParticipantTask(participants, groupMembers);
            Participant bestParticipant = pool.invoke(task);

            // If a suitable participant is found, add them to the group
            if (bestParticipant != null) {
                groupMembers.add(bestParticipant);
                bestParticipant.setGroupNumber(groupNumber);
            }
        }

        pool.shutdown();
        return groupMembers;
    }

    /**
     * This method assigns a guide cluster to a group, attempting to match
     * the group's alcohol preferences with the guide cluster's.
     *
     * The method first looks for an exact match in alcohol preferences, then
     * checks for a mixed preference cluster, and finally assigns any available
     * cluster if no specific match is found.
     *
     * @param group The group to which a guide cluster will be assigned.
     * @param guideClusters The list of available guide clusters.
     * @return The guide cluster assigned to the group, or null if none is available.
     */
    private static GuideCluster assignGuideClusterToGroup(Group group, List<GuideCluster> guideClusters) {
        String groupAlcoholType = group.getAlcoholType();

        GuideCluster chosenCluster = null;
        if (!groupAlcoholType.equals("Mixed")) {
            // Search for a guide cluster with matching alcohol preferences
            for (GuideCluster cluster : guideClusters) {
                if (cluster.getGroupNumber() == 0 && cluster.getAlcoholType().equals(groupAlcoholType)) {
                    chosenCluster = cluster;
                    break;
                }
            }
        }

        // If no exact match is found, look for a mixed preference cluster
        if (chosenCluster == null) {
            for (GuideCluster cluster : guideClusters) {
                if (cluster.getGroupNumber() == 0 && cluster.getAlcoholType().equals("Mixed")) {
                    chosenCluster = cluster;
                    break;
                }
            }
        }

        // Assign any available guide cluster if no specific match is found
        if (chosenCluster == null) {
            for (GuideCluster cluster : guideClusters) {
                if (cluster.getGroupNumber() == 0) {
                    chosenCluster = cluster;
                    break;
                }
            }
        }

        // Set the group number for the chosen guide cluster
        if (chosenCluster != null) {
            chosenCluster.setGroupNumber(group.getGroupNumber());
        }
        return chosenCluster;
    }

    /**
     * This inner class is a recursive task used to determine the best participant
     * to add to a group based on similarity and dissimilarity scores.
     *
     * The task is executed in parallel using the ForkJoinPool, and it compares
     * each available participant to the current group members to find the most
     * appropriate fit. The participant with the highest compatibility score is selected.
     */
    private static class BestParticipantTask extends RecursiveTask<Participant> {
        private final List<Participant> participants;
        private final List<Participant> group;

        /**
         * Constructor for the BestParticipantTask.
         *
         * It takes the list of available participants and the current group members,
         * which are used to evaluate and select the most suitable participant to add
         * to the group.
         *
         * @param participants The list of participants from which to choose.
         * @param group The current group to which a participant will be added.
         */
        public BestParticipantTask(List<Participant> participants, List<Participant> group) {
            this.participants = participants;
            this.group = group;
        }

        /**
         * The compute method performs the actual comparison and selection of the
         * best participant to add to the group.
         *
         * It iterates through all available participants, calculating a compatibility score
         * for each based on their compatibility with the current group members. The
         * participant with the highest score is selected.
         *
         * @return The participant who best fits the group, or {@code null} if no suitable participant is found.
         */
        @Override
        protected Participant compute() {
            double bestCompatibility = Double.MIN_VALUE;
            Participant bestParticipant = null;

            // Iterate through all participants to find the best fit for the group
            for (Participant p : participants) {
                if (p.getGroupNumber() == 0) {  // Ensure the participant is not already assigned to a group
                    double compatibility = calculateCompatibility(group, p);
                    if (compatibility > bestCompatibility) {
                        bestCompatibility = compatibility;
                        bestParticipant = p;
                    }
                }
            }

            return bestParticipant;
        }
    }

    /**
     * This method calculates a compatibility score to evaluate a participant's
     * compatibility with a group.
     *
     * The compatibility score is a combination of similarity (shared attributes) and
     * dissimilarity (differences) scores, with a small random factor (stochasticity)
     * added to introduce variability in the selection process. The method aims to
     * create balanced groups by considering both cohesion and diversity.
     *
     * @param group The current group of participants.
     * @param participant The participant being evaluated.
     * @return The calculated compatibility score.
     */
    private static double calculateCompatibility(List<Participant> group, Participant participant) {
        double similarity = 0;
        double dissimilarity = 0;

        Random random = new Random();
        int stochasticity = random.nextInt(3);  // Introduce a small random factor

        // Calculate similarity and dissimilarity scores with each group member
        for (Participant p : group) {
            similarity += calculateSimilarity(p, participant);
            dissimilarity += calculateDissimilarity(p, participant);
        }

        return similarity + dissimilarity + stochasticity;
    }

    /**
     * This method calculates the similarity score between two participants based on
     * shared attributes.
     *
     * The method compares the university, study duration, and alcohol preferences
     * of the two participants, assigning higher scores for greater similarity. These
     * attributes are considered important for group cohesion.
     *
     * @param p1 The first participant.
     * @param p2 The second participant.
     * @return A double representing the similarity score between the two participants.
     */
    private static double calculateSimilarity(Participant p1, Participant p2) {
        // Constant multiplier used to give more weight to significant similarities
        final int M = 100;

        // Initialize the similarity score
        double score = 0;

        // Compare the universities of both participants
        String uni1 = p1.getUniversity();
        String uni2 = p2.getUniversity();
        if (uni1.equals(uni2)) {
            // If the universities match, assign a high similarity score
            score += 3 * M;
        } else if (uni1.equals("Other") || uni2.equals("Other")) {
            // If one of the participants' university is marked as "Other", assign a lower similarity score
            score += 2;
        }

        // Compare the alcohol preferences of both participants.
        if (p1.getAlcoholFree().equals(p2.getAlcoholFree())) {
            // If the alcohol preferences match, assign a significant similarity score
            score += 2 * M;
        }

        // Compare the study duration of both participants
        String dur1 = p1.getStudyDuration();
        String dur2 = p2.getStudyDuration();
        if (dur1.equals(dur2)) {
            // If the study durations match, assign a similarity score
            score += M;
        } else if (dur1.equals("Other") || dur2.equals("Other")) {
            // If one of the study durations is marked as "Other", assign a minimal similarity score
            score += 1;
        }

        // Return the total similarity score
        return score;
    }

    /**
     * This method calculates the dissimilarity score between two participants based
     * on differing attributes.
     *
     * The method compares the gender, nationality, and dietary preferences of the
     * two participants, assigning higher scores for greater dissimilarity. These
     * attributes are considered important for ensuring diversity within the group.
     *
     * @param p1 The first participant.
     * @param p2 The second participant.
     * @return A double representing the dissimilarity score between the two participants.
     */
    private static double calculateDissimilarity(Participant p1, Participant p2) {
        // Initialize the dissimilarity score
        double score = 0;

        // Compare the nationalities of both participants
        if (p1.getNationality().equals(p2.getNationality()) && p1.getNationality() == null && p2.getNationality() == null) {
            // If both participants have different nationalities (or either is null), increment the score slightly
            score++;
        }

        // Compare the dietary preferences of both participants
        String diet1 = p1.getDiet();
        String diet2 = p2.getDiet();
        if (!diet1.equals("None") && !diet2.equals("None")) {
            // If neither participant's diet is "None", decrement the score significantly, promoting diversity
            score -= 3;
        } else if (diet1.equals("None") && diet2.equals("None")) {
            // If both participants have "None" as their diet, decrement the score minimally
            score -= 1;
        }

        // Return the total dissimilarity score
        return score;
    }
}