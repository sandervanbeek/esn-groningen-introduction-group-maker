# ESN Groningen Introduction Week Group Maker

Welcome to the ESN Groningen Introduction Week Group Maker! This Java application is designed to create Introduction Groups for the ESN Groningen Introduction Weeks in a straightforward and user-friendly manner.

If you encounter any problems or have questions, please feel free to reach out for support.

## Table of contents
- [Getting started](#getting-started)
- [Usage](#usage)
    - [Input files](#input-files)
    - [Running the application](#running-the-application)
- [Methodology](#methodology)
- [List of known issues](#list-of-known-issues)
- [Contributing](#contributing)
- [License](#license)

## Getting started
The `.jar` file is in `out/artifacts/groupmaker.jar`. You can download this file to immediately run the application. If you prefer to work with the source code, you can also clone the repository and compile it using your preferred method.
    
### Prerequisites
- Java Development Kit (JDK) 11 or higher

## Usage

### Input files
The application can read the following input files:

- **Participants** _(`.csv`, required)_: A file containing the list of participants, with details like name, nationality, study application, etc.
- **Guides** _(`.csv`, optional)_: A file containing the list of guides, with their corresponding details.
- **Themes** _(`.csv`, optional)_: A file containing a list of themes.
- **Mail template** _(`.docx`, optional)_: A file containing a template email to be sent to guides and participants.

The input files are expected to satisfy certain criteria, specified below.

#### Participants

The first line of this file should serve as a header. Each subsequent line in this CSV file should represent a specific participant and must include the following characteristics, listed in the following order and separated by commas:

1. **ID:** (placeholder)
2. **Email:** The email address of the participant.
3. **First name:** The first name of the participant.
4. **Last name:** The last name of the participant.
5. **Gender:** The gender of the participant. Options include "male", "female", or "other".
6. **Date of birth:** The participant's date of birth in the format YYYY-MM-DD.
7. **Nationality:** The nationality of the participant.
8. **Phone number:** The contact phone number of the participant.
9. **University:** The name of the university the participant is associated with. Options include "university_of_groningen", "hanze_university", or "other".
10. **Faculty:** The name of the faculty the participant is associated with.
11. **Study duration:** The expected duration of the participant’s study application. Options include "exchange_1", "exchange_2", "full_bachelor", "full_master", "exchange_ma", "phd", or "other".
13. **Diet:** The dietary preference of the participant. Options include "pescetarian", "vegatarian", "vegan", or "none".
14. **Diet (additional):** Any additional dietary requirements or restrictions of the participant.
15. **Alcohol-free:** Indicates if the participant prefers an alcohol-free Introduction Group. Options include "Yes" or "No".
16. **Interest:** Main area of interest of the participant. Options include "Party", "Sport", "Travel", or "Culture".
17. **Note:** (placeholder)
18. **Group:** (placeholder)
19. **Card code:** (placeholder)
20. **Track name:** (placeholder)
21. **Order provider:** (placeholder)
22. **Order payment ID:** (placeholder)
23. **Alcohol-free:** Indicates whether the participants prefers an alcohol-free Introduction Group. Options include "Yes" or "No".
24. **Introduction Week interest:** The main area of interest of the participant. Options include "Party", "Sports", "Travel", or "Culture".
25. **Requests Introduction Guide:** Indicates whether the participants wants a group with an Introduction Guide. Options include "Yes, I am fine with joining a group without an Introduction Guide" or "No, I definitely want a group with an Introduction Guide".
26. **Group Leader:** Indicates whether the participant willing to become a Group Leader. Options include "Yes, I would be willing to become a Group Leader if necessary." or "No, I don't want to become a Group Leader".
27. **Student Mobility 1:** (placeholder)
28. **Student Mobility 2:** (placeholder)

**Note:** Fields marked as placeholders may remain empty but must be included to ensure proper processing of participant data.

#### Guides

The first line of this file should serve as a header. Each subsequent line in this CSV file should represent a specific participant and must include the following characteristics, listed in the following order and separated by commas:

1. **Cluster number:** Introduction Guides with the same cluster number will be assigned to the same Introduction Group. Introduction Guides without a cluster number will not be assigned to an Introduction Group.
2. **Timestamp:** (placeholder)
3. **First name:** The first name of the Introduction Guide.
4. **Last name:** The last name of the Introduction Guide.
5. **Phone number:** The contact phone number of the Introduction Guide.
6. **Email:** The email address of the Introduction Guide.
7. **Date of birth:** The Introduction Guide's date of birth in the format YYYY-MM-DD.
8. **University:** The name of the university the participant is associated with. Options include "University of Groningen", "Hanze University of Applied Sciences", or "Other".
9. **Faculty:** The name of the faculty the Introduction Guide is associated with.
10. **T-shirt size:** (placeholder)
11. **Interest:** Main area of interest of the participant. Options include "Party", "Sports", "Travel", or "Culture".
12. **Full name Introduction Guide Partner** (placeholder)
13. **Alcohol-free:** Indicates if the Introduction Guide prefers an alcohol-free Introduction Group. Options include "Yes" or "No".
14. **Dinner time:** (placeholder)
15. **Diet:** Indicates whether the Introduction Guides has dietary preferences. Options include "Yes, pescetarian", "Yes, vegetarian", "Yes, vegan", or "No".
16. **Diet (additional):** Any additional dietary requirements or restrictions of the Introduction Guide.
17. **Briefing attendance:** (placeholder)
18. **Agreement terms:** (placeholder)
19. **Information source:** (placeholder)
20. **Sign up for buddy:** (placeholder)
21. **Other remarks:** (placeholder)
22. **Consent for data retention:** (placeholder)
23. **Agreement Code of Conduct:** (placeholder)
24. **Concent to contact for promotion:** (placeholder)

**Note:** Fields marked as placeholders may remain empty but must be included to ensure proper processing of Introduction Guide data.

#### Themes

This file should consist of a single line of themes separated by commas.

#### Mail template

This `.docx` document serves as a template for generating emails for each group. These emails are intended to inform the Introduction Guides and participants about important details regarding their group assignments. This document may contain the placeholders "[group number]", "[theme]", "[guides]", or "[participants]" to indicate specific content that will be dynamically replaced when the document is generated. This replacement works as follows:

- **[group number]**: This will be replaced with the group number of the group.
- **[theme]**: This will be replaced with the theme assigned to the group.
- **[guides]**: This will be replaced with the Introduction Guides assigned to the group.
- **[participants]**: This will be replaced with the participants of the group.

Please ensure that these placeholders are placed outside of ordered or unordered lists and tables to ensure the correct replacement of content.

### Running the application
This step-by-step guide will walk you through the process of preparing and executing the application, from setting up your input files to verifying the output. For additional information, please refer to the section [Methodology](#methodology).

1. **Prepare the input files:**
   Ensure that the input files you want to use are correctly formatted.

2. **Run the application:**
   Double-press the `.jar` file or run the application from your terminal.

3. **Import the input files:**
   Use the button `Import participants` to import the participants. Optionally, use the buttons `Import guides`, `Import themes` and `Import email template` to import the guides, themes and email template, respectively.

5. **Set the output folder:**
   Use the button `Set output folder` to set the output folder.

7. **Review the settings:**
   Click the `Settings` button to review and adjust the current settings as needed.

8. **Run the model:**
   Start the model by clicking the `Run` button.

9. **Review the output:**
   After running the model, you can view the results by clicking on the hyperlinked options: `Participants`, `Groups`, or `Logs`. If you are not satisfied with the output, return to step 8 to rerun the model.

10. **Export the output:**
    To export the results, click the `Export` button.  The application will now generate the following files in the selected output directory:
     - a `.csv` file containing the participant details, including their assigned group numbers;
     - a `.csv` file containing the group details of the generated groups.

    If a list of Introduction Guides was provided, it will also generate a `.csv` file containing the Introduction Guides details, including their assigned group numbers. If an email template was imported, the application will generate email templates for each group in accordance with this template.

12. **Verify the output:**
    Please verify that the produced files are correct. If you imported an email template, verify whether all placeholders were successfully replaced. See also [this known issue](#placeholder-replacement-in-email-templates).

## Methodology
### Input processing
The application processes input data according to the type of field, following these guidelines:

- **Binary fields:** For fields with binary choices (e.g., alcohol-free, requests Introduction Guide), the input is converted to a boolean. If the input is different from the expected values or is missing, it defaults to `false`.
  
- **Categorical fields:** For fields that correspond to specific categories (such as gender or university), the input is matched against predefined enums. If the input does not match any categories or is missing, it defaults to a residual category.

- **Other fields:** For other fields (such as first name or phone number), the input is simply copied as-is. If the input is missing, the corresponding value is set to `null`.  
  
### Group making algorithm

The group making algorithm is designed to automatically form groups that are cohesive in terms of university, study duration and alcohol preferences of its members while being diverse in terms of gender, nationality and dietary preferences. To do so, the algorithm employs a scoring system based on two main metrics: **similarity** and **dissimilarity**.

The **similarity score** quantifies how alike two participants are based on their attributes. The scoring system works as follows:

- **University comparison**:
  - If both participants attend the same university, they receive a high additional similarity score (300 points).
  - If at least one participant does not study at the UG or Hanze, they receive a minimal additional similarity score (2 points).
  - If one participant studies at the UG while the other studies at Hanze, they receive no additional similarity score (0 points).

- **Alcohol preference comparison**:
  - If both participants have the same alcohol preference, they receive a high additional similarity score (200 points).
  - If the participants have different alcohol preference, they receive no additional similarity score (0 points).
 
- **Study duration comparison**:
  - If both participants have the same expected study duration, they receive a high additional similarity score (100 points).
  - If the expected study duration of at least one participant is not known, they receive a minimal additional similarity score (1 points).
  - Else, the participants receive no additional similarity score (0 points).

Conversely, the **dissimilarity score** measures the differences between two participants. This score is designed to promote diversity in groups:

- **Nationality comparison**:
  - If participants have the same nationality, they receive a minimal decrease in dissimilarity score (-1 points).
  - Else, the participants receive no change in dissimilarity score (0 points).

- **Dietary preferences comparison**:
  - If both participants have plant-based dietary preferences (pescatarian, vegetarian, or vegan), they receive a sizeable decrease in dissimilarity score (-3 points).
  - If neither participant has plant-based dietary preferences, they receive a minimal decrease in dissimilarity score (-1 point).
  - If one participant has plant-based dietary preferences while the other does not, they receive no change in dissimilarity score (0 points).

To form the groups, the algorithm follows these steps iteratively until every participant has been placed into a group:
1. However, if participants cannot be evenly divided into groups of that size, the algorithm may slightly decrease the size for certain groups to ensure that all groups are approximately equal in size.
2. A random participant is selected as the first member of the group, providing a starting point for building the group.
3. For each participant who has not yet been assigned to a group, the algorithm calculates the compatibility score between that participant and all current group members. These scores are then summed to assess the overall fit of the participant with the group.
4. The participant with the highest total compatibility score is added to the group, ensuring that the new member best complements the existing group dynamic.
5. Repeat steps 3 and 4 until the group reaches its determined size.
6. Once all groups are formed, the algorithm assigns a guide cluster and a theme to each group, if available. Guide clusters are matched based on shared alcohol preferences. Themes are randomly allocated from the list of available options.

**Note**: The algorithm does not guarantee an optimal result. Group allocation is an [NP-hard]([URL](https://en.wikipedia.org/wiki/NP-hardness)), meaning that finding the perfect solution is computationally infeasible. Instead, the algorithm employs a [greedy](https://en.wikipedia.org/wiki/Greedy_algorithm) approach, which approximates the best result. A random component is added to the compatibility scores to ensure variability, so that each run produces different groupings.

### Output generation
By default, the application generates the following files upon exporting:

- **`participants-matched.csv`**: This file includes each participant's group number along with their first name, last name, email, phone number, gender, nationality, date of birth, university, study duration, diet, allergies, alcohol preferences, preference to be in a group with an Introduction Guide, and openness to being a Group Leader.

- **`groups.csv`**: This file provides information on each group, detailing the composition based on university, study duration, and alcohol preferences of the participants.

If a list of Introduction Guides is provided, the application will also generate:

- **`guides-matched.csv`**: This file includes each Introduction Guide's group number along with their first name, last name, phone number, university, diet, allergies, and alcohol preferences.

Finally a mail template was imported, it will be filled for each group. If a group was not assigned a theme (either because too few themes were available, or because no list of themes was provided), the `[theme]` placeholder is removed from this group's template. If a group does not have an Introduction Guide (again, because too few Introduction Guides were available, or because no list of Introduction Guides was provided), the `[guides]` placeholder for this group is replaced by a table with just a header row. The completed templates will be named `groupXX.docx`, with `XX` indicating the group number for each corresponding group.

## List of known issues
### Placeholder replacement in email templates

**Issue:** In some cases, placeholders in email templates may not be successfully replaced.

**Description:** Word processing software like Microsoft Word can split text into different chunks or “runs” based on varying formatting, even when the text appears as a single, continuous string. This splitting can occur without any visible formatting changes, especially due to features like tracking changes or specific formatting options. This application searches for placeholders within these runs rather than across entire paragraphs, which means that placeholders may not be detected if they span multiple runs.

**Potential solution:** While it is theoretically possible to gather and concatenate text from all runs within a paragraph to locate placeholders, doing so while preserving formatting using Apache POI presents significant challenges. Therefore, this approach has not been implemented.

**Workaround:** If you encounter issues with placeholder replacement, a straightforward solution is to delete the problematic placeholder and retype it. This often resolves the detection issue and allows the application to recognize the placeholder correctly.

## Contributing

This repository is no longer actively maintained. While you are welcome to fork the project or submit pull requests, please note that I may not be able to address suggestions or feature requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.