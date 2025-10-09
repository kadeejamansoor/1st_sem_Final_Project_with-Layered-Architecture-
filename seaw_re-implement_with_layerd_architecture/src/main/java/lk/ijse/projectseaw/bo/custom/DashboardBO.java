package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DashboardBO extends SuperBO {
    ArrayList<RoomStatusDTO> getAvailableRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getBookedRooms() throws SQLException, ClassNotFoundException;
    ArrayList<RoomStatusDTO> getReservedRooms() throws SQLException, ClassNotFoundException;
    Map<String, Double> profitanalysis() throws SQLException, ClassNotFoundException;
    List<Double> calculateLast30DayProfits() throws SQLException, ClassNotFoundException;
}
