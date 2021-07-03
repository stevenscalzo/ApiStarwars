package com.acceso.datos.GestiondeStarwars.repository;

import com.acceso.datos.GestiondeStarwars.entities.StarshipsFilmsEntity;
import org.springframework.data.repository.CrudRepository;

public interface StarshipsFilmsDAOInterface extends CrudRepository<StarshipsFilmsEntity, Integer> {
}
