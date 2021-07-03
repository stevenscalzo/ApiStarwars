package com.acceso.datos.GestiondeStarwars.services.interfaces;

import com.acceso.datos.GestiondeStarwars.entities.FilmsEntity;

import java.util.List;

public interface IStarWarsFilmsService {

    public List<FilmsEntity> getAllFilms();

    public FilmsEntity getFilmById(Integer id);

    public FilmsEntity saveFilm(FilmsEntity filmsEntity);

    public void deleteFilm(FilmsEntity filmsEntity);

}
