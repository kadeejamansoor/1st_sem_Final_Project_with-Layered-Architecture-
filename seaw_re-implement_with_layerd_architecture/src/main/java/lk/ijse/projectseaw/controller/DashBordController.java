package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.BookingInformationBO;
import lk.ijse.projectseaw.bo.custom.DashboardBO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {

    @FXML
    private AreaChart<String, Number> barChart;

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private Text lblBookedroom;

    @FXML
    private Text lblThismonethProfit;

    @FXML
    private Text lblThisweekProfit;

    @FXML
    private Text lblThisyearProfit;

    @FXML
    private Text lblTodayProfit;

    @FXML
    private Text lblavailableroom;

    @FXML
    private Text lblreservedroom;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton reservationbtn;

    @FXML
    private JFXButton roombtn;

    @FXML
    private JFXButton settingbtn;

    @FXML
    private JFXButton stockbtn;

    DashboardBO dashboardBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DASHBOARD_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomReservationStatistics();
        try {
            profitAnalysis();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Get the last 30 day profits
        List<Double> last30DayProfits = null;
        try {
            last30DayProfits = dashboardBO.calculateLast30DayProfits();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create a series to hold the last 30 day profit data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Last 30 Days");

        // Add each day's profit to the series
        for (int i = 0; i < last30DayProfits.size(); i++) {
            series.getData().add(new XYChart.Data<>("Day " + (i+1), last30DayProfits.get(i)));
        }

        // Set the chart data
        barChart.getData().add(series);
    }

    public void profitAnalysis() throws SQLException, ClassNotFoundException {

        Map<String, Double> profits = dashboardBO.profitanalysis();
        double todayProfit = profits.get("Today");
        double thisWeekProfit = profits.get("This week");
        double thisMonthProfit = profits.get("This month");
        double thisYearProfit = profits.get("This year");

        lblTodayProfit.setText(Double.toString(todayProfit));
        lblThisweekProfit.setText(Double.toString(thisWeekProfit));
        lblThismonethProfit.setText(Double.toString(thisMonthProfit));
        lblThisyearProfit.setText(Double.toString(thisYearProfit));

    }

    public void roomReservationStatistics() {

        try {
            ArrayList<RoomStatusDTO> availableRooms = dashboardBO.getAvailableRooms();
            lblavailableroom.setText(String.valueOf(availableRooms.size()));

            ArrayList<RoomStatusDTO> bookedRooms = dashboardBO.getBookedRooms();
            lblBookedroom.setText(String.valueOf(bookedRooms.size()));

            ArrayList<RoomStatusDTO> reservedRooms = dashboardBO.getReservedRooms();
            lblreservedroom.setText(String.valueOf(reservedRooms.size()));

        }catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnBillingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/BillingForm.fxml"));
        Stage window = (Stage) billingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Billing");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/BookingInformationForm.fxml"));
        Stage window = (Stage) bookingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Booking Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/DashBord.fxml"));
        Stage window = (Stage) dashboardbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Dashboard");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/EmployeeManagementForm.fxml"));
        Stage window = (Stage) employeebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Employee Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LogOut.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Logout page");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/ReservationForm.fxml"));
        Stage window = (Stage) reservationbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Reservation");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/RoomManagementForm.fxml"));
        Stage window = (Stage) roombtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Room Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/Settings1From.fxml"));
        Stage window = (Stage) settingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Settings");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/view/StockManagementForm.fxml"));
        Stage window = (Stage) stockbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Stock Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml"));
        Stage window = (Stage)reportbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Report");
        window.setMaximized(true);
        window.centerOnScreen();
    }


}
