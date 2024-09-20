package com.metropolitan.services;

import com.metropolitan.dtos.AutomobilDTO;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipGoriva;
import com.metropolitan.enums.TipVozila;
import com.metropolitan.enums.VrstaAutomobila;
import com.metropolitan.models.Automobil;
import com.metropolitan.models.Salon;
import com.metropolitan.repositories.AutomobilRepository;
import com.metropolitan.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobilService {

    @Autowired
   private  AutomobilRepository automobilRepository;

    @Autowired
    private SalonRepository salonRepository;

    public List<Automobil> getAllAutomobil(){
        return automobilRepository.findAll();
    }
    public Automobil getAutomobilById(int id){
        return automobilRepository.findAutomobilById(id);
    }

    public Automobil createAutomobil(AutomobilDTO automobilDTO) {
        Salon salon = salonRepository.findById(automobilDTO.getSalon_id());
        if(salon == null){
            return null;
        }
        Automobil automobil = new Automobil();
        automobil.setModel(automobilDTO.getModel());
        automobil.setBrend(automobilDTO.getBrend());
        automobil.setGodinaProizvodnje(automobilDTO.getGodinaProizvodnje());
        automobil.setTipVozila(TipVozila.AUTOMOBIL);
        automobil.setCenaPoDanu(automobilDTO.getCenaPoDanu());
        automobil.setStatusVozila(StatusVozila.valueOf(automobilDTO.getStatusVozila()));
        automobil.setKilometraza(automobilDTO.getKilometraza());
        automobil.setOpis(automobilDTO.getOpis());
        automobil.setBrojVrata(automobilDTO.getBrojVrata());
        automobil.setTipGoriva(TipGoriva.valueOf(automobilDTO.getTipGoriva()));
        automobil.setVrstaAutomobila(VrstaAutomobila.valueOf(automobilDTO.getVrstaAutomobila()));
        automobil.setBrojSedista(automobilDTO.getBrojSedista());
        automobil.setSalon(salon);
        return automobilRepository.save(automobil);
    }
}
