package com.metropolitan.controllers;

import com.metropolitan.models.Korisnik;
import com.metropolitan.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable int id) {
        Korisnik korisnik = korisnikService.deleteKorisnik(id);
        if(korisnik != null) {
            return ResponseEntity.ok(korisnik);
        }
        return ResponseEntity.notFound().build();

    }
}
