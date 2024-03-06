package dev.patika.vetSystem.dao;

import dev.patika.vetSystem.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {

    List<Animal> findByName(String name);
}
