package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.PaymentConfirmationBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.BookingDAO;
import lk.ijse.projectseaw.dao.custom.GuestDAO;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.dto.BookingDTO;
import lk.ijse.projectseaw.dto.GuestDTO;
import lk.ijse.projectseaw.entity.Booking;
import lk.ijse.projectseaw.entity.Guest;

import java.sql.SQLException;

public class PaymentConfirmationBOImpl implements PaymentConfirmationBO {
    GuestDAO guestDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.GUEST);
    BookingDAO bookingDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    RoomStatusDAO roomStatusDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    @Override
    public GuestDTO getLastGuest() throws SQLException, ClassNotFoundException {
        Guest guestdetails = guestDAO.getLastGuest();
        return new GuestDTO(guestdetails.getGuestId(), guestdetails.getFullname(), guestdetails.getAddress(), guestdetails.getCity(), guestdetails.getPostalcode(), guestdetails.getCountry(), guestdetails.getEmail(),
                guestdetails.getAge());
    }

    @Override
    public BookingDTO getLastBooking() throws SQLException, ClassNotFoundException {
        Booking bdetails = bookingDAO.getLastBooking();
        return new BookingDTO(bdetails.getBookingId(), bdetails.getBookingCheckIn(), bdetails.getBookingCheckOut(), bdetails.getBookingGuests(), bdetails.getBookingSelectRoom(), bdetails.getBookingGuestId(),bdetails.getBookingStatus());
    }

    @Override
    public double getRoomRate(String selectRoom) throws SQLException, ClassNotFoundException {
        return roomStatusDAO.getRate(selectRoom);
    }
}
