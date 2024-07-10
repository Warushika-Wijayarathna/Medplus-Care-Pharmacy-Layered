package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.TestDTO;

import java.sql.SQLException;
import java.util.List;

public interface TestBO extends SuperBO {

    List<TestDTO> getAllTests() throws SQLException, ClassNotFoundException;

    void deleteTest(TestDTO test) throws SQLException, ClassNotFoundException;

    void updateTest(TestDTO updatedTest) throws SQLException, ClassNotFoundException;

    void addTest(TestDTO newTest) throws SQLException, ClassNotFoundException;

    String getGeneratedTestId() throws SQLException, ClassNotFoundException;

    boolean checkInstant(String testId) throws SQLException, ClassNotFoundException;

    String getTestName(String testId) throws SQLException, ClassNotFoundException;

    List <String> getAllTestNames() throws SQLException, ClassNotFoundException;

    TestDTO getTestByDescription(String selectedTest) throws SQLException, ClassNotFoundException;

    List<String> getTestByDescriptionList(List<String> testNames);
}
