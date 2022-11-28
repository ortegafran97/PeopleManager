package com.ortegafran.peoplemanager.runner;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import com.ortegafran.peoplemanager.Model.Repositories.ResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TestRunner implements CommandLineRunner{
    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final ResidencyRepository residencyRepository;

    public TestRunner(PersonRepository personRepository, ResidencyRepository residencyRepository) {
        this.personRepository = personRepository;
        this.residencyRepository = residencyRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        personRepository.save(new Person(
                UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),
                "65465454","Franco",
                "Ortega",
                "varas",
                new Residency("S.M. del carril",
                        "btst1",
                        "btst2",
                        300,
                        "Norte",
                        "comment")));
        personRepository.save(new Person("23435234","Angel","Dominguez","Perez",null));
        personRepository.save(new Person("62362351","Rafael","Gomez","Gonzales",null));
        personRepository.save(new Person("12312312","Francisco","Crespo","Otamendi",null));

        Optional<Person> person = personRepository.findByDni("65465454");

        if(person.isPresent()){
            Person p = person.get();
            Residency r = new Residency("S.M. del carril",
                    "btst1",
                    "btst2",
                    300,
                    "Norte",
                    "comment");

            p.setResidency(r);
            personRepository.save(p);
        }
    }
}
