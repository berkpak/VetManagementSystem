package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vetSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vetSystem.dto.response.doctor.DoctorResponse;
import dev.patika.vetSystem.entities.Doctor;
import org.springframework.data.domain.Page;

import javax.print.Doc;

public interface IDoctorService {

   // ResultData<Doctor>  save(Doctor doctor);
    //ResultData<Doctor>  update(Doctor doctor);

 //DoctorResponse update(int id, DoctorUpdateRequest doctorUpdateRequest);

    ResultData<Doctor>  update(Doctor doctor);
   // DoctorResponse get(int id);
   Doctor get(int id);
    boolean delete(int id);
    Page<Doctor> cursor(int page, int pageSize);

    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);
}
