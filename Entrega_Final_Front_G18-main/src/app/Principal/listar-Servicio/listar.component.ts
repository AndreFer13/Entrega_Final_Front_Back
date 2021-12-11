import { Component, Input, OnInit } from '@angular/core';
import { Stecnico } from 'src/app/models/Stecnico.model';
import { StecnicoService } from 'src/app/services/stecnico.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  //@Input('entrada') public Stecnico: Stecnico[]=[];
  
  Stecnico:Stecnico[]=[];

  constructor(private StecnicoService: StecnicoService) { }

  ngOnInit(): void {
    this.obtenerStecnico();
  }
  obtenerStecnico(){
    this.StecnicoService.obtenerStecnico().subscribe((Stecnico:any) =>{
      console.log(Stecnico)
      this.Stecnico=Stecnico;
    })
  }

}
