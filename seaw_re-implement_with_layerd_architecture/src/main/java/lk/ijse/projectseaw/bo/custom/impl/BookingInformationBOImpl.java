package lk.ijse.projectseaw.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.projectseaw.bo.custom.BookingInformationBO;
import lk.ijse.projectseaw.dao.*;
import lk.ijse.projectseaw.dao.custom.*;
import lk.ijse.projectseaw.db.DBConnection;
import lk.ijse.projectseaw.dto.*;
import lk.ijse.projectseaw.entity.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class BookingInformationBOImpl implements BookingInformationBO {
    GuestDAO guestDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.GUEST);
    BookingDAO bookingDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    PaymentDAO paymentDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    ReservationDAO reservationDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public boolean saveBooking(GuestDTO gDTO, BookingDTO bDTO, PaymentDTO pDTO, RoomStatusDTO rDTO) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            /*if order id already exist*/
            if (bookingDAO.exist(bDTO.getBookingId())) {
                return false;
            }

            connection.setAutoCommit(false);

            Guest guest = new Guest(gDTO.getGuestId(), gDTO.getFullname(), gDTO.getAddress(), gDTO.getCity(), gDTO.getPostalcode(), gDTO.getCountry(), gDTO.getEmail(), gDTO.getAge());
            boolean guestAdded = guestDAO.save(guest);
            if (!guestAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Booking booking = new Booking(bDTO.getBookingId(), bDTO.getBookingCheckIn(), bDTO.getBookingCheckOut(), bDTO.getBookingGuests(), bDTO.getBookingSelectRoom(), bDTO.getBookingGuestId(), bDTO.getBookingStatus());
            boolean bookingAdded = bookingDAO.save(booking);
            if (!bookingAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            Payment payment = new Payment(pDTO.getPayment_id(), pDTO.getBooking_id(), pDTO.getGuest_id(), pDTO.getAmount(), pDTO.getPayment_date());
            boolean paymentAdded = paymentDAO.save(payment);

            if (!paymentAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //update vehicle status
            RoomStatus roomStatus = new RoomStatus(rDTO.getRoom_id(), rDTO.getRoom_status(), rDTO.getBooking_id());
            boolean roomUpdated = roomStatusDAO.updateRoomStatus(roomStatus);
            if (!roomUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);

            sendBookingConfirmationEmail(gDTO.getFullname(), bDTO.getBookingId(), bDTO.getBookingCheckIn(), bDTO.getBookingCheckOut(), bDTO.getBookingGuests(), bDTO.getBookingSelectRoom(), gDTO.getEmail(), bDTO.getBookingStatus());

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveReservation(GuestDTO gDTO, ReservationDTO rsDTO, PaymentDTO pDTO, RoomStatusDTO rDTO) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            Guest guest = new Guest(gDTO.getGuestId(), gDTO.getFullname(), gDTO.getAddress(), gDTO.getCity(), gDTO.getPostalcode(), gDTO.getCountry(), gDTO.getEmail(), gDTO.getAge());
            boolean guestAdded = guestDAO.save(guest);
            if (!guestAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Reservation reservation = new Reservation(rsDTO.getReservation_id(), rsDTO.getGuest_name(), rsDTO.getCheck_in(), rsDTO.getCheck_out(), rsDTO.getRoomId(), rsDTO.getNoOf_guest(), rsDTO.getReservation_status());
            boolean reservationAdded = reservationDAO.save(reservation);
            if (!reservationAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            Payment payment = new Payment(pDTO.getPayment_id(), pDTO.getBooking_id(), pDTO.getGuest_id(), pDTO.getAmount(), pDTO.getPayment_date());
            boolean paymentAdded = paymentDAO.save(payment);

            if (!paymentAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //update vehicle status
            RoomStatus roomStatus = new RoomStatus(rDTO.getRoom_id(), rDTO.getRoom_status(), rDTO.getBooking_id());
            boolean roomUpdated = roomStatusDAO.updateRoomStatus(roomStatus);
            if (!roomUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);

            sendReservationConfirmationEmail(gDTO.getFullname(), rsDTO.getReservation_id(), rsDTO.getCheck_in(), rsDTO.getCheck_out(), rsDTO.getNoOf_guest(), rsDTO.getRoomId(), gDTO.getEmail(), rsDTO.getReservation_status());

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendBookingConfirmationEmail(String fullname, String bookingId, Date checkin, Date checkout, int noOfGuests, String selectedRoom, String customerEmail, String status) {
        StringBuilder messageContent = new StringBuilder();
        messageContent.append("Dear ").append(fullname).append(",\n\n");

        messageContent.append("Thank you for your booking!\n\n");
        messageContent.append("Booking Details:\n\n");
        messageContent.append("Booking ID: ").append(bookingId).append("\n");
        messageContent.append("Check-in Date: ").append(checkin).append("\n");
        messageContent.append("Check-out Date: ").append(checkout).append("\n");
        messageContent.append("Number of Guests: ").append(noOfGuests).append("\n");
        messageContent.append("Selected Room: ").append(selectedRoom).append("\n\n");
        messageContent.append("We look forward to welcoming you!\n\n");
        messageContent.append("Best regards,\nYour Seaw Team");

        emailSender(customerEmail, "Booking Confirmation", messageContent.toString());
    }

    public void sendReservationConfirmationEmail(String fullname, String reservationId, LocalDate checkin, LocalDate checkout, int noOfGuests, String selectedRoom, String customerEmail, String status) {
        StringBuilder messageContent = new StringBuilder();
        messageContent.append("Dear ").append(fullname).append(",\n\n");

        messageContent.append("Thank you for your reservation!\n\n");
        messageContent.append("Reservation Details:\n\n");
        messageContent.append("Reservation ID: ").append(reservationId).append("\n");
        messageContent.append("Check-in Date: ").append(checkin).append("\n");
        messageContent.append("Check-out Date: ").append(checkout).append("\n");
        messageContent.append("Number of Guests: ").append(noOfGuests).append("\n");
        messageContent.append("Selected Room: ").append(selectedRoom).append("\n\n");
        messageContent.append("We look forward to welcoming you!\n\n");
        messageContent.append("Best regards,\nYour Seaw Team");

        emailSender(customerEmail, "Reservation Confirmation", messageContent.toString());
    }

    public void emailSender(String recipientEmail, String subject, String messageContent){
          final String EMAIL_HOST = "smtp.gmail.com";
          final String EMAIL_USERNAME = "hotelseew68@gmail.com";
          final String EMAIL_PASSWORD = "vinclrfmloaqvekn";

            // Set up email properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", EMAIL_HOST);
            properties.put("mail.smtp.port", "587");

            // Create a session with authentication
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });

            try {
                // Create a MimeMessage object
                Message message = new MimeMessage(session);
                // Set the From address
                message.setFrom(new InternetAddress(EMAIL_USERNAME));
                // Set the To address
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                // Set the Subject
                message.setSubject(subject);
                // Set the Content of the message
                message.setText(messageContent);

                // Send the message
                Transport.send(message);
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("Failed to send email.");
                new Alert(Alert.AlertType.WARNING, "Failed to send email.").show();
            }
    }

    public String generateNewGuestID() throws SQLException, ClassNotFoundException {
        return guestDAO.generateNewID();
    }

    public String generateNewBookingID() throws SQLException, ClassNotFoundException {
        return bookingDAO.generateNewID();
    }

    public String generateNewPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }

    public String generateNewReservationID() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }

    @Override
    public ArrayList<RoomStatusDTO> searchAvailableRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.searchAvailableRooms();
        ArrayList<RoomStatusDTO> arrayList = new ArrayList<>();
        for (RoomStatus i : all) {
            arrayList.add(new RoomStatusDTO( i.getRoom_id()));
        }
        return arrayList;
    }

    @Override
    public double getRoomRate(String selectRoom) throws SQLException, ClassNotFoundException {
        return roomStatusDAO.getRate(selectRoom);
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
