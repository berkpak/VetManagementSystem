package dev.patika.vetSystem.api;

import dev.patika.vetSystem.business.abstracts.IDoctorService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.result.Result;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vetSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.customer.CustomerResponse;
import dev.patika.vetSystem.dto.response.doctor.DoctorResponse;
import dev.patika.vetSystem.entities.Customer;
import dev.patika.vetSystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;


    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        return ResultHelper.created(doctorService.save(doctorSaveRequest));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return doctorService.cursor(page, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") int id){
        return doctorService.get(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){

        return doctorService.update(doctorUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}