package com.metropolitan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecenzijaDTO {
    private String komentar;
    private int ocena;
    private int vozilo_id;
    private boolean aktivna;
}
