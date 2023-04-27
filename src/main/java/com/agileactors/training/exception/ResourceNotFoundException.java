package com.agileactors.training.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundException extends Exception {
    private final String message;
}
