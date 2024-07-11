package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.OrderBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.*;
import lk.ijse.medpluscarepharmacylayered.db.DbConnection;
import lk.ijse.medpluscarepharmacylayered.entity.OrderTestDetail;
import lk.ijse.medpluscarepharmacylayered.view.tm.ItemCartTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderTestDetailDAO orderTestDetailDAO = (OrderTestDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_TEST_DETAIL);
    OrderItemDetailDAO orderItemDetailDAO = (OrderItemDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_ITEM_DETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean checkOut (List<ItemCartTm> cartItems, int contact, String totalLblText, String admin, String dateLblText){
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            admin = userDAO.getUserId(admin);
            String customer = customerDAO.searchCustomerByContact(String.valueOf(contact)).getCustomerId();

            boolean isOrderSaved = orderDAO.saveOrder(customer, totalLblText, admin, dateLblText);
            if (!isOrderSaved) {
                connection.rollback();
                return false;
            }

            String orderId = orderDAO.getOrderId();
            if (orderId == null) {
                connection.rollback();
                return false;
            }

            List<ItemCartTm> testItems = new ArrayList<>();
            List<ItemCartTm> nonTestItems = new ArrayList<>();

            for (ItemCartTm item : cartItems) {
                if (item.getItemId().startsWith("T")) {
                    testItems.add(item);
                } else {
                    nonTestItems.add(item);
                }
            }

            for (ItemCartTm item : nonTestItems) {
                boolean isOrderDetailSaved = orderItemDetailDAO.saveOrderDetail(item.getItemId(), item.getQty(), orderId);
                if (!isOrderDetailSaved) {
                    connection.rollback();
                    return false;
                }
            }

            for (ItemCartTm item : testItems) {
                boolean isTestOrderDetailSaved = orderTestDetailDAO.saveOrderDetail(item.getItemId(), item.getQty(), orderId);
                if (!isTestOrderDetailSaved) {
                    connection.rollback();
                    return false;
                }
            }

            for (ItemCartTm item : nonTestItems) {
                boolean isStockUpdated = itemDAO.updateStock(item.getItemId(), item.getQty());
                if (!isStockUpdated) {
                    connection.rollback();
                    return false;
                }
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
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderId();
    }

    @Override
    public String getDailySales(LocalDate today) throws SQLException, ClassNotFoundException {
        return orderDAO.dailySales(today);
    }

    @Override
    public String getMonthlySales(Month month, int year) throws SQLException, ClassNotFoundException {
        return orderDAO.monthlySales(month, year);
    }

    @Override
    public String getAnnualSales(int year) throws SQLException, ClassNotFoundException {
        return orderDAO.annualSales(year);
    }

    @Override
    public String getProfit(LocalDate today) throws SQLException, ClassNotFoundException {
        return orderDAO.getProfit(today);
    }

}
