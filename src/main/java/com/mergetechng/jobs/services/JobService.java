package com.mergetechng.jobs.services;


import com.mergetechng.jobs.api.IAdvanceSearch;
import com.mergetechng.jobs.commons.enums.JobStatusEnum;
import com.mergetechng.jobs.commons.util.JWTUtil;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.exceptions.JobApplicantNotFoundException;
import com.mergetechng.jobs.exceptions.JobNotExistsException;
import com.mergetechng.jobs.exceptions.UserAccountAlreadyVerifiedException;
import com.mergetechng.jobs.repositories.JobApplicantRepository;
import com.mergetechng.jobs.repositories.JobApplicationRepository;
import com.mergetechng.jobs.repositories.JobRepository;
import com.mergetechng.jobs.repositories.UserRepository;
import com.mergetechng.jobs.services.api.IJobService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService implements IJobService, IAdvanceSearch<Job> {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsUserService.class);
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IAuthenticationFacadeService iAuthenticationFacadeService;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;


    public JobService() {
    }


    @Override
    public boolean deleteJob(String jobId) throws JobNotExistsException {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            LOGGER.info("Yes Job was found");
            jobRepository.deleteById(jobId);
            return true;
        } else throw new JobNotExistsException("Oops job not found");
    }

    @Override
    public boolean updateJob(String jobId) {
        return false;
    }

    @Override
    public boolean updateJob(Job job) throws JobNotExistsException {
        Optional<Job> jobOptional = jobRepository.findById(job.getId());
        if (jobOptional.isPresent()) {
            Job newJobUpdate = jobOptional.get();
            newJobUpdate.setDateModified(new Date());
            newJobUpdate.setModifiedBy(iAuthenticationFacadeService.getAuthentication().getName());
            jobRepository.save(jobOptional.get());
            return true;
        }
        throw new JobNotExistsException("Job does not exists");
    }

    @Override
    public List<Job> getAll(Query query) {
        return null;
    }

    @Override
    public Page<Job> getPage(Query query, Pageable pageable) {
        return null;
    }


    private boolean jobExists(String jobId) {
        return jobRepository.findById(jobId).isPresent();
    }

    /**
     * @param searchPhrase The search phrase to search all the Jobs in the database
     * @return The list of the Job searched from the database
     */
    @Override
    public List<Job> fullTextSearch(String searchPhrase) {
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matchingPhrase(searchPhrase)
                .matching(searchPhrase);
        Query query = TextQuery.queryText(criteria).sortByScore();
        return mongoTemplate.find(query, Job.class);
    }


    @Override
    public Job getJob(String jobId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        return optionalJob.orElse(null);
    }

    @Override
    public boolean applyForJob(String jobId, String userId) {
        return false;
    }

    @Override
    public boolean createNewJob(Job job, String userName) throws UserAccountAlreadyVerifiedException {
        if (!userRepository.existsByUsernameOrEmail(userName, null)) {
            throw new UsernameNotFoundException("User is not found");
        }if (!userRepository.findByUsername(userName).isEmailVerified()) {
            throw new UserAccountAlreadyVerifiedException("Account owner not verified");
        } else if (!userRepository.existsByUsernameOrEmail(userName, null)) {
            throw new UsernameNotFoundException(String.format("IUser %s is not found", userName));
        }
        job.setId(UUID.randomUUID().toString());
        job.setJobStatus(JobStatusEnum.ACTIVE.name());
        job.setDateCreated(new Date());
        job.setCreatedBy(userName);
        jobRepository.insert(job);
        return true;
    }


    @Override
    public Page<Job> pageFetchJobApplicant(Integer page, Integer size, String orders, String filterAnd, String filterOr) {
        return null;
    }

    @Override
    public JobApplicant getSingleJobApplicant(String jobId, String jobApplicationId) throws JobNotExistsException {
        if (jobExists(jobId)) {
            Optional<JobApplicant> jobApplicant = jobApplicationRepository.findById(jobApplicationId);
            if (jobApplicant.isPresent() && jobApplicant.get().getId().equals(jobId)) {
                return jobApplicant.get();
            }
            throw new JobApplicantNotFoundException("Job applicant with id " + jobApplicationId + " not found");
        }
        throw new JobNotExistsException("Job with id: " + jobId + " does not exists");
    }


    /**
     * @param jobApplicationId       The job application Id to attach the CV or Resume to
     * @param resumeNameOrCvFileName The Resume or CV to be attached
     * @throws JobApplicantNotFoundException The Exception thrown when the Job Applicant is not found
     */
    @Override
    public void attachCVToJobApplication(@NotNull @NotEmpty String jobApplicationId, @NotNull @NotEmpty String resumeNameOrCvFileName) throws JobApplicantNotFoundException {
        String username = iAuthenticationFacadeService.getAuthentication().getName();
        LOGGER.info("JOB APPLICANT ID ::: {}", jobApplicationId);
        Optional<JobApplicant> optionalJobApplicant = jobApplicationRepository.findById(jobApplicationId);
        if (optionalJobApplicant.isPresent()) {
            JobApplicant jobApplicant = optionalJobApplicant.get();
            jobApplicant.setResumeDocumentUrl(resumeNameOrCvFileName);
            jobApplicant.setDateModified(new Date());
            jobApplicant.setModifiedBy(username);
            jobApplicationRepository.save(jobApplicant);
            LOGGER.info("Successfully Attached resumeNameOrCvFileName to {} jobApplicationId", jobApplicationId);
        }else {
            String message = String.format("Job Applicant: %s with jobApplicationId: %s Not Found",username , jobApplicationId );
            LOGGER.info(message);
            throw new JobApplicantNotFoundException(message);
        }
    }

    @Override
    public List<JobApplicant> getJobApplicants(String jobId) throws JobNotExistsException {
        if (jobExists(jobId)) {
            Optional<Job> jobApplicant = jobRepository.findById(jobId);
            if (jobApplicant.isPresent()) {
                return jobApplicant.get().getJobApplicants();
            } else return List.of();
        }
        throw new JobNotExistsException("Job with id: " + jobId + " does not exists");
    }

    @Override
    public Page<Job> getAllWithPageable(Query query , Pageable pageable) {
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query.with(pageable), Job.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Job.class)
        );
    }

    @Override
    public List<Job> getAllWithoutPageable(Query query) {
        return mongoTemplate.find(query, Job.class);
    }
}