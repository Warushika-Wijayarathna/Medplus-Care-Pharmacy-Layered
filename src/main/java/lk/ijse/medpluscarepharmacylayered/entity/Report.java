package lk.ijse.medpluscarepharmacylayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Report {
    private String reportId;
    private String custId;
    private String testId;
    private String result;
    private LocalDate issueDate;
    private LocalDate pickupDate;

    public Report(String custId,String testId, String result, LocalDate issueDate, LocalDate pickUpDate) {
        this.custId=custId;
        this.testId=testId;
        this.result=result;
        this.issueDate=issueDate;
        this.pickupDate=pickUpDate;
    }
}

