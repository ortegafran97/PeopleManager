package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.JobRecord;
import com.ortegafran.peoplemanager.Model.Entities.Person;
import com.ortegafran.peoplemanager.Model.Repositories.JobRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class JobRecordService {
    @Autowired
    JobRecordRepository recordRepository;

    @Autowired
    PersonService personService;

    public Optional<JobRecord> findOne(UUID idJob){
        return recordRepository.findById(idJob);
    }

    public List<Person> findEmployees(String enterprise){
        return recordRepository.findByEnterprise(enterprise);
    }

    public List<JobRecord> findByPerson(UUID idPerson){
        Optional<Person> person = personService.findById(idPerson);

        if(person.isEmpty()) return List.of();

        return recordRepository.findByPerson(person.get());
    }

    public Optional<JobRecord> addOne(UUID idPerson, JobRecord job){
        //TODO: check dates
        Optional<Person> person = personService.findById(idPerson);

        if(person.isEmpty())
            return Optional.empty();
        job.setPerson(person.get());
        return Optional.of(recordRepository.save(job));
    }

    public Optional<JobRecord> editJob(JobRecord jobRecord){
        Optional<JobRecord> record = recordRepository.findById(jobRecord.getId());

        if(record.isEmpty())
            return Optional.empty();

        return Optional.of(recordRepository.save(jobRecord));
    }

    public List<JobRecord> findAll(){
        return recordRepository.findAll();
    }

    public Boolean deleteOne(UUID id){
        Optional<JobRecord> job = findOne(id);
        if(job.isEmpty()) return false;

        recordRepository.deleteById(job.get().getId());

        return recordRepository
                .findById(id)
                .isEmpty();
    }
}
