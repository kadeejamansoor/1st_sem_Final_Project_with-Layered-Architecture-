package lk.ijse.projectseaw.bo.custom;


import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingManagementBO extends SuperBO {
    ArrayList<RoomStatusDTO> searchBookingRooms() throws SQLException, ClassNotFoundException;
    BookingDTO searchBooking(String bookingId) throws SQLException, ClassNotFoundException;
    boolean updateBooking(BookingDTO bDTO) throws SQLException, ClassNotFoundException;
    boolean deleteBooking(String id) throws SQLException, ClassNotFoundException;
    ArrayList<BookingDTO> getAllBookings() throws SQLException, ClassNotFoundException;
    boolean existBookingByCode(String code) throws SQLException, ClassNotFoundException;
}
