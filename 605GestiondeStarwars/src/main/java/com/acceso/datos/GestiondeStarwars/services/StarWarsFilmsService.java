package com.acceso.datos.GestiondeStarwars.services;

import com.acceso.datos.GestiondeStarwars.entities.*;
import com.acceso.datos.GestiondeStarwars.repository.*;
import com.acceso.datos.GestiondeStarwars.services.interfaces.IStarWarsFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StarWarsFilmsService implements IStarWarsFilmsService {

    @Autowired
    private FilmsDAOInterface filmsDAO;

    @Autowired
    private FilmsPeopleDAOInterface filmsPeopleDAO;

    @Autowired
    private FilmsPlanetsDAOInterface filmsPlanetsDAO;

    @Autowired
    private StarshipsFilmsDAOInterface starshipsFilmsDAO;

    @Autowired
    private VehiclesFilmsDAOInterface vehiclesFilmsDAO;

    @Override
    @Transactional(readOnly = true)
    public List<FilmsEntity> getAllFilms() {
        return (List<FilmsEntity>) filmsDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public FilmsEntity getFilmById(Integer id) {
        return filmsDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public FilmsEntity saveFilm(FilmsEntity filmsEntity) {
        if (filmsEntity.getCodigo() == 0) {
            filmsEntity.setCodigo(getLastId((List<FilmsEntity>) filmsDAO.findAll()));
        }
        return filmsDAO.save(filmsEntity);
    }

    @Override
    public void deleteFilm(FilmsEntity filmsEntity) {

        removeFilmFromFilmsPeopleTable(filmsEntity);
        removeFilmFromFilmsPlanetsTable(filmsEntity);
        removeFilmFromStarshipsFilmsTable(filmsEntity);
        removeFilmFromVehiclesFilmsTable(filmsEntity);
        filmsDAO.delete(filmsEntity);
    }

    private void removeFilmFromVehiclesFilmsTable(FilmsEntity filmsEntity) {
        List<VehiclesFilmsEntity> vehiclesFilmsEntities = (List<VehiclesFilmsEntity>) vehiclesFilmsDAO.findAll();
        vehiclesFilmsEntities.stream().filter(vehiclesFilmsEntity -> vehiclesFilmsEntity.getCodigoFilm() == filmsEntity.getCodigo())
                .forEach(vehiclesFilmsEntity -> vehiclesFilmsDAO.delete(vehiclesFilmsEntity));
    }

    private void removeFilmFromStarshipsFilmsTable(FilmsEntity filmsEntity) {
        List<StarshipsFilmsEntity> starshipsFilmsEntities = (List<StarshipsFilmsEntity>) starshipsFilmsDAO.findAll();
        starshipsFilmsEntities.stream().filter(starshipsFilmsEntity -> starshipsFilmsEntity.getCodigoFilm() == filmsEntity.getCodigo())
                .forEach(starshipsFilmsEntity -> starshipsFilmsDAO.delete(starshipsFilmsEntity));
    }

    private void removeFilmFromFilmsPlanetsTable(FilmsEntity filmsEntity) {
        List<FilmsPlanetsEntity> filmsPlanetsEntities = (List<FilmsPlanetsEntity>) filmsPlanetsDAO.findAll();
        filmsPlanetsEntities.stream().filter(filmsPlanetsEntity -> filmsPlanetsEntity.getCodigoFilm() == filmsEntity.getCodigo())
                .forEach(filmsPlanetsEntity -> filmsPlanetsDAO.delete(filmsPlanetsEntity));
    }

    private void removeFilmFromFilmsPeopleTable(FilmsEntity filmsEntity) {
        List<FilmsPeopleEntity> filmsPeopleEntities = (List<FilmsPeopleEntity>) filmsPeopleDAO.findAll();
        filmsPeopleEntities.stream().filter(filmsPeopleEntity -> filmsPeopleEntity.getCodigoFilm() == filmsEntity.getCodigo())
                .forEach(filmsPeopleEntity -> filmsPeopleDAO.delete(filmsPeopleEntity));
    }

    private int getLastId(List<FilmsEntity> filmsEntityList) {
        int lastId = 0;
        if (!filmsEntityList.isEmpty()) {
            FilmsEntity filmsEntity = filmsEntityList.get(filmsEntityList.size() - 1);
            lastId = filmsEntity.getCodigo();
        }
        return lastId + 1;
    }

}
