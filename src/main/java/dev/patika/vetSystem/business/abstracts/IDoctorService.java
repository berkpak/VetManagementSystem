package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vetSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.doctor.DoctorResponse;
import dev.patika.vetSystem.entities.Doctor;
import org.springframework.data.domain.Page;

import javax.print.Doc;

public interface IDoctorService {

    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);

    // DoctorResponse get(int id);
    ResultData<DoctorResponse> get(int id);

    void delete(int id);

    ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize);

    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);
}
