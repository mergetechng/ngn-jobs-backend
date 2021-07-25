package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.JobDto;
import com.mergetechng.jobs.commons.enums.CVEnum;
import com.mergetechng.jobs.commons.enums.RequestActionEnum;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.commons.util.CycleAvoidingMappingContext;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.JobApplicant;
import com.mergetechng.jobs.repositories.JobApplicantRepository;
import com.mergetechng.jobs.services.Amazons3ClientService;
import com.mergetechng.jobs.services.IAuthenticationFacadeService;
import com.mergetechng.jobs.services.api.IJobService;
import com.mergetechng.jobs.services.api.JobApiGeneralMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/job")
public class JobRestController {
    @Autowired
    private IJobService iJobService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobRestController.class);
    @Value("${amazonProperties.bucketName}")
    private static String AWS_BASE_BUCKET_URL;
    @Autowired
    private Amazons3ClientService amazons3ClientService;
    @Autowired
    private IAuthenticationFacadeService iAuthenticationFacadeService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JobApplicantRepository jobApplicantRepository;


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
    public ResponseEntity<ApiResponseDto> createJob(@RequestBody JobDto jobDto) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to create job",
                "200",
                "CREATE_JOB",
                new Date(),
                null);
        try {
            if (!iJobService.createNewJob(JobApiGeneralMapper.INSTANCE.jobDtoToJob(jobDto, new CycleAvoidingMappingContext()), iAuthenticationFacadeService.getAuthentication().getName())) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Successfully created Job");
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
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
    @DeleteMapping(value = "/delete", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> deleteJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to delete job",
                "400",
                "DELETE_JOB",
                new Date(),
                null);
        try {
            if (!iJobService.deleteJob(jobIb)) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Successfully deleted the job with id: " + jobIb);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
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
    public ResponseEntity<ApiResponseDto> updateJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "UPDATE_JOB",
                new Date(),
                null);
        try {
            if (!iJobService.updateJob(jobIb)) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Successfully update the job with id: " + jobIb);
                return ResponseEntity.ok().body(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
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
    public ResponseEntity<ApiResponseDto> getJob(@RequestParam(name = "jobId") String jobIb) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "GET_JOB",
                new Date(),
                null);
        try {
            Job job = iJobService.getJob(jobIb);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Successfully fetch the jobs" + jobIb);
            apiResponseDto.setData(job.getJobId().equals(jobIb) ? job : null);
            return ResponseEntity.ok().body(apiResponseDto);
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
    @GetMapping(value = "/search-jobs", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> fullTextSearch(@RequestParam(name = "keyPhrase") String keyPhrase) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to update job",
                "400",
                "GET_JOBS",
                new Date(),
                null);
        try {
            List<Job> searchJobs = iJobService.fullTextSearch(keyPhrase);
            apiResponseDto.setStatusCode("200");
            apiResponseDto.setMessage("Successfully fetch the jobs with key" + keyPhrase);
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
    public ResponseEntity<ApiResponseDto> pageableFetchApplicants(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "orders") String orders,
            @RequestParam(name = "filterAnd") String filterAnd,
            @RequestParam(name = "filerOr") String filerOr
    ) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
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
    @GetMapping(value = " /{jobId}/applicants/{applicantId}", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> getSingleInfoApplicantForTheJob(
            @PathParam(value = "jobId") String jobId,
            @PathParam(value = "jobApplicationId") String jobApplicationId) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed fetch applicants",
                "400",
                RequestActionEnum.GET_JOB.name(),
                new Date(),
                null);
        try {
            JobApplicant jobApplicant = iJobService.getSingleJobApplicant(jobId, jobApplicationId);
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


    @Operation(description = "Download cv for the applied Job")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "query params values -> jobApplicationId or uploadedFileIdOrFileName does not exist",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "jobId,jobApplicationId or uploadedFileIdOrFileName query params are required",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully upload CV/Resume",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Oops! Can't attach CV/Resume. jobApplicationId & jobApplicationId are required to complete this.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted CV with file name :resumeNameOrCvFileNameOrUploadedDocumentId",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Oops! Can't attach CV/Resume. jobApplicationId & jobApplicationId are required to complete this.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully downloaded the CV",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}
            )})
    @GetMapping(value = " /job-cv-resume/", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> jobCVResume(
            @RequestParam(value = "jobId", required = false) String jobId,
            @RequestParam(value = "uploadedFileIdOrFileName", required = false) String uploadedFileIdOrFileName,
            @RequestParam(value = "cvAction") String cvAction,
            @RequestParam(value = "resumeNameOrCvFileNameOrUploadedDocumentId", required = false) String resumeNameOrCvFileNameOrUploadedDocumentId,
            @RequestParam(value = "jobApplicationId", required = false) String jobApplicationId,
            HttpServletResponse httpServletResponse,
            MultipartFile multipartFile
    ) {
        String message = "Operation not allowed. Required query params not matched";
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                message,
                "400",
                RequestActionEnum.JOB_CV_RESUME_DEFAULT.name(),
                new Date(),
                null);
        try {
            switch (CVEnum.valueOf(cvAction.toUpperCase())) {
                case DOWNLOAD:
                    if (ObjectUtils.isNotEmpty(jobId) && ObjectUtils.isNotEmpty(jobApplicationId) && ObjectUtils.isNotEmpty(uploadedFileIdOrFileName)) {
                        if (Optional.ofNullable(iJobService.getJob(jobApplicationId)).isPresent() && jobApplicantRepository.findById(jobApplicationId).isPresent()) {
                            final Object[] UPLOAD_RESULT = amazons3ClientService.downloadAmazonS3ObjectDocument(uploadedFileIdOrFileName);
                            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + UPLOAD_RESULT[0] + "\"");
                            httpServletResponse.setStatus(302);
                            apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_DEFAULT.name());
//                            httpServletResponse.sendRedirect();
                            return ResponseEntity.noContent().build();
                        } else {
                            message = "query params values -> jobApplicationId or uploadedFileIdOrFileName does not exist";
                            apiResponseDto.setStatusCode("400");
                            apiResponseDto.setMessage(message);
                            apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_DEFAULT.name());
                            LOGGER.info(message);
                            return ResponseEntity.badRequest().body(apiResponseDto);
                        }
                    } else {
                        message = "jobId,jobApplicationId or uploadedFileIdOrFileName query params are required";
                        apiResponseDto.setStatusCode("400");
                        apiResponseDto.setMessage(message);
                        apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_DEFAULT.name());
                        LOGGER.info(message);
                        return ResponseEntity.badRequest().body(apiResponseDto);
                    }
                case UPLOAD:
                    if (ObjectUtils.isNotEmpty(jobId) && ObjectUtils.isNotEmpty(jobApplicationId)) {
                        message = "Successfully upload CV/Resume";
                        final String fileUrl = amazons3ClientService.uploadFileToAmazonS3ObjectBucket(multipartFile, jobApplicationId);
                        apiResponseDto.setStatusCode("200");
                        apiResponseDto.setMessage(message);
                        apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_UPLOAD.name());
                        apiResponseDto.setData(fileUrl);
                        LOGGER.info(message);
                        return ResponseEntity.ok().body(apiResponseDto);
                    }
                case DELETE:
                    apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_DELETE.name());
                    if (ObjectUtils.isNotEmpty(jobApplicationId) && ObjectUtils.isNotEmpty(resumeNameOrCvFileNameOrUploadedDocumentId)) {
                        if (amazons3ClientService.deleteFileFromS3Bucket(jobApplicationId, resumeNameOrCvFileNameOrUploadedDocumentId)) {
                            message = String.format("Successfully deleted CV with file name %s", resumeNameOrCvFileNameOrUploadedDocumentId);
                            apiResponseDto.setMessage(message);
                            LOGGER.info(message);
                            return ResponseEntity.ok().body(apiResponseDto);
                        } else {
                            message = String.format("Oops! Could not delete file with %s and job with %s", resumeNameOrCvFileNameOrUploadedDocumentId, jobApplicationId);
                            apiResponseDto.setMessage(message);
                            LOGGER.info(message);
                            return ResponseEntity.badRequest().body(apiResponseDto);
                        }
                    } else {
                        message = "Oops! Can't attach CV/Resume. jobApplicationId & jobApplicationId are required to complete this.";
                        apiResponseDto.setStatusCode("200");
                        apiResponseDto.setMessage(message);
                        LOGGER.info(message);
                        return ResponseEntity.badRequest().body(apiResponseDto);
                    }
                case ATTACH_JOB_CV:
                    if (ObjectUtils.isNotEmpty(jobApplicationId) && ObjectUtils.isNotEmpty(resumeNameOrCvFileNameOrUploadedDocumentId)) {
                        iJobService.attachCVToJobApplication(jobApplicationId, resumeNameOrCvFileNameOrUploadedDocumentId);
                        message = String.format("Successfully attached CV to %s", jobApplicationId);
                        apiResponseDto.setStatusCode("200");
                        apiResponseDto.setMessage(message);
                        apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_ATTACH.name());
                        LOGGER.info(message);
                        return ResponseEntity.ok().body(apiResponseDto);
                    } else {
                        message = "Oops! Can't attach CV/Resume. jobApplicationId & jobApplicationId are required to complete this.";
                        apiResponseDto.setStatusCode("400");
                        apiResponseDto.setAction(RequestActionEnum.JOB_CV_RESUME_ATTACH.name());
                        apiResponseDto.setMessage(message);
                        LOGGER.info(message);
                        return ResponseEntity.badRequest().body(apiResponseDto);
                    }
                default:
                    return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}