package com.metropolitan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
