package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.TestDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDAOImpl implements TestDAO {
    @Override
    public ArrayList<Test> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Test> allTests = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Test");
        while (rst.next()) {
            Test test = new Test(rst.getString("test_id"), rst.getString("description"), rst.getString("lab"), rst.getString("sample_type"), rst.getString("test_type"), rst.getDouble("price"));
            allTests.add(test);
        }
        return allTests;
    }

    @Override
    public boolean add(Test entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Test (description, lab, sample_type, test_type, price) VALUES (?,?,?,?,?,?)",
                entity.getDescription(), entity.getLab(), entity.getSampleType(), entity.getTestType(), entity.getPrice());
    }

    @Override
    public boolean update(Test entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Test SET description=?, lab=?, sample_type=?, test_type=?, price=? WHERE test_id=?",
                entity.getDescription(), entity.getLab(), entity.getSampleType(), entity.getTestType(), entity.getPrice(), entity.getTestId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('T', LPAD(next_id, 4, '0')) FROM AutoIncrement_Test");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Test WHERE test_id=?", id);
    }

    @Override
    public Test search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
