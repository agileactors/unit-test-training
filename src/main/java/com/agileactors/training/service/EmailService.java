package com.agileactors.training.service;

import com.agileactors.training.domain.Email;
import com.agileactors.training.domain.Trainer;
import com.agileactors.training.exception.EmailProcessException;

public interface EmailService {

    void sendNewTrainerCreatedEmail(Trainer trainer);

    void processEmail(Email email) throws EmailProcessException;
}
