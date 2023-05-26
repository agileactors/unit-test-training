package com.agileactors.training.controller

import com.agileactors.training.domain.Trainer
import com.agileactors.training.dto.GetTrainerDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.mock.web.MockHttpServletResponse
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.agileactors.training.TrainingApplication
import com.agileactors.training.service.TrainerService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.convert.ConversionService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@WebMvcTest(controllers = [TrainerController])
class TrainerControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    TrainerService trainerService

    @Autowired
    ConversionService conversionService

    def "test"() {
        given:
          UUID id  = UUID.randomUUID()
          Trainer trainer = new Trainer(id, "Kostas", "Sidis",  "test@test.com", [])
          trainerService.getById(id) >> trainer
          conversionService.convert(trainer, GetTrainerDto) >> new GetTrainerDto(id, "Kostas",  "Sidis", "test@test.com", [], 0.0d)
        when:
          MockHttpServletResponse mockHttpServletResponse =  mockMvc.perform(get("/trainers/" + id))
                  .andReturn().getResponse()
        then:
          mockHttpServletResponse.status == 200
          ObjectMapper objectMapper = new ObjectMapper()
          GetTrainerDto getTrainerDto = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), GetTrainerDto)
          getTrainerDto.id == id
    }

    @TestConfiguration                                          // 6
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        TrainerService trainerService() {
            return detachedMockFactory.Stub(TrainerService)
        }

        @Bean
        ConversionService conversionService() {
            return detachedMockFactory.Stub(ConversionService)
        }
    }
}
