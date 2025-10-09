package lk.ijse.projectseaw.dao.custom;

import lk.ijse.projectseaw.dao.CrudDAO;
import lk.ijse.projectseaw.entity.Guest;

import java.sql.SQLException;

public interface GuestDAO extends CrudDAO<Guest,String> {
    Guest getLastGuest() throws SQLException, ClassNotFoundException;
}
