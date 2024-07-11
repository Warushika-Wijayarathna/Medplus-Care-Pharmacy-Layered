package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.ItemBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ItemDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ItemSupplierDetailDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.SupplierDAO;
import lk.ijse.medpluscarepharmacylayered.db.DbConnection;
import lk.ijse.medpluscarepharmacylayered.dto.ItemDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    ItemSupplierDetailDAO itemSupplierDetailDAO = (ItemSupplierDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM_SUPPLIER_DETAIL);

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        List<ItemDTO> allItems = new ArrayList<>();
        List<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(
                    item.getItemId(),
                    item.getDescription(),
                    item.getQty(),
                    item.getWholeSalePrice(),
                    item.getRetailPrice(),
                    item.getDiscount(),
                    item.getExpDate()));
        }
        return allItems;
    }

    @Override
    public boolean deleteItem(ItemDTO item) {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isDetailDeleted = itemSupplierDetailDAO.deleteDetail(connection, item.getItemId());
            System.out.println("Details deleted: " + isDetailDeleted);
            if (!isDetailDeleted) {
                System.out.println("Detail not deleted");
                connection.rollback();
                return false;
            }
            boolean isItemDeleted = itemDAO.delete(item.getItemId());
            if (!isItemDeleted) {
                System.out.println("Item not deleted");
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateItem(boolean isCartEmpty, ItemDTO updatedItem, List<String> allSupplierIds) {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            Item item = new Item(
                    updatedItem.getItemId(),
                    updatedItem.getDescription(),
                    updatedItem.getQty(),
                    updatedItem.getWholeSalePrice(),
                    updatedItem.getRetailPrice(),
                    updatedItem.getDiscount(),
                    updatedItem.getExpDate());

            boolean isItemUpdated = itemDAO.update(item);
            if (!isItemUpdated) {
                System.out.println("Item not updated");
                connection.rollback();
                return false;
            }
            if (!isCartEmpty) {
                boolean isDetailsDeleted = itemSupplierDetailDAO.deleteDetail(connection, updatedItem.getItemId());
                System.out.println("Details deleted: " + isDetailsDeleted);
                if (!isDetailsDeleted) {
                    System.out.println("Details not deleted");
                    connection.rollback();
                    return false;
                }
            }

            for (String supplierId : allSupplierIds) {
                System.out.println("Supplier ID: " + supplierId);
                itemSupplierDetailDAO.save(connection, item, supplierId);
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<String> getSupplierIdsByItemId(String itemId) throws SQLException, ClassNotFoundException {
        return itemSupplierDetailDAO.getSupplierIdsByItemId(itemId);
    }

    @Override
    public String generateItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }

    @Override
    public boolean saveItem(ItemDTO newItem, List<String> allSupplierIds) {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isItemSaved = itemDAO.addItem(connection, newItem);
            if (!isItemSaved) {
                connection.rollback();
                return false;
            }

            String itemId = itemDAO.getGeneratedItemId(connection);
            connection.commit();

            connection.setAutoCommit(false);
            Item item = new Item(itemId, newItem.getDescription(), newItem.getQty(), newItem.getWholeSalePrice(), newItem.getRetailPrice(), newItem.getDiscount(), newItem.getExpDate());

            for (String supplierId : allSupplierIds) {
                boolean isItemSupplierDetailSaved = itemSupplierDetailDAO.save(connection, item, supplierId);
                if (!isItemSupplierDetailSaved) {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
        return true;
    }


    @Override
    public boolean deleteEmpty(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(item.getItemId());
    }

    @Override
    public List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        List<ItemDTO> allItems = new ArrayList<>();
        List<Item> all = queryDAO.getAll();

        for (Item item : all) {
            allItems.add(new ItemDTO(
                    item.getItemId(),
                    item.getDescription(),
                    item.getRetailPrice(),
                    item.getDiscount()));
        }
        return allItems;
    }
}
