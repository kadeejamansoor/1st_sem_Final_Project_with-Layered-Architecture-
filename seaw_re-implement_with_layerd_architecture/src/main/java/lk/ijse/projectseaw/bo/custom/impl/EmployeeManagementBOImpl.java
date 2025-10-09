package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.EmployeeManagementBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.EmployeeDAO;
import lk.ijse.projectseaw.dto.EmployeeDTO;
import lk.ijse.projectseaw.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagementBOImpl implements EmployeeManagementBO {
    EmployeeDAO employeeDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList= new ArrayList<>();
        for (Employee e : all) {
            arrayList.add(new EmployeeDTO(e.getId(), e.getName(), e.getContact(), e.getAddress(), e.getRole()));
        }
        return arrayList;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO e) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(e.getId(), e.getName(), e.getContact(), e.getAddress(), e.getRole()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO e) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(e.getId(), e.getName(), e.getContact(), e.getAddress(), e.getRole()));
    }

    @Override
    public EmployeeDTO searchEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        Employee e = employeeDAO.search(employeeId);
        return new EmployeeDTO(e.getId(), e.getName(), e.getContact(), e.getAddress(), e.getRole());

    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }
}
