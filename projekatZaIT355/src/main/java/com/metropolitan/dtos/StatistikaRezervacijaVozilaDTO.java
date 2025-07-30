package com.metropolitan.dtos;

import com.metropolitan.enums.StatusRezervacije;
import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Vozilo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatistikaRezervacijaVozilaDTO {
    private Vozilo vozilo;
    private StatusRezervacije statusRezervacije;
    private Long brojRezervacija;
}
