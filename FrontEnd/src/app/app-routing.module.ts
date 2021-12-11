import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ValidarTokenGuard } from './guards/validar-token.guard';


import { AgregarComponent } from './Principal/crear-Servicio/agregar.component';
import { CrearAgendaComponent } from './Principal/crear-agenda/crear-agenda.component';
import { InicioComponent } from './Principal/inicio/inicio.component';
import { ListarComponent } from './Principal/listar-Servicio/listar.component';
import { EntrarComponent } from './usuario/entrar/entrar.component';
import { RegistrarComponent } from './usuario/registrar/registrar.component';





import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavComponent } from './shared/nav/nav.component';


const routes: Routes = [
  {
    path:'',
    component: EntrarComponent
  },
  {
    path:'inicio',
    component: InicioComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'Servicios',
    component: ListarComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'new-Stecnico',
    component: AgregarComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'new-Agenda',
    component: CrearAgendaComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'registrar',
    component: RegistrarComponent
  },
  {
    path:'**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
