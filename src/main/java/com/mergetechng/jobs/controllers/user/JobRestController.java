package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.FilterCondition;
import com.mergetechng.jobs.commons.dto.JobDto;
import com.mergetechng.jobs.commons.enums.CVEnum;
import com.mergetechng.jobs.commons.enums.RequestActionEnum;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.commons.util.CycleAvoidingMappingContext;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.exceptions.BadRequestException;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.repositories.GenericFilterCriteriaBuilder;
import com.mergetechng.jobs.repositories.JobApplicantRepository;
import com.mergetechng.jobs.services.*;
import com.mergetechng.jobs.services.api.IJobService;
import com.mergetechng.jobs.services.api.JobApiGeneralMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/job")
public class JobRestController {
    @Autowired
    private IJobService iJobService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobRestController.class);
    @Autowired
    private Amazons3ClientService amazons3ClientService;
    @Autowired
    private IAuthenticationFacadeService iAuthenticationFacadeService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JobApplicantRepository jobApplicantRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private FilterBuilderService filterBuilderService;


    @Operation(description = "Create new Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to create new Job", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created the job",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @PostMapping(value = "/create", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> createJob(@RequestBody JobDto jobDto) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed to create job",
                "400",
                "CREATE_JOB",
                new Date(),
                null);
        try {
            if (iJobService.createNewJob(JobApiGeneralMapper.INSTANCE.jobDtoToJob(jobDto, new CycleAvoidingMappingContext()), iAuthenticationFacadeService.getAuthentication().getName())) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Successfully created Job");
                apiResponseDto.setData("Ok");
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (UserNotFoundException e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            apiResponseDto.setData("Not Ok");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            apiResponseDto.setData("Not Ok");
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Delete Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to delete Job", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully delete the job",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @DeleteMapping(value = "/remove", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> deleteJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed to delete job",
                "400",
                "DELETE_JOB",
                new Date(),
                null);
        try {
            if (iJobService.deleteJob(jobIb)) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setData("Ok");
                apiResponseDto.setMessage("Successfully deleted the job with id: " + jobIb);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                apiResponseDto.setData("Not Ok");
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            apiResponseDto.setData("Not ok");
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Update Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to update Job", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully update the job",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @PostMapping(value = "/update", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> updateJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "UPDATE_JOB",
                new Date(),
                null);
        try {
            if (iJobService.updateJob(jobIb)) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setData("Ok");
                apiResponseDto.setMessage("Successfully update the job with id: " + jobIb);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                apiResponseDto.setData("Not Ok");
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setData("Not Ok");
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Get Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to get Job", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully get job",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @GetMapping(value = "/fetch", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<Job>> getJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto<Job> apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "GET_JOB",
                new Date(),
                null);
        try {
            Job job = iJobService.getJob(jobIb);
            if (ObjectUtils.allNotNull(job.getId())) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Successfully fetch the jobs" + jobIb);
                apiResponseDto.setData(job);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                apiResponseDto.setStatusCode("404");
                apiResponseDto.setMessage("Not found : " + jobIb);
                apiResponseDto.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Job search with fulltext search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to search jobs", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully search available jobs",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @GetMapping(value = "/fullTextSearch", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<List<Job>>> fullTextSearch(@RequestParam(name = "keyphrase") String keyphrase) {
        ApiResponseDto<List<Job>> apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "GET_JOBS",
                new Date(),
                null);
        try {
            List<Job> searchJobs = iJobService.fullTextSearch(keyphrase);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Successfully fetch the jobs with key" + keyphrase);
            apiResponseDto.setData(searchJobs);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Pageable fetch all applicants for the Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to fetch {jobId} job applicants", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all job applicants",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @GetMapping(value = "/{jobId}/applicants/", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<Page<Job>>> pageableFetchApplicants(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "orders") String orders,
            @RequestParam(name = "filterAnd") String filterAnd,
            @RequestParam(name = "filerOr") String filerOr
    ) {
        ApiResponseDto<Page<Job>> apiResponseDto = ApiResponseUtil.process(
                "Failed fetch applicants",
                "400",
                "GET_JOB",
                new Date(),
                null);
        try {
            Page<Job> jobs = iJobService.pageFetchJobApplicant(page, size, orders, filterAnd, filerOr);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Successfully fetch the jobs");
            apiResponseDto.setData(jobs);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Fetch particular applicant for the Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to fetch {jobId} job applicants", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all job applicants",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @GetMapping(value = "/{jobId}/applicant/{applicantId}", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<JobApplicant>> getSingleInfoApplicantForTheJob(
            @PathVariable(value = "jobId") String jobId,
            @PathVariable(value = "applicantId") String applicantId) {
        ApiResponseDto<JobApplicant> apiResponseDto = ApiResponseUtil.process(
                "Failed fetch applicants",
                "400",
                RequestActionEnum.GET_JOB.name(),
                new Date(),
                null);
        try {
            LOGGER.info("JOB ID {}", jobId);
            JobApplicant jobApplicant = iJobService.getSingleJobApplicant(jobId, applicantId);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Successfully fetch the jobs");
            apiResponseDto.setData(jobApplicant);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Job Pageable Advance search")
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
    public ResponseEntity<ApiResponseDto<List<Job>>> advanceQuerySearch(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "filterOr", required = false) String filterOr
    ) throws BadRequestException {
        ApiResponseDto<List<Job>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                "JOB_ADVANCE_SEARCH",
                new Date(),
                null);
        try {
            GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
            List<FilterCondition> queryFilterAndCondition = filterBuilderService.createFilterCondition(filterAnd);
            List<FilterCondition> queryFilterOrCondition = filterBuilderService.createFilterCondition(filterOr);
            Query mongoQuery = filterCriteriaBuilder.addCondition(queryFilterAndCondition, queryFilterOrCondition);
            List<com.mergetechng.jobs.entities.Job> pageableUsers = jobService.getAllWithoutPageable(mongoQuery);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Ok");
            apiResponseDto.setData(pageableUsers);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Job Advance search")
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
    public ResponseEntity<ApiResponseDto<Page<Job>>> advancePageableQuerySearch(
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "orders", required = false) String orders,
            @RequestParam(value = "size", required = false) Integer size) {
        ApiResponseDto<Page<Job>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                "JOB_ADVANCE_PAGEABLE_SEARCH",
                new Date(),
                null);
        try {
            Pageable pageable = filterBuilderService.getPageable(size, page, orders);
            GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
            List<FilterCondition> queryFilterAndCondition = filterBuilderService.createFilterCondition(filterAnd);
            List<FilterCondition> queryFilterOrCondition = filterBuilderService.createFilterCondition(filterOr);
            Query mongoQuery = filterCriteriaBuilder.addCondition(queryFilterAndCondition, queryFilterOrCondition);
            Page<com.mergetechng.jobs.entities.Job> pageableUsers = jobService.getAllWithPageable(mongoQuery, pageable);
            apiResponseDto.setData(pageableUsers);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Download Applicant Application CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to download file", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully downloaded the CV",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            )})
    @GetMapping(value = "/cv-resume/download", produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> downloadJobApplicantCVResume(
            @RequestParam(value = "uploadedFileIdOrFileName", required = false) String uploadedFileIdOrFileName) {
        String message;
        try {
            final Object[] UPLOAD_RESULT = amazons3ClientService.downloadAmazonS3ObjectDocument(uploadedFileIdOrFileName);
            if (ObjectUtils.isNotEmpty(UPLOAD_RESULT)) {
                ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) UPLOAD_RESULT[1];
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.valueOf((String) UPLOAD_RESULT[2]))
                        .header("Content-Disposition", "attachment; filename=\"" + UPLOAD_RESULT[0] + "\"")
                        .body(byteArrayOutputStream.toByteArray());
            } else {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("File Does not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(description = "Download Applicant Application CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to upload file", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully upload CV/Resume",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            )
    })
    @PostMapping(value = "/cv-resume", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> uploadApplicantCVResume(
            @RequestParam(value = "jobApplicationId") String jobApplicationId,
            @RequestParam("file") MultipartFile multipartFile) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed to upload cv/resume",
                "400",
                RequestActionEnum.JOB_CV_RESUME_UPLOAD.name(),
                new Date(),
                null);
        String message;
        try {
            message = "Successfully upload CV/Resume";
            final String fileUrl = amazons3ClientService.uploadFileToAmazonS3ObjectBucket(multipartFile, jobApplicationId);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage(message);
            apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_UPLOAD.name());
            apiResponseDto.setData(fileUrl);
            LOGGER.info(message);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Download Applicant Application CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Failed to delete CV/Resume", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted CV with file name :resumeNameOrCvFileNameOrUploadedDocumentId",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
    })
    @DeleteMapping(value = "/cv-resume", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> deleteApplicantCVResume(
            @RequestParam(value = "cvResumeURLOrUploadDocumentIdOrFileName") String cvResumeURLOrUploadDocumentIdOrFileName) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed delete applicant CV/Resume",
                "400",
                RequestActionEnum.GET_JOB.name(),
                new Date(),
                null);
        String message;
        try {
            apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_DELETE.name());
            if (amazons3ClientService.deleteFileFromS3Bucket(cvResumeURLOrUploadDocumentIdOrFileName)) {
                message = String.format("Successfully deleted CV/Resume with file name %s", cvResumeURLOrUploadDocumentIdOrFileName);
                apiResponseDto.setMessage(message);
                apiResponseDto.setStatusCode("200");
                LOGGER.info(message);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                message = String.format("Oops! Could not delete file with %s", cvResumeURLOrUploadDocumentIdOrFileName);
                apiResponseDto.setMessage(message);
                LOGGER.info(message);
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Attach Applicant Application CV/Resume to Job Applied")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Oops! Can't attach CV/Resume. jobApplicationId & cvResumeURLOrUploadDocumentIdOrFileName are required to complete this.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Oops! Can't attach CV/Resume. jobApplicationId & cvResumeURLOrUploadDocumentIdOrFileName are required to complete this.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted CV with file name :cvResumeURLOrUploadDocumentIdOrFileName",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
    })
    @PutMapping(value = "/cv-resume/attach", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> attachApplicantCVResumeToJob(
            @RequestParam(value = "cvResumeURLOrUploadDocumentIdOrFileName", required = false) String cvResumeURLOrUploadDocumentIdOrFileName,
            @RequestParam(value = "jobApplicationId", required = false) String jobApplicationId) {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed attach applicant CV/Resume",
                "400",
                RequestActionEnum.GET_JOB.name(),
                new Date(),
                null);
        String message;
        try {
            apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_ATTACH.name());
            if (ObjectUtils.isNotEmpty(jobApplicationId) && ObjectUtils.isNotEmpty(cvResumeURLOrUploadDocumentIdOrFileName)) {
                iJobService.attachCVToJobApplication(jobApplicationId, cvResumeURLOrUploadDocumentIdOrFileName);
                message = String.format("Successfully attached %s to jobApplicationId:%s", cvResumeURLOrUploadDocumentIdOrFileName, jobApplicationId);
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage(message);
                apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_ATTACH.name());
                LOGGER.info(message);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                message = "Oops! Can't attach CV/Resume. jobApplicationId & cvResumeURLOrUploadDocumentIdOrFileName are required to complete this.";
                LOGGER.info(message);
                apiResponseDto.setStatusCode("400");
                apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_ATTACH.name());
                apiResponseDto.setMessage(message);
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}