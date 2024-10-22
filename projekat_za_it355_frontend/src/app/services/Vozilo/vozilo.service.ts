import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vozilo } from '../../models/Vozilo/vozilo';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  readonly apiUrl = 'http://localhost:8080/api/vozilo';
  constructor(private http: HttpClient) { }

  getVozilaBySalon(id: number):Observable<Vozilo[]> {
    return this.http.get<Vozilo[]>(this.apiUrl + '/get/salon/' + id);
  }
}
