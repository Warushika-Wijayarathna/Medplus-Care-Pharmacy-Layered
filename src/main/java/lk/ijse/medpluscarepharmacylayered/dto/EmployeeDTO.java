package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String position;
    private String address;
    private String contactNo;
    private double salary;
    private String  userId;

    public EmployeeDTO(String name, String position, String address, String contactNo, double salary, String userId) {
        this.name=name;
        this.position=position;
        this.address=address;
        this.contactNo=contactNo;
        this.salary=salary;
        this.userId=userId;
    }

    public EmployeeDTO(String id,String name, String position, String address, String contactNo, double salary) {
        this.employeeId=id;
        this.name=name;
        this.position=position;
        this.address=address;
        this.contactNo=contactNo;
        this.salary=salary;
    }
}
