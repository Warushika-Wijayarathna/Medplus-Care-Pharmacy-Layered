package lk.ijse.medpluscarepharmacylayered.dao.custom;

import lk.ijse.medpluscarepharmacylayered.dao.CrudDAO;
import lk.ijse.medpluscarepharmacylayered.dto.PrescriptionDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Prescription;

import java.sql.Connection;
import java.sql.SQLException;

public interface PrescriptionDAO extends CrudDAO<Prescription> {
    boolean add(Connection connection, PrescriptionDTO newPresc) throws SQLException, ClassNotFoundException;


}
