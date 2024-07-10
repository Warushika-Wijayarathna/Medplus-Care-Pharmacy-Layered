package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.PrescriptionDTO;
import lk.ijse.medpluscarepharmacylayered.dto.TestDTO;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionBO extends SuperBO {
    List<TestDTO> getTestsByPrescriptionId(String prescId) throws SQLException, ClassNotFoundException;

    String generatePrescriptionId() throws SQLException, ClassNotFoundException;

    List<PrescriptionDTO> getAllPrescriptions() throws SQLException, ClassNotFoundException;

    boolean deletePresc(PrescriptionDTO prescription);

    boolean updatePrescription(PrescriptionDTO updatedPresc, List<String> allTestIds);

    boolean update(PrescriptionDTO updatedPresc) throws SQLException, ClassNotFoundException;

    boolean addPrescriptionWithTestIds(PrescriptionDTO newPresc, List<String> testIds) throws SQLException;

    boolean deletePrescEmpty(PrescriptionDTO prescription) throws SQLException, ClassNotFoundException;
}
