package dev.patika.vetSystem.api;

import dev.patika.vetSystem.business.abstracts.IAnimalService;
import dev.patika.vetSystem.business.abstracts.IAppointmentService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.result.Result;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Appointment;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.appointmentService = appointmentService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.save(appointmentSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") int id) {
        return this.appointmentService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.appointmentService.cursor(page, pageSize);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.update(appointmentUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/doctorId/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByDoctorIdAndAppointmentDateBetween(
            @PathVariable("doctorId") int doctorId,
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return this.appointmentService.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
    }

    @GetMapping("/getAnimalById/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByAnimalIdAndAppointmentDateBetween(
            @PathVariable("animalId") int animalId,
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return this.appointmentService.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);
    }

}
