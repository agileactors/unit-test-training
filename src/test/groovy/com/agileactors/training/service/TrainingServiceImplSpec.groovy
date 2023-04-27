package com.agileactors.training.service

import com.agileactors.training.domain.Training
import com.agileactors.training.exception.ResourceNotFoundException
import com.agileactors.training.repository.TrainingRepository
import spock.lang.Specification

class TrainingServiceImplSpec extends Specification {

    def "get training returns the correct resource"() {
        given:
            TrainingRepository trainingRepository = Mock()
            TrainingServiceImpl trainingService = new TrainingServiceImpl(trainingRepository)

        UUID trainingId = UUID.randomUUID()
            String trainingName = "unit test"
            Training training = new Training(trainingId, trainingName, null)

        and:
            trainingRepository.findById(trainingId) >> Optional.of(training)

        when:
            Training repositoryTraining = trainingService.getById(trainingId)

        then:
            repositoryTraining.name == trainingName
            repositoryTraining.id == trainingId
    }

    def "get training throws exception if resource is not found"() {
        given:
            TrainingRepository trainingRepository = Mock()
            TrainingServiceImpl trainingService = new TrainingServiceImpl(trainingRepository)

        and:
            UUID trainingId = UUID.randomUUID()

            trainingRepository.findById(trainingId) >> Optional.empty()

        when:
            trainingService.getById(trainingId)

        then:
            final ResourceNotFoundException exception = thrown()
            exception.message == "Training[" + trainingId + "] not found"
    }
}
