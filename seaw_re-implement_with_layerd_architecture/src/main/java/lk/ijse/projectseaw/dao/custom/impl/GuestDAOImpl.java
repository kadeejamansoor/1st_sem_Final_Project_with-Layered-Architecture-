package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.dao.custom.GuestDAO;
import lk.ijse.projectseaw.entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAOImpl implements GuestDAO {
    @Override
    public boolean save(Guest entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Guest(guest_id, guest_Fullname, address, city, postal_code, country, email, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getGuestId(), entity.getFullname(), entity.getAddress(), entity.getCity(), entity.getPostalcode(), entity.getCountry(), entity.getEmail(), entity.getAge());
    }

    @Override
    public ArrayList<Guest> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Guest> allGuests = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guest ORDER BY guest_id DESC");
        while (rst.next()) {
            allGuests.add(new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6),
                    rst.getString(7), rst.getInt(8)));
        }
        return allGuests;
    }

    @Override
    public boolean update(Guest entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Guest SET guest_Fullname = ?, address = ?, city = ?, postal_code = ?, country = ?, email = ?, age = ? WHERE guest_id = ?",
                entity.getFullname(), entity.getAddress(), entity.getCity(), entity.getPostalcode(), entity.getCountry(), entity.getEmail(), entity.getAge(), entity.getGuestId() );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT guest_id FROM Guest WHERE guest_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Guest WHERE guest_id = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT guest_id FROM Guest ORDER BY guest_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("guest_id");
            int newCustomerId = Integer.parseInt(id.replace("G00-", "")) + 1;
            return String.format("G00-%03d", newCustomerId);
        } else {
            return "G00-001";
        }
    }

    @Override
    public Guest search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guest WHERE guest_id = ?", s);
        Guest guest = new Guest();

        if (rst.next()) {
            guest.setGuestId(rst.getString("guest_id"));
            guest.setFullname(rst.getString("guest_Fullname"));
            guest.setAddress(rst.getString("address"));
            guest.setCity(rst.getString("city"));
            guest.setPostalcode(rst.getString("postal_code"));
            guest.setCountry(rst.getString("country"));
            guest.setEmail(rst.getString("email"));
            guest.setAge(rst.getInt("age"));
        }

        return guest;
    }

    @Override
    public Guest getLastGuest() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guest ORDER BY guest_id DESC LIMIT 1");
        Guest guest = new Guest();

        if (rst.next()) {
            guest.setGuestId(rst.getString("guest_id"));
            guest.setFullname(rst.getString("guest_Fullname"));
            guest.setAddress(rst.getString("address"));
            guest.setCity(rst.getString("city"));
            guest.setPostalcode(rst.getString("postal_code"));
            guest.setCountry(rst.getString("country"));
            guest.setEmail(rst.getString("email"));
            guest.setAge(rst.getInt("age"));

        }

        return guest;
    }
}
