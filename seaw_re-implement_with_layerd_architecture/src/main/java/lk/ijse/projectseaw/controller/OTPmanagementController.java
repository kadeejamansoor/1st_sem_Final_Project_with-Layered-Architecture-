package lk.ijse.projectseaw.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.OTPManagementBO;
import lk.ijse.projectseaw.bo.custom.impl.PaymentConfirmationBOImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OTPmanagementController implements Initializable {
    OTPManagementBO otpManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.OTPManagement_BO);

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtOtp;

    private static int otp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        otp = email();
    }

    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        if (!validateInput(otp)) {
            return;
        }
    }

    private boolean validateInput(int otp) throws IOException {
        String otp1 = String.valueOf(otp);

        String otp2 = "";
        if (!Pattern.matches("^\\d{6}$", txtOtp.getText())) {
            Platform.runLater(() -> printLabel("Please enter a 6 digit OTP"));
            return false;
        }else if(!otp1.equals(txtOtp.getText())){
            Platform.runLater(() -> printLabel("Please enter correct OTP"));
            return false;
        }else if(otp1.equals("")){
            Platform.runLater(() -> printLabel("Please enter a 6 digit OTP"));
            return false;
        }else{
            show();
            return true;
        }
    }

    private void show() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashBord.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }

    private void printLabel(String text) {
        lblError.setText(text);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> lblError.setText(""));
                    }
                },
                2000
        );
    }

    public int email(){
        int random = otpManagementBO.firstOtp();
        return random;
    }


}
