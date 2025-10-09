package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeManagementBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO e) throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployee(String employeeId) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    String generateNewEmployeeID() throws SQLException, ClassNotFoundException;
}
