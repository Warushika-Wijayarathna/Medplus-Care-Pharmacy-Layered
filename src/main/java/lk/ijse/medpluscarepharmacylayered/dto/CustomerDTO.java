package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDTO {
    private String customerId;
    private String name;
    private int contactNo;
    private String email;

    public CustomerDTO(String name, int contact, String email) {
        this.name=name;
        this.contactNo=contact;
        this.email=email;
    }
}
