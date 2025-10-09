package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomManagementBO extends SuperBO {
    ArrayList<RoomStatusDTO> getAllRoomstatus() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getAvailableRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getBookedRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getReservedRooms() throws SQLException, ClassNotFoundException;
}
