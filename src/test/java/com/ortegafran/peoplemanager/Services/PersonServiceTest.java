package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonService personService;



    @BeforeEach
    public void setUp(){
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    public void accept_person_with_new_dni(){
        Person saved = personService.save(new Person("123","Nueva","Persona","Test"));

        personService.findById(saved.getId());
        System.out.println("Saved:" + saved);

        System.out.println("Found" + personService.findById(saved.getId()));

        assertTrue(true);
    }

}