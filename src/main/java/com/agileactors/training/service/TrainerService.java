package com.agileactors.training.service;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.CreateTrainerDto;
import com.agileactors.training.exception.ResourceNotFoundException;

import java.util.UUID;

public interface TrainerService {

    Trainer create(CreateTrainerDto createTrainerDto);

    Trainer getById(UUID id) throws ResourceNotFoundException;

    void rateTrainer(UUID id, Integer rate) throws ResourceNotFoundException;
}
