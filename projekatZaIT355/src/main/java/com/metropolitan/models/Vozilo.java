package com.metropolitan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipVozila;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Vozilo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brend;
    private int godinaProizvodnje;

    @Enumerated(EnumType.STRING)
    private TipVozila tipVozila;

    private double cenaPoDanu;

    @Enumerated(EnumType.STRING)
    private StatusVozila statusVozila;
    private double kilometraza;
    private String opis;

    @ManyToOne
    @JoinColumn(name="salon_id")
    private Salon salon;

    @OneToMany(mappedBy = "vozilo")
    @JsonIgnore
    private Set<Rezervacija> rezervacija;

    @OneToMany(mappedBy = "vozilo")
    @JsonIgnore
    private Set<SlikeVozila> slikeVozila;

}
