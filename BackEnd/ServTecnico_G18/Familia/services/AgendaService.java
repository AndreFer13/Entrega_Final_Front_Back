package com.ServTecnico_G18.Familia.services;

import java.util.List;

import com.ServTecnico_G18.Familia.models.AgendaModel;
import com.ServTecnico_G18.Familia.repositories.AgendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
    
    @Autowired
    AgendaRepository AgendaRepository;

    public void guardarAgenda(AgendaModel agenda){
        this.AgendaRepository.save(agenda);

    }

    public List<AgendaModel> traerTodos(){
        return this.AgendaRepository.findAll();
    }
    
}
