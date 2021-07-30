package com.mergetechng.jobs.services.api;

import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IJobApplicationService {
    boolean apply(String jobApplicationId) throws NgJobApplicationClosedException, NgJobExpiredException, NgJobApplicationException, NgJobTotalAllowedApplicantsExceededException, NgJobApplicationSuspendedException;
    boolean delete(String applicantId) throws NgJobDeleteException;
    Pageable fetchApplicantsWithAdvanceFilterSearch(String applicantId);
    List<JobApplicant> getAllWithoutPageable(Query mongoQuery);
    Page<JobApplicant> getAllWithPageable(Query mongoQuery, Pageable pageable);
}
