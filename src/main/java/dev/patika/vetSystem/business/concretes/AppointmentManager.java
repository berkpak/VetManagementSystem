package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IAppointmentService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.dao.AppointmentRepo;
import dev.patika.vetSystem.dao.AvailableDateRepo;
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
    public Appointment save(Appointment appointment) {

        Optional<Appointment> appointmentDate = appointmentRepo.findByDoctorIdAndAppointmentDate(
                appointment.getDoctor().getId() , appointment.getAppointmentDate());
        if (!appointmentDate.isEmpty()) {
            throw new IllegalArgumentException("Bu dokturun bu tarihte baska bir randevusu mevcut!!");

        }
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
       Appointment selectedAppointment = this.get(appointment.getId());

       selectedAppointment.setAppointmentDate(appointment.getAppointmentDate());
       selectedAppointment.setDoctor(appointment.getDoctor());
       selectedAppointment.setAnimal(appointment.getAnimal());
        return this.appointmentRepo.save(selectedAppointment);
    }

    @Override
    public Appointment get(int id) {

        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(int id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    public List<Appointment> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return this.appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
    }

    @Override
    public List<Appointment> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return this.appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId,startDateTime,endDateTime);
    }
}
