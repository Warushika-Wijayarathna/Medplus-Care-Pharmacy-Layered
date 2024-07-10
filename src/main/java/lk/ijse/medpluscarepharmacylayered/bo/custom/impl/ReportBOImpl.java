package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.ReportBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.ReportDAO;
import lk.ijse.medpluscarepharmacylayered.dto.ReportDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportBOImpl implements ReportBO {
    ReportDAO reportDAO = (ReportDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REPORT);
    @Override
    public List<ReportDTO> getAllReports() throws SQLException, ClassNotFoundException {
        List<ReportDTO> reportDTOS = new ArrayList<>();
        List<Report> reports = reportDAO.getAll();
        for (Report report : reports) {
            reportDTOS.add(new ReportDTO(
                    report.getReportId(),
                    report.getCustId(),
                    report.getTestId(),
                    report.getResult(),
                    report.getIssueDate(),
                    report.getPickupDate()));
        }
        return reportDTOS;
    }

    @Override
    public void deleteReport(String reportId) throws SQLException, ClassNotFoundException {
        reportDAO.delete(reportId);
    }

    @Override
    public void updateReport(ReportDTO updatedReport) throws SQLException, ClassNotFoundException {
        reportDAO.update(new Report(
                updatedReport.getReportId(),
                updatedReport.getCustId(),
                updatedReport.getTestId(),
                updatedReport.getResult(),
                updatedReport.getIssueDate(),
                updatedReport.getPickupDate()));
    }

    @Override
    public String getNextReportId() throws SQLException, ClassNotFoundException {
        return reportDAO.generateNewID();
    }

    @Override
    public void addReport(ReportDTO newReport) throws SQLException, ClassNotFoundException {
        reportDAO.add(new Report(
                newReport.getCustId(),
                newReport.getTestId(),
                newReport.getResult(),
                newReport.getIssueDate(),
                newReport.getPickupDate()));
    }
}
