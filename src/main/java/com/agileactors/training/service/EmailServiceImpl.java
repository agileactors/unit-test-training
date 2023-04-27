package com.agileactors.training.service;

import com.agileactors.training.domain.Email;
import com.agileactors.training.domain.EmailStatus;
import com.agileactors.training.domain.Trainer;
import com.agileactors.training.exception.EmailProcessException;
import com.agileactors.training.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    public void sendNewTrainerCreatedEmail(Trainer trainer) {
        emailRepository.save(new Email(UUID.randomUUID(), trainer.getEmail(),
                "New Trainer", String.format("Trainer %s has been added.", trainer.getEmail()),
                EmailStatus.PENDING, Instant.now(), null));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processEmail(Email email) throws EmailProcessException {
        sendHtmlEmail(email);
        emailRepository.updateEmailStatusById(email.getId(), EmailStatus.COMPLETED);
    }

    private void sendHtmlEmail(Email email) throws EmailProcessException {

        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject(email.getSubject());

            MimeMessageHelper helper = new MimeMessageHelper(msg, false);
            helper.setFrom("noreply@agileactors.com", "Agile Actors - Unit Testing");
            helper.setTo(email.getEmail());
            helper.setText(email.getBody(), true);

            javaMailSender.send(msg);
        } catch (Exception e) {
            throw new EmailProcessException(email.getId(), e);
        }
    }

}
