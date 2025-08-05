import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RecenzijaService {

  readonly apiUrl = 'http://localhost:8080/api/recenzija';
  constructor(private http: HttpClient) { }

  kreirajRecenziju(recenzija:any){
    return this.http.post(this.apiUrl, recenzija);
  }
}
