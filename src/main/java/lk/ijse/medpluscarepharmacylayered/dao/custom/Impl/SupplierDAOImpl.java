package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.SupplierDAO;
import lk.ijse.medpluscarepharmacylayered.dto.SupplierDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Supplier;
import lk.ijse.medpluscarepharmacylayered.view.tm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int contact = resultSet.getInt(3);
            String email = resultSet.getString(4);
            suppliers.add(new Supplier(id, name, contact, email));
        }
        return suppliers;
    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"INSERT INTO Supplier VALUES (?,?,?)",entity.getName(), entity.getContact(), entity.getEmail());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"UPDATE Supplier SET name=?, contact=?, email=? WHERE sp_id=?", entity.getName(), entity.getContact(), entity.getEmail(), entity.getSupplierId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null,"SELECT CONCAT('S', LPAD(next_id, 4, '0')) FROM AutoIncrement_Supplier");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete( String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"DELETE FROM Supplier WHERE sp_id=?", id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = null;
        ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Supplier WHERE sp_id=?", id);
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int contact = resultSet.getInt(3);
            String email = resultSet.getString(4);
            supplier = new Supplier(supplierId, name, contact, email);
        }
        return supplier;
    }

    @Override
    public SupplierTm getSupplierBy(String selectedSupplierName) throws SQLException, ClassNotFoundException {
        SupplierTm supplier = null;
        ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Supplier WHERE name=?", selectedSupplierName);
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int contact = resultSet.getInt(3);
            String email = resultSet.getString(4);
            supplier = new SupplierTm(supplierId, name, contact, email, null);
        }
        return supplier;
    }

    @Override
    public List<Supplier> getSupplyDetailsBySupplierId(List<String> suppliers) throws SQLException, ClassNotFoundException {
        List<Supplier> supplierDTOS = new ArrayList<>();
        for (String supplierId : suppliers) {
                ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Supplier WHERE sp_id=?", supplierId);
            while (resultSet.next()) {
                String id = resultSet.getString("sp_id");
                String name = resultSet.getString("name");
                Supplier supplier = new Supplier(id, name);
                supplierDTOS.add(supplier);
            }
        }
        return supplierDTOS;
    }
}
