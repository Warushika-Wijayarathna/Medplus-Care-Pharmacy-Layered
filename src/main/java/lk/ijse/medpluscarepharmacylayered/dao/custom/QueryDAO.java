package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.SuperDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Item> getAll() throws SQLException, ClassNotFoundException;
}
