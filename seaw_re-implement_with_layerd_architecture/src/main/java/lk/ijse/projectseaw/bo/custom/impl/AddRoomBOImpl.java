package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.AddRoomBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.SQLException;

public class AddRoomBOImpl implements AddRoomBO {
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    @Override
    public boolean saveRoom(RoomStatusDTO sDTO) throws SQLException, ClassNotFoundException {
        return roomStatusDAO.save(new RoomStatus(sDTO.getRoom_id(), sDTO.getRoom_type(), sDTO.getFloor_Number(), sDTO.getCapacity(), sDTO.getRate(), sDTO.getRoom_status(), sDTO.getBooking_id()));
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomStatusDAO.delete(id);
    }

    @Override
    public boolean updateRoom(RoomStatusDTO sDTO) throws SQLException, ClassNotFoundException {
        return roomStatusDAO.updateRoomDetails(new RoomStatus(sDTO.getRoom_id(), sDTO.getRoom_type(), sDTO.getFloor_Number(), sDTO.getCapacity(), sDTO.getRate(), sDTO.getRoom_status(), sDTO.getBooking_id()));
    }

    @Override
    public RoomStatusDTO searchRoom(String roomId) throws SQLException, ClassNotFoundException {
        RoomStatus allrm = roomStatusDAO.search(roomId);
        return new RoomStatusDTO(allrm.getRoom_id(), allrm.getRoom_type(), allrm.getFloor_Number(), allrm.getCapacity(), allrm.getRate(), allrm.getRoom_status(), allrm.getBooking_id());
    }

    @Override
    public String generateNewRoomID() throws SQLException, ClassNotFoundException {
        return roomStatusDAO.generateNewID();
    }

    @Override
    public boolean existRoomByCode(String code) throws SQLException, ClassNotFoundException{
        return roomStatusDAO.exist(code);
    }
}
