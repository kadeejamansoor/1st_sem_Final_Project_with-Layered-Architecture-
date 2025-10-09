package lk.ijse.projectseaw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//inheritance
public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent =  FXMLLoader.load(getClass().getResource("/view/WelcomeForm1.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Hotel Sea viwe");
        stage.centerOnScreen();
        stage.show();



    }
}
