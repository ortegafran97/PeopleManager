package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import org.hibernate.type.UUIDCharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> findById(UUID id){
        return personRepository.findById(id);
    }

    public Optional<Person> findByDni(String dni){
        return personRepository.findByDni(dni);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person addOne(Person person){
        return personRepository.save(person);
    }

    public boolean delete(UUID id){
        return findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }


}
