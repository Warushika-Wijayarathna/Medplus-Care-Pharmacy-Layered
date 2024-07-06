package lk.ijse.medpluscarepharmacylayered.bo;

import lk.ijse.medpluscarepharmacylayered.dto.CustomerDTO;

import java.sql.SQLException;

public interface SuperBO {
    boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException;

}
