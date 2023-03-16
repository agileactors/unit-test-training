package com.agileactors.training.service;

import com.agileactors.training.domain.Training;
import com.agileactors.training.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public String getTrainingNameById(UUID id) {
        Optional<Training> optionalTraining = trainingRepository.findById(id);

        return optionalTraining.map(Training::getName).orElse(null);

    }
}
