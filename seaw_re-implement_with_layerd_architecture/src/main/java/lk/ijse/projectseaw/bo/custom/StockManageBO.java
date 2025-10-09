package lk.ijse.projectseaw.bo.custom;

import lk.ijse.projectseaw.bo.SuperBO;
import lk.ijse.projectseaw.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockManageBO extends SuperBO {
    ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException;
    boolean saveStock(StockDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean updateStock(StockDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean deleteStock(String id) throws SQLException, ClassNotFoundException;
    StockDTO searchStock(String stockId) throws SQLException, ClassNotFoundException;
    String generateNewStockId() throws SQLException, ClassNotFoundException;
}
