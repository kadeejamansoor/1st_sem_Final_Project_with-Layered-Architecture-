package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.SalaryManagementBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.SalaryDAO;
import lk.ijse.projectseaw.dto.SalaryDTO;
import lk.ijse.projectseaw.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryManagementBOImpl implements SalaryManagementBO {
    SalaryDAO salaryDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    @Override
    public ArrayList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> all = salaryDAO.getAll();
        ArrayList<SalaryDTO> arrayList= new ArrayList<>();
        for (Salary s : all) {
            arrayList.add(new SalaryDTO(s.getSalaryId(), s.getEmpFullName(), s.getSalaryAmount(), s.getSalaryDate(), s.getEmployeeId()));
        }
        return arrayList;
    }

    @Override
    public boolean saveSalary(SalaryDTO s) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(s.getSalaryId(), s.getEmpFullName(), s.getSalaryAmount(), s.getSalaryDate(), s.getEmployeeId()));
    }

    @Override
    public boolean updateSalary(SalaryDTO s) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(s.getSalaryId(), s.getEmpFullName(), s.getSalaryAmount(), s.getSalaryDate(), s.getEmployeeId()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public SalaryDTO searchSalary(String salaryId) throws SQLException, ClassNotFoundException {
        Salary s = salaryDAO.search(salaryId);
        return new SalaryDTO(s.getSalaryId(), s.getEmpFullName(), s.getSalaryAmount(), s.getSalaryDate(), s.getEmployeeId());
    }

    @Override
    public String generateNewSalaryID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewID();
    }
}
