package lk.ijse.medpluscarepharmacylayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer {
    private String customerId;
    private String name;
    private int contactNo;
    private String email;

    public Customer(String name, int contact, String email) {
        this.name=name;
        this.contactNo=contact;
        this.email=email;
    }
}
