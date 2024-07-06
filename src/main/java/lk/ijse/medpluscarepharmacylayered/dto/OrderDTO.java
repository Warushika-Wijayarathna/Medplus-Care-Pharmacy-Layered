package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data

public class OrderDTO {
    private String orderId;
    private int qty;
    private double total;
    private String cust_id;
    private String user_id;
    private LocalDate date;
}