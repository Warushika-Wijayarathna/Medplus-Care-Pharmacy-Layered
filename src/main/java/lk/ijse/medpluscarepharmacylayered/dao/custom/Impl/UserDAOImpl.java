package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.UserDAO;
import lk.ijse.medpluscarepharmacylayered.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM User");
        ArrayList<User> users = new ArrayList<>();
        while (rst.next()) {
            users.add(new User(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return users;
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES (?,?)", entity.getUserName(), entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET userName=?,password=? WHERE userId=?", entity.getUserName(), entity.getPassword(), entity.getUserId());
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
        return SQLUtil.execute("DELETE FROM User WHERE userId=?", id);
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
