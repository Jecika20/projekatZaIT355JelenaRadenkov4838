import { Component, model, OnInit } from '@angular/core';
import { Vozilo } from '../../models/Vozilo/vozilo';
import { VoziloService } from '../../services/Vozilo/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  uloga: string | null;
  showDialog=false;
  showCreateDialog=false;
  flag:number;
  vozilo: Vozilo | undefined ;
  tipVozila:string;
  automobilForm: FormGroup;
  motorForm: FormGroup;
  camacForm: FormGroup;
  voziloForm: FormGroup;
  vrstaVozila: string='';
  constructor(private voziloService : VoziloService, private route:ActivatedRoute, private router: Router, private toastr:ToastrService,
              private formBuilder: FormBuilder
  ){}
  ngOnInit(): void {
    this.uloga = sessionStorage.getItem("uloga");
    this.id = this.route.snapshot.params['id'];
    this.loadData();
  }

  loadData(){
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

  openCreateDialog(){
    this.showCreateDialog=true;
    
    
  }
  closeCreateDialog(){
    this.showCreateDialog=false;
  }
  dodelaVrsteVozila(){
    if(this.vrstaVozila=='Automobil'){
      this.voziloForm= this.formBuilder.group({
        model: ['', [Validators.required]],
        brend: ['', [Validators.required]],
        godinaProizvodnje: ['', [Validators.required]],
        cenaPoDanu: ['', [Validators.required]],
        statusVozila: ['DOSTUPAN'],
        kilometraza: ['', [Validators.required]],
        opis: ['', [Validators.required]],
        brojVrata: ['', [Validators.required]],
        tipGoriva:['', [Validators.required]],
        brojSedista: ['', [Validators.required]],
        vrstaAutomobila: ['', [Validators.required]]
      })
    }else if(this.vrstaVozila=='Motor'){
      this.voziloForm= this.formBuilder.group({
        model: ['', [Validators.required]],
        brend: ['', [Validators.required]],
        godinaProizvodnje: ['', [Validators.required]],
        cenaPoDanu: ['', [Validators.required]],
        statusVozila: ['DOSTUPAN'],
        kilometraza: ['', [Validators.required]],
        opis: ['', [Validators.required]],
        brojCilindara: ['', [Validators.required]],
        brojTockova: ['', [Validators.required]],
        tipMotora:['', [Validators.required]]
      })
    }else if(this.vrstaVozila=='Camac'){
      this.voziloForm= this.formBuilder.group({
        model: ['', [Validators.required]],
        brend: ['', [Validators.required]],
        godinaProizvodnje: ['', [Validators.required]],
        cenaPoDanu: ['', [Validators.required]],
        statusVozila: ['DOSTUPAN'],
        kilometraza: ['', [Validators.required]],
        opis: ['', [Validators.required]],
        duzina: ['', [Validators.required]],
        tipCamca:['', [Validators.required]]
      })
    }
  }

  openDialog(id:number,flag:number, tipVozila: string){
    this.vozilo= this.vozila.find(v => v.id==id);
    this.flag=flag;
    this.tipVozila=tipVozila;
    if(this.vozilo && this.tipVozila=='AUTOMOBIL'){
      this.automobilForm= this.formBuilder.group({
        id: [this.vozilo?.id, [Validators.required]],
        model: [this.vozilo?.model, [Validators.required]],
        brend: [this.vozilo?.brend, [Validators.required]],
        godinaProizvodnje: [this.vozilo?.godinaProizvodnje, [Validators.required]],
        cenaPoDanu: [this.vozilo?.cenaPoDanu, [Validators.required]],
        statusVozila: [this.vozilo?.statusVozila, [Validators.required]],
        kilometraza: [this.vozilo?.kilometraza, [Validators.required]],
        opis: [this.vozilo?.opis, [Validators.required]],
        brojVrata: [this.vozilo?.brojVrata, [Validators.required]],
        tipGoriva:[this.vozilo?.tipGoriva, [Validators.required]],
        brojSedista: [this.vozilo?.brojSedista, [Validators.required]],
        vrstaAutomobila: [this.vozilo?.vrstaAutomobila, [Validators.required]]
      })
    }else if(this.vozilo && this.tipVozila=='MOTOR'){
      this.motorForm= this.formBuilder.group({
        id: [this.vozilo?.id, [Validators.required]],
        model: [this.vozilo?.model, [Validators.required]],
        brend: [this.vozilo?.brend, [Validators.required]],
        godinaProizvodnje: [this.vozilo?.godinaProizvodnje, [Validators.required]],
        cenaPoDanu: [this.vozilo?.cenaPoDanu, [Validators.required]],
        statusVozila: [this.vozilo?.statusVozila, [Validators.required]],
        kilometraza: [this.vozilo?.kilometraza, [Validators.required]],
        opis: [this.vozilo?.opis, [Validators.required]],
        brojCilindara: [this.vozilo?.brojCilindara, [Validators.required]],
        brojTockova: [this.vozilo?.brojTockova, [Validators.required]],
        tipMotora:[this.vozilo?.tipMotora, [Validators.required]]
      })
    }else if(this.vozilo && this.tipVozila=='CAMAC'){
      this.camacForm= this.formBuilder.group({
        id: [this.vozilo?.id, [Validators.required]],
        model: [this.vozilo?.model, [Validators.required]],
        brend: [this.vozilo?.brend, [Validators.required]],
        godinaProizvodnje: [this.vozilo?.godinaProizvodnje, [Validators.required]],
        cenaPoDanu: [this.vozilo?.cenaPoDanu, [Validators.required]],
        statusVozila: [this.vozilo?.statusVozila, [Validators.required]],
        kilometraza: [this.vozilo?.kilometraza, [Validators.required]],
        opis: [this.vozilo?.opis, [Validators.required]],
        duzina: [this.vozilo?.duzina, [Validators.required]],
        tipCamca:[this.vozilo?.tipCamca, [Validators.required]]
      })
    }
    this.showDialog= true;
  }
  closeDialog(){
    this.showDialog= false;
  }
  izmenaAutomobila(){
    if(this.automobilForm.valid){
      this.voziloService.izmenaAutomobila(this.automobilForm.value).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste izmneili vozilo iz salona");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom izmene. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showDialog= false;
        }
      })
    }else{
      this.toastr.error("Doslo je do greske popunite formu validno. Pokusajte ponovo");
    }
  }

  izmenaMotora(){
    if(this.motorForm.valid){
      this.voziloService.izmenaMotora(this.motorForm.value).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste izmneili vozilo iz salona");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom izmene. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showDialog= false;
        }
      })
    }else{
      this.toastr.error("Doslo je do greske popunite formu validno. Pokusajte ponovo");
    }
  }
  izmenaCamca(){
    if(this.camacForm.valid){
      this.voziloService.izmenaCamca(this.camacForm.value).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste izmneili vozilo iz salona");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom izmene. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showDialog= false;
        }
      })
    }else{
      this.toastr.error("Doslo je do greske popunite formu validno. Pokusajte ponovo");
    }
  }
  createVozilo(){
    if(this.voziloForm.invalid || this.vrstaVozila===undefined){
      this.toastr.error("Niste dobro popunili formu. Pokusajte ponovo");
      return;
    }
    const podaciZaKreiranje= {
      ...this.voziloForm.value, salon_id: this.id
    }
    
    if(this.vrstaVozila=='Automobil'){
      this.voziloService.kreirajAutomobil(podaciZaKreiranje).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste kreirali automobil");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom kreiranja. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showCreateDialog= false;
        }
      })
    }else if(this.vrstaVozila=='Motor'){
      this.voziloService.kreirajMotor(podaciZaKreiranje).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste kreirali motor");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom kreiranja. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showCreateDialog= false;
        }
      })
    }else if(this.vrstaVozila=='Camac'){
      this.voziloService.kreirajCamac(podaciZaKreiranje).subscribe({
        next: (res: any)=> {
          this.toastr.success("Uspesno ste kreirali camac");
          this.loadData();
        },
        error: (err: any)=>{
          this.toastr.error("Doslo je do greske prilikom kreiranja. Pokusajte ponovo");
        },
        complete: ()=>{
          this.showCreateDialog= false;
        }
      })
    }
  }
  brisanjeVozila(){
    if(this.vozilo==undefined){
      this.toastr.error("Doslo je do greske prilikom brisanja. Pokusajte ponovo");
      return;
    }
    this.voziloService.brisanjeVozila(this.vozilo.id).subscribe({
      next: (res:any)=>{
        this.toastr.success("Uspesno ste obrisali vozilo iz salona");
        this.loadData();
      },
      error: (err:any)=>{
        this.toastr.error("Doslo je do greske prilikom brisanja. Pokusajte ponovo");
      },
      complete: ()=> {
        this.showDialog=false;
      }
    })
  }
}


