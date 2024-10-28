import { Component, OnInit } from '@angular/core';
import { VoziloService } from '../../services/Vozilo/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-detaljnije-vozila',
  templateUrl: './detaljnije-vozila.component.html',
  styleUrl: './detaljnije-vozila.component.css'
})
export class DetaljnijeVozilaComponent implements OnInit {
  vozilo:any;
  id: string;
  constructor(private voziloService : VoziloService, private route:ActivatedRoute, private router: Router){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.voziloService.getVoziloById(Number(this.id)).subscribe({
      next: (res:any)=>{
        this.vozilo=res;
        console.log(this.vozilo);
      }
    });
  }

  

}
