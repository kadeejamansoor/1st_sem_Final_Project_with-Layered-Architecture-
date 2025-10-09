package lk.ijse.projectseaw.dto;

public class RoomDTO {

    private String roomId;
    private String roomType;
    private int floorNumber;
    private int capacity;
    private double rate;
    private String status;
    private String guestId;

    public RoomDTO() {
    }

    public RoomDTO(String roomId, String roomType, int floorNumber, int capacity, double rate, String status, String guestId) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.floorNumber = floorNumber;
        this.capacity = capacity;
        this.rate = rate;
        this.status = status;
        this.guestId = guestId;
    }

    public RoomDTO(String roomId, String roomType, int capacity, int floorNumber, double rate) {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", floorNumber=" + floorNumber +
                ", capacity=" + capacity +
                ", rate=" + rate +
                ", status='" + status + '\'' +
                ", guestId='" + guestId + '\'' +
                '}';
    }
}
