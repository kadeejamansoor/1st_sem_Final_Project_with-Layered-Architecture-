package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.UserDAO;
import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User(username, password) VALUES(?, ?)",
                entity.getUsername(), entity.getPassword());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET password = ? WHERE username = ?",
                entity.getUsername(), entity.getPassword());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM User WHERE username=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
