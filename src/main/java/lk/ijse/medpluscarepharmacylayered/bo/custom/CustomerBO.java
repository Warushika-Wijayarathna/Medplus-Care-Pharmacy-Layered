package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    void deleteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    void updateCustomer(CustomerDTO updatedCustomer) throws SQLException, ClassNotFoundException;

    void addCustomer(CustomerDTO newCustomer) throws SQLException, ClassNotFoundException;

    String generateCustomerId() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomerByCustId(String custId) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomerByContact(String contactNumber) throws SQLException, ClassNotFoundException;

    List<String> getAllCustNames() throws SQLException, ClassNotFoundException;

    String getCustomerId(String custId) throws SQLException, ClassNotFoundException;

    String searchCustomerByName(String selectedCust) throws SQLException, ClassNotFoundException;
}
