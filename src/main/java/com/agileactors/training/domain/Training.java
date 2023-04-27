package com.agileactors.training.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Training {

    @Id
    @Column(name = "training_id")
    private UUID id;

    @Column(name = "training_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;


}
