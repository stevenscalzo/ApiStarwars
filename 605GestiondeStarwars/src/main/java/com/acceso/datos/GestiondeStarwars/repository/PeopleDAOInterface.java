package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.PeopleEntity;
import org.springframework.data.repository.CrudRepository;

public interface PeopleDAOInterface extends CrudRepository<PeopleEntity, Integer> {
}
