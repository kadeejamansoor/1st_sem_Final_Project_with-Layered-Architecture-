package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.SalaryDAO;
import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Salary (salary_id, fullName, amount, Salarydate, employee_id) VALUES (?, ?, ?, ?, ?)",
                entity.getSalaryId(), entity.getEmpFullName(), entity.getSalaryAmount(), entity.getSalaryDate(), entity.getEmployeeId());
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allSalaries = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary");
        while (rst.next()) {
            allSalaries.add(new Salary(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5)));
        }
        return allSalaries;
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Salary SET fullName = ?, amount = ?, Salarydate = ?, employee_id = ? WHERE salary_id = ? ",
                entity.getEmpFullName(), entity.getSalaryAmount(), entity.getSalaryDate(), entity.getEmployeeId(), entity.getSalaryId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Salary WHERE salary_id=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT salary_id FROM Salary ORDER BY salary_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("salary_id");
            int newCustomerId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newCustomerId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public Salary search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary WHERE salary_id = ?", s);
        Salary salary = new Salary();

        if (rst.next()) {
            salary.setSalaryId(rst.getString("salary_id"));
            salary.setEmpFullName(rst.getString("fullName"));
            salary.setSalaryAmount(rst.getDouble("amount"));
            salary.setSalaryDate(rst.getString("Salarydate"));
            salary.setEmployeeId(rst.getString("employee_id"));
        }

        return salary;
    }
}
