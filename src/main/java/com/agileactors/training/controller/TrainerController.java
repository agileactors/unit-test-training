package com.agileactors.training.controller;

import com.agileactors.training.dto.CreateTrainerDto;
import com.agileactors.training.dto.GetTrainerDto;
import com.agileactors.training.exception.ResourceNotFoundException;
import com.agileactors.training.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private final ConversionService conversionService;

    @PostMapping
    public GetTrainerDto create(@RequestBody CreateTrainerDto createTrainerDto) {
        return conversionService.convert(trainerService.create(createTrainerDto), GetTrainerDto.class);
    }

    @GetMapping("/{id}")
    public GetTrainerDto getTrainerById(@PathVariable UUID id) throws ResourceNotFoundException {
        return conversionService.convert(trainerService.getById(id), GetTrainerDto.class);
    }
}

