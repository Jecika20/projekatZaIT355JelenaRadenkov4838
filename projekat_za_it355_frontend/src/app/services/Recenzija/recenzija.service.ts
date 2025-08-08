import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recenzija } from '../../models/Recenzija/recenzija';

@Injectable({
  providedIn: 'root'
})
export class RecenzijaService {

  readonly apiUrl = 'http://localhost:8080/api/recenzija';
  constructor(private http: HttpClient) { }

  kreirajRecenziju(recenzija:any){
    return this.http.post(this.apiUrl, recenzija);
  }

  prikazNeaktivnihRecenzija(): Observable<Recenzija[]>{
    return  this.http.get<Recenzija[]>(this.apiUrl + "/noActive");
  }
  
  aktivirajRecenziju(recenzijaId:number){
return this.http.get(this.apiUrl + "/aktiviraj/" +recenzijaId);
  }

  }

