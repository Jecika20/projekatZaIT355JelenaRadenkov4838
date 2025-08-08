import { Component, OnInit } from '@angular/core';
import { Recenzija } from '../../models/Recenzija/recenzija';
import { RecenzijaService } from '../../services/Recenzija/recenzija.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-recenzije',
  templateUrl: './recenzije.component.html',
  styleUrl: './recenzije.component.css'
})
export class RecenzijeComponent implements OnInit {



  neAktivneRecenzije: Recenzija[] = [];
  pageNeaktivne: number = 1;

  constructor(private recenzijaService: RecenzijaService, private toastr: ToastrService) {


  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.recenzijaService.prikazNeaktivnihRecenzija().subscribe({
      next: (res: Recenzija[]) => {
        this.neAktivneRecenzije = res;
      }
    })


  }
  aktiviraj(recenzijaId: number) {
    this.recenzijaService.aktivirajRecenziju(recenzijaId).subscribe({
      next: (res:any)=>{
        this.toastr.success("Uspesno aktivirana recenzija");
        this.loadData();
      },
      error:(err:any)=>{
        this.toastr.error("Doslo je do greske prilikom aktiviranja");
      }
    })
  }


}
