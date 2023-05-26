package com.agileactors.training.service

import com.agileactors.training.domain.Trainer
import com.agileactors.training.dto.CreateTrainerDto
import com.agileactors.training.repository.TrainerRepository
import org.springframework.core.convert.ConversionService
import spock.lang.Specification

class TrainerServiceImplSpec extends Specification {

    def "create trainer succeeds and returns object with id"() {
        given:
            CreateTrainerDto createTrainerDto = new CreateTrainerDto(
                    "Kostas",
                    "Sidiropoulos",
                    "kostas.sidiropoulos@test.com"
            )

            TrainerRepository trainerRepository = Mock()
            EmailService emailService = Mock()
            ConversionService conversionService = Mock()

            UUID id = UUID.randomUUID()
            Trainer trainer = new Trainer(id, createTrainerDto.firstName, createTrainerDto.lastName,
                    createTrainerDto.email, Collections.EMPTY_LIST)
            trainerRepository.save(_) >> trainer
            TrainerService trainerService = new TrainerServiceImpl(trainerRepository, emailService, conversionService)

        when:
            Trainer createdTrainer = trainerService.create(createTrainerDto)

        then:
            createdTrainer.id == id
            createdTrainer.firstName == createTrainerDto.firstName
            createdTrainer.lastName == createTrainerDto.lastName
            createdTrainer.email == createTrainerDto.email
            createdTrainer.rates.size() == 0
            1 * trainerRepository.save(_) >> trainer
            1 * conversionService.convert(createTrainerDto, Trainer.class) >> trainer
            1 * emailService.sendNewTrainerCreatedEmail(trainer)
            1 * trainerRepository.findByEmail(createTrainerDto.email)
    }
}
