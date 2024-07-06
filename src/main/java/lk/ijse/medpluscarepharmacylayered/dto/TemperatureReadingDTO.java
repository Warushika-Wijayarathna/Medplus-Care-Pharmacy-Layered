package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TemperatureReadingDTO {
    private LocalDate date;
    private LocalTime timestamp;
    private double temperature;
}
