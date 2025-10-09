package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.dao.custom.BookingDAO;
import lk.ijse.projectseaw.entity.Booking;
import lk.ijse.projectseaw.entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean save(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Booking(booking_id, check_in, check_out, no_of_guest, select_room, guest_id, BookingStatus) VALUES (?, ?, ?, ?, ?, ?, ?)",
                entity.getBookingId(), entity.getBookingCheckIn(), entity.getBookingCheckOut(), entity.getBookingGuests(), entity.getBookingSelectRoom(), entity.getBookingGuestId(), entity.getBookingStatus());
    }

    @Override
    public ArrayList<Booking> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Booking> allSalaries = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Booking ORDER BY booking_id DESC");
        while (rst.next()) {
            allSalaries.add(new Booking(rst.getString(1), rst.getDate(2), rst.getDate(3), rst.getInt(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return allSalaries;
    }

    @Override
    public boolean update(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Booking SET check_in = ?, check_out = ?, no_of_guest = ?, select_room = ?, guest_id = ? WHERE booking_id = ?",
                entity.getBookingCheckIn(), entity.getBookingCheckOut(), entity.getBookingGuests(), entity.getBookingSelectRoom(), entity.getBookingGuestId(), entity.getBookingId() );
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT booking_id FROM Booking WHERE booking_id=?", s);
        return rst.next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Booking WHERE booking_id = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT booking_id FROM Booking ORDER BY booking_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("booking_id");
            int newCustomerId = Integer.parseInt(id.replace("B00-", "")) + 1;
            return String.format("B00-%03d", newCustomerId);
        } else {
            return "B00-001";
        }
    }

    @Override
    public Booking search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Booking WHERE booking_id = ?", s);
        Booking booking = new Booking();

        if (rst.next()) {
            booking.setBookingId(rst.getString("booking_id"));
            booking.setBookingCheckIn(rst.getDate("check_in"));
            booking.setBookingCheckOut(rst.getDate("check_out"));
            booking.setBookingGuests(rst.getInt("no_of_guest"));
            booking.setBookingSelectRoom(rst.getString("select_room"));
            booking.setBookingGuestId(rst.getString("guest_id"));
        }

        return booking;
    }


    @Override
    public Booking getLastBooking() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Booking ORDER BY booking_id DESC LIMIT 1");
        Booking booking = new Booking();

        if (rst.next()) {
            booking.setBookingId(rst.getString("booking_id"));
            booking.setBookingCheckIn(rst.getDate("check_in"));
            booking.setBookingCheckOut(rst.getDate("check_out"));
            booking.setBookingGuests(rst.getInt("no_of_guest"));
            booking.setBookingSelectRoom(rst.getString("select_room"));
            booking.setBookingGuestId(rst.getString("guest_id"));
            booking.setBookingStatus(rst.getString("BookingStatus"));


        }

        return booking;
    }

    @Override
    public boolean updateReservedToBooking(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Booking SET BookingStatus = 'booked' WHERE select_room = ?",
                entity.getBookingSelectRoom());
    }
}
