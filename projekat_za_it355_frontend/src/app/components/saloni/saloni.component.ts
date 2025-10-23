import { Component, OnInit } from '@angular/core';
import { Salon } from '../../models/Salon/salon';
import { SalonService } from '../../services/Salon/salon.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
// @ts-ignore
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-saloni',
  templateUrl: './saloni.component.html',
  styleUrls: ['./saloni.component.css']
})
export class SaloniComponent implements OnInit {
  saloni: Salon[] = [];
  isAdmin: boolean = false;
  noviSalon: any = {};
  private modalRef: any;

  constructor(
    private salonService: SalonService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.isAdmin = sessionStorage.getItem('uloga') === 'ADMIN';

    if (sessionStorage.getItem('uloga') === 'RADNIK') {
      this.salonService.getSalonByRadnik().subscribe({
        next: (res: Salon) => {
          this.saloni = [res];
        }
      });
      return;
    }

    this.ucitajSalone();
  }

  ucitajSalone() {
    this.salonService.getAllSaloni().subscribe({
      next: (res: Salon[]) => (this.saloni = res),
      error: () => this.toastr.error('Greška pri učitavanju salona.')
    });
  }

  pregledVozila(id: number) {
    this.router.navigate(['/vozila', id]);
  }

  otvoriModalZaDodavanje() {
    this.noviSalon = {
      naziv: '',
      telefon: '',
      email: '',
      opis: '',
      adresa: '',
      grad: '',
      slika: ''
    };
    const modalElement = document.getElementById('salonModal');
    this.modalRef = new bootstrap.Modal(modalElement!);
    this.modalRef.show();
  }

  dodajSalon() {
    if (!this.noviSalon.naziv) {
      this.toastr.warning('Naziv salona je obavezan.');
      return;
    }

    this.salonService.createSalon(this.noviSalon).subscribe({
      next: (novi: Salon) => {
        this.saloni.push(novi);
        this.toastr.success('Salon uspešno dodat!');
        this.modalRef.hide();
      },
      error: () => this.toastr.error('Greška pri dodavanju salona!')
    });
  }
  onFileSelected(event: any) {
  const file = event.target.files[0];
  if (file) {
    if (!file.name.match(/\.(jpg|jpeg|png)$/i)) {
      this.toastr.warning('Dozvoljene su samo slike (.jpg, .png)');
      return;
    }

    
    this.noviSalon.slika = file.name;
    console.log('Izabrana slika:', this.noviSalon.slika);
  }
}
obrisiSalon(id: number) {
  if (!confirm('Da li ste sigurni da želite da obrišete ovaj salon?')) {
    return;
  }

  this.salonService.deleteSalon(id).subscribe({
    next: () => {
      this.saloni = this.saloni.filter(s => s.id !== id);
      this.toastr.success('Salon uspešno obrisan!');
    },
    error: () => this.toastr.error('Greška pri brisanju salona!')
  });
}
}
