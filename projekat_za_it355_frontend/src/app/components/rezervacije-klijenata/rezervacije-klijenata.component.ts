import { Component, OnInit } from '@angular/core';
import { RezervacijeKorisnika } from '../../models/RezervacijeKorisnika/rezervacije-korisnika';
import { RezervacijaService } from '../../services/Rezervacija/rezervacija.service';

@Component({
  selector: 'app-rezervacije-klijenata',
  templateUrl: './rezervacije-klijenata.component.html',
  styleUrl: './rezervacije-klijenata.component.css'
})
export class RezervacijeKlijenataComponent implements OnInit{
  
  aktivneRezervacije:RezervacijeKorisnika[]=[];
  otkazaneRezervacije:RezervacijeKorisnika[]=[];
  zavrseneRezervacije:RezervacijeKorisnika[]=[];
  pageAktivne: number = 1;
  pageOtkazane: number = 1;
  pageZavrsene: number = 1;

  constructor(private rezervacijaService: RezervacijaService){}
  ngOnInit(): void {
    this.rezervacijaService.getAllRezervacije().subscribe({
      next: (res:RezervacijeKorisnika[])=>{
        this.aktivneRezervacije=res.filter(r=>r.statusRezervacije==='AKTIVNA');
        this.otkazaneRezervacije=res.filter(r=>r.statusRezervacije==='OTKAZANA');
        this.zavrseneRezervacije=res.filter(r=>r.statusRezervacije==='ZAVRSENA');
      }
    })
  }

}
