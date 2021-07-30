package com.mergetechng.jobs.services;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.mergetechng.jobs.commons.EnvironmentVariables;
import com.mergetechng.jobs.commons.enums.DocumentAccessLevelEnum;
import com.mergetechng.jobs.commons.enums.DocumentStateEnum;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.UserUploadDocument;
import com.mergetechng.jobs.exceptions.JobApplicantNotFoundException;
import com.mergetechng.jobs.repositories.JobApplicantRepository;
import com.mergetechng.jobs.repositories.UserUploadedDocumentRepository;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Amazons3ClientService {
    private AmazonS3 s3client;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.uploaded_file_prefix}")
    private String uploadedFilePrefix;
    @Autowired
    private UserUploadedDocumentRepository userUploadedDocumentRepository;
    @Autowired
    private IAuthenticationFacadeService iAuthenticationFacadeService;
    @Autowired
    private JobApplicantRepository jobApplicantRepository;
    private String AMAZON_S3_DOCUMENT_UPLOAD_URL_TEMPLATE;
    @Autowired
    private JobService jobService;

    private final static Logger LOGGER = LoggerFactory.getLogger(Amazons3ClientService.class);

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(
                EnvironmentVariables.AMAZON_PROPERTIES_ACCESS_KEY,
                EnvironmentVariables.AMAZON_PROPERTIES_SECRET_KEY);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
        this.AMAZON_S3_DOCUMENT_UPLOAD_URL_TEMPLATE = this.bucketName + "." + endpointUrl + "/%s";
    }

    /**
     * @param multipartFile    The MultipartFile to be uploaded to the Amazon S3 bucket
     * @param jobApplicationId This will be used to attach the uploaded Resume or CV
     * @return String
     * @throws JobApplicantNotFoundException The Exception thrown
     */
    public String uploadFileToAmazonS3ObjectBucket(@NotNull MultipartFile multipartFile, @NotNull @NotEmpty String jobApplicationId) throws JobApplicantNotFoundException, IOException, AmazonClientException, AmazonServiceException {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = String.format(AMAZON_S3_DOCUMENT_UPLOAD_URL_TEMPLATE, fileName);
            resumeOrCVUploadHelper(fileName, file, fileUrl);
            boolean deleteOperation = file.delete();
            jobService.attachCVToJobApplication(jobApplicationId, fileName);
            LOGGER.info("\nFile Name : {} \n Date: {} By : {} \n Temp Deleted : {}", fileName, new Date(), iAuthenticationFacadeService.getAuthentication().getName(), deleteOperation);
        } catch (AmazonServiceException ase) {
            LOGGER.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
            LOGGER.info("Error Message: {} \n HTTP Status Code: {}\n  AWS Error Code:  {}\n Error Type: {}\n Request ID: {}\n " + ase.getMessage(), ase.getStatusCode(), ase.getErrorCode(), ase.getErrorType(), ase.getRequestId());
        } catch (AmazonClientException ace) {
            LOGGER.info("Caught an AmazonClientException: ");
            LOGGER.info("Error Message: " + ace.getMessage());
            throw new AmazonClientException("Error Message: " + ace.getMessage());
        } catch (IOException ioe) {
            LOGGER.info("IOE Error Message: " + ioe.getMessage());
            throw new IOException("Error Message: " + ioe.getMessage());
        }
        return fileUrl;
    }

    /**
     * @param userUploadDocumentId The UserUploadedDocument Id stored in the UserUploadedDocument collection
     * @return java.lang.Boolean
     * @throws UnsupportedOperationException The Exception Returned when LOCKED file is attempted to be deleted
     */
    public boolean deleteFileFromS3Bucket(@NotEmpty @NotNull @NotBlank String jobApplicationId, @NotEmpty @NotNull @NotBlank String userUploadDocumentId) throws UnsupportedOperationException {
        Optional<UserUploadDocument> userUploadDocumentOptional = userUploadedDocumentRepository.findByIdOrFileName(userUploadDocumentId);
        Optional<Job> optionalJob = Optional.ofNullable(jobService.getJob(jobApplicationId));
        if (userUploadDocumentOptional.isPresent() && optionalJob.isPresent()) {
            UserUploadDocument userUploadDocument = userUploadDocumentOptional.get();
            if (userUploadDocument.getDocumentState().equals(DocumentStateEnum.LOCKED.name())) {
                throw new UnsupportedOperationException("Document is locked and can't be deleted from at the moment please try again");
            } else {
                String fileName = userUploadDocument.getFileName();
                s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
                userUploadedDocumentRepository.deleteById(userUploadDocument.getId());
                return true;
            }
        }
        return false;
    }


    /**
     * @param fileName               The file name to be uploaded to the Amazon S3 Bucket
     * @param convertedMultipartFile The Convert {@link MultipartFile} to {@link File} to be uploaded
     * @return java.lang.Void
     */
    private void resumeOrCVUploadHelper(@NotEmpty @NotNull String fileName, @NotNull File convertedMultipartFile, String fileURL) throws IOException {
        LOGGER.info("Uploading {} to S3 bucket {}...\n", fileName, bucketName);
        s3client.putObject(bucketName, fileName, convertedMultipartFile);
        String username = iAuthenticationFacadeService.getAuthentication().getName();
        UserUploadDocument userUploadDocument = new UserUploadDocument();
        userUploadDocument.setUsername(username);
        userUploadDocument.setDocumentUrl(fileURL);
        userUploadDocument.setDateCreated(new Date());
        userUploadDocument.setId(UUID.randomUUID().toString());
        userUploadDocument.setDocumentState(DocumentStateEnum.OPEN.name());
        userUploadDocument.setAccessLevel(DocumentAccessLevelEnum.PRIVATE.name());
        userUploadDocument.setCreatedBy(username);
        userUploadDocument.setFileMetadata(Map.of("fileContentType", Files.probeContentType(convertedMultipartFile.toPath())));
        userUploadedDocumentRepository.insert(userUploadDocument);
    }

    private String generateFileName(MultipartFile multiPart) {
        return this.uploadedFilePrefix + "_" + new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * @param uploadedFileIdOrFileName The file name or fileId to be downloaded
     * @return Object[]
     */
    public Object[] downloadAmazonS3ObjectDocument(String uploadedFileIdOrFileName) {
        LOGGER.info("Downloading {} from S3 bucket {}...\n", uploadedFilePrefix, bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        try {
            Optional<UserUploadDocument> userUploadDocumentOptional = userUploadedDocumentRepository.findByIdOrFileName(uploadedFileIdOrFileName);
            if (userUploadDocumentOptional.isPresent()) {
                UserUploadDocument userUploadDocument = userUploadDocumentOptional.get();
                S3Object o = s3.getObject(bucketName, userUploadDocument.getFileName());
                S3ObjectInputStream s3is = o.getObjectContent();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] read_buf = new byte[1024];
                int read_len = 0;
                while ((read_len = s3is.read(read_buf)) > 0) {
                    byteArrayOutputStream.write(read_buf, 0, read_len);
                }
                s3is.close();
                byteArrayOutputStream.close();

                List<String> usersWhoAccessed = userUploadDocument.getUserIdsWhoAccessed();
                Collections.addAll(usersWhoAccessed, iAuthenticationFacadeService.getAuthentication().getName());
                userUploadDocument.setUserIdsWhoAccessed(usersWhoAccessed);

                List<String> usersWhoDownloaded = userUploadDocument.getUserIdsWhoDownload();
                Collections.addAll(usersWhoDownloaded, iAuthenticationFacadeService.getAuthentication().getName());
                userUploadDocument.setUserIdsWhoDownload(usersWhoDownloaded);

                userUploadDocument.setLastAccessDate(new Date());
                userUploadDocument.setLastDownloadDate(new Date());
                userUploadDocument.setTotalDownloadCount(userUploadDocument.getTotalDownloadCount() + 1);
                userUploadedDocumentRepository.save(userUploadDocument);

                return new Object[]{userUploadDocument.getFileName(), byteArrayOutputStream, userUploadDocument.getFileMetadata().get("fileContentType")};
            } else {
                return new Object[]{};
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return new Object[]{};
    }
}