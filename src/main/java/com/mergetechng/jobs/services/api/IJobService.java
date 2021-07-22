package com.mergetechng.jobs.services.api;

import com.mergetechng.jobs.commons.dto.JobDto;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IJobService {
    Job getJob(String jobId);
    List<Job> searchJob(String keyprhase);
    boolean deleteJob(String jobId);
    boolean updateJob(JobDto jobDto);
    List<Job> getAll(Query query);
    Page<Job> getPage(Query query, Pageable pageable);
    List<Job> fullTextSearch(String searchPhrase);
    boolean updateJob(Job job);
    boolean applyForJob(String jobId , String userId);

}
