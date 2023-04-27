package com.agileactors.training.service;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.CreateTrainerDto;
import com.agileactors.training.exception.ResourceNotFoundException;
import com.agileactors.training.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final EmailService emailService;
    private final ConversionService conversionService;

    @Override
    public Trainer create(CreateTrainerDto createTrainerDto) {
        var newTrainer = conversionService.convert(createTrainerDto, Trainer.class);

        Objects.requireNonNull(newTrainer);

        Trainer trainer = trainerRepository.save(newTrainer);

        emailService.send(trainer.getEmail(), "Success body");
        return trainer;
    }

    @Override
    public Trainer getById(UUID id) throws ResourceNotFoundException {
        return trainerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Trainer[" + id + "] not found"));
    }

    @Override
    public void rateTrainer(UUID id, Integer newRate) throws ResourceNotFoundException {
        Trainer trainer = getById(id);
        trainer.addRate(newRate);
        trainerRepository.save(trainer);
    }
}
