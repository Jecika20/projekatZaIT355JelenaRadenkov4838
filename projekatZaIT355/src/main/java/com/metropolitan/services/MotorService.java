package com.metropolitan.services;

import com.metropolitan.dtos.MotorDTO;
import com.metropolitan.dtos.UpdateMotorDTO;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipMotora;
import com.metropolitan.enums.TipVozila;
import com.metropolitan.models.Motor;
import com.metropolitan.models.Salon;
import com.metropolitan.repositories.MotorRepository;
import com.metropolitan.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorService {
    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private SalonRepository salonRepository;

    public List<Motor> getAllMotors() {
        return motorRepository.findAll();
    }
    public Motor getMotorById(int id) {
        return motorRepository.findMotorById(id);
    }

    public Motor createMotor(MotorDTO motorDTO) {
        Salon salon = salonRepository.findById(motorDTO.getSalon_id());
        if(salon == null) {
            return null;
        }
        Motor motor = new Motor();
        motor.setModel(motorDTO.getModel());
        motor.setBrend(motorDTO.getBrend());
        motor.setGodinaProizvodnje(motorDTO.getGodinaProizvodnje());
        motor.setTipVozila(TipVozila.MOTOR);
        motor.setCenaPoDanu(motorDTO.getCenaPoDanu());
        motor.setStatusVozila(StatusVozila.valueOf(motorDTO.getStatusVozila()));
        motor.setKilometraza(motorDTO.getKilometraza());
        motor.setOpis(motorDTO.getOpis());
        motor.setBrojCilindara(motorDTO.getBrojCilindara());
        motor.setBrojTockova(motorDTO.getBrojTockova());
        motor.setTipMotora(TipMotora.valueOf(motorDTO.getTipMotora()));
        motor.setSalon(salon);
        return motorRepository.save(motor);
    }

    public Motor updateMotor(UpdateMotorDTO updateMotorDTO) {
        Motor motor = motorRepository.findMotorById(updateMotorDTO.getId());
        if(motor == null) {
            return null;
        }
        motor.setModel(updateMotorDTO.getModel());
        motor.setBrend(updateMotorDTO.getBrend());
        motor.setGodinaProizvodnje(updateMotorDTO.getGodinaProizvodnje());
        motor.setCenaPoDanu(updateMotorDTO.getCenaPoDanu());
        motor.setStatusVozila(StatusVozila.valueOf(updateMotorDTO.getStatusVozila()));
        motor.setKilometraza(updateMotorDTO.getKilometraza());
        motor.setOpis(updateMotorDTO.getOpis());
        motor.setBrojCilindara(updateMotorDTO.getBrojCilindara());
        motor.setBrojTockova(updateMotorDTO.getBrojTockova());
        motor.setTipMotora(TipMotora.valueOf(updateMotorDTO.getTipMotora()));
        return motorRepository.save(motor);
    }

}
