package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.TestDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.Connection;
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

    @Override
    public boolean checkInstant(String testId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT test_type FROM Test WHERE test_id = ?", testId);
        if (resultSet.next()) {
            String testType = resultSet.getString("test_type");
            return "instant".equalsIgnoreCase(testType.trim());
        }
        return false;
    }

    @Override
    public String getTestName(String testId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT description FROM Test WHERE test_id = ?", testId);
    }

    @Override
    public Test getBy(String selectedTest) throws SQLException, ClassNotFoundException {
        try {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM Test WHERE description = ?", selectedTest);
            Test test = null;
            if (resultSet.next()) {
                test = new Test(
                        resultSet.getString("test_id"),
                        resultSet.getString("description"),
                        resultSet.getString("lab"),
                        resultSet.getString("sample_type"),
                        resultSet.getString("test_type"),
                        resultSet.getDouble("price"));
            }
            System.out.println("Test :" + test.getTestId()+test.getDescription() + test.getLab() + test.getSampleType() + test.getTestType() + test.getPrice());
            return test;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
