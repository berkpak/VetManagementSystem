package dev.patika.vetSystem.dao;

import dev.patika.vetSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    List<Customer> findByName(String name);
    List<Customer> findAnimalById(int animalId);

}
