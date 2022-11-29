package com.ortegafran.peoplemanager.Controllers;

import com.ortegafran.peoplemanager.Model.Entities.JobRecord;
import com.ortegafran.peoplemanager.Services.JobRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobRecordController {
    Logger logger = LoggerFactory.getLogger(JobRecordController.class);
    @Autowired
    private final JobRecordService jobRecordService;

    public JobRecordController(JobRecordService jobRecordService) {
        this.jobRecordService = jobRecordService;
    }

    @PostMapping(value="{id}")
    public ResponseEntity<Optional<JobRecord>> addJob(@PathVariable UUID id, @RequestBody JobRecord job){
        logger.info("Adding new Job Record for {} id.",id);
        Optional<JobRecord> jobRecord = jobRecordService.addOne(id,job);

        if(jobRecord.isEmpty()) {
            logger.warn("Error saving job record.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobRecord, HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<JobRecord>> listAll(){
        return new ResponseEntity<>(jobRecordService.findAll(),HttpStatus.OK);
    }

}
