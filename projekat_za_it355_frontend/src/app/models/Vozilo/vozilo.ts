import { Salon } from "../Salon/salon";
import { SlikeVozila } from "../SlikeVozila/slike-vozila";

export class Vozilo {
    id:number;
    model:string;
    brend:string;
    godinaProizvodnje:number;
    tipVozila:string;
    cenaPoDanu:number;
    statusVozila:string;
    kilometraza:number;
    opis:string;
    salon: Salon;
    slikeVozila: SlikeVozila[];
}
