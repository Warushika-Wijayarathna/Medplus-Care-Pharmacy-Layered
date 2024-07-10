package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.ReportDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReportBO extends SuperBO {
    List<ReportDTO> getAllReports() throws SQLException, ClassNotFoundException;

    void deleteReport(String reportId) throws SQLException, ClassNotFoundException;

    void updateReport(ReportDTO updatedReport) throws SQLException, ClassNotFoundException;

    String getNextReportId() throws SQLException, ClassNotFoundException;

    void addReport(ReportDTO newReport) throws SQLException, ClassNotFoundException;
}
