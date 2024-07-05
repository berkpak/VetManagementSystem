package dev.patika.vetSystem.api;

import dev.patika.vetSystem.business.abstracts.ICustomerService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.result.Result;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.dto.response.customer.CustomerResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;


    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
       // Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        return customerService.save(customerSaveRequest);

    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return customerService.cursor(page,pageSize);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") int id){
        return customerService.get(id);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getCustomersByName(
            @PathVariable(name = "name") String name) {
        return customerService.findByName(name);
    }

    @GetMapping("/{customerId}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getCustomerAnimals(@PathVariable("customerId") int customerId) {
        return this.customerService.findAnimalByCustomerId(customerId);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        return customerService.update(customerUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
}
