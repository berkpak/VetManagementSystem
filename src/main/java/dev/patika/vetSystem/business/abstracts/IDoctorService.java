package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.entities.Doctor;
import org.springframework.data.domain.Page;

import javax.print.Doc;

public interface IDoctorService {

    ResultData<Doctor>  save(Doctor doctor);
    ResultData<Doctor>  update(Doctor doctor);
    Doctor get(int id);
    boolean delete(int id);
    Page<Doctor> cursor(int page, int pageSize);
}
