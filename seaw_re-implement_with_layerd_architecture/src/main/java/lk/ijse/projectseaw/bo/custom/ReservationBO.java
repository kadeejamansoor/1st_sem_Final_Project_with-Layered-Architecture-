package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException;
    boolean cancelReservation(String id) throws SQLException, ClassNotFoundException;
    boolean fromReservedToBooking(String roomId, String reservationId) throws SQLException, ClassNotFoundException;
}
