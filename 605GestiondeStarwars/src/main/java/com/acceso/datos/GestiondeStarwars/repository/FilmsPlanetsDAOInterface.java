package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.FilmsPlanetsEntity;
import org.springframework.data.repository.CrudRepository;

public interface FilmsPlanetsDAOInterface extends CrudRepository<FilmsPlanetsEntity, Integer> {
}
