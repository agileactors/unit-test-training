package com.agileactors.training.service

import com.agileactors.training.domain.Training
import com.agileactors.training.repository.TrainingRepository
import spock.lang.Specification

class TrainingServiceImplSpec extends Specification {

    def "get training name by id returns correct name"() {
        given:
          TrainingRepository trainingRepository = Mock()
          TrainingServiceImpl trainingService = new TrainingServiceImpl(trainingRepository)
          Training training = new Training()
          UUID trainingId = UUID.randomUUID()
          training.setId(trainingId)
          training.setName("unit test")

        and:
          trainingRepository.findById(trainingId) >> Optional.of(training)

        when:
          String trainingName = trainingService.getTrainingNameById(trainingId)

        then:
          trainingName == "unit test"
    }

    def "get training name by id returns null when id is not found"() {
        given:
          TrainingRepository trainingRepository = Mock()
          TrainingServiceImpl trainingService = new TrainingServiceImpl(trainingRepository)
          Training training = new Training()
          UUID trainingId = UUID.randomUUID()
          training.setId(trainingId)
          training.setName("unit test")

        and:
          trainingRepository.findById(trainingId) >> Optional.of(training)

        when:
          String trainingName = trainingService.getTrainingNameById(UUID.randomUUID())

        then:
          !trainingName
    }
}
