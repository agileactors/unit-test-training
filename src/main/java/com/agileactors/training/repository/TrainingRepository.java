package com.agileactors.training.repository;

import com.agileactors.training.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {

    Training findByName(String name);
}
