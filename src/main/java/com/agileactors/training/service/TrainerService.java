package com.agileactors.training.service;

import com.agileactors.training.dto.TrainerDTO;

import java.util.UUID;

public interface TrainerService {

    TrainerDTO createTrainer(TrainerDTO trainerDTO);

    TrainerDTO getTrainer(UUID id);
}
