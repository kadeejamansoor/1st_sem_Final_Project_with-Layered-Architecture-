package lk.ijse.projectseaw.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomStatusTm {

    private String roomId;
    private String roomType;
    private int floorNumber;
    private int capacity;
    private double rate;
    private String status;
    private String guestId;

}
