package com.metropolitan.controllers;

import com.metropolitan.dtos.SlikeVozilaDTO;
import com.metropolitan.models.SlikeVozila;
import com.metropolitan.services.SlikeVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slikeVozila")
public class SlikeVozilaController {
    @Autowired
    private SlikeVozilaService slikeVozilaService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<SlikeVozila> createSlikeVozila(@RequestBody SlikeVozilaDTO slikeVozilaDTO){
        SlikeVozila slikeVozila = slikeVozilaService.createSlikeVozila(slikeVozilaDTO);
        if(slikeVozila != null){
            return ResponseEntity.ok(slikeVozila);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SlikeVozila> deleteSlikeVozila(@PathVariable int id) {
         SlikeVozila slikeVozila = slikeVozilaService.deleteSlikeVozila(id);
         if(slikeVozila != null) {
             return ResponseEntity.ok(slikeVozila);
         }
         return ResponseEntity.notFound().build();
    }

}
