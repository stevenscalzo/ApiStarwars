package com.acceso.datos.GestiondeStarwars.services;

import com.acceso.datos.GestiondeStarwars.entities.FilmsPlanetsEntity;
import com.acceso.datos.GestiondeStarwars.entities.PeopleEntity;
import com.acceso.datos.GestiondeStarwars.entities.PlanetsEntity;
import com.acceso.datos.GestiondeStarwars.repository.FilmsPlanetsDAOInterface;
import com.acceso.datos.GestiondeStarwars.repository.PeopleDAOInterface;
import com.acceso.datos.GestiondeStarwars.repository.PlanetsDAOInterface;
import com.acceso.datos.GestiondeStarwars.services.interfaces.IStarWarsPlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StarWarsPlanetsService implements IStarWarsPlanetsService {

    @Autowired
    private PlanetsDAOInterface planetsDAO;

    @Autowired
    private FilmsPlanetsDAOInterface filmsPlanetsDAO;

    @Autowired
    private PeopleDAOInterface peopleDAO;

    @Override
    @Transactional(readOnly = true)
    public List<PlanetsEntity> getAllPlanets() {
        return (List<PlanetsEntity>) planetsDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PlanetsEntity getPlanetById(Integer id) {
        return planetsDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PlanetsEntity savePlanet(PlanetsEntity planetsEntity) {
        if (planetsEntity.getCodigo() == 0) {
            planetsEntity.setCodigo(getLastId((List<PlanetsEntity>) planetsDAO.findAll()));
        }
        return planetsDAO.save(planetsEntity);
    }

    @Override
    @Transactional
    public void deletePlanet(PlanetsEntity planetsEntity) {
        removePlanetFromFilmsPlanetsTable(planetsEntity);
        removePlanetFromPeopleTable(planetsEntity);
        planetsDAO.delete(planetsEntity);

    }

    private void removePlanetFromFilmsPlanetsTable(PlanetsEntity planetsEntity) {
        List<FilmsPlanetsEntity> filmsPlanetsEntities = (List<FilmsPlanetsEntity>) filmsPlanetsDAO.findAll();
        filmsPlanetsEntities.stream().filter(filmsPlanetsEntity -> filmsPlanetsEntity.getCodigoPlanet() == planetsEntity.getCodigo())
                .forEach(filmsPlanetsEntity -> filmsPlanetsDAO.delete(filmsPlanetsEntity));
    }

    private void removePlanetFromPeopleTable(PlanetsEntity planetsEntity) {
        List<PeopleEntity> peopleEntities = (List<PeopleEntity>) peopleDAO.findAll();
        peopleEntities.stream()
                .filter(peopleEntity -> (peopleEntity.getHomeworld() != null))
                .filter(peopleEntity -> peopleEntity.getHomeworld() == planetsEntity.getCodigo())
                .forEach(peopleEntity -> {
                    peopleEntity.setHomeworld(null);
                    peopleDAO.save(peopleEntity);
                });
    }


    private int getLastId(List<PlanetsEntity> planetsEntityList) {
        int lastId = 0;
        if (!planetsEntityList.isEmpty()) {
            PlanetsEntity planetsEntity = planetsEntityList.get(planetsEntityList.size() - 1);
            lastId = planetsEntity.getCodigo();
        }
        return lastId + 1;
    }
}
