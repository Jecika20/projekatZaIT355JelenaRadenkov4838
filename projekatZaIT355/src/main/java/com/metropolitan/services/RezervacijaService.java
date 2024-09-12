package com.metropolitan.services;

import com.metropolitan.models.Rezervacija;
import com.metropolitan.repositories.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervacijaService {


    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    public List<Rezervacija> getAll() {
        return rezervacijaRepository.findAll();
    }
    public Rezervacija getById(int id) {
        return rezervacijaRepository.findById(id);
    }
}
