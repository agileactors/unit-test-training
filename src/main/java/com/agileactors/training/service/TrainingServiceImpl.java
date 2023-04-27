package com.agileactors.training.service;

import com.agileactors.training.domain.Training;
import com.agileactors.training.exception.ResourceNotFoundException;
import com.agileactors.training.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public Training getById(UUID id) throws ResourceNotFoundException {
        return trainingRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Training[" + id + "] not found"));
    }
}
