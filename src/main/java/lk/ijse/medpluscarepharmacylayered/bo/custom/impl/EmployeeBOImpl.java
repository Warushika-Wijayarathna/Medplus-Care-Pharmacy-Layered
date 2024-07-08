package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.EmployeeBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.EmployeeDAO;
import lk.ijse.medpluscarepharmacylayered.dto.EmployeeDTO;
import lk.ijse.medpluscarepharmacylayered.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> allEmployees = null;
        List<Employee> employees = employeeDAO.getAll();

        for (Employee employee : employees) {
            allEmployees.add(new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getPosition(), employee.getAddress(), employee.getContactNo(), employee.getSalary()));
        }

        return allEmployees;
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        employeeDAO.add(new Employee(employeeDTO.getName(), employeeDTO.getPosition(), employeeDTO.getAddress(), employeeDTO.getContactNo(), employeeDTO.getSalary(), employeeDTO.getEmployeeId()));
    }
}
