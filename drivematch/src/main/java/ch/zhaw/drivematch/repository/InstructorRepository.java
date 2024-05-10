package ch.zhaw.drivematch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.drivematch.model.Instructor;

public interface InstructorRepository extends MongoRepository<Instructor,String>{
    Instructor findFirstByEmail(String email);
}
