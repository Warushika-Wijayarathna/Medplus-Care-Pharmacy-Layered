package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.OrderBO;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}
