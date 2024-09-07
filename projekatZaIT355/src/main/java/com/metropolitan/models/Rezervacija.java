package com.metropolitan.models;

import com.metropolitan.enums.StatusRezervacije;
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
public class Rezervacija {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private LocalDateTime vremeOd;
    private LocalDateTime vremeDo;
    private double ukupnaCena;
    private StatusRezervacije statusRezervacije;

    @ManyToOne
    @JoinColumn(name="vozilo_id")
    private Vozilo vozilo;

    @ManyToOne
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;

}
