package dev.patika.vetSystem.api;

import dev.patika.vetSystem.business.abstracts.IAnimalService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.result.Result;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.entities.Animal;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper) {
        this.animalService = animalService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return animalService.save(animalSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return animalService.cursor(page,pageSize);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id){
        return animalService.get(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByName(@PathVariable(name = "name", required = false) String name) {
        return this.animalService.findByName(name);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
       return animalService.update(animalUpdateRequest);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }
}
