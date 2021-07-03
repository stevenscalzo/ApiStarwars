package com.acceso.datos.GestiondeStarwars.controllers;

import com.acceso.datos.GestiondeStarwars.entities.PlanetsEntity;
import com.acceso.datos.GestiondeStarwars.services.StarWarsPlanetsService;
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
public class StarWarsPlanetsController {

    @Autowired
    private StarWarsPlanetsService starWarsPlanetsService;
    private String MENSAJE = "mensaje";
    private String ERROR = "error";

    @GetMapping("/planets")
    public List<PlanetsEntity> getAllPlanets() {
        return starWarsPlanetsService.getAllPlanets();
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<?> getPlanetById(@PathVariable Integer id) {
        PlanetsEntity planetById;
        Map<String, Object> response = new HashMap<>();
        try {
            planetById = starWarsPlanetsService.getPlanetById(id);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar la consulta en la tabla planets");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (planetById == null) {
            return getResponseEntityForNotFoundPlanet(id, response);
        }
        return new ResponseEntity<>(planetById, HttpStatus.OK);
    }

    @PostMapping("/planets")
    public ResponseEntity<?> savePlanet(@RequestBody PlanetsEntity planetsEntity) {
        PlanetsEntity createdPlanet;
        Map<String, Object> response = new HashMap<>();
        planetsEntity.setCreated(String.valueOf(new Date()));
        planetsEntity.setEdited(String.valueOf(new Date()));
        try {
            createdPlanet = starWarsPlanetsService.savePlanet(planetsEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar insert en la tabla planets");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El planet ha sido creado satisfactoriamente!");
        response.put("planet", createdPlanet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/planets/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable Integer id) {
        PlanetsEntity planetsEntity;
        Map<String, Object> response = new HashMap<>();
        try {
            planetsEntity = starWarsPlanetsService.getPlanetById(id);
            if (planetsEntity == null) {
                return getResponseEntityForNotFoundPlanet(id, response);
            }
            starWarsPlanetsService.deletePlanet(planetsEntity);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el delete en la tabla planets para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "Se ha eliminado correctamente el elemento de id: ".concat(id.toString()).concat(" en la tabla planets"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/planets/{id}")
    public ResponseEntity<?> updatePlanet(@RequestBody PlanetsEntity planetsEntity, @PathVariable Integer id) {
        PlanetsEntity currentPlanetsEntity;
        PlanetsEntity updatedPlanet;
        Map<String, Object> response = new HashMap<>();
        try {
            currentPlanetsEntity = starWarsPlanetsService.getPlanetById(id);
            if (currentPlanetsEntity == null) {
                return getResponseEntityForNotFoundPlanet(id, response);
            }
            currentPlanetsEntity.setName(planetsEntity.getName());
            currentPlanetsEntity.setDiameter(planetsEntity.getDiameter());
            currentPlanetsEntity.setRotationPeriod(planetsEntity.getRotationPeriod());
            currentPlanetsEntity.setOrbitalPeriod(planetsEntity.getOrbitalPeriod());
            currentPlanetsEntity.setGravity(planetsEntity.getGravity());
            currentPlanetsEntity.setPopulation(planetsEntity.getPopulation());
            currentPlanetsEntity.setClimate(planetsEntity.getClimate());
            currentPlanetsEntity.setTerrain(planetsEntity.getTerrain());
            currentPlanetsEntity.setSurfaceWater(planetsEntity.getSurfaceWater());
            currentPlanetsEntity.setEdited(String.valueOf(new Date()));

            updatedPlanet = starWarsPlanetsService.savePlanet(currentPlanetsEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el update en la tabla planets para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El planet ha sido actualizado satisfactoriamente!");
        response.put("planet", updatedPlanet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> getResponseEntityForNotFoundPlanet(@PathVariable Integer id, Map<String, Object> response) {
        response.put(MENSAJE, "El planet con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
