import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  jwtToken: string | null;
  uloga: string | null;

  constructor(private router: Router, private toastr: ToastrService){}
  ngOnInit(): void {
    this.jwtToken = sessionStorage.getItem("jwtToken");
    this.uloga = sessionStorage.getItem("uloga");
  }


  login(){
    this.router.navigate(['/login']);
  }
  registration(){
    this.router.navigate(['/registration']);
  }

  odjava(){
    sessionStorage.clear();
    this.toastr.success("Uspesno ste se odjavili");
    setTimeout(() => {
      this.router.navigate(['/home']).then(()=>{
        this.jwtToken = null;
        this.uloga = null;
      });
    }, 500);
  }
}



