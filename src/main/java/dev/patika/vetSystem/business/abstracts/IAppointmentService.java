package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.vetSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);
    ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);
    ResultData<AppointmentResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<AppointmentResponse>>  cursor(int page, int pageSize);

    ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDate, LocalDateTime endDate);
    ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDate, LocalDateTime endDate);
}
