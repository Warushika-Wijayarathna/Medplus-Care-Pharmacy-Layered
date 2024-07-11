package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.dto.ItemDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean addItem(Connection connection, ItemDTO newItem) throws SQLException, ClassNotFoundException;

    String getGeneratedItemId(Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateStock(String itemId, int qty) throws SQLException, ClassNotFoundException;
}
