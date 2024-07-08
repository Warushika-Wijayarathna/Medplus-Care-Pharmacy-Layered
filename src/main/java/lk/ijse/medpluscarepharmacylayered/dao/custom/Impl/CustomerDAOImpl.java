package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.CustomerDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("cust_id"), rst.getString("name"), rst.getInt("contact_no"), rst.getString("email"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (name, contact_no, email) VALUES (?,?,?)", entity.getName(), entity.getContactNo(), entity.getEmail());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, contact_no=?, email=? WHERE cust_id=?", entity.getName(), entity.getContactNo(), entity.getEmail(), entity.getCustomerId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('C', LPAD(next_id, 4, '0')) FROM AutoIncrement_Customer");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE cust_id=?", id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        Customer customer = null;
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE cust_id=?", id);
        if (rst.next()) {
            customer = new Customer(rst.getString("cust_id"), rst.getString("name"), rst.getInt("contact_no"), rst.getString("email"));
        }
        return customer;
    }

}
