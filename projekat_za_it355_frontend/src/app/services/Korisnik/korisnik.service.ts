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

  createAdmin(admin:any):Observable<Korisnik>{
    return this.http.post<Korisnik>(this.apiUrl+ "/admin/register",admin);
  }

  createRadnik(radnik:any):Observable<Radnik>{
     return this.http.post<Radnik>(this.apiUrl+ "/korisnik/kreiranjeRadnika",radnik);
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
  updateAdmin(admin:any,id:number):Observable<Korisnik>{
    return this.http.put<Korisnik>(this.apiUrl+ "/korisnik/updateAdmin/" + id,admin);
  }
  updateRadnik(radnik:any,id:number):Observable<Radnik>{
    return this.http.put<Radnik>(this.apiUrl+ "/korisnik/updateRadnik/" + id,radnik);
  }
  deleteKorisnik(id:number){
    return this.http.delete(this.apiUrl + "/korisnik/" + id);
  }
}
