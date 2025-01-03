package com.example.test;

import org.mindrot.jbcrypt.BCrypt;

import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.test.CSVUtils.readCSV;
import static com.example.test.Uses.changeScene;
import static  com.example.test.Uses.centerStage;


public class loginController {

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    @FXML
    private Label loginError;
    private StudentDashboardController dashboardController;
    private Map<String, studentData> userMap = new HashMap<>();

    @FXML
    public void initialize() throws IOException, CsvException {
        String[] csvFiles = {
                "StudentData.csv", // 12 columns
                "Teacher.csv", // 12 columns
                "Admin.csv", // 11 columns
                "Librarian.csv", // 11 columns
                "AdmissionOfficer.csv"  // 11 columns
        };

        List<studentData> users = readCSV(csvFiles);
        users.forEach(user -> userMap.put(user.getUsername(), user));
    }

    @FXML
    public void close() {
        System.exit(0);
    }



    // Login and conparing password with the hash generated
    @FXML
    public void login(ActionEvent event) throws IOException {
        String loginUsername = username.getText();
        String loginPassword = password.getText();

        if (loginUsername == null || loginUsername.length() < 4) {
            displayError("Username should be at least 4 characters long");
            return;
        }

        studentData user = userMap.get(loginUsername);
        if (user == null || !PasswordUtils.checkPassword(loginPassword, user.getPassword())) {
            displayError("Invalid username or password");
            return;
        }

        clearError();

        // Determine user role and switch scene accordingly
        switch (user.getDesignation()) {
            case "Student":
                changeScene(event, "StudentDashboard.fxml", "Student Dashboard", user);
                break;

            case "Admin":
            case "Teacher":
            case "Librarian":
            case "AdmissionOfficer":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                Parent root = loader.load();
                AdminDashboardController controller = loader.getController();
                controller.setupDashboard(user);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Admin Dashboard");
                stage.show();
                break;
            default:
                displayError("Unknown designation");
        }
    }


    private void displayError(String message) {
        loginError.setText(message);
        loginError.setVisible(true);
    }

    private void clearError() {
        loginError.setText("");
        loginError.setVisible(false);
    }
}
