package com.metropolitan.services;

import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }
    public Vozilo getById(int id) {
        return voziloRepository.findById(id);
    }
}
