import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Korisnik } from '../../models/Korisnik/korisnik';
import { Radnik } from '../../models/Radnik/radnik';

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
   getZaposleniAdmini(): Observable<Korisnik[]>{
    return this.http.get<Korisnik[]> (this.apiUrl+"/korisnik/zaposleniAdmini");
  }
  getZaposleniRadnici(): Observable<Radnik[]>{
    return this.http.get<Radnik[]> (this.apiUrl+"/korisnik/zaposleniRadnici");
  }
}
