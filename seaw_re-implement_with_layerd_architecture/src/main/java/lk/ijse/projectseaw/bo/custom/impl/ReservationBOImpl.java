package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.ReservationBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.*;
import lk.ijse.projectseaw.db.DBConnection;
import lk.ijse.projectseaw.dto.ReservationDTO;
import lk.ijse.projectseaw.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    ReservationDAO reservationDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    @Override
    public ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> all = reservationDAO.getAll();
        ArrayList<ReservationDTO> arrayList= new ArrayList<>();
        for (Reservation r : all) {
            arrayList.add(new ReservationDTO(r.getReservation_id(), r.getGuest_name(), r.getCheck_in(), r.getCheck_out(), r.getRoomId(), r.getNoOf_guest(), r.getReservation_status()));
        }
        return arrayList;
    }

    @Override
    public boolean cancelReservation(String id) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            RoomStatus roomStatus = new RoomStatus();
            roomStatus.setRoom_id(id);
            boolean updateRoom = roomStatusDAO.update(roomStatus);
            if (!updateRoom) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Reservation reservation = new Reservation();
            reservation.setRoomId(id);
            boolean updateReservation = reservationDAO.updateReservation(reservation);

            if (!updateReservation) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean fromReservedToBooking(String roomId, String reservationId) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            Reservation reservation = new Reservation();
            reservation.setRoomId(roomId);
            boolean updateReservation = reservationDAO.updateReservedToBooking(reservation);

            if (!updateReservation) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            RoomStatus roomStatus = new RoomStatus();
            roomStatus.setRoom_id(roomId);
            boolean updateRoom = roomStatusDAO.updateReservedToBooking(roomStatus);
            if (!updateRoom) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
