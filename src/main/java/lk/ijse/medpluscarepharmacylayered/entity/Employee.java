package lk.ijse.medpluscarepharmacylayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String employeeId;
    private String name;
    private String position;
    private String address;
    private String contactNo;
    private double salary;
    private String  userId;

    public Employee(String name, String position, String address, String contactNo, double salary, String userId) {
        this.name=name;
        this.position=position;
        this.address=address;
        this.contactNo=contactNo;
        this.salary=salary;
        this.userId=userId;
    }

    public Employee(String id,String name, String position, String address, String contactNo, double salary) {
        this.employeeId=id;
        this.name=name;
        this.position=position;
        this.address=address;
        this.contactNo=contactNo;
        this.salary=salary;
    }
}
