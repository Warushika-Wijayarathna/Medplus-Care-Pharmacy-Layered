package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;


import lk.ijse.medpluscarepharmacylayered.bo.custom.PrescriptionBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescTestDetailDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.PrescriptionDAO;
import lk.ijse.medpluscarepharmacylayered.db.DbConnection;
import lk.ijse.medpluscarepharmacylayered.dto.PrescriptionDTO;
import lk.ijse.medpluscarepharmacylayered.dto.TestDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Prescription;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionBOImpl implements PrescriptionBO {
    PrescriptionDAO prescriptionDAO = (PrescriptionDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRESCRIPTION);
    PrescTestDetailDAO prescTestDetailDAO = (PrescTestDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRESC_TEST_DETAIL);
    @Override
    public List<TestDTO> getTestsByPrescriptionId(String prescId) throws SQLException, ClassNotFoundException {
        List<Test> tests = prescTestDetailDAO.getTestsByPrescriptionId(prescId);
        List<TestDTO> testDTOS = new ArrayList<>();
        for (Test test : tests) {
            testDTOS.add(new TestDTO(test.getTestId(),
                    test.getDescription(),
                    test.getLab(),
                    test.getSampleType(),
                    test.getTestType(),
                    test.getPrice()));
        }
        return testDTOS;
    }

    @Override
    public String generatePrescriptionId() throws SQLException, ClassNotFoundException {
        return prescriptionDAO.generateNewID();
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() throws SQLException, ClassNotFoundException {
        List<PrescriptionDTO> allPrescriptions = new ArrayList<>();
        List<Prescription> all = prescriptionDAO.getAll();
        for (Prescription prescription : all) {
            allPrescriptions.add(new PrescriptionDTO(
                    prescription.getPrescriptionId(),
                    prescription.getCustomerId(),
                    prescription.getPatientName(),
                    prescription.getAge(),
                    prescription.getMedicalOfficerName(),
                    prescription.getContext(),
                    prescription.getDuration(),
                    prescription.getDate()));
        }
        return allPrescriptions;
    }

    @Override
    public boolean deletePresc(PrescriptionDTO prescription) {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isDetailsDeleted = prescTestDetailDAO.deletePresc(connection, prescription.getPrescriptionId());
            System.out.println("Details deleted: " + isDetailsDeleted);
            if (!isDetailsDeleted) {
                System.out.println("Detail not deleted");
                connection.rollback();
                return false;
            }
            boolean isPrescriptionDeleted = prescriptionDAO.delete(prescription.getPrescriptionId());
            if (!isPrescriptionDeleted) {
                System.out.println("Prescription not deleted");
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updatePrescription(PrescriptionDTO updatedPresc, List<String> allTestIds) {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isPrescriptionUpdated = update(updatedPresc);
            if (!isPrescriptionUpdated) {
                System.out.println("PrescriptionDAOImpl update method failed");
                connection.rollback();
                return false;
            }

            boolean isOldDetailsDeleted = prescTestDetailDAO.deletePresc(connection, updatedPresc.getPrescriptionId());
            System.out.println("isOldDetailsDeleted: " + isOldDetailsDeleted);
            if (!isOldDetailsDeleted) {
                System.out.println("PrescTestDetailDAOImpl delete method failed");
                connection.rollback();
                return false;
            }

            for (String testId : allTestIds) {
                boolean isTestAdded = prescTestDetailDAO.addPresc(connection, updatedPresc.getPrescriptionId(), testId);
                System.out.println("isTestAdded: " + isTestAdded + " for testId: " + testId);
                if (!isTestAdded) {
                    System.out.println("PrescTestDetailDAOImpl add method failed for testId: " + testId);
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean update(PrescriptionDTO updatedPresc) throws SQLException, ClassNotFoundException {
            Prescription prescription = new Prescription(
                    updatedPresc.getPrescriptionId(),
                    updatedPresc.getCustomerId(),
                    updatedPresc.getPatientName(),
                    updatedPresc.getAge(),
                    updatedPresc.getMedicalOfficerName(),
                    updatedPresc.getContext(),
                    updatedPresc.getDuration(),
                    updatedPresc.getDate());
            prescriptionDAO.update(prescription);
            return true;

    }

    @Override
    public boolean addPrescriptionWithTestIds(PrescriptionDTO newPresc, List<String> testIds) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isPrescriptionAdded = prescriptionDAO.add(connection, newPresc);
            if (!isPrescriptionAdded) {
                connection.rollback();
                return false;
            }

            String prescriptionId = prescriptionDAO.getGeneratedPrescriptionId(connection);
            connection.commit();

            connection.setAutoCommit(false);

            for (String testId : testIds) {
                boolean isTestAdded = prescTestDetailDAO.addPresc(connection, prescriptionId, testId);
                if (!isTestAdded) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deletePrescEmpty(PrescriptionDTO prescription) throws SQLException, ClassNotFoundException {
        return prescriptionDAO.delete(prescription.getPrescriptionId());
    }
}
