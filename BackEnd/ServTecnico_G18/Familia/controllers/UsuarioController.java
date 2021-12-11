package com.ServTecnico_G18.Familia.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.ServTecnico_G18.Familia.models.UsuarioModel;
import com.ServTecnico_G18.Familia.services.UsuarioService;
import com.ServTecnico_G18.Familia.utils.Autorizacion;
import com.ServTecnico_G18.Familia.utils.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * Metodo para verificr si el token se encuentra activo
     * 
     * @return
     */
    @GetMapping("/verificar") //Ruta para acceder al método
    public ResponseEntity<Map<String, Boolean>> verificarToken(){ // "{"ok": true}"
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("ok",true);
        return ResponseEntity.ok(respuesta);
    }

    // Definimos el método para agregar un usuario
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        // Verificamos si existe un error
          //Map tener una clave valor {"mensaje": "Se agregó correctamente"}
          Map<String, String> respuesta= new HashMap<>();
        
          //Codificar contraseña
          usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
         
         
          UsuarioModel u=this.usuarioService.buscarUsername(usuario.getUsername());
          if(u.getId()==null){
              this.usuarioService.guardarUsuario(usuario);
              respuesta.put("mensaje","Se agregó correctamente");
          }else{
              respuesta.put("mensaje","Usuario ya existe");
          }
  
          return ResponseEntity.ok(respuesta);
    }

    // Crear método para el login de usuario
 //Método para chekear autenticidad
 @PostMapping("/usuarios/login")
 public ResponseEntity<Map<String, String>> login(@RequestBody UsuarioModel usuario){
     UsuarioModel usuarioAux=this.usuarioService.buscarUsername(usuario.getUsername());
     Map<String, String> respuesta = new HashMap<>();
     if(usuarioAux.getUsername()==null){
         respuesta.put("mensaje","usuario o contraseña incorrectos");
     }else{
         if(!BCrypt.checkpw(usuario.getPassword(), usuarioAux.getPassword())){
             respuesta.put("mensaje","usuario o contraseña incorrectos");
         }else{
             String hash ="";
             long tiempo = System.currentTimeMillis();
             if(usuarioAux.getId()!=null){
                 hash=Jwts.builder()
                        .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                        .setSubject(usuarioAux.getNombre())
                        .setIssuedAt(new Date(tiempo))
                        .setExpiration(new Date(tiempo+900000))
                        .claim("username", usuarioAux.getUsername())
                        .claim("correo", usuarioAux.getCorreo())
                        .compact();
             }
             usuarioAux.setHash(hash);
             respuesta.put("mensaje","Se accedió correctamente");
             respuesta.put("token",hash);
             respuesta.put("id",usuarioAux.getId());
             respuesta.put("nombre",usuarioAux.getNombre());
             respuesta.put("correo",usuarioAux.getCorreo());
             respuesta.put("username",usuarioAux.getUsername());
         }
     }
     return ResponseEntity.ok(respuesta);
 }
    // Método para el manejo de errores
  /*  public void throwError(Errors error) {
        String mensaje = "";
        int index = 0;
        for (ObjectError e : error.getAllErrors()) {
            if (index > 0) {
                mensaje += " | ";
            }
            mensaje += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());
        }
        throw new CustomException(mensaje);
    }*/
    @PutMapping("/usuarios") //POST 
    public ResponseEntity<Map<String, String>> actualizarUsuario(@RequestBody UsuarioModel usuario){
        
        //Map tener una clave valor {"mensaje": "Se agregó correctamente"}
        Map<String, String> respuesta= new HashMap<>();
            this.usuarioService.guardarUsuario(usuario); //Actualizo al usuario
            respuesta.put("mensaje","Se actualizó correctamente");

            //Busco el partdio que contenga a ese usuario
           // PartidoModel parAux=this.partidoService.getPartido("61945de3105cd33b0f98d96a");
            //Modifico la información de usuario dentro del partido
        //    parAux.getUsuario().setNombre(usuario.getNombre());
            //y se le agrega al partido
        //    actualizarPartido(parAux);
        return ResponseEntity.ok(respuesta);
    }
    

}
