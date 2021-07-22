package com.mergetechng.jobs.services;

import com.mergetechng.jobs.commons.dto.JobDto;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.repositories.JobRepository;
import com.mergetechng.jobs.services.api.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

public class JobService implements IJobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsUserService.class);


    public JobService(){}

    @Override
    public Job getJob(String jobId) {
        return null;
    }

    @Override
    public List<Job> searchJob(String keyprhase) {
        return null;
    }

    @Override
    public boolean deleteJob(String jobId) {
        return false;
    }

    @Override
    public boolean updateJob(JobDto jobDto) {
        return false;
    }

    @Override
    public List<Job> getAll(Query query) {
        return null;
    }

    @Override
    public Page<Job> getPage(Query query, Pageable pageable) {
        return null;
    }


    /**
     *
     * @param searchPhrase The search phrase to search all the Jobs in the database
     * @return The list of the Job searched from the database
     */
    @Override
    public List<Job> fullTextSearch(String searchPhrase) {
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matchingPhrase(searchPhrase);
        Query query = TextQuery.queryText(criteria).sortByScore();
        return mongoTemplate.find(query, Job.class);
    }

    @Override
    public boolean updateJob(Job job) {
        return false;
    }

    @Override
    public boolean applyForJob(String jobId, String userId) {
        return false;
    }
}
