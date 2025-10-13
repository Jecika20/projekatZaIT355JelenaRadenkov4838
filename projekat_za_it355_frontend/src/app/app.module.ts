import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { SaloniComponent } from './components/saloni/saloni.component';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors } from '@angular/common/http';
import { VozilaComponent } from './components/vozila/vozila.component';
import { DetaljnijeVozilaComponent } from './components/detaljnije-vozila/detaljnije-vozila.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authInterceptor } from './auth/auth.interceptor';
import { RezervacijeComponent } from './components/rezervacije/rezervacije.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { OnamaComponent } from './components/onama/onama.component';
import { RezervacijeKlijenataComponent } from './components/rezervacije-klijenata/rezervacije-klijenata.component';
import { RecenzijeComponent } from './components/recenzije/recenzije.component';
import { ZaposleniComponent } from './components/zaposleni/zaposleni.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    SaloniComponent,
    VozilaComponent,
    DetaljnijeVozilaComponent,
    LoginComponent,
    RegistrationComponent,
    RezervacijeComponent,
    OnamaComponent,
    RezervacijeKlijenataComponent,
    RecenzijeComponent,
    ZaposleniComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({ timeOut: 1000, positionClass: 'toast-top-center', preventDuplicates: true, progressBar: true }),
    NgxPaginationModule
  ],
  providers: [provideHttpClient(
    withInterceptors([authInterceptor])
  ), {
    provide: HTTP_INTERCEPTORS,
    useFactory: authInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
