package com.metropolitan.controllers;


import com.metropolitan.dtos.KorisnikDTO;
import com.metropolitan.models.Korisnik;
import com.metropolitan.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/register")
    public ResponseEntity<Korisnik> registerKorisnik(@RequestBody KorisnikDTO korisnikDTO) {
     Korisnik korisnik = korisnikService.registerKorisnik(korisnikDTO);
     if(korisnik != null) {
         return ResponseEntity.ok(korisnik);
     }
     return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<Korisnik> registerAdmin(@RequestBody KorisnikDTO korisnikDTO) {
        Korisnik admin = korisnikService.registerAdmin(korisnikDTO);
        if(admin != null) {
            return ResponseEntity.ok(admin);
        }
        return ResponseEntity.badRequest().build();

    }
}

