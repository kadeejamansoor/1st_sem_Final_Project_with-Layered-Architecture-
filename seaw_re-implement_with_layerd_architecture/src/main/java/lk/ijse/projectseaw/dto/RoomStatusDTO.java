package lk.ijse.projectseaw.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomStatusDTO {
    private String room_id;
    private String room_type;
    private int floor_Number;
    private int capacity;
    private double rate;
    private String room_status;
    private String booking_id;


    public RoomStatusDTO(String roomId) {
        this.room_id=roomId;
    }
}
