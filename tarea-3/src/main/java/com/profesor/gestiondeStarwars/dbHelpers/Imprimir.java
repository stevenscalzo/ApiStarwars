package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.*;

import java.util.List;


/**
 * Clase que imprime los valores de las tablas
 *
 * @author: Steven Scalzo
 */
public class Imprimir {

    public static void imprimirDatosDeTablaPeople(List<PeopleEntity> peopleEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Name");
        for (PeopleEntity people : peopleEntityList) {
            System.out.format("%-3s%-35s%n", people.getCodigo(), people.getName());
        }
    }

    public static void imprimirDatosDeTablaPlanets(List<PlanetsEntity> planetsEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Name");
        for (PlanetsEntity people : planetsEntityList) {
            System.out.format("%-3s%-35s%n", people.getCodigo(), people.getName());
        }
    }

    public static void imprimirDatosDeTablaPeliculas(List<FilmsEntity> filmsEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Title");
        for (FilmsEntity film : filmsEntityList) {
            System.out.format("%-3s%-35s%n", film.getCodigo(), film.getTitle());
        }
    }

    public static void imprimirDatosDeTablaSpecies(List<SpeciesEntity> speciesEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Name");
        for (SpeciesEntity specie : speciesEntityList) {
            System.out.format("%-3s%-35s%n", specie.getCodigo(), specie.getName());
        }
    }

    public static void imprimirDatosDeTablaStarships(List<StarshipsEntity> starshipsEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Name");
        for (StarshipsEntity film : starshipsEntityList) {
            System.out.format("%-3s%-35s%n", film.getCodigo(), film.getName());
        }
    }

    public static void imprimirDatosDeTablaVehicles(List<VehiclesEntity> vehiclesEntityList) {
        System.out.println();
        System.out.format("%-3s%-35s%n", "Id", "Name");
        for (VehiclesEntity vehicles : vehiclesEntityList) {
            System.out.format("%-3s%-35s%n", vehicles.getCodigo(), vehicles.getName());
        }
    }

}
