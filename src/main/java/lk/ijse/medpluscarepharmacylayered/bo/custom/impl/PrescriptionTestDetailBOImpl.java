package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.PrescriptionTestDetailBO;

import java.sql.SQLException;

public class PrescriptionTestDetailBOImpl implements PrescriptionTestDetailBO {
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}