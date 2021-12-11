package com.ServTecnico_G18.Familia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ServTecnico_G18.Familia.exceptions.CustomException;
import com.ServTecnico_G18.Familia.models.AgendaModel;
import com.ServTecnico_G18.Familia.services.AgendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin (origins = "*")
@RequestMapping("/api")
public class AgendaController {
    
    @Autowired
    AgendaService service;


    @PostMapping("/Agenda") //Método HTTP -------> POST
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody AgendaModel agenda, Errors error){
        //Verificamos si existe un error
        if(error.hasErrors()){
            throwError(error);
        }
        //Recibimos los datos por el body de la petición
        this.service.guardarAgenda(agenda); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se agregó a la Agenda correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }

    @GetMapping("/Agenda") //Método HTTP -------> GET
    public List<AgendaModel> mostrar(){
        return service.traerTodos();
    }

    @PutMapping("/Agenda") // Método HTTP ----> PUT
    public ResponseEntity<Map<String, String>> actualizar(@Valid @RequestBody AgendaModel agenda){
        //Recibimos los datos por el body de la petición
        this.service.guardarAgenda(agenda); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se actualizó la Agenda correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
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
