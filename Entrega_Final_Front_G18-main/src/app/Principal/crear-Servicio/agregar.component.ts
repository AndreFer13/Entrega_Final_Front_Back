import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { StecnicoService } from 'src/app/services/stecnico.service';




@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css']
})
export class AgregarComponent implements OnInit {

  StecnicoForm!: FormGroup;

  constructor(private StecnicoService: StecnicoService, 
    private fb: FormBuilder, 
    private router: Router) { }

  ngOnInit(): void {
  
  this.StecnicoForm=this.fb.group({
    id: [''],
    nombre: ['']
  })
}
agregar(){
  this.StecnicoService.añadirStecnico(this.StecnicoForm.value).subscribe((data:any)=>{
    console.log(data)
    if(data.estado=="true"){
 this.router.navigateByUrl("Servicios")
    }else{
      alert("Ha ocurrido un error")
    }
  })
}

}
