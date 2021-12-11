package com.ServTecnico_G18.Familia.services;

import java.util.List;

import com.ServTecnico_G18.Familia.models.StecnicoModel;
import com.ServTecnico_G18.Familia.repositories.StecnicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StecnicoService {
    
    @Autowired //Instancia del repositorio
    StecnicoRepository StecnicoRepository; //Variable que hace referencia al repositorio

    //Metodo para guardar datos del equipo
    public void guardarStecnico(StecnicoModel Stecnico){
        this.StecnicoRepository.save(Stecnico);
    }

    //Método para listar los Stecnico
    public List<StecnicoModel> obtenerStecnico(){
        return this.StecnicoRepository.findAll();
    }
}
