package lk.ijse.projectseaw.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BookingDTO {
    private String bookingId;
    private Date BookingCheckIn;
    private Date BookingCheckOut;
    private int BookingGuests;
    private String BookingSelectRoom;
    private String BookingGuestId;
    private String BookingStatus;

    public BookingDTO(String string, String string1, String string2, int anInt, String string3, String string4, String string5) {
    }

    public BookingDTO(String bookingId, Date checkin, Date checkout, int noOfGuests, String selectedRoom, String guestId) {
        this.bookingId=bookingId;
        this.BookingCheckIn=checkin;
        this.BookingCheckOut=checkout;
        this.BookingGuests=noOfGuests;
        this.BookingSelectRoom=selectedRoom;
        this.BookingGuestId=guestId;
    }

}
