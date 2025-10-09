package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.BookingInformationBO;
import lk.ijse.projectseaw.dto.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

public class BookingInformationFormController implements Initializable {

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private ComboBox<String> cmbGuest;

    @FXML
    private ComboBox<String> cmbSelectRoom;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private DatePicker dtpkCheckin;

    @FXML
    private DatePicker dtpkCheckout;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private Text lblBookedroom;

    @FXML
    private Text lblavailableroom;

    @FXML
    private Text lblreservedroom;

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
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullname;

    @FXML
    private TextField txtPostalcode;

    BookingInformationBO bookingInformationBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BOOKINGINFORMATION_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> noOfGuestList = FXCollections.observableArrayList();
        noOfGuestList.add("1");
        noOfGuestList.add("2");
        noOfGuestList.add("3");
        noOfGuestList.add("4");
        noOfGuestList.add("5");
        cmbGuest.setItems(noOfGuestList);

        updateAvailableRoomLIst();
        roomReservationStatistics();
    }

    public void roomReservationStatistics() {

        try {
            ArrayList<RoomStatusDTO> availableRooms = bookingInformationBO.getAvailableRooms();
            lblavailableroom.setText(String.valueOf(availableRooms.size()));

            ArrayList<RoomStatusDTO> bookedRooms = bookingInformationBO.getBookedRooms();
            lblBookedroom.setText(String.valueOf(bookedRooms.size()));

            ArrayList<RoomStatusDTO> reservedRooms = bookingInformationBO.getReservedRooms();
            lblreservedroom.setText(String.valueOf(reservedRooms.size()));

        }catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String guestgeneId = generateNewId(IDTypes.Guest_ID);
        String fullname = txtFullname.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String postalcode = txtPostalcode.getText();
        String country = txtCountry.getText();
        String customerEmail = txtEmail.getText();
        String ageText = txtAge.getText();

        String bookingId = generateNewId(IDTypes.BOOKING_ID);
        Date checkin = Date.valueOf(dtpkCheckin.getValue());
        Date checkout = Date.valueOf(dtpkCheckout.getValue());
        int noOfGuests = Integer.parseInt((String) cmbGuest.getValue());
        String selectedRoom = (String) cmbSelectRoom.getValue();
        String GetLastGuestId = guestgeneId;
        int age;

        String paymentId = generateNewId(IDTypes.PAYMENT_ID);
        double roomRate = getRoomRate(selectedRoom);
        LocalDate paymentDate = LocalDate.now();
        String status = "Booked";

        if (ageText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return; // Exit the method or return false
        } else {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
                return; // Exit the method or return false
            }
        }

        if(fullname.isEmpty() || address.isEmpty() || city.isEmpty() || postalcode.isEmpty() || country.isEmpty() || customerEmail.isEmpty()  ||
                cmbGuest.getValue() == null || cmbGuest.getValue().toString().isEmpty() || cmbSelectRoom.getValue() == null ||
                cmbSelectRoom.getValue().toString().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!fullname.matches("^[a-zA-Z\\s]+$")) {      //Valid: "John Doe", "Mary Ann" , Invalid: "John123", "Mary-Ann"
            new Alert(Alert.AlertType.WARNING, "Invalid name.").show();
            txtFullname.requestFocus();
            return;
        } else if (!address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid address format.").show();
            txtAddress.requestFocus();
            return;
        } else if (!city.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid city name.").show();
            txtCity.requestFocus();
            return;
        } else if (!postalcode.matches("^[0-9]{1,10}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid postal code.").show();
            txtPostalcode.requestFocus();
            return;
        } else if (!country.matches("^[a-zA-Z\\s]{1,15}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid country name.").show();
            txtCountry.requestFocus();
            return;
        } else if (!customerEmail.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid email format.").show();
            txtEmail.requestFocus();
            return;
        } else if (!ageText.matches("^(1[8-9]|[2-9][0-9])$") || (age = Integer.parseInt(ageText)) < 18 || age > 99) {
            new Alert(Alert.AlertType.WARNING, "Invalid age. Must be a number between 18 and 99.").show();
            txtAge.requestFocus();
            return;
        }

        RoomStatusDTO roomStatus = new RoomStatusDTO();
        roomStatus.setRoom_id(selectedRoom);
        roomStatus.setRoom_status("booked");
        roomStatus.setBooking_id(bookingId);

        boolean isBookingSaved = bookingInformationBO.saveBooking(new GuestDTO(guestgeneId, fullname, address, city, postalcode, country, customerEmail, age),
                new BookingDTO(bookingId, checkin, checkout, noOfGuests, selectedRoom, GetLastGuestId, status),
                new PaymentDTO(paymentId, bookingId, guestgeneId, roomRate, paymentDate),
                roomStatus);

        if (isBookingSaved) {
            updateAvailableRoomLIst();
            clearFields();
            roomReservationStatistics();
            new Alert(Alert.AlertType.INFORMATION, "Booking has been saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Booking has not been saved successfully").show();
        }

    }

    public enum IDTypes {
        Guest_ID , BOOKING_ID ,PAYMENT_ID, RESERVATION_ID,
    }

    private String generateNewId(IDTypes idTypes) {
        try {
            switch (idTypes) {
                case Guest_ID :
                    return bookingInformationBO.generateNewGuestID();
                case BOOKING_ID :
                    return bookingInformationBO.generateNewBookingID();
                case PAYMENT_ID :
                    return bookingInformationBO.generateNewPaymentID();
                case RESERVATION_ID:
                    return bookingInformationBO.generateNewReservationID();
            }
            return bookingInformationBO.generateNewGuestID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateAvailableRoomLIst() {
        try {
            List<RoomStatusDTO> list = bookingInformationBO.searchAvailableRooms();
            /*for (RoomStatusDTO room : list) {
                System.out.println(room.getRoom_id());
            }*/

            ObservableList<String> vList = FXCollections.observableArrayList();
            for (RoomStatusDTO room : list) {
                String name = room.getRoom_id();
                vList.add(name);
            }
            cmbSelectRoom.getItems().clear();
            cmbSelectRoom.setItems(vList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getRoomRate(String selectedRoom) throws SQLException, ClassNotFoundException {
        return bookingInformationBO.getRoomRate(selectedRoom);
    }

    public void clearFields() {
        txtFullname.clear();
        txtAddress.clear();
        txtCity.clear();
        txtPostalcode.clear();
        txtCountry.clear();
        txtEmail.clear();
        txtAge.clear();
        cmbGuest.getSelectionModel().clearSelection();
        cmbSelectRoom.getSelectionModel().clearSelection();
        dtpkCheckin.setValue(null);
        dtpkCheckout.setValue(null);
    }

    @FXML
    void btnReservedOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String guestgeneId = generateNewId(IDTypes.Guest_ID);
        String fullname = txtFullname.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String postalcode = txtPostalcode.getText();
        String country = txtCountry.getText();
        String customerEmail = txtEmail.getText();
        String ageText = txtAge.getText();

        String bookingId = generateNewId(IDTypes.BOOKING_ID);
        LocalDate checkin = Date.valueOf(dtpkCheckin.getValue()).toLocalDate();
        LocalDate checkout = Date.valueOf(dtpkCheckout.getValue()).toLocalDate();
        int noOfGuests = Integer.parseInt((String) cmbGuest.getValue());
        String selectedRoom = (String) cmbSelectRoom.getValue();
        String GetLastGuestId = guestgeneId;
        int age;

        String paymentId = generateNewId(IDTypes.PAYMENT_ID);
        double roomRate = getRoomRate(selectedRoom);
        LocalDate paymentDate = LocalDate.now();
        String reservationId = generateNewId(IDTypes.RESERVATION_ID);

        if (ageText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        } else {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
                return;
            }
        }

        if(fullname.isEmpty() || address.isEmpty() || city.isEmpty() || postalcode.isEmpty() || country.isEmpty() || customerEmail.isEmpty()  ||
                cmbGuest.getValue() == null || cmbGuest.getValue().toString().isEmpty() || cmbSelectRoom.getValue() == null ||
                cmbSelectRoom.getValue().toString().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!fullname.matches("^[a-zA-Z\\s]+$")) {      //Valid: "John Doe", "Mary Ann" , Invalid: "John123", "Mary-Ann"
            new Alert(Alert.AlertType.WARNING, "Invalid name.").show();
            txtFullname.requestFocus();
            return;
        } else if (!address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid address format.").show();
            txtAddress.requestFocus();
            return;
        } else if (!city.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid city name.").show();
            txtCity.requestFocus();
            return;
        } else if (!postalcode.matches("^[0-9]{1,10}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid postal code.").show();
            txtPostalcode.requestFocus();
            return;
        } else if (!country.matches("^[a-zA-Z\\s]{1,15}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid country name.").show();
            txtCountry.requestFocus();
            return;
        } else if (!customerEmail.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid email format.").show();
            txtEmail.requestFocus();
            return;
        } else if (!ageText.matches("^(1[8-9]|[2-9][0-9])$") || (age = Integer.parseInt(ageText)) < 18 || age > 99) {
            new Alert(Alert.AlertType.WARNING, "Invalid age. Must be a number between 18 and 99.").show();
            txtAge.requestFocus();
            return;
        }

        RoomStatusDTO roomStatus = new RoomStatusDTO();
        roomStatus.setRoom_id(selectedRoom);
        roomStatus.setRoom_status("reserved");
        roomStatus.setBooking_id(bookingId);

        boolean isBookingSaved = bookingInformationBO.saveReservation(new GuestDTO(guestgeneId, fullname, address, city, postalcode, country, customerEmail, age),
                new ReservationDTO(reservationId, fullname, checkin, checkout, selectedRoom, noOfGuests, "reserved"),
                new PaymentDTO(paymentId, bookingId, guestgeneId, roomRate, paymentDate),
                roomStatus);

        if (isBookingSaved) {
            updateAvailableRoomLIst();
            clearFields();
            roomReservationStatistics();
            new Alert(Alert.AlertType.INFORMATION, "Reservation has been saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Reservation has not been saved successfully").show();
        }
    }

    @FXML
    void btnPayOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/paymentConfirmationForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("payment Confirmation");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
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
    void btnBookingManageOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/BookingManagementForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Booking Management");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
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
