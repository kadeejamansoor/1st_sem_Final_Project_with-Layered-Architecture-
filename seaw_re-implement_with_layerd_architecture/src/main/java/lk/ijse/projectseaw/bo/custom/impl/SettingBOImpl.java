package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.SettingBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.UserDAO;
import lk.ijse.projectseaw.dto.UserDTO;
import lk.ijse.projectseaw.entity.User;

import java.sql.SQLException;

public class SettingBOImpl implements SettingBO {
    UserDAO userDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO u) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(u.getUsername(), u.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO u) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(u.getUsername(), u.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }
}
