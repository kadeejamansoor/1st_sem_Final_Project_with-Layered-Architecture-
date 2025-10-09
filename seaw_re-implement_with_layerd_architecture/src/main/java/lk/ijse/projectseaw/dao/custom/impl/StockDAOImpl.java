package lk.ijse.projectseaw.dao.custom.impl;

import lk.ijse.projectseaw.dao.custom.StockDAO;
import lk.ijse.projectseaw.dao.custom.impl.util.SQLUtil;
import lk.ijse.projectseaw.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Stock(stock_id, stock_name, quantity) VALUES(?, ?, ?)",
                entity.getStockId(), entity.getStockName(), entity.getQuantity() );
    }

    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStocks = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Stock ORDER BY stock_id DESC");
        while (rst.next()) {
            allStocks.add(new Stock(rst.getString(1), rst.getString(2), rst.getInt(3)));
        }
        return allStocks;
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Stock SET stock_name = ?, quantity = ? WHERE stock_id = ?",
                entity.getStockName(), entity.getQuantity(), entity.getStockId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Stock WHERE stock_id = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT stock_id FROM Stock ORDER BY stock_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("stock_id");
            int newCustomerId = Integer.parseInt(id.replace("St00-", "")) + 1;
            return String.format("St00-%03d", newCustomerId);
        } else {
            return "St00-001";
        }
    }

    @Override
    public Stock search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Stock WHERE stock_id = ?", s);
        Stock stock = new Stock();

        if (rst.next()) {
            stock.setStockId(rst.getString("stock_id"));
            stock.setStockName(rst.getString("stock_name"));
            stock.setQuantity(rst.getInt("quantity"));
        }

        return stock;
    }


}
