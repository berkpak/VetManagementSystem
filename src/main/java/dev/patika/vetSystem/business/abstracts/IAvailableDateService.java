package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vetSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.vetSystem.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    //AvailableDate save(AvailableDate availableDate);

    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    ResultData<AvailableDateResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize);
}
