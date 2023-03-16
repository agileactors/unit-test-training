package com.agileactors.training.service;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.TrainerDTO;
import com.agileactors.training.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService{

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.save(toTrainer(trainerDTO));
        return toTrainerDto(trainer);
    }

    @Override
    public TrainerDTO getTrainer(UUID id) {
        return trainerRepository.findById(id).map(this::toTrainerDto).orElse(null);
    }

    private Trainer toTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDTO.getId());
        trainer.setEmail(trainerDTO.getEmail());
        trainer.setFirstName(trainerDTO.getFirstName());
        trainer.setLastName(trainerDTO.getLastName());
        return trainer;
    }

    private TrainerDTO toTrainerDto(Trainer trainer) {
        TrainerDTO trainerDto = new TrainerDTO();
        trainerDto.setId(trainer.getId());
        trainerDto.setEmail(trainer.getEmail());
        trainerDto.setFirstName(trainer.getFirstName());
        trainerDto.setLastName(trainer.getLastName());
        return trainerDto;
    }
}
