package com.metropolitan.services;

import com.metropolitan.models.Salon;
import com.metropolitan.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    public Salon getSalonById(int id) {
        return salonRepository.findById(id);
    }
}
