package com.ServTecnico_G18.Familia.repositories;

import com.ServTecnico_G18.Familia.models.AgendaModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgendaRepository extends MongoRepository<AgendaModel, String>{
    
}
