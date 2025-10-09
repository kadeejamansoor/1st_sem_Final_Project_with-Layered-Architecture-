package lk.ijse.projectseaw.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.projectseaw.bo.custom.EmployeeManagementBO;
import lk.ijse.projectseaw.dto.EmployeeDTO;
import lk.ijse.projectseaw.dto.tm.EmployeeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {
    EmployeeManagementBO employeeManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.EMPLOYEEMANAGEMENT_BO);

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private TableColumn<?, ?> contact;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private TableColumn<?, ?> employeeManagement;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton reservationbtn;

    @FXML
    private TableColumn<?, ?> role;

    @FXML
    private JFXButton roombtn;

    @FXML
    private JFXButton settingbtn;

    @FXML
    private JFXButton stockbtn;

    @FXML
    private TableView<EmployeeTm> tbl;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadALlEmployees();
        txtID.setText(generateNewId());
    }

    private void setCellValueFactory() {
        employeeManagement.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        contact.setCellValueFactory(new PropertyValueFactory("address"));
        address.setCellValueFactory(new PropertyValueFactory("contact"));
        role.setCellValueFactory(new PropertyValueFactory("role"));
    }

    void loadALlEmployees(){
        tbl.getItems().clear();

        try {
            ArrayList<EmployeeDTO> allEmployees = employeeManagementBO.getAllEmployees();
            for (EmployeeDTO e : allEmployees) {
                tbl.getItems().add(new EmployeeTm(e.getId(), e.getName(), e.getContact(), e.getAddress(), e.getRole()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        txtID.clear();
        txtName.clear();
        txtNumber.clear();
        txtAddress.clear();
        txtAddress.clear();
        txtRole.clear();
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employeeId = txtID.getText();

        boolean b = employeeManagementBO.deleteEmployee(employeeId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadALlEmployees();
            clearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been deleted successfully").show();
        }
    }

    @FXML
    void empIdOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Empid = txtID.getText();

        EmployeeDTO e = employeeManagementBO.searchEmployee(Empid);

        txtID.setText(e.getId());
        txtName.setText(e.getName());
        txtNumber.setText(e.getContact());
        txtAddress.setText(e.getAddress());
        txtRole.setText(e.getRole());
    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Empid = txtID.getText();
        String empName = txtName.getText();
        String empContact = txtNumber.getText();
        String empAddress = txtAddress.getText();
        String empRole = txtRole.getText();

        if (Empid.isEmpty() || empName.isEmpty() || empContact.isEmpty() || empAddress.isEmpty() || empRole.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!empName.matches("[a-zA-Z ]+")) {
            new Alert(Alert.AlertType.WARNING, "Invalid employee name format").show();
            txtName.requestFocus();
            return;
        }else if (!empRole.matches("^[a-zA-Z\\s\\-]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid role format").show();
            txtRole.requestFocus();
            return;
        }

        boolean b = employeeManagementBO.saveEmployee(new EmployeeDTO(Empid, empName, empContact, empAddress, empRole));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadALlEmployees();
            clearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been saved successfully").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Empid = txtID.getText();
        String empName = txtName.getText();
        String empContact = txtNumber.getText();
        String empAddress = txtAddress.getText();
        String empRole = txtRole.getText();

        if (Empid.isEmpty() || empName.isEmpty() || empContact.isEmpty() || empAddress.isEmpty() || empRole.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!empName.matches("[a-zA-Z ]+")) {
            new Alert(Alert.AlertType.WARNING, "Invalid employee name format").show();
            txtName.requestFocus();
            return;
        }else if (!empRole.matches("^[a-zA-Z\\s\\-]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid role format").show();
            txtRole.requestFocus();
            return;
        }

        boolean b = employeeManagementBO.updateEmployee(new EmployeeDTO(Empid, empName, empContact, empAddress, empRole));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadALlEmployees();
            clearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been updated successfully").show();
        }
    }

    private String generateNewId() {
        try {
            return employeeManagementBO.generateNewEmployeeID();
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
    void btnSalaryManagementOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SalaryManagementForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Salary Management");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
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
