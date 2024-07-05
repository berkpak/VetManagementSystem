package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vetSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.vetSystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    ResultData<VaccineResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize);
    ResultData<List<VaccineResponse>> findByAnimalId(int animalId);
    ResultData<List<VaccineResponse>> findByProtectionDate(LocalDate startDate, LocalDate finishDate);

    List<Vaccine> findByNameAndCode(String name, String code);
}
