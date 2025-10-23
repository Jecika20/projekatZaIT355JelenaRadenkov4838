import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Salon } from '../../models/Salon/salon';

@Injectable({
  providedIn: 'root'
})
export class SalonService {

  readonly apiUrl= 'http://localhost:8080/api/salon';
  constructor(private http: HttpClient) { }

  getAllSaloni(): Observable<Salon[]>{
    return this.http.get<Salon[]>(this.apiUrl + '/get');
  }

  getSalonById(id: number): Observable<Salon> {
    return this.http.get<Salon>(this.apiUrl + '/get/' + id);
  }
  getSalonByRadnik(): Observable<Salon> {
    return this.http.get<Salon>(this.apiUrl + '/salonByRadnik');
  }

  createSalon(salon:any): Observable<Salon>{
      return this.http.post<Salon>(this.apiUrl,salon);
  }
  deleteSalon(id: number) {
  return this.http.delete(`${this.apiUrl}/${id}`);
}
}
