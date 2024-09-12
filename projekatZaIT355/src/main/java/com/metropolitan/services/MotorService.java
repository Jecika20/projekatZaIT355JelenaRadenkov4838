package com.metropolitan.services;

import com.metropolitan.models.Motor;
import com.metropolitan.repositories.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorService {
    @Autowired
    private MotorRepository motorRepository;

    public List<Motor> getAllMotors() {
        return motorRepository.findAll();
    }
    public Motor getMotorById(int id) {
        return motorRepository.findMotorById(id);
    }
}
