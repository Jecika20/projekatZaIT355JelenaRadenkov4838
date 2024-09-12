package com.metropolitan.controllers;

import com.metropolitan.models.Camac;
import com.metropolitan.models.Motor;
import com.metropolitan.services.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/motor")
public class MotorController {
    @Autowired
    private MotorService motorService;

    @GetMapping
    public ResponseEntity<List<Motor>> getAllMotors() {
        List<Motor> motors = motorService.getAllMotors();
        return ResponseEntity.ok(motors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Motor> getMotorById(@PathVariable int id) {
        Motor motor = motorService.getMotorById(id);
        if(motor != null) {
            return ResponseEntity.ok(motor);
        }
        return ResponseEntity.notFound().build();
    }

}
