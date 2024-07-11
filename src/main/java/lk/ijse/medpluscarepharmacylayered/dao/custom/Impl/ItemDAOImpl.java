package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ItemDAO;
import lk.ijse.medpluscarepharmacylayered.dto.ItemDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Item");
        while (resultSet.next()) {
            allItems.add(new Item(
                    resultSet.getString("item_id"),
                    resultSet.getString("description"),
                    resultSet.getInt("qty"),
                    resultSet.getDouble("whole_sale_price"),
                    resultSet.getDouble("retail_price"),
                    resultSet.getDouble("discount"),
                    resultSet.getDate("exp_date").toLocalDate()));
        }
        return allItems;
    }

    @Override
    public boolean add(Item entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"UPDATE Item SET description=?, qty=?, whole_sale_price=?, retail_price=?, discount=?, exp_date=? WHERE item_id=?",
                entity.getDescription(),
                entity.getQty(),
                entity.getWholeSalePrice(),
                entity.getRetailPrice(),
                entity.getDiscount(),
                entity.getExpDate(),
                entity.getItemId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null,"SELECT CONCAT('I', LPAD(next_id, 5, '0')) FROM AutoIncrement_Item");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"DELETE FROM Item WHERE item_id=?", id);
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addItem(Connection connection, ItemDTO newItem) throws SQLException, ClassNotFoundException {
        System.out.println("Item updated (add Item ):"+newItem.getItemId()+" "+newItem.getDescription()+" "+newItem.getQty()+" "+newItem.getWholeSalePrice()+" "+newItem.getRetailPrice()+" "+newItem.getDiscount()+" "+newItem.getExpDate());
        return SQLUtil.execute(connection, "INSERT INTO Item (description, qty, whole_sale_price, retail_price, discount, exp_date) VALUES (?, ?, ?, ?, ?, ?)",
                newItem.getDescription(),
                newItem.getQty(),
                newItem.getWholeSalePrice(),
                newItem.getRetailPrice(),
                newItem.getDiscount(),
                newItem.getExpDate());
    }

    @Override
    public String getGeneratedItemId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(connection, "SELECT * FROM Item ORDER BY item_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString("item_id");
        }
        return null;
    }

    @Override
    public boolean updateStock(String itemId, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null, "UPDATE Item SET qty=qty-? WHERE item_id=?", qty, itemId);
    }
}
