package com.mergetechng.jobs.services;


import com.mergetechng.jobs.services.api.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    /**
     *
     * @param subject The subject of the mail
     * @param text The content of the mail
     * @param attachment The file to send with the mail
     * @param to The users or emails to send the mail to
     * @return
     */
    @Retryable( value = {MailSendException.class,ConnectException.class,UnknownHostException.class}, label = "Retry Sending Email After failing")
    @Override
    public CompletableFuture<String> sendSimpleMessage(String subject, String text, FileSystemResource attachment , String... to ) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                if (attachment != null) {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    mimeMessageHelper.setFrom("developer@transcentng.com");
                    mimeMessageHelper.setSubject(subject);
                    mimeMessageHelper.setTo(to);
                    mimeMessageHelper.setSentDate(new Date());
                    mimeMessageHelper.setText(text);
                    mimeMessageHelper.addAttachment("File", attachment);
                    javaMailSender.send(mimeMessage);
                    return "Mail sent successfully";
                } else {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("developer@transcentng.com");
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(text);
                    javaMailSender.send(message);
                    LOGGER.info("Done sending mail to " +  Arrays.toString(to));
                    return "Mail sent successfully";
                }
            } catch (MessagingException e) {
                e.printStackTrace();
                LOGGER.error("ERROR", e);
                LOGGER.info("Retrying sending of mail to " + Arrays.toString(to));
            }
            return "Mail failed to send";
        });
        completableFuture.complete("Sent mail to the email address successfully");
        return completableFuture;
    }

//    public void sendSimpleMessage(String subject, String text, FileSystemResource attachment , int count , String... to) throws ExecutionException, InterruptedException {
//        sendSimpleMessage(subject , text , attachment , to);
//    }
}