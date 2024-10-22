import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../models/Vozilo/vozilo';
import { VoziloService } from '../../services/Vozilo/vozilo.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrl: './vozila.component.css'
})
export class VozilaComponent implements OnInit {
  vozila: Vozilo[]= [];
  id: string;
  constructor(private voziloService : VoziloService, private route:ActivatedRoute){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.voziloService.getVozilaBySalon(Number(this.id)).subscribe({
      next: (res: Vozilo[]) => {
        console.log(res);
      }
    });
  }
}


