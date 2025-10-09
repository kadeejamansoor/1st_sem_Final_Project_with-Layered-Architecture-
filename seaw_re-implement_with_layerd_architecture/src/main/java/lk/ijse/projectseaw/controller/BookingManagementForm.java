package lk.ijse.projectseaw.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.BookingManagementBO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.dto.tm.BookingTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookingManagementForm implements Initializable {
    BookingManagementBO bookingManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BOOKINGMANAGEMENT_BO);

    @FXML
    private ComboBox<String> cmbGuest;

    @FXML
    private ComboBox<String> cmbSelectRoom;

    @FXML
    private TableColumn<?, ?> colBookingid;

    @FXML
    private TableColumn<?, ?> colChecking;

    @FXML
    private TableColumn<?, ?> colCheckout;

    @FXML
    private TableColumn<?, ?> colGuest;

    @FXML
    private TableColumn<?, ?> colGuestId;

    @FXML
    private TableColumn<?, ?> colSelectroom;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private DatePicker dtpkCheckin;

    @FXML
    private DatePicker dtpkCheckout;

    @FXML
    private TableView<BookingTm> tblBooking;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtGuestId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> noOfGuestList = FXCollections.observableArrayList();
        noOfGuestList.add("1");
        noOfGuestList.add("2");
        noOfGuestList.add("3");
        noOfGuestList.add("4");
        noOfGuestList.add("5");
        cmbGuest.setItems(noOfGuestList);

        updateBookingRoomLIst();
        setCellValueFactory();
        loadAllBookings();
    }

    private void setCellValueFactory() {
        colBookingid.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colChecking.setCellValueFactory(new PropertyValueFactory<>("BookingCheckIn"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("BookingCheckOut"));
        colGuest.setCellValueFactory(new PropertyValueFactory<>("BookingGuests"));
        colSelectroom.setCellValueFactory(new PropertyValueFactory<>("BookingSelectRoom"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("BookingGuestId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("BookingStatus"));
    }

    void loadAllBookings(){
        tblBooking.getItems().clear();

        try {
            ArrayList<BookingDTO> allBookings = bookingManagementBO.getAllBookings();
            for (BookingDTO b : allBookings) {
                tblBooking.getItems().add(new BookingTm(b.getBookingId(), b.getBookingCheckIn(), b.getBookingCheckOut(), b.getBookingGuests(), b.getBookingSelectRoom(), b.getBookingGuestId(), b.getBookingStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateBookingRoomLIst() {
        try {
            List<RoomStatusDTO> list = bookingManagementBO.searchBookingRooms();

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

    @FXML
    void btnClearOnAction(ActionEvent event) {
        cmbGuest.getSelectionModel().clearSelection();
        cmbSelectRoom.getSelectionModel().clearSelection();
        dtpkCheckin.setValue(null);
        dtpkCheckout.setValue(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String bookingId = txtBookingId.getText();

        boolean b = bookingManagementBO.deleteBooking(bookingId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            //loadAllBookings();
            new Alert(Alert.AlertType.CONFIRMATION, "booking has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String bookingId = txtBookingId.getText();
        Date checkin = Date.valueOf(dtpkCheckin.getValue());
        Date checkout = Date.valueOf(dtpkCheckout.getValue());
        int noOfGuests = Integer.parseInt((String) cmbGuest.getValue());
        String selectedRoom = (String) cmbSelectRoom.getValue();
        String guestId = txtGuestId.getText();

        boolean b = bookingManagementBO.updateBooking(new BookingDTO(bookingId, checkin, checkout, noOfGuests, selectedRoom, guestId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "booking has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String bookingId = txtBookingId.getText();

        if (existBooking(bookingId)) {

            BookingDTO b = bookingManagementBO.searchBooking(bookingId);
            cmbSelectRoom.setValue(b.getBookingSelectRoom());

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date checkIn = b.getBookingCheckIn();
                Date checkOut = b.getBookingCheckOut();

                if (checkIn != null && checkOut != null) {
                    LocalDate checkInDate = LocalDate.parse(dateFormatter.format(checkIn), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    LocalDate checkOutDate = LocalDate.parse(dateFormatter.format(checkOut), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    dtpkCheckin.setValue(checkInDate);
                    dtpkCheckout.setValue(checkOutDate);
                }
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }

            cmbGuest.setValue(String.valueOf(b.getBookingGuests()));
            txtGuestId.setText(b.getBookingGuestId());

        }else {
            new Alert(Alert.AlertType.ERROR, "There is no such booking associated with the id").show();
        }

    }

    boolean existBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingManagementBO.existBookingByCode(id);
    }




    @FXML
    void btnGuestManagementOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/GuestManagementForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Guest Management");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
    }


}
