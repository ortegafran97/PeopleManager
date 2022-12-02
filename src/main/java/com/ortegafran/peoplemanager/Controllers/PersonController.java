package com.ortegafran.peoplemanager.Controllers;

import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<Person>> getAll(){
        logger.info("Requested list of persons");
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getOne(@PathVariable UUID id){
        logger.info("Requested person with id {}",id);

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
        logger.info("Adding new person: {}",response.get());

        if(response.isPresent())
            return ResponseEntity.ok(response.get());
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateOne(@PathVariable("id") UUID id,@RequestBody Person person){
        logger.info("Updating person with id {}",id);

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
        logger.info("Deleting person with id {}", id);

        if(personService.delete(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/residency")
    public ResponseEntity<Optional<Residency>> getResidency(@PathVariable("id") UUID personId){
        logger.warn("Getting residency for id {}",personId);

        Optional<Person> person =  personService.findById(personId);

        if(person.isPresent()) {
            return ResponseEntity.ok(Optional.of(person.get().getResidency()));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/residency")
    public ResponseEntity<Optional<Person>> addResidency(@PathVariable("id") UUID personId, @RequestBody Residency residency){
        logger.info("Adding residency for id {}",personId);

        Optional<Person> person= personService.updateResidency(personId,residency);

        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    @PutMapping("/{id}/residency")
    public ResponseEntity<Optional<Person>> editResidency(@PathVariable("id") UUID personId, @RequestBody Residency newResidency){
        logger.warn("Updating residency for id {}",personId);
        Optional<Person> residencyUpdated = personService.updateResidency(personId,newResidency);

        if(residencyUpdated.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Optional.of(residencyUpdated.get()));

    }

}
