package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import com.ortegafran.peoplemanager.Model.Repositories.ResidencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {
    PersonService personService;
    List<Person> personList = Arrays.asList(
            new Person(UUID.fromString("b1b2045b-0a4c-40ee-b43c-b19cf8f05bc7"),
                    "65465454",
                    "Franco",
                    "Ortega",
                    "varas",
                    new Residency("S.M. del carril",
                        "btst1",
                        "btst2",
                        300,
                        "Norte",
                        "comment")),
            new Person("23435234","Angel","Dominguez","Perez",null),
            new Person("62362351","Rafael","Gomez","Gonzales",null),
            new Person("12312312","Francisco","Crespo","Otamendi",null));

    @BeforeEach
    public void setUp(){
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        ResidencyService residencyService = new ResidencyService(Mockito.mock(ResidencyRepository.class));

        Mockito.when(personRepository.findAll())
                .thenReturn(personList);

        Mockito.when(personRepository.save(Mockito.any(Person.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);

        personService = new PersonService(personRepository, residencyService);
    }

    @Test
    public void accept_person_with_new_dni() {
        Person newPerson = new Person("123","Nueva","Persona","Test",null);

        Optional<Person> saved = personService.addOne(newPerson);

        assertEquals(newPerson,saved.get());
    }

    @Test
    public void reject_repeated_dni(){
        Person newPerson = new Person("65465454","Repeated","Persona","Test",null);
        Optional<Person> saved = personService.addOne(newPerson);
        assertEquals(Optional.empty(),saved);
    }

    @Test
    public void list_persons(){
        assertTrue(personService.findAll().stream().count()>0);
    }

    @Test
    public void find_person_by_id(){
        Person expected = personList.get(0);

        Optional<Person> found = personService.findById(expected.getId());
        assertEquals(
                expected,
                found.get()
        );

    }


}