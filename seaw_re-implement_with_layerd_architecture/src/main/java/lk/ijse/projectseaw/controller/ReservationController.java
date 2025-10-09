package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.ReservationBO;
import lk.ijse.projectseaw.dto.ReservationDTO;
import lk.ijse.projectseaw.dto.tm.ReservationTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    ReservationBO reservationBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.RESERVATION_BO);

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCheckIn;

    @FXML
    private TableColumn<?, ?> colCheckOut;

    @FXML
    private TableColumn<?, ?> colGuest;

    @FXML
    private TableColumn<?, ?> colGuestName;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colRoom;

    @FXML
    private TableColumn<?, ?> colStatus;

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
    private TableView<ReservationTm> tblReservation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllReservation();
    }

    private void setCellValueFactory() {
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("guest_name"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("check_in"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("check_out"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("noOf_guest"));
        tblReservation.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("reservation_status"));

        tblReservation.getColumns().get(7).setCellValueFactory(param -> {
            ImageView cancel = new ImageView("assets/img/table icon/cancel.png");
            ImageView booked = new ImageView("assets/img/table icon/confirm.png");
            HBox hbox = new HBox(20, cancel, booked);
            hbox.setAlignment(Pos.CENTER);

            cancel.setOnMouseClicked(event -> {
                // Handle cancel button click
                try {
                    boolean c = reservationBO.cancelReservation(param.getValue().getRoomId());

                    if (c) {
                        new Alert(Alert.AlertType.INFORMATION, "Reservation has been cancel successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Reservation has not been cancel successfully").show();
                    }

                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                loadAllReservation();
            });

            booked.setOnMouseClicked(event -> {
                // Handle booked button click
                try {
                    boolean b =reservationBO.fromReservedToBooking(param.getValue().getRoomId(), param.getValue().getReservation_id());

                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION, "Reservation has been booked successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Reservation has not been booked successfully").show();
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                loadAllReservation();
            });

            return new ReadOnlyObjectWrapper(hbox);
        });
    }

    void loadAllReservation(){
        tblReservation.getItems().clear();

        try {
            ArrayList<ReservationDTO> allReservations = reservationBO.getAllReservations();
            for (ReservationDTO r : allReservations) {
                tblReservation.getItems().add(new ReservationTm(r.getReservation_id(), r.getGuest_name(), r.getCheck_in(), r.getCheck_out(), r.getRoomId(), r.getNoOf_guest(), r.getReservation_status()));
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
