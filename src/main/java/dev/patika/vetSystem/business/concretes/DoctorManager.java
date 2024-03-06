package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IDoctorService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.DoctorRepo;
import dev.patika.vetSystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public ResultData<Doctor> save(Doctor doctor) {

        Doctor newDoctor = this.doctorRepo.save(doctor);
        return ResultHelper.created(newDoctor);
    }

    @Override
    public ResultData<Doctor>  update(Doctor doctor) {
       Doctor selectedDoctor = this.get(doctor.getId());

       selectedDoctor.setName(doctor.getName());
       selectedDoctor.setCity(doctor.getCity());
       selectedDoctor.setAddress(doctor.getAddress());
       selectedDoctor.setMail(doctor.getMail());
       selectedDoctor.setPhone(doctor.getPhone());

       Doctor updatedDoctor = this.doctorRepo.save(selectedDoctor);
        return ResultHelper.success(updatedDoctor);
    }

    @Override
    public Doctor get(int id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(int id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.doctorRepo.findAll(pageable);
    }
}
