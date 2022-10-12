package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
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

//    public List<Person> getAll(){
//        return personRepository.getAll();
//    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> findById(UUID id){
        return Optional.of(personRepository.getReferenceById(id));
    }

    public boolean delete(UUID id){
        return findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }
}
