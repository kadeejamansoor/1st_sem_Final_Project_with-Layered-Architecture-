package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.BookingManagementBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.BookingDAO;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.entity.Booking;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingManagementBOImpl implements BookingManagementBO {
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    BookingDAO bookingDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    @Override
    public ArrayList<RoomStatusDTO> searchBookingRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.searchBookingRooms();
        ArrayList<RoomStatusDTO> arrayList = new ArrayList<>();
        for (RoomStatus i : all) {
            arrayList.add(new RoomStatusDTO( i.getRoom_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<BookingDTO> getAllBookings() throws SQLException, ClassNotFoundException {
        ArrayList<Booking> all = bookingDAO.getAll();
        ArrayList<BookingDTO> arrayList= new ArrayList<>();
        for (Booking bDTO : all) {
            arrayList.add(new BookingDTO(bDTO.getBookingId(), bDTO.getBookingCheckIn(), bDTO.getBookingCheckOut(), bDTO.getBookingGuests(), bDTO.getBookingSelectRoom(), bDTO.getBookingGuestId(), bDTO.getBookingStatus()));
        }
        return arrayList;
    }

    @Override
    public BookingDTO searchBooking(String bookingId) throws SQLException, ClassNotFoundException {
        Booking bDTO = bookingDAO.search(bookingId);
        return new BookingDTO(bDTO.getBookingId(), bDTO.getBookingCheckIn(), bDTO.getBookingCheckOut(), bDTO.getBookingGuests(), bDTO.getBookingSelectRoom(), bDTO.getBookingGuestId(), bDTO.getBookingStatus());

    }

    @Override
    public boolean updateBooking(BookingDTO bDTO) throws SQLException, ClassNotFoundException {
        return bookingDAO.update(new Booking(bDTO.getBookingId(), bDTO.getBookingCheckIn(), bDTO.getBookingCheckOut(), bDTO.getBookingGuests(), bDTO.getBookingSelectRoom(), bDTO.getBookingGuestId()));
    }

    @Override
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.delete(id);
    }

    @Override
    public boolean existBookingByCode(String code) throws SQLException, ClassNotFoundException{
        return bookingDAO.exist(code);
    }
}
