package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IVaccineService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.VaccineRepo;
import dev.patika.vetSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vetSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, IModelMapperService modelMapper) {
        this.vaccineRepo = vaccineRepo;

        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        List<Vaccine> vaccines = this.findByNameAndCode(vaccineSaveRequest.getName(), vaccineSaveRequest.getCode());
        if(!vaccines.isEmpty()){
            for(Vaccine vaccine1: vaccines){
                if(vaccine1.getProtectionFinishDate().isAfter(vaccine1.getProtectionStartDate())){
                    throw new RuntimeException("Koruyuculuk tarihi bitmeden yeni bir asi kayit edilemez");
                }
            }
        }
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        this.vaccineRepo.save(saveVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
    }

    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
       Vaccine selectedVaccine = this.vaccineRepo.findById(vaccineUpdateRequest.getId())
                       .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

       selectedVaccine.setName(vaccineUpdateRequest.getName());
       selectedVaccine.setCode(vaccineUpdateRequest.getCode());
       selectedVaccine.setProtectionStartDate(vaccineUpdateRequest.getProtectionStartDate());
       selectedVaccine.setProtectionFinishDate(vaccineUpdateRequest.getProtectionFinishDate());

        Vaccine updateVaccine = this.vaccineRepo.save(selectedVaccine);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    @Override
    public ResultData<VaccineResponse> get(int id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    @Override
    public boolean delete(int id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(int animalId) {
        if (animalId <= 0) {
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
        List<Vaccine> vaccines = vaccineRepo.findByAnimalId(animalId);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByProtectionDate(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(startDate, finishDate);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }


    @Override
    public List<Vaccine> findByNameAndCode(String name, String code) {
        return this.vaccineRepo.findByNameAndCode(name, code);
    }


}
