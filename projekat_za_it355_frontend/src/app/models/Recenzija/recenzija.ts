import { Korisnik } from "../Korisnik/korisnik";
import { Vozilo } from "../Vozilo/vozilo";

export class Recenzija {
    id:number;
    komentar: string;
    ocena:number;
    korisnik:Korisnik;
    vozilo:Vozilo;
    aktivna: boolean;
    vremeKreiranja: Date;
}
