package com.agileactors.training.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class EmailProcessException extends Exception {
    private final UUID emailId;
    private final Throwable throwable;
}
