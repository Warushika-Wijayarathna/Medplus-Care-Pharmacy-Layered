package lk.ijse.medpluscarepharmacylayered.view.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserTm {

    private String userId;
    private String userName;
    private String password;
    private JFXButton update;


    public UserTm(String userId, String userName, String password){
        this.userId=userId;
        this.userName=userName;
        this.password=password;
    }
}
