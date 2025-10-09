package lk.ijse.projectseaw.dao;

import lk.ijse.projectseaw.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        GUEST, BOOKING, PAYMENT, ROOMSTATUS, RESERVATION, STOCK, EMPLOYEE, SALARY, USER,
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case GUEST:
                return (T) new GuestDAOImpl();
            case BOOKING:
                return (T) new BookingDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case ROOMSTATUS:
                return (T) new RoomStatusDAOImpl();
            case RESERVATION:
                return (T) new ReservationDAOImpl();
            case STOCK:
                return (T) new StockDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}
