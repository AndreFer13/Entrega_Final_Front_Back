import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EntrarComponent } from './usuario/entrar/entrar.component';
import { RegistrarComponent } from './usuario/registrar/registrar.component';
import { InicioComponent } from './Principal/inicio/inicio.component';
import { NavComponent } from './shared/nav/nav.component';
import { AgregarComponent } from './Principal/crear-Servicio/agregar.component';
/*import { FormsModule, ReactiveFormsModule } from '@angular/forms';*/
import { ListarComponent } from './Principal/listar-Servicio/listar.component';
import { CrearAgendaComponent } from './Principal/crear-agenda/crear-agenda.component';






@NgModule({
  declarations: [
    AppComponent,
    AgregarComponent,    
    ListarComponent,
    CrearAgendaComponent,
    InicioComponent,
    NavComponent,
    EntrarComponent,
    RegistrarComponent,  
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
   // FormsModule,
    ReactiveFormsModule
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
