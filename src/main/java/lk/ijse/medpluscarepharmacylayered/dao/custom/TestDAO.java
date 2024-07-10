package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.SQLException;

public interface TestDAO extends CrudDAO<Test> {
    boolean checkInstant(String testId) throws SQLException, ClassNotFoundException;

    String getTestName(String testId) throws SQLException, ClassNotFoundException;

    Test getBy(String selectedTest) throws SQLException, ClassNotFoundException;
}
