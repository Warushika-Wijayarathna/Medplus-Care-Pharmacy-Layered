package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.TemperatureDAO;
import lk.ijse.medpluscarepharmacylayered.entity.TemperatureReading;

import java.sql.SQLException;
import java.util.ArrayList;

public class TemperatureDAOImpl implements TemperatureDAO {
    @Override
    public ArrayList<TemperatureReading> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(TemperatureReading entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TemperatureReading entity) throws SQLException, ClassNotFoundException {
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
    public TemperatureReading search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
