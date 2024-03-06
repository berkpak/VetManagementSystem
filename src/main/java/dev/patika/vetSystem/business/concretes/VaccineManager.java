package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IVaccineService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.dao.VaccineRepo;
import dev.patika.vetSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;

    }

    @Override
    public Vaccine save(Vaccine vaccine) {

        if(vaccine.getProtectionStartDate().isAfter(vaccine.getProtectionFinishDate())){
            throw new IllegalArgumentException("Koruyuculuk tarihi gecmis asiyi kayit edemezsiniz. Tarihleri tekrar kontrol ediniz");
        }
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
       Vaccine selectedVaccine = this.get(vaccine.getId());

       selectedVaccine.setName(vaccine.getName());
       selectedVaccine.setCode(vaccine.getCode());
       selectedVaccine.setProtectionStartDate(vaccine.getProtectionStartDate());
       selectedVaccine.setProtectionFinishDate(vaccine.getProtectionFinishDate());
        return vaccineRepo.save(selectedVaccine);
    }

    @Override
    public Vaccine get(int id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(int id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public List<Vaccine> findByAnimalId(int animalId) {
        Objects.requireNonNull(animalId, Msg.NOT_FOUND);
        return vaccineRepo.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> findByProtectionDate(LocalDate startDate, LocalDate finishDate) {
        return this.vaccineRepo.findByProtectionStartDateBetween(startDate,finishDate);
    }


}
