package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.OrderItemDetail;

import java.sql.SQLException;

public interface OrderItemDetailDAO extends CrudDAO<OrderItemDetail> {
    boolean saveOrderDetail(String itemId, int qty, String orderId) throws SQLException, ClassNotFoundException;
}
