package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IAppointmentService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.AppointmentRepo;
import dev.patika.vetSystem.dao.AvailableDateRepo;
import dev.patika.vetSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.vetSystem.entities.Appointment;
import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    public AppointmentManager(AppointmentRepo appointmentRepo, AvailableDateRepo availableDateRepo, IModelMapperService modelMapper) {
        this.appointmentRepo = appointmentRepo;
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        Appointment appointment = modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        Optional<Appointment> appointmentDate = appointmentRepo.findByDoctorIdAndAppointmentDate(
                appointment.getDoctor().getId(), appointment.getAppointmentDate());
        if (!appointmentDate.isEmpty()) {
            throw new RuntimeException("Bu doktorun bu tarihte başka bir randevusu mevcut!");
        }

        Optional<AvailableDate> availableDate = availableDateRepo.findByDoctorIdAndAvailableDate(
                appointment.getDoctor().getId(), appointment.getAppointmentDate().toLocalDate());
        if (availableDate.isEmpty()) {
            throw new RuntimeException("Doktor seçilen günde müsait değil");
        }

        Appointment savedAppointment = appointmentRepo.save(appointment);
        AppointmentResponse appointmentResponse = modelMapper.forResponse().map(savedAppointment, AppointmentResponse.class);
        return ResultHelper.created(appointmentResponse);
    }

    @Override
    public ResultData<AppointmentResponse> get(int id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        AppointmentResponse appointmentResponse = modelMapper.forResponse().map(appointment, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);
    }

    @Override
    public ResultData<CursorResponse<AppointmentResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = appointmentRepo.findAll(pageable);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }

    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment selectedAppointment = this.appointmentRepo.findById(appointmentUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        selectedAppointment.setAppointmentDate(appointmentUpdateRequest.getAppointmentDate());
            selectedAppointment.setDoctor(appointmentUpdateRequest.getDoctorId());
        selectedAppointment.setAnimal(appointmentUpdateRequest.getAnimalId());

        Appointment updatedAppointment = appointmentRepo.save(selectedAppointment);
        AppointmentResponse appointmentResponse = modelMapper.forResponse().map(updatedAppointment, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);
    }


    @Override
    public boolean delete(int id) {
        Appointment appointment = this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.appointmentRepo.delete(appointment);
        return true;
    }
    @Override
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Appointment> appointments = this.appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Appointment> appointments = this.appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

}
