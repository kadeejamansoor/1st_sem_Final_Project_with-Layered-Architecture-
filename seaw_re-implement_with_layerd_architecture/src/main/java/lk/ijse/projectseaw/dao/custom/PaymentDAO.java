package lk.ijse.projectseaw.dao.custom;

import lk.ijse.projectseaw.dao.CrudDAO;
import lk.ijse.projectseaw.entity.Payment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    ArrayList<Payment> getAllPayment() throws SQLException, ClassNotFoundException;
    ArrayList<Payment> getAllLast30DayProfits() throws SQLException, ClassNotFoundException;
}
