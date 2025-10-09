package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;

import java.sql.SQLException;

public interface AddRoomBO extends SuperBO {
    boolean saveRoom(RoomStatusDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;
    boolean updateRoom(RoomStatusDTO sDTO) throws SQLException, ClassNotFoundException;
    RoomStatusDTO searchRoom(String roomId) throws SQLException, ClassNotFoundException;
    String generateNewRoomID() throws SQLException, ClassNotFoundException;
    boolean existRoomByCode(String code) throws SQLException, ClassNotFoundException;
}
