package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.profesor.gestiondeStarwars.App.session;
import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.volverPrimeraLetraMayuscula;
import static com.profesor.gestiondeStarwars.dbHelpers.Imprimir.*;

/**
 * Clase que realiza las distintas búsquedas
 *
 * @author: Steven Scalzo
 */
public class Busqueda {

    public static void buscarPorNombre(String tabla) {
        System.out.println("Inserte el nombre que desea buscar en la tabla " + tabla);
        teclado.nextLine();
        String name = teclado.nextLine().toLowerCase();
        if (tabla.equals("people")) {
            buscarPersonajePorNombre(session, name);
        } else {
            tabla = volverPrimeraLetraMayuscula(tabla);
            try {
                String columna = "name";
                if (tabla.equals("Films")) {
                    columna = "title";
                }
                Query consulta = session.createQuery("FROM " + tabla + "Entity tabla WHERE lower(tabla." + columna + ") like '%" + name + "%'");
                switch (tabla) {
                    case "Planets":
                        buscarPlanetas(consulta);
                        break;
                    case "Species":
                        buscarEspecies(consulta);
                        break;
                    case "Films":
                        buscarPeliculas(consulta);
                        break;
                    case "Starships":
                        buscarStarShips(consulta);
                        break;
                    case "Vehicles":
                        buscarVehiculos(consulta);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en la busqueda en la tabla " + tabla);
            }
        }
    }

    public static void buscarContenido(String tabla) {
        tabla = volverPrimeraLetraMayuscula(tabla);
        try {
            Query consulta = session.createQuery("FROM " + tabla + "Entity");
            switch (tabla) {
                case "People":
                    buscarPersonajes(consulta);
                    break;
                case "Planets":
                    buscarPlanetas(consulta);
                    break;
                case "Species":
                    buscarEspecies(consulta);
                    break;
                case "Films":
                    buscarPeliculas(consulta);
                    break;
                case "Starships":
                    buscarStarShips(consulta);
                    break;
                case "Vehicles":
                    buscarVehiculos(consulta);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error en la busqueda en la tabla " + tabla);
        }
    }

    private static void buscarPlanetas(Query consulta) {
        List<PlanetsEntity> planetsEntityList = consulta.list();
        if (planetsEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaPlanets(planetsEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarEspecies(Query consulta) {
        List<SpeciesEntity> speciesEntityList = consulta.list();
        if (speciesEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaSpecies(speciesEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarPeliculas(Query consulta) {
        List<FilmsEntity> filmsEntityList = consulta.list();
        if (filmsEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaPeliculas(filmsEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarStarShips(Query consulta) {
        List<StarshipsEntity> starshipsEntityList = consulta.list();
        if (starshipsEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaStarships(starshipsEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarVehiculos(Query consulta) {
        List<VehiclesEntity> vehiclesEntityList = consulta.list();
        if (vehiclesEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaVehicles(vehiclesEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarPersonajes(Query consulta) {
        List<PeopleEntity> peopleEntityList = consulta.list();
        if (peopleEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            imprimirDatosDeTablaPeople(peopleEntityList);
        }
        System.out.println("\n");
    }

    private static void buscarPersonajePorNombre(Session session, String name) {
        List<PeopleEntity> peopleEntityList = session.createNativeQuery(
                "SELECT * " +
                        "FROM people " +
                        "WHERE lower(name) like :name")
                .addEntity(PeopleEntity.class)
                .setParameter("name", "%" + name + "%")
                .list();
        if (peopleEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            System.out.println();
            System.out.format("%-3s%-35s%n", "Id", "Name");
            for (PeopleEntity people : peopleEntityList) {
                System.out.format("%-3s%-35s%n", people.getCodigo(), people.getName());
            }
        }
        System.out.println("\n");
    }

    public static void buscarPeopleSinSpecie() {
        List<PeopleEntity> peopleEntityList = session.createNativeQuery(
                "SELECT * " +
                        "FROM people " +
                        "WHERE NOT EXISTS (" +
                        "SELECT * FROM species_people " +
                        "WHERE people.codigo = species_people.codigo_people)")
                .addEntity(PeopleEntity.class)
                .list();
        if (peopleEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            System.out.println();
            System.out.format("%-3s%-35s%n", "Id", "Name");
            for (PeopleEntity people : peopleEntityList) {
                System.out.format("%-3s%-35s%n", people.getCodigo(), people.getName());
            }
        }
        System.out.println("\n");
    }

    public static void buscarPeopleMasPeliculas() {
        List<PeopleEntity> peopleEntityList = session.createNativeQuery(
                "SELECT * FROM people WHERE people.codigo IN" +
                        "(SELECT codigo_people FROM films_people GROUP BY" +
                        " codigo_people ORDER BY COUNT(films_people.codigo_people) DESC LIMIT 1)")
                .addEntity(PeopleEntity.class)
                .list();
        if (peopleEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            System.out.println();
            System.out.format("%-3s%-35s%n", "Id", "Name");
            for (PeopleEntity people : peopleEntityList) {
                System.out.format("%-3s%-35s%n", people.getCodigo(), people.getName());
            }
        }
        System.out.println("\n");
    }

    public static void buscarColorPeloMasRepetido() {
        List<PeopleEntity> peopleEntityList = session.createNativeQuery(
                "SELECT * FROM people WHERE hair_color = (" +
                        "SELECT people.hair_color FROM people WHERE people.hair_color <>'none' " +
                        "GROUP BY people.hair_color ORDER BY COUNT(people.hair_color) " +
                        "DESC LIMIT 1) LIMIT 1")
                .addEntity(PeopleEntity.class)
                .list();
        if (peopleEntityList.isEmpty()) {
            System.out.println("No se han obtenido resultados en la busqueda");
        } else {
            System.out.println();
            System.out.format("%-35s%n", "Color");
            for (PeopleEntity people : peopleEntityList) {
                System.out.format("%-35s%n", people.getHairColor());
            }
        }
        System.out.println("\n");
    }
}
