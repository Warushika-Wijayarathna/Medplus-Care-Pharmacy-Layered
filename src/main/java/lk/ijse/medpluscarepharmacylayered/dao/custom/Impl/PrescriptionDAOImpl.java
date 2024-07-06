package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescriptionDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public ArrayList<Prescription> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Prescription entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Prescription entity) throws SQLException, ClassNotFoundException {
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
    public Prescription search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
