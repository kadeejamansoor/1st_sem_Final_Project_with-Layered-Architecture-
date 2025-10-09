package lk.ijse.projectseaw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.SalaryManagementBO;
import lk.ijse.projectseaw.dto.SalaryDTO;
import lk.ijse.projectseaw.dto.tm.SalaryTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalaryManagmentController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colFullName;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private DatePicker dtpkSalaryDate;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtSalaryId;

    SalaryManagementBO salaryManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SALARYMANAGEMENT_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllSalaries();
        txtSalaryId.setText(generateNewId());
    }

    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("empFullName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("salaryAmount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("salaryDate"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    }

    void loadAllSalaries(){
        tblSalary.getItems().clear();

        try {
            ArrayList<SalaryDTO> allSalaries = salaryManagementBO.getAllSalaries();
            for (SalaryDTO s : allSalaries) {
                tblSalary.getItems().add(new SalaryTm(s.getSalaryId(), s.getEmpFullName(), s.getSalaryAmount(), s.getSalaryDate(), s.getEmployeeId()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtSalaryId.clear();
        txtFullName.clear();
        txtAmount.clear();
        dtpkSalaryDate.setValue(null);
        txtEmployeeId.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();

        boolean b = salaryManagementBO.deleteSalary(SalaryId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been deleted successfully").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();
        String FullName = txtFullName.getText();
        double Amount = Double.parseDouble(txtAmount.getText());
        String salaryDate = String.valueOf(dtpkSalaryDate.getValue());
        String EmployeeId = txtEmployeeId.getText();

        boolean b = salaryManagementBO.saveSalary(new SalaryDTO(SalaryId, FullName, Amount, salaryDate, EmployeeId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been saved successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();
        String FullName = txtFullName.getText();
        double Amount = Double.parseDouble(txtAmount.getText());
        String salaryDate = String.valueOf(dtpkSalaryDate.getValue());
        String EmployeeId = txtEmployeeId.getText();

        boolean b = salaryManagementBO.updateSalary(new SalaryDTO(SalaryId, FullName, Amount, salaryDate, EmployeeId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();

        SalaryDTO s = salaryManagementBO.searchSalary(SalaryId);

        txtSalaryId.setText(s.getSalaryId());
        txtFullName.setText(s.getEmpFullName());
        txtAmount.setText(String.valueOf(s.getSalaryAmount()));

        if (s.getSalaryDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            dtpkSalaryDate.setValue(LocalDate.parse(s.getSalaryDate(), formatter));
        } else {
            dtpkSalaryDate.setValue(null);
        }

        txtEmployeeId.setText(s.getEmployeeId());
    }

    private String generateNewId() {
        try {
            return salaryManagementBO.generateNewSalaryID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
