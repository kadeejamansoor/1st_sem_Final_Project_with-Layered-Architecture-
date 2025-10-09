package lk.ijse.projectseaw.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Room {
    private String roomId;
    private String roomType;
    private int floorNumber;
    private int capacity;
    private double rate;
    private String status;
    private String guestId;


    public Room(String string, String string1, int anInt, int anInt1, double aDouble) {
    }

    public Room(String roomId) {
    }
}
