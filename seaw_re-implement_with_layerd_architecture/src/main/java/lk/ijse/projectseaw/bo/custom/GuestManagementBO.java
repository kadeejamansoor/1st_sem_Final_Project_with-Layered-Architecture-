package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.GuestDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestManagementBO extends SuperBO {
    ArrayList<GuestDTO> getAllGuests() throws SQLException, ClassNotFoundException;
    boolean deleteGuest(String id) throws SQLException, ClassNotFoundException;
    boolean updateGuest(GuestDTO gDTO) throws SQLException, ClassNotFoundException;
    GuestDTO searchGuest(String guestId) throws SQLException, ClassNotFoundException;
    boolean existGuestByCode(String code) throws SQLException, ClassNotFoundException;
}
