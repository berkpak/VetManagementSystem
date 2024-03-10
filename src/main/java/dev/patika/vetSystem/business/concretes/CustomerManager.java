package dev.patika.vetSystem.business.concretes;

import dev.patika.vetSystem.business.abstracts.ICustomerService;
import dev.patika.vetSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.Msg;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import dev.patika.vetSystem.dao.CustomerRepo;
import dev.patika.vetSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetSystem.dto.response.customer.CustomerResponse;
import dev.patika.vetSystem.entities.Animal;
import dev.patika.vetSystem.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;

    }

    /*@Override
    public ResultData<Customer> save(Customer customer) {
        Customer newCustomer = this.customerRepo.save(customer);
        return ResultHelper.created(newCustomer);
    }

     */

    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {

        Optional<Customer> customerFromDb = customerRepo.findByNameAndPhoneAndMail(customerSaveRequest.getName(), customerSaveRequest.getPhone(), customerSaveRequest.getMail());
        if(customerFromDb.isPresent()){
            throw new RuntimeException(Msg.ALREADY_EXIST);
        }
        Customer customer = modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerRepo.save(customer);

        return modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public ResultData<Customer> update(Customer customer) {
        Customer selectedCustomer = this.get(customer.getId());

        selectedCustomer.setName(customer.getName());
        selectedCustomer.setMail(customer.getMail());
        selectedCustomer.setPhone(customer.getPhone());
        selectedCustomer.setAddress(customer.getAddress());
        selectedCustomer.setCity(customer.getCity());

        Customer updatedCustomer = this.customerRepo.save(selectedCustomer);

        return ResultHelper.success(updatedCustomer) ;
    }

    @Override
    public Customer get(int id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Customer> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return customerRepo.findByName(name);
    }

    @Override
    public List<Animal> findAnimalByCustomerId(int customerId) {
        Customer customer = get(customerId);

        if (customer.getAnimalList() != null && !customer.getAnimalList().isEmpty()) {
            return customer.getAnimalList();
        } else {
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(int id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepo.findAll(pageable);
    }
}
