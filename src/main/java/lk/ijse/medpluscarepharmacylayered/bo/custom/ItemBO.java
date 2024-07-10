package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    List<ItemDTO> getAllItem();

    boolean deleteItem(ItemDTO item);

    boolean updateItem(ItemDTO updatedItem, List<String> allSupplierIds);

    List<String> getSupplierIdsByItemId(String itemId);

    String generateItemId(ItemDTO itemDTO);

    boolean saveItem(ItemDTO newItem, List<String> allSupplierIds);
}
