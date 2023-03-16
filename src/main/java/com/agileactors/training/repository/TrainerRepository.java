package com.agileactors.training.repository;

import com.agileactors.training.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, UUID> {

    Trainer findByFirstNameAndLastName(String firstName, String lastName);

    Trainer findByLastName(String lastName);

}
