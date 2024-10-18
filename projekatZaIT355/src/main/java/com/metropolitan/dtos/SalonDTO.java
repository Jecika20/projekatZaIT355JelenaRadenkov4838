package com.metropolitan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalonDTO {
    private String naziv;
    private String telefon;
    private String email;
    private String opis;
    private String adresa;
    private String grad;
    private String slika;


}
