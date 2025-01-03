package com.example.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.example.test.Uses.*;

public class AdminDashboardController {
    @FXML
    private TableView<UserDetails> studentReport_tableView;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_StudentId;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_fullName;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_username;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_email;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_faculty;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_sport;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_scid;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_problem;
    @FXML
    private TableColumn<UserDetails, Integer> studentReport_col_survey;
    @FXML
    private TableColumn<UserDetails, String> studentReport_col_courses;
    @FXML
    private Label availableCourse_facultyLabel;
    @FXML
    private Button addStudent_addBtn;

    @FXML
    private DatePicker addStudent_birthDate;

    @FXML
    private Button addStudent_clearBtn;

    @FXML
    private TableColumn<studentData, String> addStudent_col_birthDate;

    @FXML
    private TableColumn<studentData, String> addStudent_col_faculty;

    @FXML
    private TableColumn<studentData, String> addStudent_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudent_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudent_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudent_col_designation;

    @FXML
    private TableColumn<studentData, String> addStudent_col_studentNum;

    @FXML
    private TableColumn<studentData, String> addStudent_col_year;

    @FXML
    private Button addStudent_deleteBtn;

    @FXML
    private TextField addStudent_email;

    @FXML
    private ChoiceBox<String> addStudent_faculty;
    private String[] faculty = {"BCS", "BBA", "BSC"};

    @FXML
    private TextField addStudent_firstName;

    @FXML
    private AnchorPane addStudent_form;

    @FXML
    private ChoiceBox<String> addStudent_gender;
    private String[] gender = {"male", "female"};

    @FXML
    private ImageView addStudent_imageView;

    @FXML
    private Button addStudent_insertBtn;
    private String imageUrl;


    @FXML
    private TextField addStudent_lastName;

    @FXML
    private TextField addStudent_password;

    @FXML
    private TextField addStudent_phone;

    @FXML
    private TextField addStudent_search;

    @FXML
    private ChoiceBox<String> addStudent_designation;
    private String[] designation = {"Student", "Librarian", "Teacher", "AdmissionOfficer", "Admin"};

    @FXML
    private TextField addStudent_userId;

    @FXML
    private TableView<studentData> addStudent_tableView;

    @FXML
    private Button addStudent_updateBtn;

    @FXML
    private TextField addStudent_username;

    @FXML
    private ChoiceBox<Integer> addStudent_year;
    private Integer[] year = {2021, 2022, 2023, 2024};

    @FXML
    private Button addstudent_Btn;

    @FXML
    private Button availableCourse_Btn;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private TableColumn<studentData, String> availableCourse_col_course;

    @FXML
    private TableColumn<studentData, String> availableCourse_col_description;

    @FXML
    private TableColumn<studentData, String> availableCourse_col_faculty;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_faculty;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TableView<studentData> availableCourse_tableView;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button home_Btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private BarChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalMaleChart;

    @FXML
    private FontAwesomeIconView logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private FontAwesomeIconView minimize;

    @FXML
    private Button studentGrade_Btn;

    @FXML
    private Button studentGrade_clearBtn;


    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_search;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private TextField studentGrade_studentNum;


    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label username;

    @FXML
    private Label adduserError;

    @FXML
    private Label addCourse_label;

    private Map<String, studentData> userMap = new HashMap<>();
    private Map<String, studentData> courseMap = new HashMap<>();



    String[] csvFiles = {
            "StudentData.csv", // 12 columns
            "Teacher.csv", // 12 columns
            "Admin.csv", // 11 columns
            "Librarian.csv", // 11 columns
            "AdmissionOfficer.csv"  // 11 columns
    };

    private ObservableList<studentData> studentDataList = FXCollections.observableArrayList();
    private ObservableList<studentData> courseDataList = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws FileNotFoundException {

        addStudent_gender.getItems().addAll(gender);
        addStudent_year.getItems().addAll(year);
        addStudent_faculty.getItems().addAll(faculty);
        addStudent_designation.getItems().addAll(designation);

        // Adding drag functionality for the main_form AnchorPane
        main_form.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        main_form.setOnMouseDragged(event -> {
            Stage stage = (Stage) main_form.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            stage.setOpacity(.8);

        });

        main_form.setOnMouseReleased(event -> {
            Stage stage = (Stage) main_form.getScene().getWindow();
            stage.setOpacity(1);
        });

        // initialize add student columns
        addStudent_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        addStudent_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudent_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudent_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudent_col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        addStudent_col_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        addStudent_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudent_col_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));


        // Load data from CSV
        loadCSVData("StudentData.csv");
        loadCSVData("Teacher.csv");
        loadCSVData("Admin.csv");
        loadCSVData("Librarian.csv");
        loadCSVData("AdmissionOfficer.csv");
        addStudent_tableView.setItems(studentDataList);


        // Initialize teh course columns
        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        availableCourse_col_faculty.setCellValueFactory(new PropertyValueFactory<>("courseEnrolled"));

        loadCourseCSVData();
        availableCourse_tableView.setItems(courseDataList);

        // to calculate total students/ males and females
        calculateCounts();


        // Initialize report columns
        studentReport_col_StudentId.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        studentReport_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        studentReport_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        studentReport_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentReport_col_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        studentReport_col_sport.setCellValueFactory(new PropertyValueFactory<>("interestedSport"));
        studentReport_col_scid.setCellValueFactory(new PropertyValueFactory<>("scId"));
        studentReport_col_problem.setCellValueFactory(new PropertyValueFactory<>("problemSubject"));
        studentReport_col_survey.setCellValueFactory(new PropertyValueFactory<>("surveyCount"));
        studentReport_col_courses.setCellValueFactory(new PropertyValueFactory<>("enrolledCourses"));

        // Load data into the TableView
        studentReport_tableView.setItems(getUserDetails());


    }

    // Loaders


    // load csv data for results
    private ObservableList<UserDetails> getUserDetails() {
        ObservableList<UserDetails> userDetailsList = FXCollections.observableArrayList();

        try {
            UserDetailsCollector collector = new UserDetailsCollector();
            Map<String, UserDetails> userDetailsMap = collector.getAllUserDetails();
            userDetailsList.addAll(userDetailsMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDetailsList;
    }




    // load csv data for courses

    private void loadCourseCSVData() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Courses.csv"))) {
            String headers = reader.readLine(); // Read and ignore headers
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 3) {
                    System.err.println("Incorrect number of fields in CSV line: " + line);
                    continue;
                }
                String course = fields[0].trim();
                String course_description = fields[1].trim();
                Integer course_enrolled = Integer.valueOf(fields[2].trim());

                System.out.println("Loaded course: " + course + ", description: " + course_description);

                studentData courseD = new studentData(course, course_description, course_enrolled);
                courseDataList.add(courseD);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        availableCourse_tableView.setItems(courseDataList);
    }




    // load csv data for ad student table
    private void loadCSVData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read headers (assuming they are in the first line and can be ignored)
            String headers = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Assuming the order of fields in CSV based on the provided format:
                String userNum = fields[0].trim();
                String year = fields[9].trim();
                String faculty = fields.length > 12 ? fields[12].trim() : null;
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String gender = fields[3].trim();
                String birthDate = fields[4].trim();
                String designation = fields[8].trim();

                // Create studentData object and add to list
                studentData studentD = new studentData(userNum, year, faculty, firstName, lastName, gender, birthDate, designation);
                studentDataList.add(studentD);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set table data
        addStudent_tableView.setItems(studentDataList);
    }





    @FXML
    public void close() {
        System.exit(0);
    }



    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }



    public void logout(ActionEvent event) throws IOException {
        changeScene(event, "login.fxml", "Login");

    }




    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_Btn) {
            home_form.setVisible(true);
            addStudent_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addstudent_Btn.setStyle("-fx-background-color:transparent");
            availableCourse_Btn.setStyle("-fx-background-color:transparent");
            studentGrade_Btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == addstudent_Btn) {
            home_form.setVisible(false);
            addStudent_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            addstudent_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_Btn.setStyle("-fx-background-color:transparent");
            availableCourse_Btn.setStyle("-fx-background-color:transparent");
            studentGrade_Btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == availableCourse_Btn) {
            home_form.setVisible(false);
            addStudent_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            availableCourse_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addstudent_Btn.setStyle("-fx-background-color:transparent");
            home_Btn.setStyle("-fx-background-color:transparent");
            studentGrade_Btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == studentGrade_Btn) {
            home_form.setVisible(false);
            addStudent_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            studentGrade_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addstudent_Btn.setStyle("-fx-background-color:transparent");
            availableCourse_Btn.setStyle("-fx-background-color:transparent");
            home_Btn.setStyle("-fx-background-color:transparent");
        }
    }


    public boolean userIdExists(String userId) throws IOException {
        String[] fileNames = {"StudentData.csv", "Teacher.csv", "Admin.csv", "Librarian.csv", "AdmissionOfficer.csv"};

        for (String fileName : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length > 0) {
                        if (fields[0].trim().equals(userId)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean usernameExists(String username) throws IOException {
        String[] fileNames = {"StudentData.csv", "Teacher.csv", "Admin.csv", "Librarian.csv", "AdmissionOfficer.csv"};

        for (String fileName : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length > 6) {
                        if (fields[6].trim().equals(username)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }




// add Student or user detail and hash the password
@FXML
public void addUser_addBtn(ActionEvent event) throws IOException {
    String userId = addStudent_userId.getText();
    String username = addStudent_username.getText();

    // Check if the user ID or username already exists
    if (userIdExists(userId)) {
        displayError(adduserError,"userId already exist");
        return; // Exit the method if user ID already exists
    } else if (usernameExists(username)) {
        displayError(adduserError,"Username already exists.");
        return; // Exit the method if username already exists
    }

    // Collect user input
    String firstName = addStudent_firstName.getText();
    String lastName = addStudent_lastName.getText();
    String gender = addStudent_gender.getValue();
    String birthDate = addStudent_birthDate.getValue().toString();
    String email = addStudent_email.getText();
    String faculty = addStudent_faculty.getValue();
    String year = addStudent_year.getValue().toString();
    String password = addStudent_password.getText();
    String phone = addStudent_phone.getText();
    String designation = addStudent_designation.getValue();

    // Validate inputs
    if (userId.isEmpty() || username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
            gender.isEmpty() || birthDate.isEmpty() || email.isEmpty() || faculty.isEmpty() ||
            year.isEmpty() || password.isEmpty() || phone.isEmpty() || designation.isEmpty()) {
        displayError(adduserError, "Please enter data in all fields");
    } else if (username.length() < 4) {
        displayError(adduserError, "Username should be at least 4 characters long");
    } else if (userMap.containsKey(username)) {
        displayError(adduserError, "Username already exists");
    } else if (userMap.containsKey(userId)) {
        displayError(adduserError, "User ID already exists");
    } else {
        // Hash the password before saving
        String hashedPassword = PasswordUtils.hashPassword(password);

        studentData newUser = new studentData(userId, firstName, lastName, gender, 
        birthDate, email, faculty, year, username, hashedPassword, phone, designation, imageUrl);

        CSVWriterUtil.writeUser(newUser);
        userMap.put(username, newUser);
        studentData studentD = new studentData(userId, year, faculty, firstName, lastName, 
        gender, birthDate, designation);
        studentDataList.add(studentD);
        adduserSucess(adduserError, "User Added Successfully");

        addStudent_tableView.setItems(studentDataList); // Update table view
        clearFields(); // Clear the input fields after adding the user
    }
}

    // to add image of user
    @FXML
    public void addUser_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", 
        "*.jpg", "*.jpeg", "*.gif"));
        File selectedImage = fileChooser.showOpenDialog(null);

        if (selectedImage != null) {
            imageUrl = selectedImage.toURI().toString();

//            System.out.println("Selected Image URL: " + imageUrl); // Debugging statement

            Image image = new Image(imageUrl);
            addStudent_imageView.setImage(image);

        }
    }


    @FXML
    public void addStudent_clearBtn(ActionEvent event){
        clearFields();
    }


    // update User detail by ID
    @FXML
    public void addStudent_updateBtn(ActionEvent event) {
        String userId = addStudent_userId.getText(); // Get the ID to update

        // Updated details from input fields
        String updatedFirstname = addStudent_firstName.getText();
        String updatedLastname = addStudent_lastName.getText();
        // For future Use we can add other field to update

        try {
            String designation = getUserDesignation(userId);
            String filePath = getFilePathByDesignation(designation);

            updateStudentInFile(filePath, userId, updatedFirstname, updatedLastname);
            adduserSucess(adduserError, "User Updated Successfully");
            clearFields();

            // Reload the table view if needed
            loadCSVData("StudentData.csv");
            loadCSVData("Teacher.csv");
            loadCSVData("Admin.csv");
            loadCSVData("Librarian.csv");
            loadCSVData("AdmissionOfficer.csv");
            addStudent_tableView.refresh();

        } catch (IOException e) {
            adduserError.setText("Failed to update user.");
            e.printStackTrace();
        }
    }

    private void updateStudentInFile(String filePath, String userId, String updatedFirstname, String updatedLastname) throws IOException {
        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> updatedLines = new ArrayList<>();

        // Iterate over each line and update the matching row
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length > 0 && fields[0].trim().equals(userId)) {
                // Update the fields only if new values are provided
                if (updatedFirstname != null && !updatedFirstname.isEmpty()) {
                    fields[1] = updatedFirstname;
                }
                if (updatedLastname != null && !updatedLastname.isEmpty()) {
                    fields[2] = updatedLastname;
                }
                //For future Update more fields as needed
            }
            // Join the fields back into a CSV format
            updatedLines.add(String.join(",", fields));
        }

        // Write the updated lines back to the file
        Files.write(Paths.get(filePath), updatedLines);
    }




    public String getUserDesignation(String userId) throws IOException {
        String[] fileNames = {"StudentData.csv", "Teacher.csv", "Admin.csv", "Librarian.csv", "AdmissionOfficer.csv"};
        String[] designations = {"Student", "Teacher", "Admin", "Librarian", "AdmissionOfficer"};

        for (int i = 0; i < fileNames.length; i++) {
            String filePath = fileNames[i];
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length > 0 && fields[0].trim().equals(userId)) {
                        return designations[i];
                    }
                }
            }
        }
        throw new IOException("User ID not found in any CSV file.");
    }


    public void deleteUserFromFile(String userId, String designation) throws IOException {
        String filePath = getFilePathByDesignation(designation);
        System.out.println("File path: " + filePath); // Debugging line

        // Read all lines from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Filter out the line with the userId
        List<String> updatedLines = new ArrayList<>();
        String headerLine = lines.get(0); // Assuming the first line is the header
        updatedLines.add(headerLine);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] fields = line.split(",");
            if (fields.length > 0 && !fields[0].trim().equals(userId)) {
                updatedLines.add(line);
            }
        }

        // Write the updated lines back to the original file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine + System.lineSeparator());
            }
        }
    }

    private String getFilePathByDesignation(String designation) {
        switch (designation) {
            case "Student":
                return "StudentData.csv";
            case "Teacher":
                return "Teacher.csv";
            case "Admin":
                return "Admin.csv";
            case "Librarian":
                return "Librarian.csv";
            case "AdmissionOfficer":
                return "AdmissionOfficer.csv";
            default:
                throw new IllegalArgumentException("Unknown designation: " + designation);
        }
    }


    @FXML
    public void addStudent_deleteBtn(ActionEvent event) throws IOException {
        String deleteId = addStudent_userId.getText(); // Get the ID to delete
        String designation = getUserDesignation(deleteId);

        try {
            deleteUserFromFile(deleteId, designation);
            studentDataList.removeIf(student -> student.getUserNumber().equals(deleteId));
            addStudent_tableView.setItems(studentDataList); // Update table view

            adduserSucess(adduserError, "User Deleted Successfully");
            clearFields();
        } catch (IOException e) {
            adduserError.setText("Failed to delete user.");
            e.printStackTrace();
        }
    }



    private void clearFields() {
        addStudent_userId.clear();
        addStudent_firstName.clear();
        addStudent_lastName.clear();
        addStudent_gender.getSelectionModel().clearSelection();
        addStudent_birthDate.getEditor().clear();
        addStudent_email.clear();
        addStudent_faculty.getSelectionModel().clearSelection();
        addStudent_year.getSelectionModel().clearSelection();
        addStudent_username.clear();
        addStudent_password.clear();
        addStudent_phone.clear();
        addStudent_designation.getSelectionModel().clearSelection();
        addStudent_imageView.setImage(null);
        imageUrl = null;
        availableCourse_course.clear();
        availableCourse_description.clear();
    }

    public void setupDashboard(studentData user) {
        String designation = user.getDesignation();
        String adminname = user.getUsername();

        switch (designation) {
            case "Admin":
                username.setText("ADMIN");
                availableCourse_faculty.setVisible(false);
                availableCourse_facultyLabel.setVisible(false);
                availableCourse_faculty.setDisable(true);
                availableCourse_updateBtn.setVisible(false);
                availableCourse_updateBtn.setDisable(true);
                // Admin has access to everything
                home_form.setVisible(true);
                addStudent_form.setVisible(false);
                availableCourse_form.setVisible(false);
                studentGrade_form.setVisible(false);
                break;
            case "Teacher":
                username.setText("TEACHER \n"+adminname);
                // Teacher only sees student grades
                home_Btn.setVisible(false);
                addstudent_Btn.setVisible(false);
                availableCourse_Btn.setVisible(false);
                studentGrade_Btn.setVisible(true);


                studentGrade_form.setVisible(true);
                home_form.setVisible(false);
                addStudent_form.setVisible(false);
                availableCourse_form.setVisible(false);
                break;
            case "Librarian":
                username.setText("Librarian");
                availableCourse_faculty.setVisible(false);
                availableCourse_facultyLabel.setVisible(false);
                availableCourse_faculty.setDisable(true);
                availableCourse_updateBtn.setVisible(false);
                availableCourse_updateBtn.setDisable(true);
                // Librarian only sees courses
                home_Btn.setVisible(false);
                addstudent_Btn.setVisible(false);
                availableCourse_Btn.setVisible(true);
                studentGrade_Btn.setVisible(false);

                availableCourse_form.setVisible(true);
                home_form.setVisible(false);
                addStudent_form.setVisible(false);
                studentGrade_form.setVisible(false);
                break;
            case "AdmissionOfficer":
                username.setText("Admission Officer");

                // Restrict designation choice box to only "Student" and make it unchangeable
                addStudent_designation.getItems().clear();
                addStudent_designation.getItems().add("Student");
                addStudent_designation.setValue("Student");
//                addStudent_designation.setDisable(true);
                // Admission Officer sees add student and home form
                home_Btn.setVisible(true);
                addstudent_Btn.setVisible(true);
                availableCourse_Btn.setVisible(false);
                studentGrade_Btn.setVisible(false);
                addStudent_deleteBtn.setVisible(false);

                home_form.setVisible(true);
                addStudent_form.setVisible(true);
                addstudent_Btn.setText("Add Student");
                availableCourse_form.setVisible(false);
                studentGrade_form.setVisible(false);
                break;
            default:
                displayError(adduserError, "Unknown designation");
        }
    }

    // Variables to hold the offset for dragging
    private double xOffset = 0;
    private double yOffset = 0;


@FXML
public void availableCourse_addBtn(ActionEvent event){
        try {
            String coursename = availableCourse_course.getText();
            String course_description = availableCourse_description.getText();
            Integer course_enrolled = 0;

            studentData newCourse = new studentData(coursename, course_description,course_enrolled); //lastName, gender, birthDate, email, faculty, year, username, password, phone, designation, imageUrl);

            CSVWriterUtil.writeCourse(newCourse);
            courseMap.put(coursename, newCourse);
            studentData courseD = new studentData(coursename, course_description,course_enrolled);
            courseDataList.add(courseD);
            adduserSucess(addCourse_label, "Course Added Sucessfully");

            availableCourse_tableView.setItems(courseDataList); // Update table view
//            clearFields();



        } catch (IOException e) {
            displayError(adduserError, "Failed to sign up. Please try again.");
            e.printStackTrace();
        }
    }

    @FXML
    public void availableCourse_updateBtn(ActionEvent event) {
        // Get the course name from the text field
        String courseName = availableCourse_course.getText();

        // Find the course data with the specified course name
        Optional<studentData> courseToUpdate = courseDataList.stream()
                .filter(course -> course.getCourseName().equals(courseName))
                .findFirst();

        if (courseToUpdate.isPresent()) {
            studentData courseData = courseToUpdate.get();

            // Increase the enrolled count by 1
            int newEnrolledCount = courseData.getCourseEnrolled() + 1;
            courseData.setCourseEnrolled(newEnrolledCount);

            // Update the course data list and table view
            availableCourse_tableView.refresh();
            adduserSucess(addCourse_label, "Course updated successfully");

            // Optionally update the CSV file
            try {
                CSVWriterUtil.updateCourse(courseData);
            } catch (IOException e) {
                displayError(addCourse_label, "Failed to update course in file.");
                e.printStackTrace();
            }

        } else {
            displayError(addCourse_label, "Course not found.");
        }
    }


    @FXML
    public void availableCourse_deletBtn(ActionEvent event) {
        String deleteCourse = availableCourse_course.getText(); // Get the course name to delete

        // Read all lines from the file
        String filePath = "Courses.csv";
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            displayError(addCourse_label, "Failed to read the file.");
            return;
        }

        // Remove the course from the lines list
        boolean isRemoved = false;
        if (!lines.isEmpty()) {
            String headerLine = lines.get(0);
            List<String> updatedLines = new ArrayList<>();
            updatedLines.add(headerLine); // Write header to updated lines

            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                if (fields.length >= 3) {
                    String courseName = fields[0].trim();
                    if (courseName.equals(deleteCourse)) {
                        isRemoved = true;
                        continue; // Skip this line
                    }
                }
                updatedLines.add(lines.get(i)); // Add line unchanged if it doesn't match
            }

            // Write all lines back to the original file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
                displayError(addCourse_label, "Failed to write to the file.");
            }

            // Update the table view and clear fields if the course was removed
            if (isRemoved) {
                // Update the courseDataList and table view
                courseDataList.removeIf(course -> course.getCourseName().equals(deleteCourse));
                availableCourse_tableView.setItems(courseDataList);
                adduserSucess(addCourse_label, "Course Deleted Successfully");
            } else {
                adduserSucess(addCourse_label, "Course Not Found");
            }

            clearFields();
        }
    }


    @FXML
    public void availableCourse_clearBtn(ActionEvent event){
        clearFields();
    }

    @FXML
    private void calculateCounts() {
        String csvFile = "StudentData.csv";
        int totalRows = 0;
        int maleCount = 0;
        int femaleCount = 0;

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;

            // Read header line (if exists)
            reader.readNext();

            // Read the file line by line
            while ((line = reader.readNext()) != null) {
                totalRows++;

                // Check the designation column (index 8)
                if (line.length > 8) {
                    String designation = line[3];
                    if ("male".equalsIgnoreCase(designation)) {
                        maleCount++;
                    } else if ("female".equalsIgnoreCase(designation)) {
                        femaleCount++;
                    }
                }
            }

            // Update the UI with the results
            home_totalEnrolled.setText(String.valueOf(totalRows));
            home_totalMale.setText(String.valueOf(maleCount));
            home_totalFemale.setText(String.valueOf(femaleCount));

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}


