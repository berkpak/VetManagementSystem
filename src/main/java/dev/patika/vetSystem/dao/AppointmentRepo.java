package dev.patika.vetSystem.dao;

import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDate, LocalDateTime endDate);
    Optional<Appointment> findByDoctorIdAndAppointmentDate(int doctorId , LocalDateTime appointmentDate);

}
