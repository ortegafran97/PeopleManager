package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {
    PersonService personService;

    @BeforeEach
    public void setUp(){
        List<Person> personList = Arrays.asList(
                new Person(UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),"65465454","Franco","Ortega","varas"),
                new Person("23435234","Angel","Dominguez","Perez"),
                new Person("62362351","Rafael","Gomez","Gonzales"),
                new Person("12312312","Francisco","Crespo","Otamendi"));


        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    public void accept_person_with_new_dni(){
        Person saved = personService.save(new Person("123","Nueva","Persona","Test"));

        personService.findById(saved.getId());
        System.out.println("Saved:" + saved);

        System.out.println("Found" + personService.findById(saved.getId()));

    @Test
    public void list_persons(){
        assertTrue(personService.findAll().stream().count()>0);
    }

//    @Test
    public void find_person_by_id(){
//        Optional<Person> found = personService.findById(UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"));
        Optional<Person> found = personService.findByDni("65465454");

        System.out.println(found);

        assertEquals(
                new Person(UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),"65465454","Franco","Ortega","varas"),
                found.get()
        );

    }

}