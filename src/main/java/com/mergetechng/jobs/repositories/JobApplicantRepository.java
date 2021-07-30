package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.JobApplicant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobApplicantRepository extends MongoRepository<JobApplicant , String> {
    JobApplicant findByJobId(String jobId);
}