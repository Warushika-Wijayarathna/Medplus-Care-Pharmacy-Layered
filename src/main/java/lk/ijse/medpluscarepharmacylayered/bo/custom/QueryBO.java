package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;

import java.sql.SQLException;

public interface QueryBO extends SuperBO {
    boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException;
}
