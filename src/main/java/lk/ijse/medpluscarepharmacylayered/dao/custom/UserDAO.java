package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    boolean auth(String username, String password) throws SQLException, ClassNotFoundException;

    String getUserId(String admin) throws SQLException, ClassNotFoundException;
}
