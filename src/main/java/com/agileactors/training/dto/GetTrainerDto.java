package com.agileactors.training.dto;

import java.util.List;
import java.util.UUID;
public class GetTrainerDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Integer> rates;
    private Double rate;

    public GetTrainerDto(UUID id, String firstName, String lastName, String email, List<Integer> rates, Double rate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rates = rates;
        this.rate = rate;
    }

    public GetTrainerDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getRates() {
        return rates;
    }

    public void setRates(List<Integer> rates) {
        this.rates = rates;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
