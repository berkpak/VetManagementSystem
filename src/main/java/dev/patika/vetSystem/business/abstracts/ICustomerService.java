package dev.patika.vetSystem.business.abstracts;

import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetSystem.dto.response.animal.AnimalResponse;
import dev.patika.vetSystem.dto.response.customer.CustomerResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

   // ResultData<Customer> save(Customer customer);

    CustomerResponse save (CustomerSaveRequest customerSaveRequest);
    ResultData<Customer> update(Customer customer);
    Customer get(int id);
    boolean delete(int id);
    Page<Customer> cursor(int page, int pageSize);
    List<Customer> findByName(String name);

    List<Animal> findAnimalByCustomerId(int customerId);

}
