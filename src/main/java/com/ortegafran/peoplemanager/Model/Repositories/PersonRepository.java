package com.ortegafran.peoplemanager.Model.Repositories;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
//    List<Person> getAll();
//    Person save(Person person);
//    void delete(UUID PersonId);
}
