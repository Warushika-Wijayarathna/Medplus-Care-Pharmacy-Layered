package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.view.tm.ItemCartTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean checkOut(List<ItemCartTm> cartItems, int contact, String text, String admin, String text1);

    String getOrderId() throws SQLException, ClassNotFoundException;

    String getDailySales(LocalDate today) throws SQLException, ClassNotFoundException;

    String getMonthlySales(Month month, int year) throws SQLException, ClassNotFoundException;

    String getAnnualSales(int year) throws SQLException, ClassNotFoundException;

    String getProfit(LocalDate today) throws SQLException, ClassNotFoundException;
}
