package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.GuestManagementBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.GuestDAO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.GuestDTO;
import lk.ijse.projectseaw.entity.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestManagementBOImpl implements GuestManagementBO {
    GuestDAO guestDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.GUEST);
    @Override
    public ArrayList<GuestDTO> getAllGuests() throws SQLException, ClassNotFoundException {
        ArrayList<Guest> all = guestDAO.getAll();
        ArrayList<GuestDTO> arrayList= new ArrayList<>();
        for (Guest g : all) {
            arrayList.add(new GuestDTO(g.getGuestId(), g.getFullname(), g.getAddress(), g.getCity(), g.getPostalcode(), g.getCountry(), g.getEmail(), g.getAge()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteGuest(String id) throws SQLException, ClassNotFoundException {
        return guestDAO.delete(id);
    }

    @Override
    public boolean updateGuest(GuestDTO g) throws SQLException, ClassNotFoundException {
        return guestDAO.update(new Guest(g.getGuestId(), g.getFullname(), g.getAddress(), g.getCity(), g.getPostalcode(), g.getCountry(), g.getEmail(), g.getAge()));
    }

    @Override
    public GuestDTO searchGuest(String guestId) throws SQLException, ClassNotFoundException {
        Guest g = guestDAO.search(guestId);
        return new GuestDTO(g.getGuestId(), g.getFullname(), g.getAddress(), g.getCity(), g.getPostalcode(), g.getCountry(), g.getEmail(), g.getAge());
    }

    @Override
    public boolean existGuestByCode(String code) throws SQLException, ClassNotFoundException{
        return guestDAO.exist(code);
    }
}
