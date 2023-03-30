package com.agileactors.training.service

import com.agileactors.training.domain.Trainer
import com.agileactors.training.dto.TrainerDTO
import com.agileactors.training.repository.TrainerRepository
import spock.lang.Specification

class TrainerServiceImplSpec extends Specification {

    def "create trainer succeeds and returns object with id"() {
        given:
          TrainerDTO trainerDTO = new TrainerDTO(firstName: "Kostas", lastName: "Sidiropoulos",
                  email: "kostas.sidiropoulos@test.com")
          TrainerRepository trainerRepository = Mock()
          EmailService emailService = Mock()
          UUID id = UUID.randomUUID()
          Trainer trainer = new Trainer(id: id, firstName: trainerDTO.firstName, lastName: trainerDTO.lastName,
                  email: trainerDTO.email)
           trainerRepository.save(_) >> trainer
          TrainerService trainerService = new TrainerServiceImpl(trainerRepository, emailService)

        when:
          TrainerDTO returnedTrainerDTO = trainerService.createTrainer(trainerDTO)

        then:
          returnedTrainerDTO.id == id
          returnedTrainerDTO.firstName == trainerDTO.firstName
          returnedTrainerDTO.lastName ==  trainerDTO.lastName
          returnedTrainerDTO.email == trainerDTO.email
          1 * trainerRepository.save(_) >> trainer
          1 * emailService.send(trainerDTO.email, "Success body")

    }

    def "rate trainer succeeds and calculates average rate"() {
        given:
          TrainerRepository trainerRepository = Mock()
          EmailService emailService = Mock()
          UUID id = UUID.randomUUID()
          Trainer trainer = new Trainer(id: id, firstName: "Kostas", lastName: "Sidiropoulos",
                  email: "kostas.sidiropoulos@test.com", rates: [])
          trainerRepository.findById(id) >> Optional.of(trainer)
          TrainerService trainerService = new TrainerServiceImpl(trainerRepository, emailService)

        when:
          trainerService.rateTrainer(id, 2);

        then:
          trainer.rates[0] == 2
          trainer.rate == 2
    }
}
