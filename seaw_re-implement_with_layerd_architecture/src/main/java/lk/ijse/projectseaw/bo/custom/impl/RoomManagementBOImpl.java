package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.RoomManagementBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManagementBOImpl implements RoomManagementBO {
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    @Override
    public ArrayList<RoomStatusDTO> getAllRoomstatus() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getAll();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus r : all) {
            arrayList.add(new RoomStatusDTO(r.getRoom_id(), r.getRoom_type(), r.getFloor_Number(), r.getCapacity(), r.getRate(), r.getRoom_status(), r.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomStatusDTO> getAvailableRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getAvailableRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomStatusDTO> getBookedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getBookedRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomStatusDTO> getReservedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getReservedRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }
}
