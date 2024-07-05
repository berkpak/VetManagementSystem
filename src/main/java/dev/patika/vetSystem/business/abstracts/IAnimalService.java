package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.entities.Animal;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IAnimalService {

   // Animal save(Animal animal);
   ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    ResultData<AnimalResponse> get(int id);
    ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize);
    boolean delete(int id);
    ResultData<List<AnimalResponse>> findByName(String name);
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);


}
