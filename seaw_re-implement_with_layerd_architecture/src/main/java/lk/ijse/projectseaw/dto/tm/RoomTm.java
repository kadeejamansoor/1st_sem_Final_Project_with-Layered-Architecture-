package lk.ijse.projectseaw.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomTm {
    private String roomId;
    private String roomType;
    private int floorNumber;
    private int capacity;
    private double rate;
}
