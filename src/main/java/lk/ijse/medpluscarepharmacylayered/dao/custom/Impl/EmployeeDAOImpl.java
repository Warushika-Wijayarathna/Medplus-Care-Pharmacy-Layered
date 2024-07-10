package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.EmployeeDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            employeeList.add(new Employee(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6),
                    rst.getString(7)));
        }
        return employeeList;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?,?)",
                entity.getName(),
                entity.getPosition(),
                entity.getAddress(),
                entity.getContactNo(),
                entity.getSalary(),
                entity.getUserId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name=?,position=?,address=?,contact_no=?,salary=? WHERE emp_id=?",
                entity.getName(),
                entity.getPosition(),
                entity.getAddress(),
                entity.getContactNo(),
                entity.getSalary(),
                entity.getEmployeeId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
