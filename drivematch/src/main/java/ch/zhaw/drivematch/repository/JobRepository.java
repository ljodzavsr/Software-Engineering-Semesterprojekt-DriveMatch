package ch.zhaw.drivematch.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.drivematch.model.Job;
import ch.zhaw.drivematch.model.JobStateAggregation;
import ch.zhaw.drivematch.model.JobType;

public interface JobRepository extends MongoRepository<Job,String>{
    List<Job> findByEarningsGreaterThan(Double earnings);
    List<Job> findByJobType(JobType jobType);
    @Aggregation("{$group:{_id:'$jobState',jobIds:{$push:'$_id',},count:{$count:{}}}}")
    List<JobStateAggregation> getJobStateAggregation();
}
