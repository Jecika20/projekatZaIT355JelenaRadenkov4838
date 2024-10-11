package com.metropolitan.controllers;

import com.metropolitan.models.Vozilo;
import com.metropolitan.services.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vozilo")
public class VoziloController {

    @Autowired
    private VoziloService voziloService;

    @GetMapping("/get")
    public ResponseEntity<List<Vozilo>> getAll() {
        List<Vozilo> vozila = voziloService.getAll();
        return ResponseEntity.ok(vozila);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Vozilo> getVoziloById(@PathVariable  int id) {
        Vozilo vozilo = voziloService.getById(id);
        if(vozilo != null) {
            return ResponseEntity.ok(vozilo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/salon/{salon_id}")
    public ResponseEntity<List<Vozilo>> getVozilaBySalon(@PathVariable int salon_id) {
        List<Vozilo> vozila= voziloService.getVozilaBySalon(salon_id);
        if(vozila != null) {
            return ResponseEntity.ok(vozila);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statusRezervisano/{id}")
    public ResponseEntity<Vozilo> changeToRezervisano(@PathVariable int id) {
        Vozilo vozilo = voziloService.changeToRezervisano(id);
        if(vozilo != null) {
            return ResponseEntity.ok(vozilo);
        }
        return ResponseEntity.notFound().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statusDostupno/{id}")
    public ResponseEntity<Vozilo> changeToDostupno(@PathVariable int id) {
      Vozilo vozilo = voziloService.changeToDostupno(id);
      if(vozilo != null) {
          return ResponseEntity.ok(vozilo);
      }
      return ResponseEntity.notFound().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statusPopravka/{id}")
    public ResponseEntity<Vozilo> changeToPopravka(@PathVariable int id) {
        Vozilo vozilo = voziloService.changeToPopravka(id);
        if(vozilo != null) {
            return ResponseEntity.ok(vozilo);
        }
        return ResponseEntity.notFound().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Vozilo> deleteVozilo(@PathVariable int id){
        Vozilo vozilo = voziloService.deleteVozilo(id);
        if(vozilo != null) {
            return ResponseEntity.ok(vozilo);
        }
        return ResponseEntity.notFound().build();
    }




}
