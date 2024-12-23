import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Rezervacija } from '../../models/Rezervacija/rezervacija';
import { Observable } from 'rxjs';
import { RezervacijeKorisnika } from '../../models/RezervacijeKorisnika/rezervacije-korisnika';

@Injectable({
  providedIn: 'root'
})
export class RezervacijaService {
  readonly apiUrl= 'http://localhost:8080/api/rezervacija';
  constructor(private http: HttpClient) { }

  createRezervacija(rezervacija:Rezervacija){
    return this.http.post(this.apiUrl, rezervacija);
  }
  
  getRezervacijeByKorisnik():Observable<RezervacijeKorisnika[]> {
    return this.http.get<RezervacijeKorisnika[]>(this.apiUrl + "/byKorisnik");
  }

  otkaziRezervaciju(id:number){
    return this.http.get(this.apiUrl + "/statusToOtkazano/" + id);
  }

  getAllRezervacije():Observable<RezervacijeKorisnika[]> {
    return this.http.get<RezervacijeKorisnika[]>(this.apiUrl);
  }

  zavrsiRezervaciju(id:number){
    return this.http.get(this.apiUrl + "/statusToZavrseno/" +id);
  }
  brisanjeRezervacije(id:number){
    return this.http.delete(this.apiUrl+ "/" +id);
  }
}