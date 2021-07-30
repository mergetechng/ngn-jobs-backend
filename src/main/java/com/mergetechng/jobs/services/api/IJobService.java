package com.mergetechng.jobs.services.api;

import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.exceptions.JobApplicantNotFoundException;
import com.mergetechng.jobs.exceptions.JobNotExistsException;
import com.mergetechng.jobs.exceptions.UserAccountAlreadyVerifiedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IJobService {
    Job getJob(String jobId);

    boolean deleteJob(String jobId) throws JobNotExistsException;

    boolean updateJob(String jobId);

    List<Job> getAll(Query query);

    Page<Job> getPage(Query query, Pageable pageable);

    List<Job> fullTextSearch(String searchPhrase);

    boolean updateJob(Job job) throws JobNotExistsException;

    boolean applyForJob(String jobId, String userId);

    boolean createNewJob(Job job, String userName) throws UserAccountAlreadyVerifiedException;

    Page<Job> pageFetchJobApplicant(Integer page, Integer size, String orders, String filterAnd, String filterOr);

    JobApplicant getSingleJobApplicant(String jobId, String jobApplicationId) throws JobNotExistsException;

    List<JobApplicant> getJobApplicants(String jobId) throws JobNotExistsException;

    void attachCVToJobApplication(@NotNull @NotEmpty String jobApplicationId, @NotNull @NotEmpty String resumeNameOrCvFileName) throws JobApplicantNotFoundException;

    void attachCVToJobApplicationAfters3Upload(@NotNull @NotEmpty String jobApplicationId, @NotNull @NotEmpty String documentURL) throws JobApplicantNotFoundException;

}
