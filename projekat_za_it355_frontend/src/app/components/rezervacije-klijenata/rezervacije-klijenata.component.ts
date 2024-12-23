import { Component, OnInit } from '@angular/core';
import { RezervacijeKorisnika } from '../../models/RezervacijeKorisnika/rezervacije-korisnika';
import { RezervacijaService } from '../../services/Rezervacija/rezervacija.service';
import { ToastrService } from 'ngx-toastr';

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
  showDialog=false;
  rezervacija: number;
  flag:number;
 

  constructor(private rezervacijaService: RezervacijaService, private toastr:ToastrService){}
  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.rezervacijaService.getAllRezervacije().subscribe({
      next: (res:RezervacijeKorisnika[])=>{
        this.aktivneRezervacije=res.filter(r=>r.statusRezervacije==='AKTIVNA');
        this.otkazaneRezervacije=res.filter(r=>r.statusRezervacije==='OTKAZANA');
        this.zavrseneRezervacije=res.filter(r=>r.statusRezervacije==='ZAVRSENA');
      }
    })
  }
  openDialog(id:number,flag:number){
    this.rezervacija=id;
    this.flag=flag;
    this.showDialog= true;
  }
  closeDialog(){
    this.showDialog= false;
  }


  otkazi(){
    this.rezervacijaService.otkaziRezervaciju(this.rezervacija).subscribe({
      next: (res:any)=>{
        this.toastr.success("Uspesno ste otkazali rezervaciju");
        this.loadData();
      },
      error: (err:any)=>{
        this.toastr.error("Doslo je do greske prilikom otkazivanja rezervacije");
      },
      complete: ()=>{
        this.showDialog=false;
      }
    });

    
  }
  zavrsi(){
    this.rezervacijaService.zavrsiRezervaciju(this.rezervacija).subscribe({
      next: (res:any)=>{
        this.toastr.success("Uspesno ste zavrsili rezervaciju");
        this.loadData();
      },
      error: (err:any)=>{
        this.toastr.error("Doslo je do greske prilikom zavrsavanja rezervacije");
      },
      complete: ()=>{
        this.showDialog=false;
      }
    });

  }
  obrisi(){
    this.rezervacijaService.brisanjeRezervacije(this.rezervacija).subscribe({
      next: (res:any)=>{
        this.toastr.success("Uspesno ste obrisali rezervaciju");
        this.loadData();
      },
      error: (err:any)=>{
        this.toastr.error("Doslo je do greske prilikom brisanje rezervacije");
      },
      complete: ()=>{
        this.showDialog=false;
      }
    });
   }
}
