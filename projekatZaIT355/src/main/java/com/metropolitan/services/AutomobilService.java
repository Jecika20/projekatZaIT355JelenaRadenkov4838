package com.metropolitan.services;

import com.metropolitan.models.Automobil;
import com.metropolitan.repositories.AutomobilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobilService {

    @Autowired
   private  AutomobilRepository automobilRepository;

    public List<Automobil> getAllAutomobil(){
        return automobilRepository.findAll();
    }
    public Automobil getAutomobilById(int id){
        return automobilRepository.findAutomobilById(id);
    }
}
