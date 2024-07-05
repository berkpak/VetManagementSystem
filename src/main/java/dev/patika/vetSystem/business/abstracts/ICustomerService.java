package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetSystem.dto.response.CursorResponse;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.dto.response.customer.CustomerResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

   // ResultData<Customer> save(Customer customer);

    ResultData<CustomerResponse>save (CustomerSaveRequest customerSaveRequest);
    ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);
    ResultData<CustomerResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize);
    ResultData<List<CustomerResponse>> findByName(String name);

    ResultData<List<AnimalResponse>> findAnimalByCustomerId(int customerId);

}
