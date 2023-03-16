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
          UUID id = UUID.randomUUID()
          Trainer trainer = new Trainer(id: id, firstName: trainerDTO.firstName, lastName: trainerDTO.lastName,
                  email: trainerDTO.email)
          trainerRepository.save(_) >> trainer
          TrainerService trainerService = new TrainerServiceImpl(trainerRepository)

        when:
          TrainerDTO returnedTrainerDTO = trainerService.createTrainer(trainerDTO)

        then:
          returnedTrainerDTO.id == id
          returnedTrainerDTO.firstName == trainerDTO.firstName
          returnedTrainerDTO.lastName ==  trainerDTO.lastName
          returnedTrainerDTO.email == trainerDTO.email
    }
}
