package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    boolean saveOrder(String contact, String totalLblText, String admin, String dateLblText) throws SQLException, ClassNotFoundException;

    String getOrderId() throws SQLException, ClassNotFoundException;
}
