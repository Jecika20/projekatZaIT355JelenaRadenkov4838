import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SaloniComponent } from './components/saloni/saloni.component';
import { VozilaComponent } from './components/vozila/vozila.component';
import { DetaljnijeVozilaComponent } from './components/detaljnije-vozila/detaljnije-vozila.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { RezervacijeComponent } from './components/rezervacije/rezervacije.component';

const routes: Routes = [
  {
    path: 'home',component: HomeComponent
  },
  {
    path: 'saloni', component: SaloniComponent
  },
  {
    path: 'vozila/:id',component : VozilaComponent
  },
  {
    path:'detaljnije-vozila/:id', component: DetaljnijeVozilaComponent
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'registration',component:RegistrationComponent
  },
  {
    path: 'rezervacije',component: RezervacijeComponent
  },
  {
    path: '', redirectTo: '/home',pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
