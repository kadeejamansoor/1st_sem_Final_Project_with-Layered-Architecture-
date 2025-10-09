package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomStatusDAOImpl implements RoomStatusDAO {
    @Override
    public boolean save(RoomStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO RoomStatus(room_id, room_type, floor_Number, capacity, rate, room_status, booking_id) VALUES (?, ?, ?, ?,?,?,?)",
                entity.getRoom_id(), entity.getRoom_type(), entity.getFloor_Number(), entity.getCapacity(), entity.getRate(), entity.getRoom_status(), entity.getBooking_id() );
    }

    @Override
    public ArrayList<RoomStatus> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> allRoomStatus = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus");
        while (rst.next()) {
            allRoomStatus.add(new RoomStatus(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getInt(4), rst.getDouble(5), rst.getString(6), rst.getString(7)));
        }
        return allRoomStatus;
    }

    @Override
    public boolean update(RoomStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE RoomStatus SET room_status = 'available' WHERE room_id = ?",
                entity.getRoom_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT room_id FROM RoomStatus WHERE room_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM RoomStatus WHERE room_id = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT room_id FROM RoomStatus ORDER BY room_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("room_id");
            int newCustomerId = Integer.parseInt(id.replace("R00-", "")) + 1;
            return String.format("R00-%03d", newCustomerId);
        } else {
            return "R00-001";
        }
    }

    @Override
    public RoomStatus search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus WHERE room_id = ?", s);
        RoomStatus roomStatus = new RoomStatus();

        if (rst.next()) {
            roomStatus.setRoom_id(rst.getString("room_id"));
            roomStatus.setRoom_type(rst.getString("room_type"));
            roomStatus.setFloor_Number(rst.getInt("floor_Number"));
            roomStatus.setCapacity(rst.getInt("capacity"));
            roomStatus.setRate(rst.getDouble("rate"));
            roomStatus.setRoom_status(rst.getString("room_status"));
            roomStatus.setBooking_id(rst.getString("booking_id"));
        }

        return roomStatus;
    }

    public boolean updateRoomStatus(RoomStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE RoomStatus SET room_status = ?, booking_id = ? WHERE room_id = ?",  entity.getRoom_status(), entity.getBooking_id(), entity.getRoom_id());
    }

    public ArrayList<RoomStatus> searchAvailableRooms() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT room_id FROM RoomStatus WHERE room_status = 'available'");
        ArrayList<RoomStatus> allIRooms = new ArrayList<>();
        while (rst.next()) {
            String roomId = rst.getString("room_id");
            RoomStatus vehicleStatus = new RoomStatus();
            vehicleStatus.setRoom_id(roomId);
            allIRooms.add(vehicleStatus);
        }
        return allIRooms;
    }

    public ArrayList<RoomStatus> searchBookingRooms() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus");
        ArrayList<RoomStatus> allIRooms = new ArrayList<>();
        while (rst.next()) {
            String roomId = rst.getString("room_id");
            RoomStatus vehicleStatus = new RoomStatus();
            vehicleStatus.setRoom_id(roomId);
            allIRooms.add(vehicleStatus);
        }
        return allIRooms;
    }

    public double getRate(String selectRoom) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT roomRate FROM Room WHERE room_id = ?", selectRoom);
        double rate = 0.0;  // Initialize rate with a default value

        if (rst.next()) {
            rate = rst.getDouble("roomRate");
        }

        return rate;
    }

    @Override
    public ArrayList<RoomStatus> getAvailableRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> allrooms = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus WHERE room_status = 'available'");
        while (rst.next()) {
            allrooms.add(new RoomStatus(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getInt(4), rst.getDouble(5), rst.getString(6), rst.getString(7)));
        }
        return allrooms;
    }

    @Override
    public ArrayList<RoomStatus> getBookedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> allrooms = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus WHERE room_status = 'Booked'");
        while (rst.next()) {
            allrooms.add(new RoomStatus(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getInt(4), rst.getDouble(5), rst.getString(6), rst.getString(7)));
        }
        return allrooms;
    }

    @Override
    public ArrayList<RoomStatus> getReservedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> allrooms = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM RoomStatus WHERE room_status = 'reserved'");
        while (rst.next()) {
            allrooms.add(new RoomStatus(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getInt(4), rst.getDouble(5), rst.getString(6), rst.getString(7)));
        }
        return allrooms;
    }

    @Override
    public boolean updateReservedToBooking(RoomStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE RoomStatus SET room_status = 'booked' WHERE room_id = ?",
                entity.getRoom_id());
    }

    @Override
    public boolean updateRoomDetails(RoomStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE RoomStatus SET room_type = ?, floor_Number = ?, capacity = ?,  rate = ? WHERE room_id = ?",
                entity.getRoom_type(), entity.getFloor_Number(), entity.getCapacity(), entity.getRate(), entity.getRoom_id());
    }
}
