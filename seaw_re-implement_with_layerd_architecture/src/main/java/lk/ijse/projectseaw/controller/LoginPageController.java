package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblError1;

    @FXML
    private Label lblError2;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    private int loginAttempts = 5;
    private boolean isLockedOut = false;

    @FXML
    void txtPasswordOnKeyPressed(KeyEvent event) {
        validate(event);
    }

    @FXML
    void txtUsernameOnKeyPressed(KeyEvent event) {
        validate(event);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String password = TxtPassword.getText();

        if (TxtPassword.getText().isBlank() && txtUserName.getText().isBlank()) {
            lblError1.setText("Please provide User Name and Password .          ");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                lblError1.setText("");
            }));
            timeline.play();
            return;
        }

        if (txtUserName.getText().isBlank()) {
            lblError2.setText("Please provide User name on User name Field .    ");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                lblError2.setText("");
            }));
            timeline.play();
            return;
        }

        if (TxtPassword.getText().isBlank()) {
            lblError1.setText("Please provide Password on Password Field .      ");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                lblError1.setText("");
            }));
            timeline.play();
            return;
        }

        if (isCredentialsValid(userName, password)) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OTPmanagement.fxml"));

            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage)root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("OTP Confirmation");
            stage.centerOnScreen();

        } else {
            lblError1.setText("Incorrect username or password! ");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                lblError1.setText("");
            }));
            timeline.play();
            TxtPassword.clear();
            txtUserName.clear();
        }
    }

    private boolean isCredentialsValid(String userName, String password) {
        if (userName.equals("user") && password.equals("pass")) {
            return true;
        } else {
            return false;
        }
    }

    private void validate(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            if (TxtPassword.getText().isBlank() && txtUserName.getText().isBlank()) {
                lblError1.setText("Please provide User Name and Password .          ");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    lblError1.setText("");
                }));
                timeline.play();
                return;
            }

            if (txtUserName.getText().isBlank()) {
                lblError2.setText("Please provide User name on User name Field .    ");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    lblError2.setText("");
                }));
                timeline.play();
                return;
            }

            if (TxtPassword.getText().isBlank()) {
                lblError1.setText("Please provide Password on Password Field .      ");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    lblError1.setText("");
                }));
                timeline.play();
                return;
            }
            btnLogin.fire();
        }
    }

    @FXML
    public void textFields_Key_Released(KeyEvent keyEvent) {
        System.out.println("press");
    }

}
