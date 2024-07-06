package lk.ijse.medpluscarepharmacylayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Supplier {
    private String supplierId;
    private String name;
    private int contact;
    private String email;

    public Supplier(String name, int contact, String email) {
        this.name=name;
        this.contact=contact;
        this.email=email;
    }


    public Supplier(String id, String name) {
        this.supplierId=id;
        this.name=name;
    }
}
