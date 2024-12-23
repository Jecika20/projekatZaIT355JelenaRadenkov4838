import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vozilo } from '../../models/Vozilo/vozilo';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  readonly apiUrl = 'http://localhost:8080/api/vozilo';
  readonly apiUrlAutomobil = 'http://localhost:8080/api/automobil';
  readonly apiUrlMotor = 'http://localhost:8080/api/motor';
  readonly apiUrlCamac = 'http://localhost:8080/api/camac';
  constructor(private http: HttpClient) { }

  getVozilaBySalon(id: number):Observable<Vozilo[]> {
    return this.http.get<Vozilo[]>(this.apiUrl + '/get/salon/' + id);
  }

  getVoziloById(id:number){
    return this.http.get(this.apiUrl +  '/get/' + id);
  }

  brisanjeVozila(id:number){
    return this.http.delete(this.apiUrl+"/" + id);
  }

  izmenaAutomobila(automobil:any){
    return this.http.put(this.apiUrlAutomobil, automobil);
  }
  izmenaMotora(motor:any){
    return this.http.put(this.apiUrlMotor, motor);
  }
  izmenaCamca(camac:any){
    return this.http.put(this.apiUrlCamac, camac);
  }
  kreirajAutomobil(automobil:any){
    return this.http.post(this.apiUrlAutomobil, automobil);
  }
  kreirajMotor(motor:any){
    return this.http.post(this.apiUrlMotor, motor);
  }
  kreirajCamac(camac:any){
    return this.http.post(this.apiUrlCamac, camac);
  }
}
