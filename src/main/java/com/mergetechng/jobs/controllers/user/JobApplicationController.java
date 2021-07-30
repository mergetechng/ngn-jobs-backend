package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.FilterCondition;
import com.mergetechng.jobs.commons.enums.RequestActionEnum;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.*;
import com.mergetechng.jobs.repositories.GenericFilterCriteriaBuilder;
import com.mergetechng.jobs.services.FilterBuilderService;
import com.mergetechng.jobs.services.api.IJobApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job/application")
public class JobApplicationController {

    @Autowired
    private IJobApplicationService iJobApplicationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationController.class);

    @Autowired
    private FilterBuilderService filterBuilderService;

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
    public ResponseEntity<ApiResponseDto<String>> applyForJobApplication(
            @RequestParam(value = "jobId") String jobId) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
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
                apiResponseDto.setMessage(String.format("Not Found jobId: %s", jobId));
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
    public ResponseEntity<ApiResponseDto<String>> deleteApplicantFromJobApplication(
            @RequestParam(value = "jobApplicationId") String jobApplicationId) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Error Deleting Application",
                "400",
                RequestActionEnum.JOB_APPLICATION_DELETE.name(),
                new Date(),
                null);
        try {
            if (iJobApplicationService.delete(jobApplicationId)) {
                apiResponseDto.setMessage(String.format("Successfully deleted applicant with applicantId: %s", jobApplicationId));
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                apiResponseDto.setMessage(String.format("Failed to delete for Applicant with applicantId: %s", jobApplicationId));
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


    @Operation(description = "Pageable Advance search")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Advance search completed successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Pageable Advance search completed with BadRequest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/advanceQuerySearch")
    public ResponseEntity<ApiResponseDto<List<JobApplicant>>> advanceQuerySearch(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "filterOr", required = false) String filterOr
    ) throws BadRequestException {
        ApiResponseDto<List<JobApplicant>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                "ADVANCE_SEARCH",
                new Date(),
                null);
        try {
            GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
            List<FilterCondition> queryFilterAndCondition = filterBuilderService.createFilterCondition(filterAnd);
            List<FilterCondition> queryFilterOrCondition = filterBuilderService.createFilterCondition(filterOr);
            Query mongoQuery = filterCriteriaBuilder.addCondition(queryFilterAndCondition, queryFilterOrCondition);
            List<JobApplicant> pageableUsers = iJobApplicationService.getAllWithoutPageable(mongoQuery);
            apiResponseDto.setData(pageableUsers);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "User Advance search")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Advance search completed successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Advance search completed with BadRequest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/advancePageableQuerySearch")
    public ResponseEntity<ApiResponseDto<Page<JobApplicant>>> advancePageableQuerySearch(
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "orders", required = false) String orders,
            @RequestParam(value = "size", required = false) Integer size) {
        ApiResponseDto<Page<JobApplicant>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                "ADVANCE_SEARCH",
                new Date(),
                null);
        try {

            Pageable pageable = filterBuilderService.getPageable(size, page, orders);
            GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();

            List<FilterCondition> queryFilterAndCondition = filterBuilderService.createFilterCondition(filterAnd);
            List<FilterCondition> queryFilterOrCondition = filterBuilderService.createFilterCondition(filterOr);

            LOGGER.info(Arrays.toString(queryFilterAndCondition.toArray()));
            LOGGER.info(Arrays.toString(queryFilterOrCondition.toArray()));

            Query mongoQuery = filterCriteriaBuilder.addCondition(queryFilterAndCondition, queryFilterOrCondition);
            Page<JobApplicant> pageableUsers = iJobApplicationService.getAllWithPageable(mongoQuery, pageable);

            apiResponseDto.setData(pageableUsers);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}