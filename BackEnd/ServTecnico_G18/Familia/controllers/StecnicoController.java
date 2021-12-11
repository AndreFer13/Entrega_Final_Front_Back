package com.ServTecnico_G18.Familia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ServTecnico_G18.Familia.exceptions.CustomException;
import com.ServTecnico_G18.Familia.models.StecnicoModel;
import com.ServTecnico_G18.Familia.services.StecnicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Definimos que el controlador es de tipo REST

@CrossOrigin (origins = "*")
@RequestMapping("/api") // El Path principal
public class StecnicoController {
    
    @Autowired //Instalacia del servicio
    StecnicoService StecnicoService;

    @PostMapping("/Stecnico") //Define la petición HTTP con la que ejecutará el método (POST) y El path (/api/partidos)
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody StecnicoModel Stecnico, Errors error){
       //Verificamos si existe un error
        if(error.hasErrors()){
            throwError(error);
        }
        //Recibimos los datos por el body de la petición
        this.StecnicoService.guardarStecnico(Stecnico); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se agregó el Stecnico correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }


    @GetMapping("/Stecnico")//Define la petición HTTP con la que ejecutará el método (GET) 
    public List<StecnicoModel> mostrar(){
        //Invocamos el metodo creado en el servicio
        return this.StecnicoService.obtenerStecnico(); 
    }

    //Método para el manejo de errores
    public void throwError(Errors error){
        String mensaje="";
        int index=0;
        for(ObjectError e: error.getAllErrors()){
            if(index>0){
                mensaje +=" | ";
            }
            mensaje+=String.format("Parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
        }
        throw new CustomException(mensaje);
    }
}
