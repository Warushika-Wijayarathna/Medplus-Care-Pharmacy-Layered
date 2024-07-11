package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.dto.SupplierDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Supplier;
import lk.ijse.medpluscarepharmacylayered.view.tm.SupplierTm;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    SupplierTm getSupplierBy(String selectedSupplierName) throws SQLException, ClassNotFoundException;

    List<Supplier> getSupplyDetailsBySupplierId(List<String> suppliers) throws SQLException, ClassNotFoundException;
}
