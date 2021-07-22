package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    Job findFirstById(String jobId);
    boolean findAllById(String[] jobId);
}