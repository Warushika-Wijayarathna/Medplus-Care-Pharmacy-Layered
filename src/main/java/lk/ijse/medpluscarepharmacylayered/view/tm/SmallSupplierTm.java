package lk.ijse.medpluscarepharmacylayered.view.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SmallSupplierTm<S> {
    private String supplierId;
    private String name;
    private JFXButton remove;
}
