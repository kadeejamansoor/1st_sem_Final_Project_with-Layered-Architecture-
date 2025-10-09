package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.dao.custom.PaymentDAO;
import lk.ijse.projectseaw.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment (payment_id, booking_id, guest_id, amount, payment_date) VALUES (?, ?, ?, ?, ?)",
                entity.getPayment_id(), entity.getBooking_id(), entity.getGuest_id(), entity.getAmount(), entity.getPayment_date());
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allSalaries = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payment");
        while (rst.next()) {
            allSalaries.add(new Payment(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getDate(5).toLocalDate()));
        }
        return allSalaries;
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT payment_id FROM Payment ORDER BY payment_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("payment_id");
            int newCustomerId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newCustomerId);
        } else {
            return "P00-001";
        }
    }

    @Override
    public Payment search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Payment> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payment ORDER BY payment_id DESC LIMIT 365");
        while (rst.next()){
            allPayments.add(new Payment(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getDate(5).toLocalDate()));
        }
        return allPayments;
    }

    @Override
    public ArrayList<Payment> getAllLast30DayProfits() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payment ORDER BY payment_id DESC LIMIT 30");
        while (rst.next()){
            allPayments.add(new Payment(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getDate(5).toLocalDate()));
        }
        return allPayments;
    }

}


