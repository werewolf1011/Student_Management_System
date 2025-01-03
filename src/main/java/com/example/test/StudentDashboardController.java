package com.example.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static com.example.test.Uses.*;
import static com.example.test.Uses.displayError;

import java.security.SecureRandom;
import java.util.Random;


public class StudentDashboardController {
    @FXML
    private Label problem_msg;
    @FXML
    private TextField problem_SCId;
    @FXML
    private TextField problem_subject;
    @FXML
    private TextField problem_email;
    @FXML
    private TextField problem_gender;
    @FXML
    private TextField problem_faculty;
    @FXML
    private TextField problem_address;
    @FXML
    private TextField problem_sid;
    @FXML
    private TextArea problem_desc;
    @FXML
    private TextField problem_Id;
    @FXML
    private Label surveyForm_msg;
    @FXML
    private TextField survey_fname;
    @FXML
    private TextField survey_sid;
    @FXML
    private TextField survey_faculty;
    @FXML
    private Label q1;
    @FXML
    private Label q3;
    @FXML
    private Label q2;
    @FXML
    private Button submit_answer;
    @FXML
    private Label sportForm_msg;
    @FXML
    private Button submit_sport;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private AnchorPane problem_form;
    @FXML
    private Button problem_submitBtn;
    @FXML
    private TextField problem_fname;
    @FXML
    private AnchorPane questionform;
    @FXML
    private Label availableCourse_facultyLabel;
    @FXML
    private TextField availableCourse_faculty;
    @FXML
    private Label addCourse_label;
    @FXML
    private Button availableCourse_clearBtn;
    @FXML
    private Button availableCourse_updateBtn;
    @FXML
    private TextField availableCourse_course;
    @FXML
    private Button ECA_btn;
    @FXML
    private AnchorPane ECA_form;
    @FXML
    private Button Library_btn;
    @FXML
    private AnchorPane Library_form;
    @FXML
    private Button Problem_btn;
    @FXML
    private Button QuestionForm_btn;
    @FXML
    private Button Report_btn;
    @FXML
    private AnchorPane Report_form;
    @FXML
    private TextField Sports_faculty;
    @FXML
    private TextField Sports_fname;
    @FXML
    private TextField Sports_interested;
    @FXML
    private TextField Sports_sid;
    @FXML
    private TextArea a1;
    @FXML
    private TextArea a2;
    @FXML
    private TextArea a3;
    @FXML
    private Label adduserError;
    @FXML
    private TableColumn<studentData, String> availableCourse_col_course;
    @FXML
    private TableColumn<studentData, String> availableCourse_col_description;
    @FXML
    private TableColumn<studentData, Integer> availableCourse_col_status;
    @FXML
    private TableView<studentData> availableCourse_tableView;
    @FXML
    private Button close;
    @FXML
    private Button home_Btn;
    @FXML
    private AnchorPane home_form;
    @FXML
    private ImageView student_image;
    @FXML
    private Label student_email;
    @FXML
    private Label student_faculty;
    @FXML
    private Label student_firstname;
    @FXML
    private Label student_gender;
    @FXML
    private Label student_lastname;
    @FXML
    private Label student_phone;
    @FXML
    private Label student_userid;
    @FXML
    private Label student_username;
    @FXML
    private Label student_year;
    @FXML
    private Label student_birthdate;
    @FXML
    private Label username;
    @FXML
    private AnchorPane main_form;

    private ObservableList<studentData> courseDataList = FXCollections.observableArrayList();

    // Variables to hold the offset for dragging
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void initialize() throws FileNotFoundException {
        problem_SCId.setDisable(true);

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

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        availableCourse_col_status.setCellValueFactory(new PropertyValueFactory<>("courseEnrolled"));


        // Read the CSV and populate the TableView
        List<studentData> courses = readCoursesFromCSV("Courses.csv");
        courseDataList.addAll(courses);  // Add courses to the observable list
        availableCourse_tableView.setItems(courseDataList);


    }


    public static List<studentData> readCoursesFromCSV(String fileName) {
        List<studentData> courses = new ArrayList<>();
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] courseData = line.split(csvSplitBy);
                // Handle course description which may contain commas
                String courseDescription = courseData[1];
                if (courseData.length > 3) {
                    for (int i = 2; i < courseData.length - 1; i++) {
                        courseDescription += "," + courseData[i];
                    }
                }
                int courseEnrolled = Integer.parseInt(courseData[courseData.length - 1]);
                courses.add(new studentData(courseData[0], courseDescription, courseEnrolled));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }


    @FXML
    public void close(ActionEvent event) {
        System.exit(0);
    }

    public void logout(ActionEvent event) throws IOException {
        changeScene(event, "login.fxml", "Login");
    }

    public void minimize(ActionEvent event) {
        Stage stage = (Stage) student_firstname.getScene().getWindow();
        stage.setIconified(true);
    }

    void displayUserDetails(studentData user) {
        String um = user.getUsername();
        try (CSVReader reader = new CSVReader(new FileReader("StudentData.csv"))) {
            List<String[]> csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                String[] col = csvData.get(i);
                String username = col[6];
                if (username.equals(um)) {
                    student_email.setText(col[5]);
                    student_faculty.setText(col[12]);
                    student_firstname.setText(col[1]);
                    student_gender.setText(col[3]);
                    String imageurl = col[10];
                    if (imageurl != null && !imageurl.isEmpty()) {
                        Image image = new Image(imageurl);
                        student_image.setImage(image);
                    } else {
                        student_image.setImage(null);
                    }
                    student_lastname.setText(col[2]);
                    student_phone.setText(col[11]);
                    student_userid.setText(col[0]);
                    student_username.setText(col[6]);
                    student_year.setText(col[9]);
                    student_birthdate.setText(col[4]);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        username.setText(user.getUsername());
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_Btn) {
            home_form.setVisible(true);
            problem_form.setVisible(false);
            Library_form.setVisible(false);
            ECA_form.setVisible(false);
            questionform.setVisible(false);
            Report_form.setVisible(false);
            home_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Library_btn.setStyle("-fx-background-color:transparent");
            Problem_btn.setStyle("-fx-background-color:transparent");
            ECA_btn.setStyle("-fx-background-color:transparent");
            QuestionForm_btn.setStyle("-fx-background-color:transparent");
            Report_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Library_btn) {

            home_form.setVisible(false);
            problem_form.setVisible(false);
            Library_form.setVisible(true);
            ECA_form.setVisible(false);
            questionform.setVisible(false);
            Report_form.setVisible(false);

            Library_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_Btn.setStyle("-fx-background-color:transparent");
            Problem_btn.setStyle("-fx-background-color:transparent");
            ECA_btn.setStyle("-fx-background-color:transparent");
            QuestionForm_btn.setStyle("-fx-background-color:transparent");
            Report_btn.setStyle("-fx-background-color:transparent");
            String username = student_username.getText();
            loadEnrolledCourses(username);


        } else if (event.getSource() == Problem_btn) {
            home_form.setVisible(false);
            problem_form.setVisible(true);
            Library_form.setVisible(false);
            ECA_form.setVisible(false);
            questionform.setVisible(false);
            Report_form.setVisible(false);

            Problem_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_Btn.setStyle("-fx-background-color:transparent");
            Library_btn.setStyle("-fx-background-color:transparent");
            ECA_btn.setStyle("-fx-background-color:transparent");
            QuestionForm_btn.setStyle("-fx-background-color:transparent");
            Report_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == ECA_btn) {
            home_form.setVisible(false);
            problem_form.setVisible(false);
            Library_form.setVisible(false);
            ECA_form.setVisible(true);
            questionform.setVisible(false);
            Report_form.setVisible(false);

            ECA_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Library_btn.setStyle("-fx-background-color:transparent");
            Problem_btn.setStyle("-fx-background-color:transparent");
            home_Btn.setStyle("-fx-background-color:transparent");
            QuestionForm_btn.setStyle("-fx-background-color:transparent");
            Report_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == QuestionForm_btn) {
            home_form.setVisible(false);
            problem_form.setVisible(false);
            Library_form.setVisible(false);
            ECA_form.setVisible(false);
            questionform.setVisible(true);
            Report_form.setVisible(false);

            QuestionForm_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Library_btn.setStyle("-fx-background-color:transparent");
            Problem_btn.setStyle("-fx-background-color:transparent");
            ECA_btn.setStyle("-fx-background-color:transparent");
            home_Btn.setStyle("-fx-background-color:transparent");
            Report_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Report_btn) {
            home_form.setVisible(false);
            problem_form.setVisible(false);
            Library_form.setVisible(false);
            ECA_form.setVisible(false);
            questionform.setVisible(false);
            Report_form.setVisible(true);

            Report_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            Library_btn.setStyle("-fx-background-color:transparent");
            Problem_btn.setStyle("-fx-background-color:transparent");
            ECA_btn.setStyle("-fx-background-color:transparent");
            QuestionForm_btn.setStyle("-fx-background-color:transparent");
            home_Btn.setStyle("-fx-background-color:transparent");
        }
    }

    public void saveEnrolledCourses(String username) {
        String fileName = username + "_enrolledCourses.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Label[] labels = {l1, l2, l3, l4, l5, l6, l7};
            for (Label label : labels) {
                if (!label.getText().isEmpty()) {
                    writer.write(label.getText());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void availableCourseUpdateBtnClicked() {
        String courseName = availableCourse_course.getText().trim();

        Optional<studentData> courseToUpdate = courseDataList.stream()
                .filter(course -> course.getCourseName().equalsIgnoreCase(courseName))
                .findFirst();

        if (courseToUpdate.isPresent()) {
            studentData courseData = courseToUpdate.get();

            // Increase the enrolled count by 1
            int newEnrolledCount = courseData.getCourseEnrolled() + 1;
            courseData.setCourseEnrolled(newEnrolledCount);

            // Check which label is empty and set the text
            Label[] labels = {l1, l2, l3, l4, l5, l6, l7};
            for (Label label : labels) {
                if (label.getText().isEmpty()) {
                    label.setText("Enrolled for " + courseData.getCourseName());
                    break;
                }
            }

            // Update the course data list and table view
            availableCourse_tableView.refresh();
            adduserSucess(addCourse_label, "Course enrolled successfully");

            // Optionally update the CSV file
            try {
                CSVWriterUtil.updateCourse(courseData);
            } catch (IOException e) {
                displayError(addCourse_label, "Failed to update course in file.");
                e.printStackTrace();
            }

            // Save enrolled courses to file to persist data
            saveEnrolledCourses(student_username.getText());
        } else {
            displayError(addCourse_label, "Course not found.");
        }
    }



    public void loadEnrolledCourses(String username) {
        String fileName = username + "_enrolledCourses.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Label[] labels = {l1, l2, l3, l4, l5, l6, l7};
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < labels.length) {
                labels[i].setText(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void availableCourseClearBtnClicked() {
        availableCourse_course.clear();
    }

    private static final String FULL_NAME_PATTERN = "^[a-zA-Z]+\\s+[a-zA-Z]+(\\s+[a-zA-Z]+)*$";

    @FXML
    public void submit_sport(ActionEvent event) throws IOException {
        String FullName = Sports_fname.getText();
        String Faculty = Sports_faculty.getText();
        String StudentId = Sports_sid.getText();
        String InterestedSport = Sports_interested.getText();

        // Check if any field is empty
        if (Objects.equals(FullName, "") || Objects.equals(Faculty, "") || Objects.equals(StudentId, "") || Objects.equals(InterestedSport, "")) {
            displayError(sportForm_msg, "Please fill every field");
        } else {
            // Validate the full name
            if (FullName.matches(FULL_NAME_PATTERN)) {
                studentData newParticipant = new studentData(FullName, Faculty, StudentId, InterestedSport);
                CSVWriterUtil.writeSportDetail(newParticipant);
                adduserSucess(sportForm_msg, "Form Submitted Successfully");
//                clearFields();
            } else {
                displayError(sportForm_msg, "INVALID FULL NAME");
            }
        }
    }


    @FXML
    public void submit_answer(ActionEvent event) throws IOException {
        String FullName = survey_fname.getText();
        String StudentId = survey_sid.getText();
        String Faculty = survey_faculty.getText();
        String Q1 = q1.getText();
        String A1 = a1.getText();
        String Q2 = q2.getText();
        String A2 = a2.getText();
        String Q3 = q3.getText();
        String A3 = a3.getText();

        if (Objects.equals(FullName, "") || Objects.equals(StudentId, "") || Objects.equals(Faculty, "") || Objects.equals(A1, "") || Objects.equals(A2, "") || Objects.equals(A3, "")) {
            displayError(surveyForm_msg, "Please fill every field");
        } else {
            if (FullName.matches(FULL_NAME_PATTERN)) {
                studentData newSurvey = new studentData(FullName, StudentId, Faculty, Q1, A1, Q2, A2, Q3, A3);
                CSVWriterUtil.writeSurveyDetailsl(newSurvey);
                adduserSucess(surveyForm_msg, "Survey Submitted Successfully");
//                clearFields();
            } else {
                displayError(surveyForm_msg, "INVALID FULL NAME");

            }
        }
    }


    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new SecureRandom();

    private String generateRandomSCId(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC_CHARACTERS.charAt(RANDOM.nextInt(ALPHANUMERIC_CHARACTERS.length())));
        }
        return sb.toString();
    }


    @FXML
    public void problem_submitBtn(ActionEvent event) throws IOException {
        String SCId = generateRandomSCId(6);
        problem_SCId.setText(SCId);
        String FullName = problem_fname.getText();
        String ProblemId = problem_Id.getText();
        String StudentId = problem_sid.getText();
        String Address = problem_address.getText();
        String Faculty = problem_faculty.getText();
        String Gender = problem_gender.getText();
        String Email = problem_email.getText();
        String Subject = problem_subject.getText();
        String Description = problem_desc.getText();

        if (Objects.equals(FullName, "") || Objects.equals(StudentId, "") || Objects.equals(Faculty, "") || Objects.equals(ProblemId, "")
                || Objects.equals(Address, "") || Objects.equals(Gender, "") || Objects.equals(Email, "") || Objects.equals(Subject, "") ||
                Objects.equals(Description, "") || Objects.equals(SCId, "")) {
            displayError(problem_msg, "Please fill every field");
        } else {
            if (FullName.matches(FULL_NAME_PATTERN)) {
                studentData newProblem = new studentData(SCId, FullName, ProblemId, StudentId, Address, Faculty, Gender, Email, Subject, Description);
                CSVWriterUtil.writeProblem(newProblem);
                adduserSucess(problem_msg, "Problem Submitted Successfully");
//                clearFields();
            } else {
                displayError(problem_msg, "INVALID FULL NAME");
            }
        }
    }




    private void clearFields() {
        Sports_fname.clear();
        Sports_faculty.clear();
        Sports_sid.clear();
        Sports_interested.clear();
    }

}

