package dev.patika.vetSystem.dao;

import dev.patika.vetSystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByNameAndPhoneAndMail(String name, String phone, String mail);
}
