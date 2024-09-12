package com.metropolitan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metropolitan.enums.Uloga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private String sifra;
    private String adresa;
    private String grad;
    @Enumerated(EnumType.STRING)
    private Uloga uloga;

    @OneToMany(mappedBy = "korisnik")
    @JsonIgnore
    private Set<Rezervacija> rezervacije;

}
