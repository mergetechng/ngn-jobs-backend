package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.JobApplicant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface JobApplicationRepository extends MongoRepository<JobApplicant, String> {
    Optional<JobApplicant> findByApplicantUsernameAndJobId(String username, String jobId);
}
