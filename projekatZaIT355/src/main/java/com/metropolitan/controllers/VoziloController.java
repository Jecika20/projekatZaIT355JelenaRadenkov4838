package com.metropolitan.controllers;

import com.metropolitan.models.Vozilo;
import com.metropolitan.services.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vozilo")
public class VoziloController {

    @Autowired
    private VoziloService voziloService;

    @GetMapping
    public ResponseEntity<List<Vozilo>> getAll() {
        List<Vozilo> vozila = voziloService.getAll();
        return ResponseEntity.ok(vozila);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vozilo> getVoziloById(@PathVariable  int id) {
        Vozilo vozilo = voziloService.getById(id);
        if(vozilo != null) {
            return ResponseEntity.ok(vozilo);
        }
        return ResponseEntity.notFound().build();
    }
}
