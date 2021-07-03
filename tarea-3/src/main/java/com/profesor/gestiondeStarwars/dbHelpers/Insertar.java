package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static com.profesor.gestiondeStarwars.App.session;
import static com.profesor.gestiondeStarwars.dbHelpers.Entidades.*;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.getEntityName;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.obtenerTodosLosRegistrosDeLaTabla;
import static com.profesor.gestiondeStarwars.dbHelpers.Validadores.*;

/**
 * Clase que inserta una nueva entidad a la tabla correspondiente
 *
 * @author: Steven Scalzo
 */
public class Insertar {

    public static void insertarEnTabla(String tabla) {
        switch (tabla) {
            case "people":
                insertarNuevoPeople();
                break;
            case "planets":
                insertarNuevoPlanet();
                break;
            case "species":
                insertarNuevaSpecie();
                break;
            case "films":
                insertarNuevoFilm();
                break;
            case "starships":
                insertarNuevaStarship();
                break;
            case "vehicles":
                insertarNuevoVehicle();
                break;
            default:
                break;
        }
    }

    private static void insertarNuevoPeople() {
        try {
            PeopleEntity peopleEntity = crearPeopleEntity();
            insertarEntityEnBaseDeDatos(peopleEntity);
            insertarPeopleEnTablaDeFilms(peopleEntity);
            insertarPeopleEnTablaDeSpecies(peopleEntity);
            insertarPeopleEnTablaDeStarships(peopleEntity);
            insertarPeopleEnTablaDeVehicles(peopleEntity);
            System.out.println("Se ha agregado correctamente el nuevo People");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando la nuevo people");
        }
    }

    private static void insertarNuevoFilm() {
        try {
            FilmsEntity filmsEntity = crearFilmEntity();
            insertarEntityEnBaseDeDatos(filmsEntity);
            insertarFilmEnTablaDeVehicles(filmsEntity);
            insertarFilmEnTablaDeStarships(filmsEntity);
            System.out.println("Se ha agregado correctamente el nuevo Film");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando la nuevo film");
        }
    }

    private static void insertarNuevoPlanet() {
        try {
            PlanetsEntity planetsEntity = crearPlanetEntity();
            insertarEntityEnBaseDeDatos(planetsEntity);
            insertarPlanetEnTablaDeFilms(planetsEntity);
            System.out.println("Se ha agregado correctamente el nuevo Planet");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando la nuevo planet");
        }
    }

    private static void insertarNuevaSpecie() {
        try {
            SpeciesEntity speciesEntity = crearSpecieEntity();
            insertarEntityEnBaseDeDatos(speciesEntity);
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando la nueva specie");
        }
    }

    public static void insertarNuevaStarship() {
        try {
            StarshipsEntity starshipsEntity = crearNuevaStarship();
            insertarEntityEnBaseDeDatos(starshipsEntity);
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando la nueva starship");
        }
    }

    private static void insertarNuevoVehicle() {
        try {
            VehiclesEntity vehiclesEntity = crearVehicleEntity();
            insertarEntityEnBaseDeDatos(vehiclesEntity);
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error insertando el nuevo vehicle");
        }
    }

    private static void insertarPeopleEnTablaDeVehicles(PeopleEntity peopleEntity) {
        try {
            Query vehicles = obtenerTodosLosRegistrosDeLaTabla("vehicles");
            List<VehiclesEntity> vehiclesEntities = vehicles.list();
            System.out.println("Inserte los Vehicles que ha piloteado el personaje");
            String[] idVehicles = validarCodigoDeVehicles(vehiclesEntities);
            Transaction transaction = session.beginTransaction();
            for (String idVehicle : idVehicles) {
                VehiclesPeopleEntity vehiclesPeopleEntity = new VehiclesPeopleEntity();
                vehiclesPeopleEntity.setCodigoPeople(peopleEntity.getCodigo());
                vehiclesPeopleEntity.setCodigoVehicle(Integer.parseInt(idVehicle));
                session.save(vehiclesPeopleEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo People en la tabla vehicles_people");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla vehicles_people");
        }
    }

    private static void insertarPeopleEnTablaDeStarships(PeopleEntity peopleEntity) {
        try {
            Query starships = obtenerTodosLosRegistrosDeLaTabla("starships");
            List<StarshipsEntity> starshipsEntities = starships.list();
            System.out.println("Inserte las Starships que ha piloteado el personaje");
            String[] idStarships = validarCodigoDeStarship(starshipsEntities);
            Transaction transaction = session.beginTransaction();
            for (String idStarship : idStarships) {
                StarshipsPeopleEntity starshipsPeopleEntity = new StarshipsPeopleEntity();
                starshipsPeopleEntity.setCodigoPeople(peopleEntity.getCodigo());
                starshipsPeopleEntity.setCodigoStarship(Integer.parseInt(idStarship));
                session.save(starshipsPeopleEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo People en la tabla starships_people");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla starships_people");
        }
    }

    private static void insertarPeopleEnTablaDeSpecies(PeopleEntity peopleEntity) {
        try {
            Query species = obtenerTodosLosRegistrosDeLaTabla("species");
            List<SpeciesEntity> speciesEntities = species.list();
            String idSpecie = validarCodigoDeSpecie(speciesEntities);
            Transaction transaction = session.beginTransaction();
            SpeciesPeopleEntity speciesPeopleEntity = new SpeciesPeopleEntity();
            speciesPeopleEntity.setCodigoPeople(peopleEntity.getCodigo());
            speciesPeopleEntity.setCodigoSpecie(Integer.parseInt(idSpecie));
            session.save(speciesPeopleEntity);
            transaction.commit();
            System.out.println("Se han agregado correctamente el codigo del nuevo People en la tabla specie_people");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla specie_people");
        }
    }

    private static void insertarPeopleEnTablaDeFilms(PeopleEntity peopleEntity) {
        try {
            Query films = obtenerTodosLosRegistrosDeLaTabla("films");
            List<FilmsEntity> filmsEntities = films.list();
            System.out.println("Inserte la pelicula en la que ha aparecido el personaje");
            String[] idPeliculas = validarCodigoDePelicula(filmsEntities);
            Transaction transaction = session.beginTransaction();
            for (String idPelicula : idPeliculas) {
                FilmsPeopleEntity filmsPeopleEntity = new FilmsPeopleEntity();
                filmsPeopleEntity.setCodigoPeople(peopleEntity.getCodigo());
                filmsPeopleEntity.setCodigoFilm(Integer.parseInt(idPelicula));
                session.save(filmsPeopleEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo People en la tabla films_people");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla films_people");
        }
    }

    private static void insertarPlanetEnTablaDeFilms(PlanetsEntity planetsEntity) {
        try {
            Query films = obtenerTodosLosRegistrosDeLaTabla("films");
            List<FilmsEntity> filmsEntities = films.list();
            System.out.println("Inserte las peliculas en las que ha aparecido el planeta");
            String[] idPeliculas = validarCodigoDePelicula(filmsEntities);
            Transaction transaction = session.beginTransaction();
            for (String idPelicula : idPeliculas) {
                FilmsPlanetsEntity filmsPlanetsEntity = new FilmsPlanetsEntity();
                filmsPlanetsEntity.setCodigoPlanet(planetsEntity.getCodigo());
                filmsPlanetsEntity.setCodigoFilm(Integer.parseInt(idPelicula));
                session.save(filmsPlanetsEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo Planet en la tabla films_planets");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla films_planets");
        }
    }

    private static void insertarFilmEnTablaDeVehicles(FilmsEntity filmsEntity) {
        try {
            Query vehicles = obtenerTodosLosRegistrosDeLaTabla("vehicles");
            List<VehiclesEntity> vehiclesEntities = vehicles.list();
            System.out.println("Inserte los Vehicles que han aparecido en el Film");
            String[] idVehicles = validarCodigoDeVehicles(vehiclesEntities);
            Transaction transaction = session.beginTransaction();
            for (String idVehicle : idVehicles) {
                VehiclesFilmsEntity vehiclesFilmsEntity = new VehiclesFilmsEntity();
                vehiclesFilmsEntity.setCodigoFilm(filmsEntity.getCodigo());
                vehiclesFilmsEntity.setCodigoVehicle(Integer.parseInt(idVehicle));
                session.save(vehiclesFilmsEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo Film en la tabla vehicles_film");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla vehicles_film");
        }
    }

    private static void insertarFilmEnTablaDeStarships(FilmsEntity filmsEntity) {
        try {
            Query starships = obtenerTodosLosRegistrosDeLaTabla("starships");
            List<StarshipsEntity> starshipsEntities = starships.list();
            System.out.println("Inserte los Starships que han aparecido en el Film");
            String[] idStarships = validarCodigoDeStarship(starshipsEntities);
            Transaction transaction = session.beginTransaction();
            for (String idStarship : idStarships) {
                StarshipsFilmsEntity starshipsFilmsEntity = new StarshipsFilmsEntity();
                starshipsFilmsEntity.setCodigoFilm(filmsEntity.getCodigo());
                starshipsFilmsEntity.setCodigoStarship(Integer.parseInt(idStarship));
                session.save(starshipsFilmsEntity);
            }
            transaction.commit();
            System.out.println("Se han agregado correctamente los codigos del nuevo Film en la tabla starships_film");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insertando la data en la tabla starships_film");
        }
    }

    private static void insertarEntityEnBaseDeDatos(Object entity) {
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        System.out.println("El elemento se ha insertado en la tabla " + getEntityName(entity) + " correctamente");
    }
}
