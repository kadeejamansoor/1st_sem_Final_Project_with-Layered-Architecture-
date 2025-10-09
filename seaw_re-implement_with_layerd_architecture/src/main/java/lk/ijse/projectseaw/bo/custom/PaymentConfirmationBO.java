package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.GuestDTO;

import java.sql.SQLException;

public interface PaymentConfirmationBO extends SuperBO {
    GuestDTO getLastGuest() throws SQLException, ClassNotFoundException;
    BookingDTO getLastBooking() throws SQLException, ClassNotFoundException;
    double getRoomRate(String selectRoom) throws SQLException, ClassNotFoundException;
}
