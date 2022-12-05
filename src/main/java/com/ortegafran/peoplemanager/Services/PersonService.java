package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
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

    @Autowired
    private final ResidencyService residencyService;

    public PersonService(PersonRepository personRepository, ResidencyService residencyService) {
        this.personRepository = personRepository;
        this.residencyService = residencyService;
    }


    public Person save(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> findById(UUID id){
        List<Person> list = personRepository
                .findAll().stream()
                .filter(person -> person.getId().equals(id))
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
                .filter(person -> person.getDni().equals(dni))
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
        if(search.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(personRepository.save(person));
    }

    public Optional<Person> updatePerson(Person person){
        Optional<Person> p = findById(person.getId());

        if(p.isEmpty())
            return Optional.empty();

        Person updated = person;
        updated.setId(person.getId());

        return Optional.of(personRepository.save(updated));
    }

    public boolean delete(UUID id){
        return findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }

    public Optional<Person> updateResidency(UUID personId, Residency residency){
        Optional<Person> person = findById(personId);

        if(person.isEmpty()) return Optional.empty();

        Residency old = person.get().getResidency();
        UUID idRes = old.getId();

        if(idRes.equals(null)) residencyService.addOne(residency);
        else{
            residency.setId(idRes);
            person.get().setResidency(residencyService.updateOne(residency).get());
            personRepository.save(person.get());
        }


        return Optional.of(findById(personId).get());
    }
}
