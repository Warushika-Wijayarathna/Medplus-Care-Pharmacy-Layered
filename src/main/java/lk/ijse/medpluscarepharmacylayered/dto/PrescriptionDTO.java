package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data

public class PrescriptionDTO{
    private String prescriptionId;
    private String customerId;
    private String patientName;
    private int age;
    private String medicalOfficerName;
    private String context;
    private String duration;
    private LocalDate date;
}
