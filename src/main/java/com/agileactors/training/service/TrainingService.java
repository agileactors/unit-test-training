package com.agileactors.training.service;

import com.agileactors.training.domain.Training;
import com.agileactors.training.exception.ResourceNotFoundException;

import java.util.UUID;

public interface TrainingService {
    Training getById(UUID id) throws ResourceNotFoundException;
}
