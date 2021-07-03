package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.FilmsPeopleEntity;
import org.springframework.data.repository.CrudRepository;

public interface FilmsPeopleDAOInterface extends CrudRepository<FilmsPeopleEntity, Integer> {
}
