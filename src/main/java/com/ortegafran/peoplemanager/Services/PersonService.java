package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import org.hibernate.type.UUIDCharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        List<Person> list = personRepository
                .findAll().stream()
                .filter(person -> person.getId()==id)
                .collect(Collectors.toList());

        if(list.stream().count()>0) {
            return list.stream().findFirst();
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Person> findByDni(String dni){
        List<Person> list = personRepository
                .findAll().stream()
                .filter(person -> person.getDni()==dni)
                .collect(Collectors.toList());

        if(list.stream().count()>0) {
            return list.stream().findFirst();
        }
        else {
            return Optional.empty();
        }
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> addOne(Person person){
        Optional<Person> search = findByDni(person.getDni());

        if(search.isEmpty()) {
            return Optional.of(personRepository.save(person));
        }
        return Optional.empty();
    }

    public boolean delete(UUID id){
        return findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }


}
