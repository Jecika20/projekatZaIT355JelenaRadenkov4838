import { Recenzija } from "../Recenzija/recenzija";
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
    recenzija: Recenzija[];

    //dodatno za automobil
    brojVrata: number;
    tipGoriva: string;
    brojSedista: number;
    vrstaAutomobila: string;

    //dodatno za motor
    brojCilindara:number;
    brojTockova:number;
    tipMotora:string;

    // dodatno za camac
    duzina: number;
    tipCamca: string;
}
