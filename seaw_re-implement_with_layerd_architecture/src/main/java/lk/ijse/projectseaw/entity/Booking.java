package lk.ijse.projectseaw.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Booking {
    private String bookingId;
    private Date BookingCheckIn;
    private Date BookingCheckOut;
    private int BookingGuests;
    private String BookingSelectRoom;
    private String BookingGuestId;
    private String BookingStatus;

    public Booking(String string, String string1, String string2, int anInt, String string3, String string4, String string5) {
    }

    public Booking(String bookingId, Date bookingCheckIn, Date bookingCheckOut, int bookingGuests, String bookingSelectRoom, String bookingGuestId) {
        this.bookingId=bookingId;
        this.BookingCheckIn=bookingCheckIn;
        this.BookingCheckOut=bookingCheckOut;
        this.BookingGuests=bookingGuests;
        this.BookingSelectRoom=bookingSelectRoom;
        this.BookingGuestId=bookingGuestId;
    }

    public Booking(String id) {
        this.bookingId=id;
    }
}
