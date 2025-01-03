package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Uses {

    public static Stage getCurrentStage(ActionEvent e) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }

    public static void changeScene(ActionEvent event, String sceneName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Uses.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = getCurrentStage(event);
        stage.setTitle(title);
        stage.setScene(scene);

        // Center the stage
        centerStage(stage);
    }

    public static void changeScene(ActionEvent event, String sceneName, String title, studentData user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Uses.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the controller and pass the user data
        Object controller = fxmlLoader.getController();
        if (controller instanceof StudentDashboardController) {
            ((StudentDashboardController) controller).displayUserDetails(user);
        }

        Stage stage = getCurrentStage(event);
        stage.setTitle(title);
        stage.setScene(scene);

        // Center the stage
        centerStage(stage);
        stage.show();
    }

    public static void centerStage(Stage stage) {
        // Get screen bounds
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        // Get the size of the stage
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        // Calculate the position for centering
        double x = (bounds.getWidth() - stageWidth) / 2;
        double y = (bounds.getHeight() - stageHeight) / 2;

        // Set the position of the stage
        stage.setX(x);
        stage.setY(y);
    }

    public static void displayError(Label label, String errorMessage) {
        label.setText(errorMessage);
        label.setVisible(true);
        label.setStyle("-fx-text-fill: red");
    }

    public static void adduserSucess(Label label, String errorMessage) {
        label.setText(errorMessage);
        label.setVisible(true);
        label.setStyle("-fx-text-fill: green");
    }

    public static void clearError(Label label) {
        label.setText("");
        label.setVisible(false);
    }
}
