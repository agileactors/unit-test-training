package com.agileactors.training.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateTrainerDto {
    private final String firstName;
    private final String lastName;
    private final String email;
}
