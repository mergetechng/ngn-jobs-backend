package com.mergetechng.jobs.services.api;

import org.springframework.core.io.FileSystemResource;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IEmailService {
    CompletableFuture<String> sendSimpleMessage(String subject, String text, FileSystemResource attachment, String... to) throws MessagingException, ExecutionException, InterruptedException;
//    void sendSimpleMessage(String subject, String text, FileSystemResource attachment, int count, String... to) throws MessagingException, ExecutionException, InterruptedException;
}

