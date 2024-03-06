package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);
    AvailableDate update(AvailableDate availableDate);
    AvailableDate get(int id);
    boolean delete(int id);
    Page<AvailableDate> cursor(int page,int pageSize);
}
