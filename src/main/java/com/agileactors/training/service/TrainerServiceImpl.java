package com.agileactors.training.service;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.TrainerDTO;
import com.agileactors.training.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;
    private EmailService emailService;

    public TrainerServiceImpl(TrainerRepository trainerRepository, EmailService emailService) {
        this.trainerRepository = trainerRepository;
        this.emailService = emailService;
    }

    @Override
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.save(toTrainer(trainerDTO));
        emailService.send(trainer.getEmail(), "Success body");
        return toTrainerDto(trainer);
    }



    @Override
    public TrainerDTO getTrainer(UUID id) {
        return trainerRepository.findById(id).map(this::toTrainerDto).orElse(null);
    }

    @Override
    public void rateTrainer(UUID id, Integer newRate) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<Integer> rates = trainer.getRates();
        rates.add(newRate);
        Double avgRate = averageRate(rates);
        trainer.setRate(avgRate);
        trainerRepository.save(trainer);
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

    private Double averageRate(List<Integer> rates) {
        return rates.stream()
                .mapToDouble(a->a)
                .average().orElse(0);
    }
}
