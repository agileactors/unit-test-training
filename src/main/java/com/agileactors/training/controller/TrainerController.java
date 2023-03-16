package com.agileactors.training.controller;

import com.agileactors.training.dto.TrainerDTO;
import com.agileactors.training.service.TrainerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerController {

    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/trainer")
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        return trainerService.createTrainer(trainerDTO);
    }
}
