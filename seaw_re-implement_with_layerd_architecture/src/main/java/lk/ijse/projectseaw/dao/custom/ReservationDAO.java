package lk.ijse.projectseaw.dao.custom;

import lk.ijse.projectseaw.dao.CrudDAO;
import lk.ijse.projectseaw.entity.Reservation;

import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    boolean updateReservation(Reservation dto) throws SQLException, ClassNotFoundException;
    boolean updateReservedToBooking(Reservation entity) throws SQLException, ClassNotFoundException;
}
