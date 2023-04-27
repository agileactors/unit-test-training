package com.agileactors.training.controller;

import com.agileactors.training.dto.CreateTrainerDto;
import com.agileactors.training.dto.GetTrainerDto;
import com.agileactors.training.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private final ConversionService conversionService;

    @PostMapping
    public GetTrainerDto create(CreateTrainerDto createTrainerDto) {
        return conversionService.convert(trainerService.create(createTrainerDto), GetTrainerDto.class);
    }
}

