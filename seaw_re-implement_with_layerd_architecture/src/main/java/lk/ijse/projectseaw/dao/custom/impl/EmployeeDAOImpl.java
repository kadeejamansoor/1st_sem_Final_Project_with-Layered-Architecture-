package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.EmployeeDAO;
import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (empid, name, contact, address, role) VALUES (?, ?, ?, ?, ?)",
                entity.getId(), entity.getName(), entity.getContact(), entity.getAddress(), entity.getRole());
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * from Employee");
        while (rst.next()) {
            allEmployees.add(new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    rst.getString(5)));
        }
        return allEmployees;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name=?,contact=?,address=?,role=? where empid=?",
                entity.getName(), entity.getContact(), entity.getAddress(), entity.getRole(), entity.getId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE empid=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT empid FROM Employee ORDER BY empid DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("empid");
            int newCustomerId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newCustomerId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public Employee search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE empid=?", s);
        Employee employee = new Employee();

        if (rst.next()) {
            employee.setId(rst.getString("empid"));
            employee.setName(rst.getString("name"));
            employee.setContact(rst.getString("contact"));
            employee.setAddress(rst.getString("address"));
            employee.setRole(rst.getString("role"));
        }

        return employee;
    }
}
