package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;


import lk.ijse.medpluscarepharmacylayered.bo.custom.PrescriptionBO;

import java.sql.SQLException;

public class PrescriptionBOImpl implements PrescriptionBO {
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}
