import { Component, OnInit } from '@angular/core';
import { VoziloService } from '../../services/Vozilo/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { RezervacijaService } from '../../services/Rezervacija/rezervacija.service';
import { Rezervacija } from '../../models/Rezervacija/rezervacija';
import { Recenzija } from '../../models/Recenzija/recenzija';
import { RecenzijaService } from '../../services/Recenzija/recenzija.service';

@Component({
  selector: 'app-detaljnije-vozila',
  templateUrl: './detaljnije-vozila.component.html',
  styleUrl: './detaljnije-vozila.component.css'
})
export class DetaljnijeVozilaComponent implements OnInit {
  vozilo:any;
  id: string;
  jwtToken: string | null;
  uloga: string | null;
  showDialog=false;
  dialogTitle="Forma za rezervaciju vozila ";
  vremeForm: FormGroup;
  cenaPoDanu: number;
  rezervacija:Rezervacija = new Rezervacija();
  cena: number;
  aktivneRecenzije: Recenzija[] = [];
  recenzijaForm: FormGroup;
  showRecenzijaModal=false;
  constructor(private voziloService : VoziloService, private route:ActivatedRoute, private router: Router,
     private formBuilder: FormBuilder, private toastr: ToastrService, private rezervacijaService:RezervacijaService,
    private recenzijaService: RecenzijaService){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.uloga = sessionStorage.getItem("uloga");
    this.jwtToken = sessionStorage.getItem("jwtToken");
    this.voziloService.getVoziloById(Number(this.id)).subscribe({
      next: (res:any)=>{
        this.vozilo=res;
        console.log(this.vozilo);
        this.aktivneRecenzije= this.vozilo.recenzija.filter((r: Recenzija) => r.aktivna);
      }
    });
    
    this.vremeForm= this.formBuilder.group({
      vremeOd: ['',[Validators.required]],
      vremeDo: ['',[Validators.required]],
      ukupnaCena: [{value:0, disabled:true}]
    });
    this.vremeForm.valueChanges.subscribe(()=> {
      this.racunanjeUkupneCene();
    });
    this.recenzijaForm= this.formBuilder.group({
      ocena: ['',[Validators.required,Validators.min(1),Validators.max(5)]],
      komentar:['',[Validators.required]]
    })
  }

  racunanjeUkupneCene(){
    const vremeOd = this.vremeForm.get('vremeOd')?.value;
    const vremeDo = this.vremeForm.get('vremeDo')?.value;
    if(vremeOd && vremeDo){
      const start = moment(vremeOd);
      const end = moment(vremeDo);
      const trajanje= moment.duration(end.diff(start));
      const brojDana = trajanje.asDays();
      if(brojDana >0){
        this.vremeForm.patchValue({ukupnaCena: Math.floor(brojDana * this.cenaPoDanu)});
        this.cena = Math.floor(brojDana*this.cenaPoDanu);
      }
      
    }
  }

  openDialog(id:number, model:string,brend:string, cenaPoDanu:number){
    this.dialogTitle += brend + " " + model;
    this.cenaPoDanu= cenaPoDanu;
    this.showDialog= true;
  }
  closeDialog(){
    this.showDialog= false;
  }

  createRezervacija(){
      if(this.vremeForm.valid){
        this.rezervacija.vremeOd= this.vremeForm.value.vremeOd;
        this.rezervacija.vremeDo= this.vremeForm.value.vremeDo;
        this.rezervacija.ukupnaCena= this.cena;
        this.rezervacija.vozilo_id= Number(this.id);
        console.log(this.rezervacija);
        this.rezervacijaService.createRezervacija(this.rezervacija).subscribe({
          next: (res: any) =>{
            this.toastr.success("Uspesno kreirana rezervacija");
            setTimeout(()=>{
              this.router.navigate(['/rezervacije']);
            },500);
          }, 
          error: (err: any)=> {
            this.toastr.error("Doslo je do greske prilikom rezervacije pokusajte ponovo");
          }
        })
    }else{
      this.toastr.error("Doslo je do greske prilikom rezervacije pokusajte ponovo");
    }
  }

  createRecenzija(){
    if(this.recenzijaForm.invalid){
      this.recenzijaForm.markAllAsTouched();
      return;
    }
    const novaRecenzija= {
      komentar: this.recenzijaForm.value.komentar,
      ocena: this.recenzijaForm.value.ocena,
      vozilo_id: Number(this.vozilo.id),
      aktivna: false
    };

    this.recenzijaService.kreirajRecenziju(novaRecenzija).subscribe({
      next: (res: any)=>{
        this.toastr.success("Uspesno kreirana recenzija, ceka se potvrda");
        this.closeRecenzijaModal();
      },
      error: (err: any)=>{
        this.toastr.error("Doslo je do greske, da bi ste kreirali recenziju morate imati rezervaciju za ovo vozilo");
        this.closeRecenzijaModal();
      }
    })
  }
  openRecenzijaModal() {
    this.showRecenzijaModal = true;
  }

  closeRecenzijaModal(event?: MouseEvent) {
    if (!event || (event.target as HTMLElement).classList.contains('modal')) {
      this.showRecenzijaModal = false;
      this.recenzijaForm.reset();
    }
  }
}
