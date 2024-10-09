package com.metropolitan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RezervacijaDTO {
    private LocalDateTime vremeOd;
    private LocalDateTime vremeDo;
    private double ukupnaCena;
    private int vozilo_id;


}
