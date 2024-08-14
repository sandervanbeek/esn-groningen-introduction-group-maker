package nl.esn.groningen.groupmaker.model;

/**
 * The {@code Guide} class represents a guide associated with a group of participants.
 * It contains personal information, dietary preferences, university affiliation, and
 * contact details for the guide.
 *
 * <p>The class uses enumerations to categorize dietary types and universities, providing
 * a structured way to handle this information. A guide's data is initialized from a
 * comma-separated string, which allows for flexible data input, such as from a CSV file.</p>
 */
public class Guide {

    /**
     * Enum representing the dietary preference of the guide.
     */
    private enum Diet {
        NONE, PESCATARIAN, VEGETARIAN, VEGAN
    }

    /**
     * Enum representing the university the guide is associated with.
     */
    private enum University {
        UG, HANZE, OTHER
    }

    private int groupNumber;
    private final int clusterNumber;
    private final String phoneNumber;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final University university;
    private final Diet diet;
    private String allergies;
    private final boolean alcoholFree;

    /**
     * Constructs a {@code Guide} by parsing the provided comma-separated string.
     *
     * <p>This constructor initializes the guide's attributes based on the input string,
     * which is expected to contain specific information in a predefined order.</p>
     *
     * @param line a comma-separated string containing the guide's details
     */
    public Guide(String line) {
        // Split the input string into an array of characteristics
        String[] characteristics = line.split(",", -1);

        // Initialize attributes based on the parsed characteristics
        this.clusterNumber = Integer.parseInt(characteristics[0]);
        this.phoneNumber = characteristics[4].replaceAll("^\"|\"$", "");
        this.email = characteristics[5].replaceAll("^\"|\"$", "");
        this.firstName = characteristics[2].replaceAll("^\"|\"$", "");
        this.lastName = characteristics[3].replaceAll("^\"|\"$", "");

        // Determine the university based on the provided information
        this.university = characteristics[7].equals("University of Groningen") ?
                University.UG : (characteristics[7].equals("Hanze University of Applied Sciences") ?
                University.HANZE : University.OTHER);

        // Determine the dietary preference based on the provided information
        this.diet = characteristics[14].equals("Yes, pescetarian") ?
                Diet.PESCATARIAN : (characteristics[14].equals("Yes, vegetarian") ?
                Diet.VEGETARIAN : (characteristics[14].equals("Yes, vegan") ?
                Diet.VEGAN : Diet.NONE));

        // Process the allergies input
        this.allergies = characteristics[15].replaceAll("^\"|\"$", "");
        this.allergies = (allergies.equalsIgnoreCase("no") ||
                allergies.equalsIgnoreCase("none") ||
                allergies.equalsIgnoreCase("no restrictions") ||
                allergies.equalsIgnoreCase("nothing") ||
                allergies.equalsIgnoreCase("nope") ||
                allergies.equalsIgnoreCase("n/a") ||
                allergies.equalsIgnoreCase("na") ||
                allergies.equalsIgnoreCase("non") ||
                allergies.equalsIgnoreCase("nil") ||
                allergies.equals("-")) ? null : this.allergies;

        // Determine if the guide is alcohol-free based on the provided information
        this.alcoholFree = !characteristics[12].isEmpty() && characteristics[12].charAt(0) == 'Y';
    }

    /**
     * Returns the group number to which this guide is assigned.
     *
     * @return the group number
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Returns the cluster number assigned to this guide.
     *
     * @return the cluster number
     */
    public int getClusterNumber() {
        return clusterNumber;
    }

    /**
     * Returns the phone number of this guide.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address of this guide.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the first name of this guide.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of this guide.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the university affiliation of this guide.
     *
     * @return the university name as a string
     */
    public String getUniversity() {
        switch (university) {
            case UG:
                return "UG";
            case HANZE:
                return "Hanze";
            default:
                return "Other";
        }
    }

    /**
     * Returns the dietary preference of this guide.
     *
     * @return the dietary preference as a capitalized string
     */
    public String getDiet() {
        String dietString = diet.toString();
        return dietString.substring(0, 1).toUpperCase() + dietString.substring(1).toLowerCase(); // Capitalize the first letter
    }

    /**
     * Returns the allergies of this guide.
     *
     * @return the allergies, or {@code null} if no allergies are reported
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Indicates whether this guide is alcohol-free.
     *
     * @return "Yes" if the guide is alcohol-free, "No" otherwise
     */
    public String getAlcoholFree() {
        return alcoholFree ? "Yes" : "No";
    }

    /**
     * Sets the group number for this guide.
     *
     * @param groupNumber the group number to assign to this guide
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}