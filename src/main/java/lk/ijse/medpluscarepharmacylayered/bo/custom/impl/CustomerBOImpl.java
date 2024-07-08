package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.CustomerBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.CustomerDAO;
import lk.ijse.medpluscarepharmacylayered.dto.CustomerDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers= customerDAO.getAll();
        List<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            allCustomers.add(
                    new CustomerDTO(customer.getCustomerId(),
                            customer.getName(),
                            customer.getContactNo(),
                            customer.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public void deleteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        customerDAO.delete(customer.getCustomerId());
    }

    @Override
    public void updateCustomer(CustomerDTO updatedCustomer) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(updatedCustomer.getCustomerId(),
                updatedCustomer.getName(),
                updatedCustomer.getContactNo(),
                updatedCustomer.getEmail()));
    }

    @Override
    public void addCustomer(CustomerDTO newCustomer) throws SQLException, ClassNotFoundException {
        customerDAO.add(new Customer(newCustomer.getCustomerId(),
                newCustomer.getName(),
                newCustomer.getContactNo(),
                newCustomer.getEmail()));
    }


    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

}
