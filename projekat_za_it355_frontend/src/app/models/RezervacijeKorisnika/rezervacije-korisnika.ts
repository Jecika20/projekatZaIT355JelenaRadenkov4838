import { Vozilo } from "../Vozilo/vozilo";

export class RezervacijeKorisnika {
    id:number;
    vremeOd: Date;
    vremeDo: Date;
    ukupnaCena:number;
    statusRezervacije: string;
    vozilo: Vozilo;
    
    
}
