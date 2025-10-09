package lk.ijse.projectseaw.dao.custom;

import lk.ijse.projectseaw.dao.CrudDAO;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomStatusDAO extends CrudDAO<RoomStatus,String> {
    boolean updateRoomStatus(RoomStatus entity) throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatus> searchAvailableRooms() throws SQLException, ClassNotFoundException;
    double getRate(String selectRoom) throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatus> getAvailableRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatus> getBookedRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatus> getReservedRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatus> searchBookingRooms() throws SQLException, ClassNotFoundException;
    boolean updateReservedToBooking(RoomStatus entity) throws SQLException, ClassNotFoundException;
    boolean updateRoomDetails(RoomStatus entity) throws SQLException, ClassNotFoundException;
}
