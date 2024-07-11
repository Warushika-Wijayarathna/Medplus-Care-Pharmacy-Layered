package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.OrderDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null, "SELECT CONCAT('O', LPAD(next_id, 4, '0')) FROM AutoIncrement_Order");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrder(String contact, String totalLblText, String admin, String dateLblText) throws SQLException, ClassNotFoundException {
        String orderId = generateNewID();

        return SQLUtil.execute(null, "INSERT INTO `Order` VALUES (?, ?, ?, ?, ?)",
                orderId, totalLblText, contact, admin, dateLblText);
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null, "SELECT o_id FROM `Order` ORDER BY o_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getProfit(LocalDate today) throws SQLException, ClassNotFoundException {
        Double profit = 0.0;
        ResultSet resultSet = SQLUtil.execute(null, "SELECT daily_profit FROM DailyProfitView WHERE sale_date = ?", today);
        if (resultSet.next()) {
            profit = resultSet.getDouble(1);
        }
        return profit.toString();
    }

    @Override
    public String annualSales(int year) throws SQLException, ClassNotFoundException {
        Double total = 0.0;
        ResultSet resultSet = SQLUtil.execute(null, "SELECT SUM(total) FROM `Order` WHERE YEAR(date) = ?", year);
        if (resultSet.next()) {
            total = resultSet.getDouble(1);
        }
        return total.toString();
    }

    @Override
    public String monthlySales(Month month, int year) throws SQLException, ClassNotFoundException {
        Double total = 0.0;
        ResultSet resultSet = SQLUtil.execute(null, "SELECT SUM(total) FROM `Order` WHERE MONTH(date) = ? AND YEAR(date) = ?", month.getValue(), year);
        if (resultSet.next()) {
            total = resultSet.getDouble(1);
        }
        return total.toString();
    }

    @Override
    public String dailySales(LocalDate today) throws SQLException, ClassNotFoundException {
        Double total = 0.0;
        ResultSet resultSet = SQLUtil.execute(null, "SELECT SUM(total) FROM `Order` WHERE date = ?", today);
        if (resultSet.next()) {
            total = resultSet.getDouble(1);
        }
        return total.toString();
    }
}
