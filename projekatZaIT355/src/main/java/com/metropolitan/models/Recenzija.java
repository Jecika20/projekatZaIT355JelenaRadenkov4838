package com.metropolitan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recenzija {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String komentar;
    @Column(nullable = false)
    private int ocena;
    @ManyToOne
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;
    @ManyToOne
    @JoinColumn(name="vozilo_id")
    @JsonIgnore
    private Vozilo vozilo;

    private boolean aktivna=false;

    private LocalDateTime vremeKreiranja;
}
