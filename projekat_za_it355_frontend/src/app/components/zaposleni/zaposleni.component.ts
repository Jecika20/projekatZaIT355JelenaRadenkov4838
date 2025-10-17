import { Component, OnInit } from '@angular/core';
import { Korisnik } from '../../models/Korisnik/korisnik';
import { Radnik } from '../../models/Radnik/radnik';
import { KorisnikService } from '../../services/Korisnik/korisnik.service';
// @ts-ignore
import * as bootstrap from 'bootstrap';
import { Salon } from '../../models/Salon/salon';
import { SalonService } from '../../services/Salon/salon.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-zaposleni',
  templateUrl: './zaposleni.component.html',
  styleUrl: './zaposleni.component.css'
})
export class ZaposleniComponent implements OnInit{
  admini: Korisnik[]= [];
  radnici: Radnik[]=[];
  saloni: Salon[]=[];
  izabraniZaposleni: any = {};
  private modalRef: any;
  privatniTip: 'admin' | 'radnik' | null = null;

  constructor(private korisnikService:KorisnikService,
    private salonService:SalonService,
    private toastr: ToastrService
  ){

  }
   ngOnInit(): void {
    this.loadAdmini();
    this.loadRadnici();
    this.loadSaloni();
  }

  loadAdmini(){
    this.korisnikService.getZaposleniAdmini().subscribe((data)=>{
      this.admini= data;
    });
  }
  loadRadnici(){
     this.korisnikService.getZaposleniRadnici().subscribe((data)=>{
      this.radnici= data;
    });
  }
  loadSaloni(){
      this.salonService.getAllSaloni().subscribe((data)=>{
      this.saloni= data;
      });
  }
  otvoriModal(zaposleni: any): void {
    this.izabraniZaposleni = { ...zaposleni };
    const modalElement = document.getElementById('editModal');
    if(zaposleni.salon){
      this.privatniTip='radnik';
    }else{
      this.privatniTip='admin';
    }
    this.modalRef = new bootstrap.Modal(modalElement!);
    this.modalRef.show();
  }

  otvoriModalZaDodavanje(tip: 'admin' | 'radnik'): void {
    this.privatniTip = tip;
    this.izabraniZaposleni = {
      ime: '',
      prezime: '',
      email: '',
      telefon: '',
      grad: '',
      sifra: '',
      adresa: '',
      salon_id: null
    };
    const modalElement = document.getElementById('editModal');
    this.modalRef = new bootstrap.Modal(modalElement!);
    this.modalRef.show();
  }

  sacuvajIzmene(): void {
    console.log(this.izabraniZaposleni);
    if (this.izabraniZaposleni.id) {
      if(this.privatniTip==='admin'){
        this.korisnikService.updateAdmin( this.izabraniZaposleni,this.izabraniZaposleni.id).subscribe({
      next: () => {
        this.toastr.success('Podaci admina uspešno ažurirani!');
        this.modalRef.hide();

        this.loadAdmini();
      },
      error: (err) => {
        this.toastr.error('Greška prilikom ažuriranja admina!');
      }
    });
      }else if(this.privatniTip==='radnik'){
        this.korisnikService.updateRadnik( this.izabraniZaposleni,this.izabraniZaposleni.id).subscribe({
      next: () => {
        this.toastr.success('Podaci radnika uspešno ažurirani!');
        this.modalRef.hide();

        this.loadRadnici();
      },
      error: (err) => {
        this.toastr.error('Greška prilikom ažuriranja radnika!');
      }
    });
      }
    } else {
      if (this.privatniTip === 'admin') {
        this.korisnikService.createAdmin(this.izabraniZaposleni).subscribe({
          next: admin => {
            this.admini.push(admin);
            this.toastr.success("Uspesno kreiran admin");
            this.modalRef.hide();
          },
          error: err => this.toastr.error('Greška prilikom kreiranja admina')
        });
      } else if (this.privatniTip === 'radnik') {
        this.korisnikService.createRadnik(this.izabraniZaposleni).subscribe({
          next: radnik => {
             this.toastr.success("Uspesno kreiran radnik");
            this.radnici.push(radnik);
            this.modalRef.hide();
          },
          error: err => this.toastr.error('Greška prilikom kreiranja radnika')
        });
      }
    }
  }

  obrisiZaposlenog(id: number): void {
    if (confirm('Da li ste sigurni da želite da obrišete zaposlenog?')) {
      this.korisnikService.deleteKorisnik(id).subscribe({
      next: () => {
        this.admini = this.admini.filter(a => a.id !== id);
        this.radnici = this.radnici.filter(r => r.id !== id);

        this.toastr.success('Zaposleni je uspešno obrisan!');
      },
      error: (err) => {
        this.toastr.error('Došlo je do greške pri brisanju zaposlenog!');
      }
    });
    }
  }
}
