package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ReportDTO {
    private String custId;
    private String reportId;
    private String testId;
    private String result;
    private LocalDate issueDate;
    private LocalDate pickupDate;

    public ReportDTO(String custId,String testId, String result, LocalDate issueDate, LocalDate pickUpDate) {
        this.custId=custId;
        this.testId=testId;
        this.result=result;
        this.issueDate=issueDate;
        this.pickupDate=pickUpDate;
    }
}

