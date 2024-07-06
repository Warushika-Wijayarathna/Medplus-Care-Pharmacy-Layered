package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    boolean auth(String userName, String password) throws SQLException, ClassNotFoundException;
}
