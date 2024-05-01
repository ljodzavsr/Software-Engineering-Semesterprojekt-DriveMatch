package ch.zhaw.drivematch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.drivematch.model.Freelancer;

public interface FreelancerRepository extends MongoRepository<Freelancer,String>{
    Freelancer findFirstByEmail(String email);
}
