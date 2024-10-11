package com.metropolitan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Salon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String naziv;
    private String telefon;
    private String email;
    private String opis;
    private String adresa;
    private String grad;

    @OneToMany(mappedBy = "salon",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Vozilo> vozila;

}
