package lk.ijse.projectseaw.bo.custom.impl;

import lk.ijse.projectseaw.bo.custom.StockManageBO;
import lk.ijse.projectseaw.dao.DAOFactory;
import lk.ijse.projectseaw.dao.custom.StockDAO;
import lk.ijse.projectseaw.dto.StockDTO;
import lk.ijse.projectseaw.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageBOImpl implements StockManageBO {
    StockDAO stockDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> all = stockDAO.getAll();
        ArrayList<StockDTO> arrayList= new ArrayList<>();
        for (Stock s : all) {
            arrayList.add(new StockDTO(s.getStockId(), s.getStockName(), s.getQuantity()));
        }
        return arrayList;
    }

    @Override
    public boolean saveStock(StockDTO s) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(s.getStockId(), s.getStockName(), s.getQuantity()));
    }

    @Override
    public boolean updateStock(StockDTO s) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(s.getStockId(), s.getStockName(), s.getQuantity()));
    }

    @Override
    public boolean deleteStock(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public StockDTO searchStock(String stockId) throws SQLException, ClassNotFoundException {
        Stock s = stockDAO.search(stockId);
        return new StockDTO(s.getStockId(), s.getStockName(), s.getQuantity());

    }

    @Override
    public String generateNewStockId() throws SQLException, ClassNotFoundException {
        return stockDAO.generateNewID();
    }
}
