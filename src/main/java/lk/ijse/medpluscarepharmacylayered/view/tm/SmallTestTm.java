package lk.ijse.medpluscarepharmacylayered.view.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SmallTestTm {
    private String testId;
    private String description;
    private JFXButton remove;
}
