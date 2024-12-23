import { Component, OnInit } from '@angular/core';
import { RezervacijeKorisnika } from '../../models/RezervacijeKorisnika/rezervacije-korisnika';
import { RezervacijaService } from '../../services/Rezervacija/rezervacija.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-rezervacije',
  templateUrl: './rezervacije.component.html',
  styleUrl: './rezervacije.component.css'
})
export class RezervacijeComponent implements OnInit{
  rezervacije: RezervacijeKorisnika[] =[];
  showDialog=false;
  rezervacijaZaOtkazivanje: number;
  page: number = 1;
  constructor(private rezervacijaService: RezervacijaService,private toastr:ToastrService){

  }
  ngOnInit(): void {
   this.loadData();
  }
  loadData(){
    this.rezervacijaService.getRezervacijeByKorisnik().subscribe({
      next: (res: RezervacijeKorisnika[] ) => {
        this.rezervacije = res;
      }
     })
  }

  openDialog(id:number){
    this.rezervacijaZaOtkazivanje=id;
    this.showDialog= true;
  }
  closeDialog(){
    this.showDialog= false;
  }


  otkaziRezervaciju(){
    this.rezervacijaService.otkaziRezervaciju(this.rezervacijaZaOtkazivanje).subscribe({
      next: (res:any) => 
      {
        this.toastr.success("Uspesno ste otkazali rezervaciju");
        this.loadData();
      },
      error: (err:any) =>{
        this.toastr.error ("Doslo je do greske prilikom otkazivanja");
      },
      complete: ()=>{
        this.showDialog = false;
      }
    })
  }
}
