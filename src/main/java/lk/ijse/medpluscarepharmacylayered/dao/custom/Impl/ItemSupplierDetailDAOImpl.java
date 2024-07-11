package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ItemSupplierDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;
import lk.ijse.medpluscarepharmacylayered.entity.ItemSupplierDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemSupplierDetailDAOImpl implements ItemSupplierDetailDAO {
    @Override
    public ArrayList<ItemSupplierDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ItemSupplierDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ItemSupplierDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemSupplierDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Connection connection, Item itemId, String supplierId) throws SQLException, ClassNotFoundException {
        System.out.println("ItemId: " + itemId.getItemId() + " SupplierId: " + supplierId);
        return SQLUtil.execute(connection, "INSERT INTO item_supplier_detail VALUES (?,?)", supplierId, itemId.getItemId());
    }

    @Override
    public boolean deleteDetail(Connection connection, String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "DELETE FROM item_supplier_detail WHERE item_id=?", itemId);
    }

    @Override
    public List<String> getSupplierIdsByItemId(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null,"SELECT sp_id FROM item_supplier_detail WHERE item_id=?", itemId);
        List<String> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(resultSet.getString("sp_id"));
        }
        return suppliers;
    }
}
