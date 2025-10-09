package lk.ijse.projectseaw.dao.custom;

import lk.ijse.projectseaw.dao.CrudDAO;
import lk.ijse.projectseaw.entity.Booking;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO<Booking,String> {
    Booking getLastBooking() throws SQLException, ClassNotFoundException;
    boolean updateReservedToBooking(Booking entity) throws SQLException, ClassNotFoundException;
}
