package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    void saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    void updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;
}
