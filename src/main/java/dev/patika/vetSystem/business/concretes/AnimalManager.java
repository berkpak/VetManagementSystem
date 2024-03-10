package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IAnimalService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.AlreadyExistsException;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.AnimalRepo;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepo animalRepo, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
    }

  /*  @Override
    public Animal  save(Animal animal) {
        return this.animalRepo.save(animal);
    }*/




    @Override
    public Animal update(Animal animal) {
        Animal selectedAnimal = this.get(animal.getId());

        /*selectedAnimal.setName(animal.getName());
        selectedAnimal.setBreed(animal.getBreed());
        selectedAnimal.setColour(animal.getColour());
        selectedAnimal.setSpecies(animal.getSpecies());
        selectedAnimal.setGender(animal.getGender());
        selectedAnimal.setDateOfBirth(animal.getDateOfBirth());
        selectedAnimal.setCustomer(animal.getCustomer());

         */

        return this.animalRepo.save(selectedAnimal);
    }

    @Override
    public Animal get(int id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public boolean delete(int id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> findByName(String name) {
        if (name == null || name.isEmpty()) {
            ResultHelper.validateError(Msg.NOT_FOUND);
        }
        return animalRepo.findByName(name);
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {

        Optional<Animal> animalFromDb = animalRepo.findByNameAndSpeciesAndBreed(animalSaveRequest.getName(), animalSaveRequest.getSpecies(),animalSaveRequest.getBreed());
        if(animalFromDb.isPresent()){
            throw new AlreadyExistsException(Msg.ALREADY_EXIST);
        }
        Animal animal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        this.animalRepo.save(animal);
                return modelMapper.forResponse().map(animal, AnimalResponse.class);
    }
}
