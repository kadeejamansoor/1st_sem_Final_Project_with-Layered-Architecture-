package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingInformationBO extends SuperBO {
    boolean saveBooking(GuestDTO guestDTO, BookingDTO bookingDTO, PaymentDTO paymentDTO, RoomStatusDTO roomStatus) throws SQLException, ClassNotFoundException;
    String generateNewGuestID() throws SQLException, ClassNotFoundException;
    String generateNewBookingID() throws SQLException, ClassNotFoundException;
    String generateNewPaymentID() throws SQLException, ClassNotFoundException;
    String generateNewReservationID() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> searchAvailableRooms() throws SQLException, ClassNotFoundException ;
    double getRoomRate(String selectRoom) throws SQLException, ClassNotFoundException;

    boolean saveReservation(GuestDTO gDTO, ReservationDTO rsDTO, PaymentDTO pDTO, RoomStatusDTO rDTO) throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getAvailableRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getBookedRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getReservedRooms() throws SQLException, ClassNotFoundException;
}
