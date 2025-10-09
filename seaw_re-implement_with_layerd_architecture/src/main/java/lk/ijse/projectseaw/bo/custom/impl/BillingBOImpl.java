package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.BillingBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.PaymentDAO;
import lk.ijse.projectseaw.dto.PaymentDTO;
import lk.ijse.projectseaw.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillingBOImpl implements BillingBO {
    PaymentDAO salaryDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> all = salaryDAO.getAll();
        ArrayList<PaymentDTO> arrayList= new ArrayList<>();
        for (Payment p : all) {
            arrayList.add(new PaymentDTO(p.getPayment_id(), p.getBooking_id(), p.getGuest_id(), p.getAmount(), p.getPayment_date()));
        }
        return arrayList;
    }
}
