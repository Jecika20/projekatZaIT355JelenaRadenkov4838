package com.metropolitan.services;

import com.metropolitan.dtos.SlikeVozilaDTO;
import com.metropolitan.models.SlikeVozila;
import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.SlikeVozilaRepository;
import com.metropolitan.repositories.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlikeVozilaService {
    @Autowired
    private SlikeVozilaRepository slikeVozilaRepository;

    @Autowired
    private VoziloRepository voziloRepository;


    public SlikeVozila deleteSlikeVozila(int id) {
        SlikeVozila slikeVozila = slikeVozilaRepository.findById(id);
        if(slikeVozila == null) {
           return null;
        }
        slikeVozilaRepository.delete(slikeVozila);
        return slikeVozila;
    }

    public SlikeVozila createSlikeVozila(SlikeVozilaDTO slikeVozilaDTO) {
        Vozilo vozilo = voziloRepository.findById(slikeVozilaDTO.getVozilo_id());
        if(vozilo == null) {
            return null;
        }
        SlikeVozila slikeVozila = new SlikeVozila();
        slikeVozila.setVozilo(vozilo);
        slikeVozila.setSlika(slikeVozilaDTO.getSlika());
       return  slikeVozilaRepository.save(slikeVozila);
    }
}


