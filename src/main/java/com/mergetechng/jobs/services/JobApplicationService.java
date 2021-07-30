package com.mergetechng.jobs.services;

import com.mergetechng.jobs.commons.enums.JobStatusEnum;
import com.mergetechng.jobs.controllers.user.JobApplicationController;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.exceptions.*;
import com.mergetechng.jobs.repositories.JobApplicationRepository;
import com.mergetechng.jobs.services.api.IJobApplicationService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.Optional;
import java.util.UUID;

@Service
public class JobApplicationService implements IJobApplicationService {
    @Autowired
    private IAuthenticationFacadeService iAuthenticationFacadeService;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobService jobService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationController.class);

    @Override
    public boolean apply(String jobId) throws NgJobApplicationClosedException, NgJobTotalAllowedApplicantsExceededException, NgJobApplicationSuspendedException {
        Optional<Job> optionalJob = Optional.ofNullable(jobService.getJob(jobId));
        if (optionalJob.isPresent()) {
            Job fetchedJob = optionalJob.get();
            if (ObjectUtils.isNotEmpty(fetchedJob) && fetchedJob.getJobStatus().equals(JobStatusEnum.ACTIVE.name())) {
                String username = iAuthenticationFacadeService.getAuthentication().getName();
                Optional<JobApplicant> jobApplicantOptional = jobApplicationRepository.findByApplicantUsernameAndJobId(username , jobId);
                if (jobApplicantOptional.isPresent()){
                    throw new UnsupportedOperationException("Already applied. Can't apply twice");
                }
                JobApplicant jobApplicant = new JobApplicant();
                jobApplicant.setId(UUID.randomUUID().toString());
                jobApplicant.setDateCreated(new Date());
                jobApplicant.setApplicantUsername(username);
                jobApplicant.setCreatedBy(username);
                jobApplicant.setJobId(jobId);
                jobApplicationRepository.insert(jobApplicant);
                return true;
            } else if (fetchedJob.getJobStatus().equals(JobStatusEnum.CLOSED.name())) {
                throw new NgJobApplicationClosedException("The application for this job is closed");
            } else if (fetchedJob.getJobStatus().equals(JobStatusEnum.APPLICATION_NUMBER_REACHED.name())) {
                throw new NgJobTotalAllowedApplicantsExceededException("The application for this job has reached maximum number of applicant");
            } else if (fetchedJob.getJobStatus().equals(JobStatusEnum.SUSPENDED.name())) {
                throw new NgJobApplicationSuspendedException("The application for this job is suspended");
            }
        }
        return false;
    }

    @Override
    public boolean delete(String applicantId) {
        Optional<JobApplicant> optionalJobApplicant = jobApplicationRepository.findById(applicantId);
        if (optionalJobApplicant.isPresent()) {
            jobApplicationRepository.deleteById(applicantId);
            //delete the CV/Resume later
            return true;
        } else return false;
    }

    @Deprecated
    @Override
    public Pageable fetchApplicantsWithAdvanceFilterSearch(String applicantId) {
        return null;
    }
}