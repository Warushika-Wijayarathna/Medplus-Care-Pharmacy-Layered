package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.entity.PrescTestDetail;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PrescTestDetailDAO extends CrudDAO<PrescTestDetail> {
    boolean addPresc(Connection connection, String prescriptionId, String testId) throws SQLException;

    List<Test> getTestsByPrescriptionId(String prescId) throws SQLException, ClassNotFoundException;

    boolean deletePresc(Connection connection, String prescriptionId) throws SQLException;
}
