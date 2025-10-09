package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.BillingBO;
import lk.ijse.projectseaw.dto.PaymentDTO;
import lk.ijse.projectseaw.dto.tm.PaymentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillingFormController implements Initializable {

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colGuestId;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton reservationbtn;

    @FXML
    private JFXButton roombtn;

    @FXML
    private JFXButton settingbtn;

    @FXML
    private JFXButton stockbtn;

    @FXML
    private TableView<PaymentTm> tblBilling;

    BillingBO billingBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BILLING_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllPayments();
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guest_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("Payment_date"));
    }

    void loadAllPayments(){
        tblBilling.getItems().clear();

        try {
            ArrayList<PaymentDTO> allPayments = billingBO.getAllPayments();
            for (PaymentDTO p : allPayments) {
                tblBilling.getItems().add(new PaymentTm(p.getPayment_id(), p.getBooking_id(), p.getGuest_id(), p.getAmount(), p.getPayment_date()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnBillingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/BillingForm.fxml"));
        Stage window = (Stage) billingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Billing");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/BookingInformationForm.fxml"));
        Stage window = (Stage) bookingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Booking Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/DashBord.fxml"));
        Stage window = (Stage) dashboardbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Dashboard");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/EmployeeManagementForm.fxml"));
        Stage window = (Stage) employeebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Employee Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LogOut.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Logout page");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/ReservationForm.fxml"));
        Stage window = (Stage) reservationbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Reservation");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/RoomManagementForm.fxml"));
        Stage window = (Stage) roombtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Room Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/Settings1From.fxml"));
        Stage window = (Stage) settingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Settings");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/StockManagementForm.fxml"));
        Stage window = (Stage) stockbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Stock Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml"));
        Stage window = (Stage)reportbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Report");
        window.setMaximized(true);
        window.centerOnScreen();
    }


}
