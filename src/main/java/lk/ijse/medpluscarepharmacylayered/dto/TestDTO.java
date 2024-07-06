package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TestDTO {
    private String  testId;
    private String description;
    private String lab;
    private String sampleType;
    private String testType;
    private double price;

    public TestDTO(String desc, String lab, String sampleType, String testType, double priceOf) {
        this.description=desc;
        this.lab=lab;
        this.sampleType=sampleType;
        this.testType=testType;
        this.price=priceOf;
    }

    public TestDTO(String testId, String testName) {
        this.testId=testId;
        this.description=testName;
    }
}
