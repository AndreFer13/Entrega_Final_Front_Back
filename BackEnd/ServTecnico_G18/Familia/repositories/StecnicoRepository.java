package com.ServTecnico_G18.Familia.repositories;

import com.ServTecnico_G18.Familia.models.StecnicoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //Etiqueta que asígna la interfaz como repositorio
public interface StecnicoRepository extends MongoRepository<StecnicoModel,String>{
    /* 
    La Clase MongoRepository contiene creados 
    métodos de las funcione básica del CRUD
    C - Crear o agregar datos
    R - Leer datos 
    U - Actualizar
    D - Eliminar datos
    */

}
