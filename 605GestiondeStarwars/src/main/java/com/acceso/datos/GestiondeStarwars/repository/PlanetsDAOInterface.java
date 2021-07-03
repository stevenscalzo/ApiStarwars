package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.PlanetsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlanetsDAOInterface extends CrudRepository<PlanetsEntity, Integer> {
}
