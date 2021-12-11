import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Stecnico } from '../models/Stecnico.model';

@Injectable({
  providedIn: 'root'
})
export class StecnicoService {

  baseURL= environment.baseUrl;

  constructor(private http: HttpClient) { }
 
  getToken(){
    return localStorage.getItem("token") || '';
  }
  get headers(){
    return {headers: {"Authorization": this.getToken()} }
  }  
  obtenerStecnico(){
    return this.http.get(`${this.baseURL}/api/Stecnico`, this.headers)
  }

  añadirStecnico(data:any){
    return this.http.post(`${this.baseURL}/api/Stecnico`,data, this.headers)
  }


}

 



  