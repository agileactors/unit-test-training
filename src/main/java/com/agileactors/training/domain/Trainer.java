package com.agileactors.training.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Trainer {

    @Id
    @Column(name = "trainer_id")
    private UUID id;

    @Column(name = "trainer_first_name")
    private String firstName;

    @Column(name = "trainer_last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "rates")
    private List<Integer> rates;

    public double getAvgRate() {
        return rates.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }

    public void addRate(Integer newRate) {
        if (rates == null) {
            rates = new ArrayList<>();
        }
        rates.add(newRate);
    }
}
