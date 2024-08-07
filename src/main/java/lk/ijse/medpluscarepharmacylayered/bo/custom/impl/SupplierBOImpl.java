package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.SupplierBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.SupplierDAO;
import lk.ijse.medpluscarepharmacylayered.dto.SupplierDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Supplier;
import lk.ijse.medpluscarepharmacylayered.view.tm.SupplierTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        List<Supplier> suppliers = supplierDAO.getAll();

        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
        }

        return supplierDTOS;
    }

    @Override
    public void deleteSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        supplierDAO.delete( supplier.getSupplierId());
    }

    @Override
    public void updateSupplier(SupplierDTO updatedSupplier) throws SQLException, ClassNotFoundException {
        supplierDAO.update(new Supplier(updatedSupplier.getSupplierId(), updatedSupplier.getName(), updatedSupplier.getContact(), updatedSupplier.getEmail()));
    }

    @Override
    public void addSupplier(SupplierDTO newSupplier) throws SQLException, ClassNotFoundException {
        supplierDAO.add(new Supplier(newSupplier.getSupplierId(), newSupplier.getName(), newSupplier.getContact(), newSupplier.getEmail()));
    }

    @Override
    public String generateSupplierId(SupplierDTO newSupplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public List<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        List<String> supplierNames = new ArrayList<>();
        List<SupplierDTO> supplierDTOS = getAllSuppliers();
        for (SupplierDTO supplierDTO : supplierDTOS) {
            supplierNames.add(supplierDTO.getName());
        }
        return supplierNames;
    }

    @Override
    public List<SupplierDTO> getSupplierDetailsBySupplierId(List<String> suppliers) throws SQLException, ClassNotFoundException {
        List<Supplier> supplierList = new ArrayList<>();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        supplierList = supplierDAO.getSupplyDetailsBySupplierId(suppliers);
        for (Supplier supplier : supplierList) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
        }

        return supplierDTOS;
    }

    @Override
    public SupplierTm getSupplierTmByName(String selectedSupplierName) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierBy(selectedSupplierName);
    }

}
