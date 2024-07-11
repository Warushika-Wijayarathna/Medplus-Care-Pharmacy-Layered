package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.UserDAO;
import lk.ijse.medpluscarepharmacylayered.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    public boolean auth(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM User WHERE usr_name = ?";

        ResultSet resultSet = SQLUtil.execute(null,sql, userName);
        if (resultSet.next()) {
            String dbPassword = resultSet.getString("password");
            return dbPassword.equals(password);
        } else {
            return false;
        }
    }

    @Override
    public String getUserId(String admin) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null,"SELECT usr_id FROM User WHERE usr_name=?", admin);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(null,"SELECT * FROM User");
        ArrayList<User> users = new ArrayList<>();
        while (rst.next()) {
            users.add(new User(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return users;
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"INSERT INTO User VALUES (?,?)", entity.getUserName(), entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"UPDATE User SET usr_name=?,password=? WHERE usr_id=?", entity.getUserName(), entity.getPassword(), entity.getUserId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"DELETE FROM User WHERE userId=?", id);
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
