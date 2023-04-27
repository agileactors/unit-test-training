package com.agileactors.training.converter;

import com.agileactors.training.domain.Trainer;
import com.agileactors.training.dto.GetTrainerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrainerToGetTrainerDtoConverter implements Converter<Trainer, GetTrainerDto> {

    @Override
    public GetTrainerDto convert(Trainer trainer) {
        return new GetTrainerDto(
                trainer.getId(),
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getEmail(),
                trainer.getRates(),
                trainer.getAvgRate()
        );
    }
}
