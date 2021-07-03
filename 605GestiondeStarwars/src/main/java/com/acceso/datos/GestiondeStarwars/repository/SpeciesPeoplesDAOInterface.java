package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.SpeciesPeopleEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpeciesPeoplesDAOInterface extends CrudRepository<SpeciesPeopleEntity, Integer> {
}
