package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vetSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    //AvailableDate save(AvailableDate availableDate);

    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDate update(AvailableDate availableDate);
    AvailableDate get(int id);
    boolean delete(int id);
    Page<AvailableDate> cursor(int page,int pageSize);
}
