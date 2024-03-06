package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IAvailableDateService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.dao.AvailableDateRepo;
import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
       AvailableDate selectedAvailableDate = this.get(availableDate.getId());

       selectedAvailableDate.setAvailableDate(availableDate.getAvailableDate());
       selectedAvailableDate.setDoctor(availableDate.getDoctor());
        return this.availableDateRepo.save(selectedAvailableDate);
    }

    @Override
    public AvailableDate get(int id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(int id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepo.findAll(pageable);
    }
}
