package com.agileactors.training.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetTrainerDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<Integer> rates;
    private final Double rate;

}
