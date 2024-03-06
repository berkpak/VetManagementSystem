package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);
    Appointment update(Appointment appointment);
    Appointment get(int id);
    boolean delete(int id);
    Page<Appointment> cursor(int page, int pageSize);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDate, LocalDateTime endDate);
}
