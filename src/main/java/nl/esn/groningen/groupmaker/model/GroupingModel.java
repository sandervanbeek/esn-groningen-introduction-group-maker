package nl.esn.groningen.groupmaker.model;

import nl.esn.groningen.groupmaker.util.DialogHandler;
import nl.esn.groningen.groupmaker.util.GenerateOutputDocuments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The {@code GroupingModel} class represents the core data model for managing
 * groups, guides, and participants within the application. It extends {@link Observable}
 * to allow observers to receive updates when the state changes.
 *
 * <p>This class handles the storage of various settings, guide clusters, participant lists,
 * group configurations, and themes. It provides methods to load data from files and
 * perform checks to ensure the validity of the groupings and themes based on the
 * given settings.</p>
 *
 * @see GuideCluster
 * @see Participant
 * @see Group
 * @see Settings
 */
public class GroupingModel extends Observable implements Observer {
    private Settings settings;
    private List<GuideCluster> guideClusters;
    private List<Participant> participants;
    private List<Group> groups;
    private String[] themes;
    private File emailTemplate;
    private String guidesFilePath;
    private String participantsFilePath;
    private String themesFilePath;
    private String outputFolderPath;
    private boolean solved = false;

    /**
     * Constructs a {@code GroupingModel} instance with default settings.
     */
    public GroupingModel() {
        this.settings = new Settings();
    }

    /**
     * Sets the configuration settings for this grouping model.
     *
     * <p>This method resets the state and checks the quantity of guide clusters and themes.</p>
     *
     * @param settings the {@link Settings} to be applied to this model
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
        solved = false;
        checkGuideClustersQuantity();
        checkThemesQuantity();
    }

    /**
     * Loads guides from the specified file and updates the guide clusters.
     *
     * <p>If a guides file is already loaded, the user will be prompted to confirm overwriting.</p>
     *
     * @param file the file containing guide information
     */
    public void setGuides(File file) {
        if (guidesFilePath != null && !DialogHandler.confirmOverwriteWarning()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Guide> guides = new ArrayList<>();
            br.readLine(); // Read header (not used)
            String line = br.readLine();

            // Read all lines and create Guide objects
            while (line != null) {
                Guide guide = new Guide(line);
                guides.add(guide);
                line = br.readLine();
            }

            this.guidesFilePath = file.getAbsolutePath();
            this.guideClusters = separateGuidesIntoClusters(guides);
            checkGuideClustersQuantity();
            checkThemesQuantity();
        } catch (IOException ex) {
            // Reset values on error
            this.guidesFilePath = null;
            this.guideClusters = null;
            DialogHandler.showImportError();
        } finally {
            solved = false;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Separates a list of guides into clusters based on their cluster numbers.
     *
     * @param guides the list of {@link Guide} objects to be clustered
     * @return a list of {@link GuideCluster} containing grouped guides
     */
    public static List<GuideCluster> separateGuidesIntoClusters(List<Guide> guides) {
        Map<Integer, GuideCluster> clusterMap = new HashMap<>(); // Map to hold clusters by number

        // Group guides by cluster number
        for (Guide guide : guides) {
            int clusternumber = guide.getClusterNumber();

            // Check if the cluster number is already in the map
            if (!clusterMap.containsKey(clusternumber)) {
                clusterMap.put(clusternumber, new GuideCluster()); // Create new cluster if not exist
            }

            // Add the guide to the appropriate cluster
            clusterMap.get(clusternumber).addGuide(guide);
        }

        // Return the list of GuideClusters
        return new ArrayList<>(clusterMap.values());
    }

    /**
     * Loads participants from the specified file.
     *
     * <p>If a participants file is already loaded, the user will be prompted to confirm overwriting.</p>
     *
     * @param file the file containing participant information
     */
    public void setParticipants(File file) {
        if (participantsFilePath != null && !DialogHandler.confirmOverwriteWarning()) {
            return; // Exit if user cancels overwrite
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Participant> participants = new ArrayList<>();
            br.readLine(); // Read header (not used)
            String line = br.readLine();

            // Read all lines and create Participant objects
            while (line != null) {
                Participant participant = new Participant(line);
                participants.add(participant);
                line = br.readLine();
            }

            this.participantsFilePath = file.getAbsolutePath();
            this.participants = participants;
            checkGuideClustersQuantity();
            checkThemesQuantity();
        } catch (IOException ex) {
            // Reset values on error
            this.themesFilePath = null;
            this.themes = null;
            DialogHandler.showImportError();
        } finally {
            solved = false;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Sets the participants for this model directly from a provided list.
     *
     * @param participants the list of {@link Participant} to set
     */
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * Loads themes from the specified file.
     *
     * <p>If a themes file is already loaded, the user will be prompted to confirm overwriting.</p>
     *
     * @param file the file containing theme information
     */
    public void setThemes(File file) {
        if (themesFilePath != null && !DialogHandler.confirmOverwriteWarning()) {
            return; // Exit if user cancels overwrite
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] themes = line.split(","); // Split line into themes
            this.themesFilePath = file.getAbsolutePath();
            this.themes = themes;
            checkThemesQuantity();
        } catch (IOException ex) {
            this.themesFilePath = null;
            this.themes = null;
            DialogHandler.showImportError();
        } finally {
            solved = false;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Sets the email template file for this model.
     *
     * <p>If an email template is already set, the user will be prompted to confirm overwriting.</p>
     *
     * @param file the email template file
     */
    public void setEmailTemplate(File file) {
        if (emailTemplate != null && !DialogHandler.confirmOverwriteWarning()) {
            return; // Exit if user cancels overwrite
        }

        this.emailTemplate = file;
        setChanged();
        notifyObservers();
    }

    /**
     * Sets the groups for this model.
     *
     * @param groups the list of {@link Group} to set
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Sets the output folder path for this model.
     *
     * <p>If an output folder path is already set, the user will be prompted to confirm overwriting.</p>
     *
     * @param file the output folder path
     */
    public void setOutputFolderPath(File file) {
        if (outputFolderPath != null && !DialogHandler.confirmOverwriteWarning()) {
            return; // Exit if user cancels overwrite
        }

        this.outputFolderPath = file.getAbsolutePath();
        setChanged();
        notifyObservers();
    }

    /**
     * Sets the solved state of the model.
     *
     * @param solved true if the grouping is solved; false otherwise
     */
    public void setSolved(boolean solved) {
        this.solved = solved;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the current settings of this model.
     *
     * @return the {@link Settings} of this model
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Returns the list of groups created.
     *
     * @return a list of {@link Group} objects
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Returns the list of guide clusters.
     *
     * @return a list of {@link GuideCluster} objects
     */
    public List<GuideCluster> getGuideClusters() {
        return guideClusters;
    }

    /**
     * Returns the list of participants.
     *
     * @return a list of {@link Participant} objects
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * Returns the themes associated with this model.
     *
     * @return an array of theme strings
     */
    public String[] getThemes() {
        return themes;
    }

    /**
     * Returns the file path of the guides file.
     *
     * @return the guides file path as a string
     */
    public String getGuidesFilePath() {
        return guidesFilePath;
    }

    /**
     * Returns the file path of the participants file.
     *
     * @return the participants file path as a string
     */
    public String getParticipantsFilePath() {
        return participantsFilePath;
    }

    /**
     * Returns the file path of the themes file.
     *
     * @return the themes file path as a string
     */
    public String getThemesFilePath() {
        return themesFilePath;
    }

    /**
     * Returns the email template file.
     *
     * @return the email template file
     */
    public File getEmailTemplate() {
        return emailTemplate;
    }

    /**
     * Returns the output folder path.
     *
     * @return the output folder path as a string
     */
    public String getOutputFolderPath() {
        return outputFolderPath;
    }

    /**
     * Checks if the grouping has been solved.
     *
     * @return true if the grouping is solved; false otherwise
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Exports the generated output documents using the {@link GenerateOutputDocuments} utility.
     */
    public void export() {
        GenerateOutputDocuments.generate(this);
    }

    /**
     * Checks the quantity of guide clusters against the required number based on participants.
     *
     * <p>If the number of required groups exceeds the available guide clusters, a warning is shown.</p>
     */
    private void checkGuideClustersQuantity() {
        if (participants != null && guideClusters != null) {
            // Calculate the number of groups needed based on the number of participants and group size
            int numberOfGroups = (int) Math.ceil((double) participants.size() / settings.getGroupSize());
            int numberOfGuideClusters = guideClusters.size(); // Get the current number of guide clusters

            // Check if there are enough guide clusters for the number of groups required
            if (numberOfGroups > numberOfGuideClusters) {
                // Show a warning if not enough guide clusters are available
                DialogHandler.showNotEnoughGuideClustersWarning(numberOfGroups, numberOfGuideClusters);
            } else if (numberOfGuideClusters > numberOfGroups) {
                // Show a warning if there are too many guide clusters compared to the required groups
                DialogHandler.showTooManyGuideClustersWarning(numberOfGroups, numberOfGuideClusters);
            }
        }
    }

    /**
     * Checks the quantity of themes against the required number based on participants.
     *
     * <p>If the number of required groups exceeds the available themes, a warning is shown.</p>
     */
    private void checkThemesQuantity() {
        // Ensure both participants and themes are not null
        if (participants != null && themes != null) {
            // Calculate the number of groups needed based on the number of participants and group size
            int numberOfGroups = (int) Math.ceil((double) participants.size() / settings.getGroupSize());
            int numberOfThemes = themes.length;

            if (numberOfGroups > numberOfThemes) {
                // Show a warning if not enough themes are available
                DialogHandler.showNotEnoughThemesWarning(numberOfGroups, numberOfThemes);
            }
        }
    }

    /**
     * Updates the observable state when changes occur.
     *
     * @param o the observable object that has changed
     * @param arg additional argument (not used)
     */
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}