package com.metropolitan.dtos;

import com.metropolitan.enums.TipMotora;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MotorDTO {
    private String model;
    private String brend;
    private int godinaProizvodnje;
    private double cenaPoDanu;
    private String statusVozila;
    private double kilometraza;
    private String opis;
    private int salon_id;
    private int brojCilindara;
    private int brojTockova;
    private String tipMotora;
}
