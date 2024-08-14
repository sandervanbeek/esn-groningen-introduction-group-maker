package nl.esn.groningen.groupmaker.util;

import nl.esn.groningen.groupmaker.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Utility class for handling various dialog operations, such as file selection
 * and displaying warnings or error messages to the user.
 *
 * <p>This class provides static methods to interact with the user through
 * file dialogs and message dialogs. It includes methods for opening CSV and DOCX
 * files, selecting folders, confirming actions, and displaying warnings or error messages.</p>
 */
public class DialogHandler {

    /**
     * Displays a dialog to open a CSV file.
     *
     * @return The selected CSV file as a {@link File}, or null if the dialog was cancelled.
     */
    public static File showOpenCSVFileDialog() {
        // Create a file dialog for loading CSV files, filtered to show only .csv files.
        FileDialog fileDialog = new FileDialog(MainFrame.getInstance(), "Select CSV File", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> name.toLowerCase().endsWith(".csv"));
        fileDialog.setVisible(true);

        // Get the selected directory and file name from the dialog.
        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        // If both directory and file name are selected, return the File object. Otherwise, return null.
        if (directory != null && fileName != null) {
            return new File(directory, fileName);
        } else {
            return null;
        }
    }

    /**
     * Displays a dialog to open a DOCX file.
     *
     * @return The selected DOCX file as a {@link File}, or null if the dialog was cancelled.
     */
    public static File showOpenDocxFileDialog() {
        // Create a file dialog for loading DOCX files, filtered to show only .docx files.
        FileDialog fileDialog = new FileDialog(MainFrame.getInstance(), "Select DOCX File", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> name.toLowerCase().endsWith(".docx"));
        fileDialog.setVisible(true);

        // Get the selected directory and file name from the dialog.
        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        // If both directory and file name are selected, return the File object. Otherwise, return null.
        if (directory != null && fileName != null) {
            return new File(directory, fileName);
        } else {
            return null;
        }
    }

    /**
     * Displays a dialog to select a folder.
     *
     * @return The selected folder as a {@link File}, or null if the dialog was cancelled.
     */
    public static File showOpenFolderDialog() {
        // Create a file chooser dialog configured to select directories only.
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false); // Disable the "All Files" option.

        // Show the dialog and check if the user approved the selection.
        int returnValue = chooser.showOpenDialog(MainFrame.getInstance());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile(); // Return the selected folder.
        } else {
            return null; // Return null if the user cancelled the dialog.
        }
    }

    /**
     * Displays a confirmation dialog for overwriting a file.
     *
     * @return true if the user confirms overwriting; false otherwise.
     */
    public static boolean confirmOverwriteWarning() {
        int confirmation = JOptionPane.showConfirmDialog(
                MainFrame.getInstance(),
                "This file has already been set. Do you want to overwrite the existing file?",
                "Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Return true if the user selected OK, false otherwise.
        return confirmation == JOptionPane.OK_OPTION;
    }

    /**
     * Displays a warning message indicating that there are not enough themes for the groups.
     *
     * @param numberOfGroups The number of groups.
     * @param numberOfThemes The number of themes available.
     */
    public static void showNotEnoughThemesWarning(int numberOfGroups, int numberOfThemes) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "The number of groups (" + numberOfGroups + ") exceeds the number of themes (" + numberOfThemes + ").\nSome groups will not be assigned a theme.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Displays a warning message indicating that there are not enough guide clusters for the groups.
     *
     * @param numberOfGroups The number of groups.
     * @param numberOfGuideClusters The number of guide clusters available.
     */
    public static void showNotEnoughGuideClustersWarning(int numberOfGroups, int numberOfGuideClusters) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "The number of Introduction Guides clusters (" + numberOfGroups + ") is not sufficient for the number of groups (" + numberOfGuideClusters + ").\nSome groups will not be assigned a guide.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Displays a warning message indicating that there are too many guide clusters for the groups.
     *
     * @param numberOfGroups The number of groups.
     * @param numberOfGuideClusters The number of guide clusters available.
     */
    public static void showTooManyGuideClustersWarning(int numberOfGroups, int numberOfGuideClusters) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "The number of Introduction Guides clusters (" + numberOfGroups + ") exceeds the number of groups (" + numberOfGuideClusters + ").\nSome guides will not be assigned a group.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Displays an error message indicating that a file could not be imported.
     */
    public static void showImportError() {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "The file could not be imported.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Displays an error message indicating that an error occurred during export.
     */
    public static void showExportError() {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "An error occurred while exporting the files.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Displays an error message indicating that an error occurred while attempting
     * to open the help documentation in the user's web browser.
     */
    public static void showHelpError() {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "An error occurred while trying to open the documentation in your web browser.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}