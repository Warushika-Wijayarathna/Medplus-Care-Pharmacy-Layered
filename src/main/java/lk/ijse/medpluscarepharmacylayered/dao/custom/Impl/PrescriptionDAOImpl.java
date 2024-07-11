package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescriptionDAO;
import lk.ijse.medpluscarepharmacylayered.dto.PrescriptionDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Prescription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public ArrayList<Prescription> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Prescription> allPrescriptions = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(null,"SELECT * FROM Prescription");
        while (rst.next()) {
            Prescription prescription = new Prescription(
                    rst.getString("presc_id"),
                    rst.getString("cust_id"),
                    rst.getString("patient_name"),
                    rst.getInt("age"),
                    rst.getString("medical_officer_name"),
                    rst.getString("context"),
                    rst.getString("duration"),
                    LocalDate.parse(rst.getString("date")));
            allPrescriptions.add(prescription);
        }

        return allPrescriptions;
    }

    @Override
    public boolean add(Prescription entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Prescription entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"UPDATE Prescription SET cust_id = ?, patient_name = ?, age = ?, medical_officer_name = ?, context = ?, duration = ?, date = ? WHERE presc_id = ?",
                entity.getCustomerId(),
                entity.getPatientName(),
                entity.getAge(),
                entity.getMedicalOfficerName(),
                entity.getContext(),
                entity.getDuration(),
                entity.getDate(),
                entity.getPrescriptionId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(null,"SELECT CONCAT('PR' , LPAD(next_id, 3, '0')) FROM AutoIncrement_Prescription");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"DELETE FROM Prescription WHERE presc_id = ?", id);
    }

    @Override
    public Prescription search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Connection connection, PrescriptionDTO newPresc) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(connection, "INSERT INTO Prescription (cust_id, patient_name, age, medical_officer_name, context, duration, date) VALUES (?, ?, ?, ?, ?, ?, ?)",
                newPresc.getCustomerId(),
                newPresc.getPatientName(),
                newPresc.getAge(),
                newPresc.getMedicalOfficerName(),
                newPresc.getContext(),
                newPresc.getDuration(),
                newPresc.getDate());
        return true;
    }

    @Override
    public String getGeneratedPrescriptionId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection, "SELECT * FROM Prescription ORDER BY presc_id DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString("presc_id");
            }
        return null;
    }


}
