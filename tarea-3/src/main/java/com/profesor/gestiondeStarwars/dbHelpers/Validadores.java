package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.FilmsEntity;
import com.profesor.gestiondeStarwars.hibernate.SpeciesEntity;
import com.profesor.gestiondeStarwars.hibernate.StarshipsEntity;
import com.profesor.gestiondeStarwars.hibernate.VehiclesEntity;

import java.util.List;

import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.Imprimir.*;

/**
 * Clase que valida los valores que se piden
 *
 * @author: Steven Scalzo
 */
public class Validadores {

    static String validarCodigoDeSpecie(List<SpeciesEntity> speciesEntities) {
        boolean speciesValidadas = false;
        String idSpecie = "";
        while (!speciesValidadas) {
            speciesValidadas = true;
            System.out.println("Por favor, introduzca el codigo de la specie a la cual pertenece el personaje");
            imprimirDatosDeTablaSpecies(speciesEntities);
            idSpecie = teclado.nextLine();
            try {
                String finalIdSpecie = idSpecie;
                if (!speciesEntities.stream().anyMatch(o -> o.getCodigo() == Integer.parseInt(finalIdSpecie))) {
                    speciesValidadas = false;
                }
            } catch (Exception e) {
                System.out.println("La Specie de codigo " + idSpecie + " no es una specie valida, intente de nuevo");
                speciesValidadas = false;
            }
        }
        return idSpecie;
    }

    static String[] validarCodigoDePelicula(List<FilmsEntity> filmsEntities) {
        boolean peliculasValidas = false;
        String[] idPeliculas = null;
        while (!peliculasValidas) {
            peliculasValidas = true;
            System.out.println("Por favor, introduzca el codigo de las peliculas (separadas entre si con un espacio)");
            imprimirDatosDeTablaPeliculas(filmsEntities);
            idPeliculas = teclado.nextLine().split(" ");
            for (String idPelicula : idPeliculas) {
                try {
                    if (!filmsEntities.stream().anyMatch(o -> o.getCodigo() == Integer.parseInt(idPelicula))) {
                        peliculasValidas = false;
                    }
                } catch (Exception e) {
                    System.out.println("La Pelicula de codigo " + idPelicula + " no es una pelicula valida, intente de nuevo");
                    peliculasValidas = false;
                }
            }
        }
        return idPeliculas;
    }

    static String[] validarCodigoDeStarship(List<StarshipsEntity> starshipsEntities) {
        boolean starshipsValidadas = false;
        String[] idStarships = null;
        while (!starshipsValidadas) {
            starshipsValidadas = true;
            System.out.println("Por favor, introduzca el codigo de las starships (separadas entre si con un espacio)");
            imprimirDatosDeTablaStarships(starshipsEntities);
            idStarships = teclado.nextLine().split(" ");
            for (String idStarship : idStarships) {
                try {
                    if (!starshipsEntities.stream().anyMatch(o -> o.getCodigo() == Integer.parseInt(idStarship))) {
                        starshipsValidadas = false;
                    }
                } catch (Exception e) {
                    System.out.println("La Starship de codigo " + idStarship + " no es una starship valida, intente de nuevo");
                    starshipsValidadas = false;
                }
            }
        }
        return idStarships;
    }

    static String[] validarCodigoDeVehicles(List<VehiclesEntity> vehiclesEntities) {
        boolean vehiclesValidados = false;
        String[] idVehicles = null;
        while (!vehiclesValidados) {
            vehiclesValidados = true;
            System.out.println("Por favor, introduzca el codigo de los vehicles (separadas entre si con un espacio)");
            imprimirDatosDeTablaVehicles(vehiclesEntities);
            idVehicles = teclado.nextLine().split(" ");
            for (String idVehicle : idVehicles) {
                try {
                    if (!vehiclesEntities.stream().anyMatch(o -> o.getCodigo() == Integer.parseInt(idVehicle))) {
                        vehiclesValidados = false;
                    }
                } catch (Exception e) {
                    System.out.println("El Vehicle de codigo " + idVehicle + " no es un vehicle valido, intente de nuevo");
                    vehiclesValidados = false;
                }
            }
        }
        return idVehicles;
    }
}
