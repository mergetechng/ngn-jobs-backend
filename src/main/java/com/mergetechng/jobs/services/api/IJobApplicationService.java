package com.mergetechng.jobs.services.api;

import com.mergetechng.jobs.exceptions.*;
import org.springframework.data.domain.Pageable;

public interface IJobApplicationService {
    boolean apply(String jobApplicationId) throws NgJobApplicationClosedException, NgJobExpiredException, NgJobApplicationException, NgJobTotalAllowedApplicantsExceededException, NgJobApplicationSuspendedException;
    boolean delete(String applicantId) throws NgJobDeleteException;
    Pageable fetchApplicantsWithAdvanceFilterSearch(String applicantId);
}
