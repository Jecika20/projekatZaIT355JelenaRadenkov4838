package com.metropolitan.repositories;

import com.metropolitan.models.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Integer> {
    Motor findMotorById(int id);

}
