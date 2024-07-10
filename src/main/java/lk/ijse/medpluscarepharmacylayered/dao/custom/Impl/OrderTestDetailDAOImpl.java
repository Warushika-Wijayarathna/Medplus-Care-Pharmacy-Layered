package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.custom.OrderTestDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.OrderTestDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderTestDetailDAOImpl implements OrderTestDetailDAO {
    @Override
    public ArrayList<OrderTestDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(OrderTestDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderTestDetail entity) throws SQLException, ClassNotFoundException {
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
    public boolean delete( String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderTestDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
