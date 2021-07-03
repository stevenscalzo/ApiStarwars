package com.acceso.datos.GestiondeStarwars.controllers;

import com.acceso.datos.GestiondeStarwars.entities.PeopleEntity;
import com.acceso.datos.GestiondeStarwars.services.StarWarsPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class StarWarsPeopleController {

    @Autowired
    private StarWarsPeopleService starWarsPeopleService;
    private String MENSAJE = "mensaje";
    private String ERROR = "error";

    @GetMapping("/people")
    public List<PeopleEntity> getAllPeople() {
        return starWarsPeopleService.getAllPeople();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<?> getPeopleById(@PathVariable Integer id) {
        PeopleEntity peopleById;
        Map<String, Object> response = new HashMap<>();
        try {
            peopleById = starWarsPeopleService.getPeopleById(id);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar la consulta en la tabla people");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (peopleById == null) {
            return getResponseEntityForNotFoundPeople(id, response);
        }
        return new ResponseEntity<>(peopleById, HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<?> savePeople(@RequestBody PeopleEntity peopleEntity) {
        PeopleEntity createdPeople;
        Map<String, Object> response = new HashMap<>();
        peopleEntity.setCreated(String.valueOf(new Date()));
        peopleEntity.setEdited(String.valueOf(new Date()));
        try {
            createdPeople = starWarsPeopleService.savePeople(peopleEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar insert en la tabla people");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El people ha sido creado satisfactoriamente!");
        response.put("people", createdPeople);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePeople(@PathVariable Integer id) {
        PeopleEntity peopleEntity;
        Map<String, Object> response = new HashMap<>();
        try {
            peopleEntity = starWarsPeopleService.getPeopleById(id);
            if (peopleEntity == null) {
                return getResponseEntityForNotFoundPeople(id, response);
            }
            starWarsPeopleService.deletePeople(peopleEntity);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el delete en la tabla people para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "Se ha eliminado correctamente el elemento de id: ".concat(id.toString()).concat(" en la tabla people"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<?> updatePeople(@RequestBody PeopleEntity peopleEntity, @PathVariable Integer id) {
        PeopleEntity currentPeopleEntity;
        PeopleEntity updatedPeople;
        Map<String, Object> response = new HashMap<>();
        try {
            currentPeopleEntity = starWarsPeopleService.getPeopleById(id);
            if (currentPeopleEntity == null) {
                return getResponseEntityForNotFoundPeople(id, response);
            }
            currentPeopleEntity.setName(peopleEntity.getName());
            currentPeopleEntity.setBirthYear(peopleEntity.getBirthYear());
            currentPeopleEntity.setEyeColor(peopleEntity.getEyeColor());
            currentPeopleEntity.setGender(peopleEntity.getGender());
            currentPeopleEntity.setHairColor(peopleEntity.getHairColor());
            currentPeopleEntity.setHeight(peopleEntity.getHeight());
            currentPeopleEntity.setMass(peopleEntity.getMass());
            currentPeopleEntity.setSkinColor(peopleEntity.getSkinColor());
            currentPeopleEntity.setEdited(String.valueOf(new Date()));

            updatedPeople = starWarsPeopleService.savePeople(currentPeopleEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el update en la tabla people para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El people ha sido actualizado satisfactoriamente!");
        response.put("people", updatedPeople);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> getResponseEntityForNotFoundPeople(@PathVariable Integer id, Map<String, Object> response) {
        response.put(MENSAJE, "El people con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
