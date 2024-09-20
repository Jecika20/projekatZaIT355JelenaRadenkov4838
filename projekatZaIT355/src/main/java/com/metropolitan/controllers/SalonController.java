package com.metropolitan.controllers;

import com.metropolitan.dtos.SalonDTO;
import com.metropolitan.models.Salon;
import com.metropolitan.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salon")
public class SalonController {

    @Autowired
    private SalonService salonService;

    @GetMapping
    public ResponseEntity<List<Salon>>  getAllSalons(){
        List<Salon> saloni = salonService.getAllSalons();
        return ResponseEntity.ok(saloni);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salon> getSalonById(@PathVariable int id){
      Salon salon = salonService.getSalonById(id);
      if(salon != null){
          return ResponseEntity.ok(salon);
      }
      return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Salon> createSalon(@RequestBody SalonDTO salonDTO){
            Salon salon=salonService.createSalon(salonDTO);
            if(salon != null){
                return ResponseEntity.ok(salon);
            }
            return ResponseEntity.badRequest().build();
    }

}
