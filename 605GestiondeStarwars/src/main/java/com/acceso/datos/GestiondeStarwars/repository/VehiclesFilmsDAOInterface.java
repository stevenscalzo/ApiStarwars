package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.VehiclesFilmsEntity;
import org.springframework.data.repository.CrudRepository;

public interface VehiclesFilmsDAOInterface extends CrudRepository<VehiclesFilmsEntity, Integer> {
}
