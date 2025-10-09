package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryManagementBO extends SuperBO {
    ArrayList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;
    boolean saveSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean updateSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;
    SalaryDTO searchSalary(String salaryId) throws SQLException, ClassNotFoundException;
    String generateNewSalaryID() throws SQLException, ClassNotFoundException;
}
