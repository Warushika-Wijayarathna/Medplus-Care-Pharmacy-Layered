package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.ItemSupplierDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.ItemSupplierDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

}
