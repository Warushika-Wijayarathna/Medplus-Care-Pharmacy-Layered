package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.ReportBO;

import java.sql.SQLException;

public class ReportBOImpl implements ReportBO {
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}