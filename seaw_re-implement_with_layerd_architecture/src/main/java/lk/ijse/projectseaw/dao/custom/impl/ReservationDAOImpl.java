package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.ReservationDAO;
import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Reservation (reservationId, guestName, checkIn, checkOut, room_id, noOfGuest, reservation_status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                entity.getReservation_id(), entity.getGuest_name(), entity.getCheck_in(), entity.getCheck_out(), entity.getRoomId(), entity.getNoOf_guest(), entity.getReservation_status());
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> allReservation = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Reservation");
        while (rst.next()) {
            LocalDate startDate = rst.getDate(3).toLocalDate();
            LocalDate endDate = rst.getDate(4).toLocalDate();
            allReservation.add(new Reservation(rst.getString(1), rst.getString(2), startDate, endDate,
                    rst.getString(5), rst.getInt(6), rst.getString(7)));
        }
        return allReservation;
    }

    @Override
    public boolean update(Reservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT reservationId FROM Reservation ORDER BY reservationId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("reservationId");
            int newCustomerId = Integer.parseInt(id.replace("RV00-", "")) + 1;
            return String.format("RV00-%03d", newCustomerId);
        } else {
            return "RV00-001";
        }
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateReservation(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Reservation SET reservation_status = 'cancel' WHERE room_id = ?",
                entity.getRoomId());
    }

    @Override
    public boolean updateReservedToBooking(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Reservation SET reservation_status = 'booked' WHERE room_id = ?",
                entity.getRoomId());
    }
}
