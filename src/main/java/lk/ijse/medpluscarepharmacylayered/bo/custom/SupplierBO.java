package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.SupplierDTO;
import lk.ijse.medpluscarepharmacylayered.view.tm.SupplierTm;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    void deleteSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    void updateSupplier(SupplierDTO updatedSupplier) throws SQLException, ClassNotFoundException;

    void addSupplier(SupplierDTO newSupplier) throws SQLException, ClassNotFoundException;

    String generateSupplierId(SupplierDTO newSupplier) throws SQLException, ClassNotFoundException;

    List <String> getAllSupplierNames() throws SQLException, ClassNotFoundException;

    List<SupplierDTO> getSupplierDetailsBySupplierId(List<String> suppliers) throws SQLException, ClassNotFoundException;

    SupplierTm getSupplierTmByName(String selectedSupplierName) throws SQLException, ClassNotFoundException;
}
