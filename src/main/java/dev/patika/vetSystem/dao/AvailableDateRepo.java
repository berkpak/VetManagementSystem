package dev.patika.vetSystem.dao;

import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Integer> {
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(int doctorId, LocalDate date);


}
