package com.agileactors.training.converter;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.CreateTrainerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class CreateTrainerDtoTrainerConverter implements Converter<CreateTrainerDto, Trainer> {

    @Override
    public Trainer convert(CreateTrainerDto createTrainerDto) {
        return new Trainer(
                UUID.randomUUID(),
                createTrainerDto.getFirstName(),
                createTrainerDto.getLastName(),
                createTrainerDto.getEmail(),
                Collections.emptyList()
        );
    }
}
