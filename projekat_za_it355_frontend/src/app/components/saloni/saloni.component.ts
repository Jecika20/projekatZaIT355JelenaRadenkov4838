import { Component, OnInit } from '@angular/core';
import { Salon } from '../../models/Salon/salon';
import { SalonService } from '../../services/Salon/salon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-saloni',
  templateUrl: './saloni.component.html',
  styleUrl: './saloni.component.css'
})
export class SaloniComponent implements OnInit {
  saloni: Salon[]= [];

  constructor(private salonService: SalonService, private router: Router){}
  
  ngOnInit(): void {
   this.salonService.getAllSaloni().subscribe({
    next: (res:Salon[])=>{
     this.saloni= res;
    }
   });
  }

  pregledVozila(id:number){
    this.router.navigate(['/vozila', id]);
  }

}
