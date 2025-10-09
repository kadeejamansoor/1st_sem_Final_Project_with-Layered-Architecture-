package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.DashboardBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.PaymentDAO;
import lk.ijse.projectseaw.dao.custom.RoomStatusDAO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.entity.Payment;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {
    RoomStatusDAO roomStatusDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMSTATUS);
    PaymentDAO paymentDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public ArrayList<RoomStatusDTO> getAvailableRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getAvailableRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomStatusDTO> getBookedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getBookedRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomStatusDTO> getReservedRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomStatus> all = roomStatusDAO.getReservedRooms();
        ArrayList<RoomStatusDTO> arrayList= new ArrayList<>();
        for (RoomStatus s : all) {
            arrayList.add(new RoomStatusDTO(s.getRoom_id(), s.getRoom_type(), s.getFloor_Number(), s.getCapacity(), s.getRate(), s.getRoom_status(), s.getBooking_id()));
        }
        return arrayList;
    }

    @Override
    public Map<String, Double> profitanalysis() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = paymentDAO.getAllPayment();
        Map<String, Double> profits = new HashMap<>();

        double todayProfit = 0;
        double thisWeekProfit = 0;
        double thisMonthProfit = 0;
        double thisYearProfit = 0;

        LocalDate today = LocalDate.now();

        for (Payment payment : allPayments) {
            // Get the payment date and amount
            LocalDate paymentDate = payment.getPayment_date();
            double profit = payment.getAmount();

            // Calculate the profit
            // double profit = price - cost;

            // Add the profit to the corresponding variable based on its date range
            if (paymentDate.equals(today)) {
                todayProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(7))) {
                thisWeekProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(30))) {
                thisMonthProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(365))) {
                thisYearProfit += profit;
            }
        }

        // Store the profits in the map
        profits.put("Today", todayProfit);
        profits.put("This week", thisWeekProfit);
        profits.put("This month", thisMonthProfit);
        profits.put("This year", thisYearProfit);

        return profits;
    }

    @Override
    public List<Double> calculateLast30DayProfits() throws SQLException, ClassNotFoundException {
        List<Double> profits = new ArrayList<>();
        try {
            // Retrieve the last 30 payments from the Payment table
            ArrayList<Payment> Last30DayProfits = paymentDAO.getAllLast30DayProfits();

            // Initialize a map to store the profits for each day
            Map<LocalDate, Double> dailyProfits = new HashMap<>();

            for (Payment payment : Last30DayProfits) {
                LocalDate paymentDate = payment.getPayment_date();
                double profit = payment.getAmount();

                // Add the profit to the corresponding variable based on its date range
                dailyProfits.put(paymentDate, dailyProfits.getOrDefault(paymentDate, 0.0) + profit);
            }

            // Get the profits for each day in the last 30 days and add them to the list
            LocalDate today = LocalDate.now();
            for (int i = 0; i < 30; i++) {
                LocalDate date = today.minusDays(i);
                double profit = dailyProfits.getOrDefault(date, 0.0);
                profits.add(profit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of profits
        return profits;
    }



}
