package com.ortegafran.peoplemanager.Model.Repositories;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findById(UUID id);
    Optional<Person> findByDni(String dni);
}
