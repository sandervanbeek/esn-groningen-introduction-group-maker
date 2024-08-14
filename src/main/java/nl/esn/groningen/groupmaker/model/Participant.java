package nl.esn.groningen.groupmaker.model;

/**
 * The {@code Participant} class represents a participant in the GroupMaker application.
 *
 * <p>This class stores various attributes related to a participant, such as personal
 * information, preferences, and grouping details. The participant data is initialized
 * by parsing a line of comma-separated values (CSV).</p>
 */
public class Participant {

    /**
     * Enum representing the gender of the participant.
     */
    private enum Gender {
        MALE, FEMALE, OTHER
    }

    /**
     * Enum representing the dietary preference of the participant.
     */
    private enum Diet {
        NONE, PESCATARIAN, VEGETARIAN, VEGAN
    }

    /**
     * Enum representing the university the participant is associated with.
     */
    private enum University {
        UG, HANZE, OTHER
    }

    /**
     * Enum representing the duration of the participant's study.
     */
    private enum StudyDuration {
        PHD, FULL_MASTER, EXCHANGE_MA, FULL_BACHELOR, EXCHANGE_1, EXCHANGE_2, OTHER
    }

    // Participant attributes
    private final String phoneNumber;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String nationality;
    private final String birthDate;
    private final University university;
    private final StudyDuration studyDuration;
    private final Diet diet;
    private String allergies;
    private final boolean alcoholFree;
    private final boolean requestsGuide;
    private final boolean canGuide;
    private int groupNumber;

    /**
     * Constructs a {@code Participant} object by parsing a line of CSV data.
     *
     * <p>This constructor extracts participant information such as personal details,
     * preferences, and grouping capabilities from the given CSV line. Various fields
     * are initialized based on specific keywords or values found in the line.</p>
     *
     * @param line The line of CSV data representing a participant.
     */
    public Participant(String line) {
        String[] characteristics = line.split(",", -1);

        this.phoneNumber = characteristics[7].replaceAll("^\"|\"$", "");
        this.email = characteristics[1].replaceAll("^\"|\"$", "");
        this.firstName = characteristics[2].replaceAll("^\"|\"$", "");
        this.lastName = characteristics[3].replaceAll("^\"|\"$", "");
        this.gender = characteristics[4].equals("male") ? Gender.MALE :
                (characteristics[4].equals("female") ? Gender.FEMALE : Gender.OTHER);
        this.nationality = characteristics[6].replaceAll("^\"|\"$", "");
        this.birthDate = characteristics[5];
        this.university = characteristics[8].equals("university_of_groningen") ? University.UG :
                (characteristics[8].equals("hanze_university") ? University.HANZE : University.OTHER);
        this.studyDuration = characteristics[10].equals("phd") ? StudyDuration.PHD :
                (characteristics[10].equals("full_master") ? StudyDuration.FULL_MASTER :
                        (characteristics[10].equals("exchange_ma") ? StudyDuration.EXCHANGE_MA :
                                (characteristics[10].equals("full_bachelor") ? StudyDuration.FULL_BACHELOR :
                                        (characteristics[10].equals("exchange_1") ? StudyDuration.EXCHANGE_1 :
                                                (characteristics[10].equals("exchange_2") ? StudyDuration.EXCHANGE_2 : StudyDuration.OTHER)))));
        this.diet = characteristics[11].equals("pescetarian") ? Diet.PESCATARIAN :
                (characteristics[11].equals("vegatarian") ? Diet.VEGETARIAN :
                        (characteristics[11].equals("vegan") ? Diet.VEGAN : Diet.NONE));
        this.allergies = characteristics[12].replaceAll("^\"|\"$", "");
        this.allergies = (allergies.equalsIgnoreCase("no") || allergies.equalsIgnoreCase("none") ||
                allergies.equalsIgnoreCase("no restrictions") || allergies.equalsIgnoreCase("nothing") ||
                allergies.equalsIgnoreCase("nope") || allergies.equalsIgnoreCase("n/a") ||
                allergies.equalsIgnoreCase("na") || allergies.equalsIgnoreCase("non") ||
                allergies.equalsIgnoreCase("nil") || allergies.equals("-")) ? null : this.allergies;
        this.alcoholFree = !characteristics[21].isEmpty() && characteristics[21].charAt(0) == 'Y';
        this.requestsGuide = !characteristics[23].isEmpty() && characteristics[23].charAt(0) == 'Y';
        this.canGuide = !characteristics[24].isEmpty() && characteristics[24].charAt(0) == 'Y';
    }

    /**
     * Sets the group number assigned to the participant.
     *
     * @param groupNumber The group number to be assigned.
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Returns the email address of the participant.
     *
     * @return The participant's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the participant.
     *
     * @return The participant's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the first name of the participant.
     *
     * @return The participant's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the participant.
     *
     * @return The participant's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the gender of the participant as a string.
     *
     * @return The participant's gender ("Male", "Female", or "Other").
     */
    public String getGender() {
        String genderString = gender.toString();
        return genderString.substring(0, 1).toUpperCase() + genderString.substring(1).toLowerCase();
    }

    /**
     * Returns the nationality of the participant.
     *
     * @return The participant's nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Returns the birthdate of the participant.
     *
     * @return The participant's birthdate.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the university the participant is associated with as a string.
     *
     * @return The participant's university ("UG", "Hanze", or "Other").
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
     * Returns the duration of the participant's study program as a string.
     *
     * @return The participant's study duration.
     */
    public String getStudyDuration() {
        switch (studyDuration) {
            case PHD:
                return "PhD";
            case FULL_MASTER:
                return "Full Master";
            case EXCHANGE_MA:
                return "Exchange MA";
            case FULL_BACHELOR:
                return "Full Bachelor";
            case EXCHANGE_1:
                return "Exchange 1";
            case EXCHANGE_2:
                return "Exchange 2";
            default:
                return "Other";
        }
    }

    /**
     * Returns the dietary preference of the participant as a string.
     *
     * @return The participant's diet ("None", "Pescatarian", "Vegetarian", or "Vegan").
     */
    public String getDiet() {
        String dietString = diet.toString();
        return dietString.substring(0, 1).toUpperCase() + dietString.substring(1).toLowerCase();
    }

    /**
     * Returns the participant's allergies.
     *
     * @return The participant's allergies or {@code null} if none are specified.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Returns whether the participant is alcohol-free.
     *
     * @return "Yes" if the participant is alcohol-free, otherwise "No".
     */
    public String getAlcoholFree() {
        return alcoholFree ? "Yes" : "No";
    }

    /**
     * Returns the group number assigned to the participant.
     *
     * @return The participant's group number.
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Returns whether the participant has requested a guide.
     *
     * @return "Yes" if the participant has requested a guide, otherwise "No".
     */
    public String getRequestsGuide() {
        return requestsGuide ? "Yes" : "No";
    }

    /**
     * Returns whether the participant can serve as a guide.
     *
     * @return "Yes" if the participant can serve as a guide, otherwise "No".
     */
    public String getCanGuide() {
        return canGuide ? "Yes" : "No";
    }
}