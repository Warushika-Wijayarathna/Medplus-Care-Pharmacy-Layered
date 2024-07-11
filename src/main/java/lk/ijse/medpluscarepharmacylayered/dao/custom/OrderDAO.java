package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

public interface OrderDAO extends CrudDAO<Order> {
    boolean saveOrder(String contact, String totalLblText, String admin, String dateLblText) throws SQLException, ClassNotFoundException;

    String getOrderId() throws SQLException, ClassNotFoundException;

    String getProfit(LocalDate today) throws SQLException, ClassNotFoundException;

    String annualSales(int year) throws SQLException, ClassNotFoundException;

    String monthlySales(Month month, int year) throws SQLException, ClassNotFoundException;

    String dailySales(LocalDate today) throws SQLException, ClassNotFoundException;
}
