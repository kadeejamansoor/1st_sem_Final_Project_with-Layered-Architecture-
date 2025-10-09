package lk.ijse.projectseaw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class WelcomeFormController implements Initializable {

    @FXML
    private Text txtTime;

    private boolean bool = true;

    public String time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread() {
            public void run() {
                while(bool) {
                    try {
                        Thread.sleep(1000);
                    }catch(Exception e){}
                    Date currentDate = new Date();
                    SimpleDateFormat clockFormat = new SimpleDateFormat("h:mm a");
                    //System.out.println(clockFormat.format(currentDate));
                    time = (clockFormat.format(currentDate));
                    txtTime.setText(time);
                }
            }
        }.start();

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
// Get the current stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load the LoginPageForm and create a new stage
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginPage3.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();

        // Close the current stage
        currentStage.close();
    }


}
