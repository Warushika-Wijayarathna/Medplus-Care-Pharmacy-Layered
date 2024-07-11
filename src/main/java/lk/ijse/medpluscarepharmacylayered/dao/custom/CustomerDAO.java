package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer searchCustomerByCustId(String custId) throws SQLException, ClassNotFoundException;

    Customer searchCustomerByContact(String contactNumber) throws SQLException, ClassNotFoundException;

    String getCustomerId(String custId) throws SQLException, ClassNotFoundException;

    String searchCustomerByName(String selectedCust) throws SQLException, ClassNotFoundException;
}
