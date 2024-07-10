package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescTestDetailDAO;
import lk.ijse.medpluscarepharmacylayered.entity.PrescTestDetail;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescTestDetailDAOImpl implements PrescTestDetailDAO {
    @Override
    public ArrayList<PrescTestDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(PrescTestDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PrescTestDetail entity) throws SQLException, ClassNotFoundException {
        return false;
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
        return false;
    }

    @Override
    public PrescTestDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addPresc(Connection connection, String prescriptionId, String testId) throws SQLException {
        System.out.println("Prescription ID: "+ prescriptionId);
        System.out.println("Test ID: "+ testId);
        boolean result = SQLUtil.execute(connection, "INSERT INTO presc_test_detail (presc_id, test_id) VALUES (?, ?)", prescriptionId, testId);
        return result;
    }

    @Override
    public List<Test> getTestsByPrescriptionId(String prescId) throws SQLException, ClassNotFoundException {
        List<Test> testsByDesc = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Test WHERE test_id IN (SELECT test_id FROM presc_test_detail WHERE presc_id = ?)", prescId);
        while (rst.next()) {
            Test test = new Test(
                    rst.getString("test_id"),
                    rst.getString("description"),
                    rst.getString("lab"),
                    rst.getString("sample_type"),
                    rst.getString("test_type"),
                    rst.getDouble("price"));
            testsByDesc.add(test);

            System.out.println("Test :"+ test.getTestId()+test.getDescription()+test.getLab()+ test.getSampleType()+test.getTestType()+test.getPrice());
        }
        return testsByDesc;
    }

    @Override
    public boolean deletePresc(Connection connection, String prescriptionId) throws SQLException {
        boolean result = SQLUtil.execute(connection, "DELETE FROM presc_test_detail WHERE presc_id = ?", prescriptionId);
        System.out.println("Result: "+ result);
        return result;
    }

}
