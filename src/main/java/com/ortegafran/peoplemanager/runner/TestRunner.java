package com.ortegafran.peoplemanager.runner;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Model.Repositories.JobRecordRepository;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import com.ortegafran.peoplemanager.Model.Repositories.ResidencyRepository;
import com.ortegafran.peoplemanager.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TestRunner implements CommandLineRunner{
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final PersonService personService;
    @Autowired
    private final ResidencyRepository residencyRepository;
    @Autowired
    private final JobRecordRepository jobRecordRepository;

    public TestRunner(PersonRepository personRepository, PersonService personService, ResidencyRepository residencyRepository, JobRecordRepository jobRecordRepository) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.residencyRepository = residencyRepository;
        this.jobRecordRepository = jobRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        actions();
    }

    private void actions(){
        Person p1 = new Person(
                UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),
                "65465454", "Franco",
                "Ortega",
                "varas",
                new Residency("S.M. del carril",
                        "btst1",
                        "btst2",
                        300,
                        "Norte",
                        "comment")
        );
        Person p2 = new Person("23435234", "Angel", "Dominguez", "Perez", null);

        personService.addOne(p1);
        personService.addOne(p2);
        personService.addOne(new Person("62362351", "Rafael", "Gomez", "Gonzales", null));
        personService.addOne(new Person("12312312", "Francisco", "Crespo", "Otamendi", null));

        /* Jobs insertions */
        /* JobRecord j1 = new JobRecord("GL",
                "GL",
                LocalDateTime.of(2020,01,01,00,00),
                null);
        j1.setPerson(p1);

        jobRecordRepository.save(j1);

        jobRecordRepository.findAll()
                .stream().collect(Collectors.toList())
                .forEach( jobRecord -> System.out.println(jobRecord));
        */
    }


}
