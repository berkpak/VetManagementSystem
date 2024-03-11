package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.entities.Animal;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IAnimalService {

   // Animal save(Animal animal);
    Animal update(Animal animal);
    Animal get(int id);
    Page<Animal> cursor(int page, int pageSize);
    boolean delete(int id);
    List<Animal> findByName(String name);

    AnimalResponse save(AnimalSaveRequest animalSaveRequest);


}
