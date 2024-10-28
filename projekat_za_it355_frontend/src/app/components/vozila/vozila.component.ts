import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../models/Vozilo/vozilo';
import { VoziloService } from '../../services/Vozilo/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrl: './vozila.component.css'
})
export class VozilaComponent implements OnInit {
  vozila: Vozilo[]= [];
  automobili: Vozilo[] = [];
  motori: Vozilo[] = [];
  camci: Vozilo[] = [];
  id: string;
  constructor(private voziloService : VoziloService, private route:ActivatedRoute, private router: Router){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.voziloService.getVozilaBySalon(Number(this.id)).subscribe({
      next: (res: Vozilo[]) => {
       this.vozila = res;
       this.automobili= this.vozila.filter(v => v.tipVozila == 'AUTOMOBIL');
       this.motori= this.vozila.filter(v => v.tipVozila== 'MOTOR');
       this.camci= this.vozila.filter(v => v.tipVozila== 'CAMAC');
      }
    });
  }

  detaljnijeVozila(id:number){
    this.router.navigate(['/detaljnije-vozila', id]);
  }
}


