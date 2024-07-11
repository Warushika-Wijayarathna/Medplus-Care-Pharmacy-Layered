package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.OrderTestDetail;

import java.sql.SQLException;

public interface OrderTestDetailDAO extends CrudDAO<OrderTestDetail> {
    boolean saveOrderDetail(String itemId, int qty, String orderId) throws SQLException, ClassNotFoundException;
}
