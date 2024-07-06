package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDTO {
    private String supplierId;
    private String name;
    private int contact;
    private String email;

    public SupplierDTO(String name, int contact, String email) {
        this.name=name;
        this.contact=contact;
        this.email=email;
    }


    public SupplierDTO(String id, String name) {
        this.supplierId=id;
        this.name=name;
    }
}
