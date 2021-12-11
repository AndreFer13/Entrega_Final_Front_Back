import { Component, OnInit } from '@angular/core';
import { Agenda } from 'src/app/models/Agenda.model';
import { AgendaService } from 'src/app/services/Agenda.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

Agenda: Agenda[] = [];

  constructor(private AgendaService: AgendaService) { }

  ngOnInit(): void {
    this.traerAgenda()
  }

  traerAgenda(){
    this.AgendaService.obtenerAgenda().subscribe((Agenda:any) =>{
      console.log(Agenda)
      this.Agenda=Agenda;
    })
  }

}
