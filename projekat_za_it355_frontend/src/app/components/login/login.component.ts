import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KorisnikService } from '../../services/Korisnik/korisnik.service';
import { Router } from '@angular/router';
import {  ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private korisnikService: KorisnikService, private router:Router, 
    private toastr:ToastrService
  ){}
  ngOnInit(): void {
    this.loginForm= this.formBuilder.group({
      email: ['',[Validators.required, Validators.email]],
      sifra: ['',[Validators.required,Validators.minLength(3)]]
    })
  }

  onSubmit(){
    if(this.loginForm.valid){
      this.korisnikService.loginKorisnik(this.loginForm.value.email, this.loginForm.value.sifra).subscribe({
        next: (res: any)=>{
          console.log(res);
          sessionStorage.setItem("jwtToken", res.jwtToken);
          sessionStorage.setItem("uloga", res.uloga);
          this.toastr.success("Uspesno ste se ulogovali");
          setTimeout(() => {
            this.loginForm.reset();
            this.router.navigate(['/home']);
          }, 500);
        },
        error: (err: any) => {
          this.toastr.error("Doslo je do greske prilikom prijave pokusajte ponovo ukoliko imate nalog");
        }
      })
    }else{
      this.toastr.error("Doslo je do greske prilikom prijave pokusajte ponovo ukoliko imate nalog");
    }
    
  }


}
