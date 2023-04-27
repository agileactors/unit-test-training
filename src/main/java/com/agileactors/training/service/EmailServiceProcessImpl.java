package com.agileactors.training.service;

import com.agileactors.training.domain.Email;
import com.agileactors.training.domain.EmailStatus;
import com.agileactors.training.exception.EmailProcessException;
import com.agileactors.training.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceProcessImpl {

    private final EmailRepository emailRepository;
    private final EmailService emailService;

    @Scheduled(cron = "*/10 * * * * *")
    private void processEmail() {
        log.info("Email processing started");
        List<Email> pendingEmails = emailRepository.findAllByEmailStatus(EmailStatus.PENDING);
        log.info("Found {} emails for processing.", pendingEmails.size());

        if (pendingEmails.size() == 0) {
            log.info("Exiting process.");
            return;
        }

        pendingEmails.forEach(email -> {
            try {
                emailService.processEmail(email);
                log.info("Email {} has been sent.", email);
            } catch (EmailProcessException e) {
                log.error("Sending Email[" + email.getId() + "] failed.", e.getThrowable());
            }
        });
        log.info("Exiting process.");

    }

}
