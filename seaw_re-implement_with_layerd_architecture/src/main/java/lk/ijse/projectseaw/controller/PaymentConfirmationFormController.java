package lk.ijse.projectseaw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.impl.PaymentConfirmationBOImpl;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.GuestDTO;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentConfirmationFormController implements Initializable {

    PaymentConfirmationBOImpl paymentConfirmationBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PAYMENTCONFIRMATION_BO);

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblBookingId;

    @FXML
    private Label lblCheckIn;

    @FXML
    private Label lblCheckOut;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblGuesdId;

    @FXML
    private Label lblPostalCode;

    @FXML
    private Label lblSelectRoom;

    @FXML
    private Label lblState;

    @FXML
    private Label lblTotal;

    public String selectRoom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGuestData();
        setBookingData();
        try {
            setTotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {

    }

    public void setGuestData()  {
        try {
            GuestDTO guest = paymentConfirmationBO.getLastGuest();

            lblGuesdId.setText(guest.getGuestId());
            lblFullName.setText(guest.getFullname());
            lblAddress.setText(guest.getAddress());
            lblCity.setText(guest.getCity());
            lblPostalCode.setText(guest.getPostalcode());
            lblCountry.setText(guest.getCountry());
            lblState.setText(guest.getEmail());
            lblAge.setText(String.valueOf(guest.getAge()));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setBookingData(){
        try {
            BookingDTO booking = paymentConfirmationBO.getLastBooking();

            lblBookingId.setText(booking.getBookingId());
            lblCheckIn.setText(String.valueOf(booking.getBookingCheckIn()));
            lblCheckOut.setText(String.valueOf(booking.getBookingCheckOut()));
            lblSelectRoom.setText(booking.getBookingSelectRoom());
            selectRoom = booking.getBookingSelectRoom();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setTotal() throws SQLException, ClassNotFoundException {
        double roomRate = getRoomRate(selectRoom);
        lblTotal.setText(String.valueOf(roomRate));
    }

    public double getRoomRate(String selectedRoom) throws SQLException, ClassNotFoundException {
        return paymentConfirmationBO.getRoomRate(selectedRoom);
    }


}
