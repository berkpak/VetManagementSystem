package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.IAnimalService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.AlreadyExistsException;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.AnimalRepo;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepo animalRepo, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        Animal selectedAnimal = this.animalRepo.findById(animalUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        selectedAnimal.setName(animalUpdateRequest.getName());
        selectedAnimal.setBreed(animalUpdateRequest.getBreed());
        selectedAnimal.setColour(animalUpdateRequest.getColour());
        selectedAnimal.setSpecies(animalUpdateRequest.getSpecies());
        selectedAnimal.setGender(animalUpdateRequest.getGender());
        selectedAnimal.setDateOfBirth(animalUpdateRequest.getDateOfBirth());
        selectedAnimal.setCustomer(animalUpdateRequest.getCustomer());

        Animal updatedAnimal = this.animalRepo.save(selectedAnimal);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }


    @Override
    public ResultData<AnimalResponse> get(int id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }
@Override
public ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    Page<Animal> animalPage = this.animalRepo.findAll(pageable);
    Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    return ResultHelper.cursor(animalResponsePage);
}

    @Override
    public boolean delete(int id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public ResultData<List<AnimalResponse>> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        String lowerCase = name.toLowerCase();
        List<Animal> filteredAnimals = animalRepo.findByName(lowerCase);
        List<AnimalResponse> animalResponses = filteredAnimals.stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @Override
    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        Optional<Animal> animalFromDb = animalRepo.findByNameAndSpeciesAndBreed(animalSaveRequest.getName(), animalSaveRequest.getSpecies(), animalSaveRequest.getBreed());
        if(animalFromDb.isPresent()){
            throw new AlreadyExistsException(Msg.ALREADY_EXIST);
        }
        Animal animal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        this.animalRepo.save(animal);
        AnimalResponse animalResponse = modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.created(animalResponse);
    }
}
