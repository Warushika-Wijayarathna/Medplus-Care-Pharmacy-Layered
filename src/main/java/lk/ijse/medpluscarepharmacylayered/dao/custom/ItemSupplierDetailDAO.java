package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;
import lk.ijse.medpluscarepharmacylayered.entity.ItemSupplierDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemSupplierDetailDAO extends CrudDAO<ItemSupplierDetail> {
    boolean save(Connection connection, Item itemId, String supplierId) throws SQLException, ClassNotFoundException;

    boolean deleteDetail(Connection connection, String itemId) throws SQLException, ClassNotFoundException;

    List<String> getSupplierIdsByItemId(String itemId) throws SQLException, ClassNotFoundException;
}
