package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    public boolean auth(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM User WHERE usr_name = ?";

        ResultSet resultSet = SQLUtil.execute(sql, userName);
        if (resultSet.next()) {
            String dbPassword = resultSet.getString("password");
            return dbPassword.equals(password);
        } else {
            return false;
        }
    }
}
