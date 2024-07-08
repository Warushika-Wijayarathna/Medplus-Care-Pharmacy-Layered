package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.TestBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.TestDAO;
import lk.ijse.medpluscarepharmacylayered.dto.TestDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestBOImpl implements TestBO {
    TestDAO testDAO = (TestDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEST);
    @Override
    public List<TestDTO> getAllTests() throws SQLException, ClassNotFoundException {
        List<Test> tests = testDAO.getAll();
        List<TestDTO> allTests = new ArrayList<>();

        for (Test test : tests) {
            allTests.add(
                    new TestDTO(test.getTestId(),
                            test.getDescription(),
                            test.getLab(),
                            test.getSampleType(),
                            test.getTestType(),
                            test.getPrice()));
        }
        return allTests;

    }

    @Override
    public void deleteTest(TestDTO test) throws SQLException, ClassNotFoundException {
        testDAO.delete(test.getTestId());
    }

    @Override
    public void updateTest(TestDTO updatedTest) throws SQLException, ClassNotFoundException {
        testDAO.update(new Test(updatedTest.getTestId(),
                updatedTest.getDescription(),
                updatedTest.getLab(),
                updatedTest.getSampleType(),
                updatedTest.getTestType(),
                updatedTest.getPrice()));
    }

    @Override
    public void addTest(TestDTO newTest) throws SQLException, ClassNotFoundException {
        testDAO.add(new Test(newTest.getTestId(),
                newTest.getDescription(),
                newTest.getLab(),
                newTest.getSampleType(),
                newTest.getTestType(),
                newTest.getPrice()));
    }

    @Override
    public String getGeneratedTestId() throws SQLException, ClassNotFoundException {
        return testDAO.generateNewID();
    }


}
