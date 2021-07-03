package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.FilmsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmsDAOInterface extends CrudRepository<FilmsEntity, Integer> {

}
