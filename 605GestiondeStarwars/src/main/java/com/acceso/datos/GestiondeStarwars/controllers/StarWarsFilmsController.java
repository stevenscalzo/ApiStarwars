package com.acceso.datos.GestiondeStarwars.controllers;

import com.acceso.datos.GestiondeStarwars.entities.FilmsEntity;
import com.acceso.datos.GestiondeStarwars.services.StarWarsFilmsService;
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
public class StarWarsFilmsController {

    @Autowired
    private StarWarsFilmsService starWarsFilmsService;
    private String MENSAJE = "mensaje";
    private String ERROR = "error";

    @GetMapping("/films")
    public List<FilmsEntity> getAllFilms() {
        return starWarsFilmsService.getAllFilms();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Integer id) {
        FilmsEntity filmById;
        Map<String, Object> response = new HashMap<>();
        try {
            filmById = starWarsFilmsService.getFilmById(id);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar la consulta en la tabla films");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (filmById == null) {
            return getResponseEntityForNotFoundFilm(id, response);
        }
        return new ResponseEntity<>(filmById, HttpStatus.OK);
    }

    @PostMapping("/films")
    public ResponseEntity<?> saveFilm(@RequestBody FilmsEntity filmsEntity) {
        Map<String, Object> response = new HashMap<>();
        FilmsEntity createdFilmEntity;
        filmsEntity.setCreated(String.valueOf(new Date()));
        filmsEntity.setEdited(String.valueOf(new Date()));
        try {
            createdFilmEntity = starWarsFilmsService.saveFilm(filmsEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar insert en la tabla films");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El film ha sido creado satisfactoriamente!");
        response.put("film", createdFilmEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
        FilmsEntity filmsEntity;
        Map<String, Object> response = new HashMap<>();
        try {
            filmsEntity = starWarsFilmsService.getFilmById(id);
            if (filmsEntity == null) {
                return getResponseEntityForNotFoundFilm(id, response);
            }
            starWarsFilmsService.deleteFilm(filmsEntity);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el delete en la tabla films para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "Se ha eliminado correctamente el elemento de id: ".concat(id.toString()).concat(" en la tabla films"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<?> updateFilm(@RequestBody FilmsEntity filmsEntity, @PathVariable Integer id) {
        FilmsEntity currentFilmsEntity;
        FilmsEntity updatedFilmEntity;
        Map<String, Object> response = new HashMap<>();
        try {
            currentFilmsEntity = starWarsFilmsService.getFilmById(id);
            if (currentFilmsEntity == null) {
                return getResponseEntityForNotFoundFilm(id, response);
            }
            currentFilmsEntity.setTitle(filmsEntity.getTitle());
            currentFilmsEntity.setEpisodeId(filmsEntity.getEpisodeId());
            currentFilmsEntity.setOpeningCrawl(filmsEntity.getOpeningCrawl());
            currentFilmsEntity.setDirector(filmsEntity.getDirector());
            currentFilmsEntity.setProducer(filmsEntity.getProducer());
            currentFilmsEntity.setReleaseDate(filmsEntity.getReleaseDate());
            currentFilmsEntity.setEdited(String.valueOf(new Date()));

            updatedFilmEntity = starWarsFilmsService.saveFilm(currentFilmsEntity);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al realizar el update en la tabla films para el id: ".concat(id.toString()));
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(MENSAJE, "El film ha sido actualizado satisfactoriamente!");
        response.put("film", updatedFilmEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> getResponseEntityForNotFoundFilm(@PathVariable Integer id, Map<String, Object> response) {
        response.put(MENSAJE, "El film con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
