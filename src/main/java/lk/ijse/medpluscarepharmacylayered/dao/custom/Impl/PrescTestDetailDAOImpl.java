package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescTestDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.PrescTestDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrescTestDetailDAOImpl implements PrescTestDetailDAO {
    @Override
    public ArrayList<PrescTestDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(PrescTestDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PrescTestDetail entity) throws SQLException, ClassNotFoundException {
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
    public PrescTestDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
