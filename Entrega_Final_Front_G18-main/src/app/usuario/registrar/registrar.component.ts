import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {

  UsuarioForm!: FormGroup;

  constructor(private UsuarioService: UsuarioService, 
    private fb: FormBuilder, 
    private router: Router) { }

  ngOnInit(): void {
  
  this.UsuarioForm=this.fb.group({
  
    nombre: [''],
    correo: [''],
    username: [''],
    password: ['']
  })
}
agregar(){
  this.UsuarioService.añadirUsuario(this.UsuarioForm.value).subscribe((data:any)=>{
    console.log(data)
    if(data.mensaje=="Se agregó correctamente"){
 this.router.navigateByUrl("/")
    }else{
      alert("Ha ocurrido un error")
    }
  })
}
}
