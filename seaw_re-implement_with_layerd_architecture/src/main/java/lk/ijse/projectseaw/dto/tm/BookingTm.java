package lk.ijse.projectseaw.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BookingTm {
    private String bookingId;
    private Date BookingCheckIn;
    private Date BookingCheckOut;
    private int BookingGuests;
    private String BookingSelectRoom;
    private String BookingGuestId;
    private String BookingStatus;




}
