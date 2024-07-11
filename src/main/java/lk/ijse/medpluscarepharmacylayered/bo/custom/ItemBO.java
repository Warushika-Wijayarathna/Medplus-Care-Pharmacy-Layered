package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean deleteItem(ItemDTO item);

    boolean updateItem(boolean isCartEmpty, ItemDTO updatedItem, List<String> allSupplierIds);

    List<String> getSupplierIdsByItemId(String itemId) throws SQLException, ClassNotFoundException;

    String generateItemId() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO newItem, List<String> allSupplierIds);

    boolean deleteEmpty(ItemDTO item) throws SQLException, ClassNotFoundException;

    List<ItemDTO> getAll() throws SQLException, ClassNotFoundException;
}
