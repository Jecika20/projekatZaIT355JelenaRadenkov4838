import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SaloniComponent } from './components/saloni/saloni.component';
import { VozilaComponent } from './components/vozila/vozila.component';

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
    path: '', redirectTo: '/home',pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
