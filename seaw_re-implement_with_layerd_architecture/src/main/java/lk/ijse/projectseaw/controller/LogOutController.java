package lk.ijse.projectseaw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutController {

    @FXML
    private Button cancelbtn;

    @FXML
    private Button logoutbtn;

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/DashBord.fxml"));
        Stage window = (Stage)cancelbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Dashboard");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
// Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Load the LoginPageForm and create a new stage
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/WelcomeForm1.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Welcome Page");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();

        // Close the current stage
        currentStage.close();
    }

}
