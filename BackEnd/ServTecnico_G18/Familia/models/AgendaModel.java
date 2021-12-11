package com.ServTecnico_G18.Familia.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="agenda")
public class AgendaModel {
    @Id
    private String id;
    private String fecha;
    private String nombre;
    //private int golesvisitante;
    private StecnicoModel servicio;
    //private StecnicoModel visitante;
    private UsuarioModel usuario;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public StecnicoModel getServicio() {
        return servicio;
    }
    public void setServicio(StecnicoModel servicio) {
        this.servicio = servicio;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public UsuarioModel getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    
    
}
