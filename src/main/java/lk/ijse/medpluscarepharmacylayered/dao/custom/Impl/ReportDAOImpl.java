package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ReportDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public ArrayList<Report> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Report> reportList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(null,"SELECT * FROM Report");

        while (resultSet.next()) {
            String reportId = resultSet.getString(1);
            String custId = resultSet.getString(2);
            String testId = resultSet.getString(3);
            String result = resultSet.getString(4);
            LocalDate issueDate = LocalDate.parse(resultSet.getString(5));
            LocalDate pickupDate = LocalDate.parse(resultSet.getString(6));

            Report report = new Report(reportId, custId, testId, result, issueDate, pickupDate);
            reportList.add(report);
        }

        return reportList;
    }

    @Override
    public boolean add(Report entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"INSERT INTO Report VALUES (?,?,?,?,?)",
                entity.getCustId(),
                entity.getTestId(),
                entity.getResult(),
                entity.getIssueDate(),
                entity.getPickupDate());
    }

    @Override
    public boolean update(Report entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"UPDATE Report SET cust_id = ?, test_id = ?, result = ?, issue_date = ?, pickup_date = ? WHERE r_Id = ?",
                entity.getCustId(),
                entity.getTestId(),
                entity.getResult(),
                entity.getIssueDate(),
                entity.getPickupDate(),
                entity.getReportId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"SELECT CONCAT('R', LPAD(next_id, 4, '0')) FROM AutoIncrement_Report");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(null,"DELETE FROM Report WHERE r_Id = ?", id);
    }

    @Override
    public Report search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
