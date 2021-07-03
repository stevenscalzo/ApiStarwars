package com.acceso.datos.GestiondeStarwars.services.interfaces;

import com.acceso.datos.GestiondeStarwars.entities.PlanetsEntity;

import java.util.List;

public interface IStarWarsPlanetsService {

    public List<PlanetsEntity> getAllPlanets();

    public PlanetsEntity getPlanetById(Integer id);

    public PlanetsEntity savePlanet(PlanetsEntity planetsEntity);

    public void deletePlanet(PlanetsEntity planetsEntity);
}
