package lk.ijse.projectseaw.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomStatus {
    private String room_id;
    private String room_type;
    private int floor_Number;
    private int capacity;
    private double rate;
    private String room_status;
    private String booking_id;


    public RoomStatus(String roomId, String roomStatus, String bookingId) {
        this.room_id=roomId;
        this.room_status=roomStatus;
        this.booking_id=bookingId;
    }

    public RoomStatus(String id) {
        this.booking_id=id;
    }
}
