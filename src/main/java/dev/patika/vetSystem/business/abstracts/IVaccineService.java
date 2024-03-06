package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);
    Vaccine update(Vaccine vaccine);
    Vaccine get(int id);
    boolean delete(int id);
    Page<Vaccine> cursor(int page, int pageSize);
    List<Vaccine> findByAnimalId(int animalId);
    List<Vaccine> findByProtectionDate(LocalDate startDate, LocalDate finishDate);
}
