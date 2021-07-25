package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.enums.RequestActionEnum;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.exceptions.*;
import com.mergetechng.jobs.services.api.IJobApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/job/application")
public class JobApplicationController {

    @Autowired
    private IJobApplicationService iJobApplicationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationController.class);

    @Operation(description = "Create New Job Applicant Or Apply to a Job Campaign")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to create new Job Applicant",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Job already expired... Application is closed",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Maximum number of applicant exceeded can't apply",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to apply. Server Error Encountered",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Job application was successful",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @PutMapping(value = "/apply", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> applyForJobApplication(
            @RequestParam(value = "jobId") String jobId) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Error Occurred",
                "400",
                RequestActionEnum.JOB_APPLICATION_APPLY.name(),
                new Date(),
                null);
        try {
            if (iJobApplicationService.apply(jobId)) {
                apiResponseDto.setMessage(String.format("Successfully applied for the Job with jobId: %s", jobId));
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                apiResponseDto.setMessage(String.format("Failed to applied for the Job with jobId: %s", jobId));
                apiResponseDto.setStatusCode("400");
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (NgJobApplicationClosedException
                | NgJobExpiredException
                | NgJobApplicationException | NgJobTotalAllowedApplicantsExceededException
                | NgJobApplicationSuspendedException e
        ) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Delete A Job Applicant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to delete Job Applicant",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicant Successfully Deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @DeleteMapping(value = "/remove", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> deleteApplicantFromJobApplication(
            @RequestParam(value = "applicantId") String applicantId) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Error Deleting Application",
                "400",
                RequestActionEnum.JOB_APPLICATION_DELETE.name(),
                new Date(),
                null);
        try {
            if (iJobApplicationService.delete(applicantId)) {
                apiResponseDto.setMessage(String.format("Successfully deleted applicant with applicantId: %s", applicantId));
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                apiResponseDto.setMessage(String.format("Failed to delete for Applicant with applicantId: %s", applicantId));
                apiResponseDto.setStatusCode("400");
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Delete A Job Applicant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Fetch completed with Error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Fetch query completed successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/advanceFilterSearch", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> fetchApplicantsWithAdvanceFilterSearch(
            @RequestParam(value = "size") String applicantId) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                RequestActionEnum.JOB_APPLICATION_DELETE.name(),
                new Date(),
                null);
        try {
            Pageable pageable = iJobApplicationService.fetchApplicantsWithAdvanceFilterSearch(applicantId);
            apiResponseDto.setMessage("Fetch query completed successfully");
            apiResponseDto.setData(pageable);
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}