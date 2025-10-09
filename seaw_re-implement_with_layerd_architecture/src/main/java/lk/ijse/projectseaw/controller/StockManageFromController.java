package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.StockManageBO;
import lk.ijse.projectseaw.dto.StockDTO;
import lk.ijse.projectseaw.dto.tm.StockTm;
import lk.ijse.projectseaw.entity.Stock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockManageFromController implements Initializable {
    StockManageBO stockManageBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STOCKMANAGE_BO);

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStkId;

    @FXML
    private TableColumn<?, ?> colStkName;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private JFXButton employeebtn;

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

    @FXML
    private TableView<StockTm> tblStockManagement;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtStockId;

    @FXML
    private TextField txtStockName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllStocks();
        txtStockId.setText(generateNewId());
    }

    private void setCellValueFactory() {
        colStkId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colStkName.setCellValueFactory(new PropertyValueFactory<>("StockName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    void loadAllStocks(){
        tblStockManagement.getItems().clear();

        try {
            ArrayList<StockDTO> allStocks = stockManageBO.getAllStocks();
            for (StockDTO s : allStocks) {
                tblStockManagement.getItems().add(new StockTm(s.getStockId(), s.getStockName(), s.getQuantity()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String stockId = txtStockId.getText();
        String stockName = txtStockName.getText();
        int qty = Integer.parseInt(txtQuantity.getText());
        String qtyText = txtQuantity.getText();

        if (stockId.isEmpty() || stockName.isEmpty() || qtyText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }

        boolean b = stockManageBO.saveStock(new StockDTO(stockId, stockName, qty));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllStocks();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "stock has been saved successfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String stockId = txtStockId.getText();

        boolean b = stockManageBO.deleteStock(stockId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllStocks();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "stock has been deleted successfully").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtStockId.clear();
        txtStockName.clear();
        txtQuantity.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String stockId = txtStockId.getText();
        String stockName = txtStockName.getText();
        int qty = Integer.parseInt(txtQuantity.getText());

        boolean b = stockManageBO.updateStock(new StockDTO(stockId, stockName, qty));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllStocks();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "stock has been saved successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String stockId = txtStockId.getText();

        StockDTO s = stockManageBO.searchStock(stockId);

        txtStockId.setText(s.getStockId());
        txtStockName.setText(s.getStockName());
        txtQuantity.setText(String.valueOf(s.getQuantity()));
    }

    private String generateNewId() {
        try {
            return stockManageBO.generateNewStockId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
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


    @FXML
    void btnStockReportOnAction(ActionEvent event) {

    }


}
