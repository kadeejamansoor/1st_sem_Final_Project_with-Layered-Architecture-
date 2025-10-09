package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillingBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
}
