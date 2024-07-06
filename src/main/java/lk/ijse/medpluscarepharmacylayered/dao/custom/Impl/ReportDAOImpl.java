package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.ReportDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public ArrayList<Report> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Report entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Report entity) throws SQLException, ClassNotFoundException {
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
    public Report search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
