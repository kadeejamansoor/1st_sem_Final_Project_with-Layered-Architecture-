package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.UserDTO;

import java.sql.SQLException;

public interface SettingBO extends SuperBO {
    boolean saveUser(UserDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO u) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
}
