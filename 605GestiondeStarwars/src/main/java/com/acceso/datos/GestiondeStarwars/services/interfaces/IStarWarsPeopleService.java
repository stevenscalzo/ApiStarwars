package com.acceso.datos.GestiondeStarwars.services.interfaces;

import com.acceso.datos.GestiondeStarwars.entities.PeopleEntity;

import java.util.List;

public interface IStarWarsPeopleService {

    public List<PeopleEntity> getAllPeople();

    public PeopleEntity getPeopleById(Integer id);

    public PeopleEntity savePeople(PeopleEntity peopleEntity);

    public void deletePeople(PeopleEntity peopleEntity);
}
