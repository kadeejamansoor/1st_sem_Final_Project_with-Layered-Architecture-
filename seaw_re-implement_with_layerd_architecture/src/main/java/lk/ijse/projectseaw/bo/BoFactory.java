package lk.ijse.projectseaw.bo;

import lk.ijse.projectseaw.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        BOOKINGINFORMATION_BO , PAYMENTCONFIRMATION_BO, BOOKINGMANAGEMENT_BO, GUESTMANAGEMENT_BO, RESERVATION_BO,
        ROOMMANAGEMENT_BO, ADDROOM_BO, STOCKMANAGE_BO, EMPLOYEEMANAGEMENT_BO, SALARYMANAGEMENT_BO, BILLING_BO,
        SETTING_BO, DASHBOARD_BO, OTPManagement_BO,
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes) {
            case BOOKINGINFORMATION_BO:
                return (T) new BookingInformationBOImpl();
            case PAYMENTCONFIRMATION_BO:
                return (T) new PaymentConfirmationBOImpl();
            case BOOKINGMANAGEMENT_BO:
                return (T) new BookingManagementBOImpl();
            case GUESTMANAGEMENT_BO:
                return (T) new GuestManagementBOImpl();
            case RESERVATION_BO:
                return (T) new ReservationBOImpl();
            case ROOMMANAGEMENT_BO:
                return (T) new RoomManagementBOImpl();
            case ADDROOM_BO:
                return (T) new AddRoomBOImpl();
            case STOCKMANAGE_BO:
                return (T) new StockManageBOImpl();
            case EMPLOYEEMANAGEMENT_BO:
                return (T) new EmployeeManagementBOImpl();
            case SALARYMANAGEMENT_BO:
                return (T) new SalaryManagementBOImpl();
            case BILLING_BO:
                return (T) new BillingBOImpl();
            case SETTING_BO:
                return (T) new SettingBOImpl();
            case DASHBOARD_BO:
                return (T) new DashboardBOImpl();
            case OTPManagement_BO:
                return (T) new OTPManagementBOImpl();
            default:
                return null;
        }
    }
}
