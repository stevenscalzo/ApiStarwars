package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.StarshipsPeopleEntity;
import org.springframework.data.repository.CrudRepository;

public interface StarshipsPeoplesDAOInterface extends CrudRepository<StarshipsPeopleEntity, Integer> {
}
