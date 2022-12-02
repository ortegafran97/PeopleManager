package com.ortegafran.peoplemanager.Model.Repositories;

import com.ortegafran.peoplemanager.Model.Entities.JobRecord;
import com.ortegafran.peoplemanager.Model.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRecordRepository extends JpaRepository<JobRecord, UUID> {
    List<Person> findByEnterprise(String enterprise);
    List<JobRecord> findByPerson(Person person);
}
