import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KorisnikService } from '../../services/Korisnik/korisnik.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit{
  registrationForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private korisnikService: KorisnikService, private router: Router, 
    private toastr: ToastrService
  ){}
  ngOnInit(): void {
    this.registrationForm= this.formBuilder.group({
      ime:['',[Validators.required,Validators.minLength(3)]],
      prezime:['',[Validators.required,Validators.minLength(3)]],
      email: ['',[Validators.required, Validators.email]],
      telefon:['',[Validators.required,Validators.minLength(5)]],
      sifra: ['',[Validators.required,Validators.minLength(3)]],
      potvrdaSifre:['',[Validators.required]],
      adresa:['',[Validators.required]],
      grad:['',[Validators.required]],
    },{
      validators: this.proveraSifre
    }
  )
  }

  proveraSifre(group: FormGroup){
    const sifra= group.get("sifra")?.value;
    const potvrdaSifre= group.get("potvrdaSifre")?.value;
    console.log(sifra,potvrdaSifre);
    return sifra === potvrdaSifre ? null : {
      sifraMismatch: true
    }
  }

  onSubmit(){
    if(this.registrationForm.valid){
      this.korisnikService.registrationKorisnik(this.registrationForm.value).subscribe({
        next: (res:any)=>{
          this.toastr.success("Uspesna registracija");
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 500);
        },
        error: (err:any)=> {
          this.toastr.error("Greska prilikom registracije pokusajte ponovo");
        }
      })
    }else{
      this.toastr.error("Greska prilikom registracije pokusajte ponovo");
    }
  }
}
