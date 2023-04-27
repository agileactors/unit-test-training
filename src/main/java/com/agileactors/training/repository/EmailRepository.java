package com.agileactors.training.repository;

import com.agileactors.training.domain.Email;
import com.agileactors.training.domain.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {

    List<Email> findAllByEmailStatus(EmailStatus emailStatus);

    @Modifying
    @Query("update Email email set email.emailStatus = :emailStatus where email.id = :emailId")
    void updateEmailStatusById(UUID emailId, EmailStatus emailStatus);

}