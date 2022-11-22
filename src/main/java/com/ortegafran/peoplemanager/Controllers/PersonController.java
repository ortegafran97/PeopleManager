package com.ortegafran.peoplemanager.Controllers;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<Person>> getAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getOne(@PathVariable UUID id){
        Optional<Person> person = personService.findById(id);

        if(person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Person> saveOne(@RequestBody Person person){
        Optional<Person> response = personService.addOne(person);

        if(response.isPresent())
            return ResponseEntity.ok(response.get());
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateOne(@PathVariable("id") UUID id,@RequestBody Person person){
        Optional<Person> personExists = personService.findById(id);

        if(personExists.isPresent()){
            person.setId(personExists.get().getId());
            personService.save(person);
            return new ResponseEntity(person, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Person>> deleteOne(@PathVariable("id") UUID id){
        if(personService.delete(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
