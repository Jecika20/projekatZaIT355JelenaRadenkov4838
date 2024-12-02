import { Korisnik } from "../Korisnik/korisnik";
import { Vozilo } from "../Vozilo/vozilo";

export class RezervacijeKorisnika {
    id:number;
    vremeOd: string;
    vremeDo: string;
    ukupnaCena:number;
    statusRezervacije: string;
    vozilo: Vozilo;
    korisnik:Korisnik;
    
}
