package ch.zhaw.drivematch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Job;
import ch.zhaw.drivematch.model.JobCreateDTO;
import ch.zhaw.drivematch.model.JobStateAggregation;
import ch.zhaw.drivematch.model.JobType;
import ch.zhaw.drivematch.repository.JobRepository;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @PostMapping("/job")
    public ResponseEntity<Job> createJob(@RequestBody JobCreateDTO cDTO) {
        Job jDAO = new Job(cDTO.getDescription(), cDTO.getJobType(), cDTO.getEarnings());
        Job j = jobRepository.save(jDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @GetMapping("/job")
    public ResponseEntity<List<Job>> getAllJobs(
                @RequestParam(required = false) Double min,
                @RequestParam(required = false) JobType type) {
        List<Job> allJobs;
        if (min == null && type == null) {
            allJobs = jobRepository.findAll();
        } else {
            if (min != null && type != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (min != null) {
                allJobs = jobRepository.findByEarningsGreaterThan(min);
            } else {
                allJobs = jobRepository.findByJobType(type);
            }
        }
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/job/aggregation/state")
    public List<JobStateAggregation> getJobStateAggregation() {
        return jobRepository.getJobStateAggregation();
    }
}


