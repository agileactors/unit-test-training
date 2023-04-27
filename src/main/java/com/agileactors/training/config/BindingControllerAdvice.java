package com.agileactors.training.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class BindingControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}