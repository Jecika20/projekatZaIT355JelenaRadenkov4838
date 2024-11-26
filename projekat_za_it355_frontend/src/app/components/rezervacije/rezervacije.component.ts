import { Component, OnInit } from '@angular/core';
import { RezervacijeKorisnika } from '../../models/RezervacijeKorisnika/rezervacije-korisnika';
import { RezervacijaService } from '../../services/Rezervacija/rezervacija.service';

@Component({
  selector: 'app-rezervacije',
  templateUrl: './rezervacije.component.html',
  styleUrl: './rezervacije.component.css'
})
export class RezervacijeComponent implements OnInit{
  rezervacije: RezervacijeKorisnika[] =[];
  constructor(private rezervacijaService: RezervacijaService){

  }
  ngOnInit(): void {
   this.rezervacijaService.getRezervacijeByKorisnik().subscribe({
    next: (res: RezervacijeKorisnika[] ) => {
      this.rezervacije = res;
    }
   })
  }

}
