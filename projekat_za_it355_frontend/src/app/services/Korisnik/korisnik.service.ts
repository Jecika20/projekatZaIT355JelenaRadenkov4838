import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  readonly apiUrl= 'http://localhost:8080/api';
  constructor(private http: HttpClient) { }

  registrationKorisnik(korisnik:any){
    return this.http.post(this.apiUrl+"/register",korisnik);
  }

  loginKorisnik(email: string, sifra: string){
      const params = new HttpParams().set('email', email).set('sifra',sifra);
      return this.http.get(this.apiUrl + "/login", {params});
  }
}
