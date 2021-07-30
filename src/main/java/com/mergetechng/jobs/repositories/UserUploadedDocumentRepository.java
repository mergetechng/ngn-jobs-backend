package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.UserUploadDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserUploadedDocumentRepository extends MongoRepository<UserUploadDocument , String> {
    Optional<UserUploadDocument> findByIdOrFileName(String userUploadDocumentId , String uploadedFileName );
}
