package dev.patika.vetSystem.dto.response.availableDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private int id;
    private LocalDate availableDate;
    private int doctorId;
}
