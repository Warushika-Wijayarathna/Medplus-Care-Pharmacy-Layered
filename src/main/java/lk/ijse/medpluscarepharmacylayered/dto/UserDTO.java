package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {
    private String userId;
    private String userName;
    private String password;

    public UserDTO(String userName, String password){
        this.userName=userName;
        this.password=password;
    }
}
