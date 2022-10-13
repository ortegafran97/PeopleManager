package com.ortegafran.peoplemanager.runner;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TestRunner implements CommandLineRunner{
    @Autowired
    private final PersonRepository repo;

    public TestRunner(PersonRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception{
        repo.save(new Person(
                UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),
                "65465454","Franco",
                "Ortega",
                "varas"));
        repo.save(new Person("23435234","Angel","Dominguez","Perez"));
        repo.save(new Person("62362351","Rafael","Gomez","Gonzales"));
        repo.save(new Person("12312312","Francisco","Crespo","Otamendi"));
    }
}
