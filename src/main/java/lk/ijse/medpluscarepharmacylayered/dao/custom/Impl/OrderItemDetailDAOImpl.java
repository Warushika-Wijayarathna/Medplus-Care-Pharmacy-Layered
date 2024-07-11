package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.OrderItemDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.OrderItemDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDetailDAOImpl implements OrderItemDetailDAO {
    @Override
    public ArrayList<OrderItemDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(OrderItemDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderItemDetail entity) throws SQLException, ClassNotFoundException {
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
    public OrderItemDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetail(String itemId, int qty, String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"INSERT INTO order_item_detail VALUES (?,?,?)", orderId,itemId, qty);
    }
}
