import { Component, OnInit } from '@angular/core';
import { Korisnik } from '../../models/Korisnik/korisnik';
import { Radnik } from '../../models/Radnik/radnik';
import { KorisnikService } from '../../services/Korisnik/korisnik.service';
// @ts-ignore
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-zaposleni',
  templateUrl: './zaposleni.component.html',
  styleUrl: './zaposleni.component.css'
})
export class ZaposleniComponent implements OnInit{
  admini: Korisnik[]= [];
  radnici: Radnik[]=[];
  izabraniZaposleni: any = {};
  private modalRef: any;
  privatniTip: 'admin' | 'radnik' | null = null;

  constructor(private korisnikService:KorisnikService){

  }
   ngOnInit(): void {
    this.loadAdmini();
    this.loadRadnici();
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
  otvoriModal(zaposleni: any): void {
    this.izabraniZaposleni = { ...zaposleni };
    const modalElement = document.getElementById('editModal');
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
      grad: ''
    };
    const modalElement = document.getElementById('editModal');
    this.modalRef = new bootstrap.Modal(modalElement!);
    this.modalRef.show();
  }

  sacuvajIzmene(): void {
    if (this.izabraniZaposleni.id) {
      console.log('Izmenjen zaposleni:', this.izabraniZaposleni);
    } else {
      if (this.privatniTip === 'admin') {
        const noviAdmin = { ...this.izabraniZaposleni, id: this.admini.length + 1 };
        this.admini.push(noviAdmin);
      } else if (this.privatniTip === 'radnik') {
        const noviRadnik = { ...this.izabraniZaposleni, id: this.radnici.length + 1 };
        this.radnici.push(noviRadnik);
      }
    }
    this.modalRef.hide();
  }

  obrisiZaposlenog(id: number): void {
    if (confirm('Da li ste sigurni da želite da obrišete zaposlenog?')) {
      this.admini = this.admini.filter(a => a.id !== id);
      this.radnici = this.radnici.filter(r => r.id !== id);
    }
  }
}
