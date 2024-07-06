package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.TestDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestDAOImpl implements TestDAO {
    @Override
    public ArrayList<Test> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Test entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Test entity) throws SQLException, ClassNotFoundException {
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
    public Test search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
