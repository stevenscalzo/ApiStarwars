package com.acceso.datos.GestiondeStarwars.services;

import com.acceso.datos.GestiondeStarwars.entities.*;
import com.acceso.datos.GestiondeStarwars.repository.*;
import com.acceso.datos.GestiondeStarwars.services.interfaces.IStarWarsPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StarWarsPeopleService implements IStarWarsPeopleService {

    @Autowired
    private PeopleDAOInterface peopleDAO;

    @Autowired
    private FilmsPeopleDAOInterface filmsPeopleDAO;

    @Autowired
    private StarshipsPeoplesDAOInterface starshipsPeoplesDAO;

    @Autowired
    private VehiclesPeoplesDAOInterface vehiclesPeoplesDAO;

    @Autowired
    private SpeciesPeoplesDAOInterface speciesPeoplesDAO;


    @Override
    @Transactional(readOnly = true)
    public List<PeopleEntity> getAllPeople() {
        return (List<PeopleEntity>) peopleDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PeopleEntity getPeopleById(Integer id) {
        return peopleDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PeopleEntity savePeople(PeopleEntity peopleEntity) {
        if (peopleEntity.getCodigo() == 0) {
            peopleEntity.setCodigo(getLastId((List<PeopleEntity>) peopleDAO.findAll()));
        }
        return peopleDAO.save(peopleEntity);
    }

    @Override
    public void deletePeople(PeopleEntity peopleEntity) {
        removePeopleFromFilmsPeopleTable(peopleEntity);
        removePeopleFromStarshipsPeopleTable(peopleEntity);
        removePeopleFromVehiclesPeopleTable(peopleEntity);
        removePeopleFromSpeciesPeopleTable(peopleEntity);
        peopleDAO.delete(peopleEntity);
    }

    private int getLastId(List<PeopleEntity> peopleEntityList) {
        int lastId = 0;
        if (!peopleEntityList.isEmpty()) {
            PeopleEntity peopleEntity = peopleEntityList.get(peopleEntityList.size() - 1);
            lastId = peopleEntity.getCodigo();
        }
        return lastId + 1;
    }

    private void removePeopleFromFilmsPeopleTable(PeopleEntity peopleEntity) {
        List<FilmsPeopleEntity> filmsPeopleEntities = (List<FilmsPeopleEntity>) filmsPeopleDAO.findAll();
        filmsPeopleEntities.stream().filter(filmsPeopleEntity -> filmsPeopleEntity.getCodigoPeople() == peopleEntity.getCodigo())
                .forEach(filmsPeopleEntity -> filmsPeopleDAO.delete(filmsPeopleEntity));
    }

    private void removePeopleFromStarshipsPeopleTable(PeopleEntity peopleEntity) {
        List<StarshipsPeopleEntity> starshipsPeopleEntities = (List<StarshipsPeopleEntity>) starshipsPeoplesDAO.findAll();
        starshipsPeopleEntities.stream().filter(starshipsPeopleEntity -> starshipsPeopleEntity.getCodigoPeople() == peopleEntity.getCodigo())
                .forEach(starshipsPeopleEntity -> starshipsPeoplesDAO.delete(starshipsPeopleEntity));
    }

    private void removePeopleFromVehiclesPeopleTable(PeopleEntity peopleEntity) {
        List<VehiclesPeopleEntity> vehiclesPeopleEntities = (List<VehiclesPeopleEntity>) vehiclesPeoplesDAO.findAll();
        vehiclesPeopleEntities.stream().filter(vehiclesPeopleEntity -> vehiclesPeopleEntity.getCodigoPeople() == peopleEntity.getCodigo())
                .forEach(vehiclesPeopleEntity -> vehiclesPeoplesDAO.delete(vehiclesPeopleEntity));
    }

    private void removePeopleFromSpeciesPeopleTable(PeopleEntity peopleEntity) {
        List<SpeciesPeopleEntity> speciesPeopleEntities = (List<SpeciesPeopleEntity>) speciesPeoplesDAO.findAll();
        speciesPeopleEntities.stream().filter(speciesPeopleEntity -> speciesPeopleEntity.getCodigoPeople() == peopleEntity.getCodigo())
                .forEach(speciesPeopleEntity -> speciesPeoplesDAO.delete(speciesPeopleEntity));
    }


}
