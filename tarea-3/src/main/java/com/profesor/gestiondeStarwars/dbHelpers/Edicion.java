package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import static com.profesor.gestiondeStarwars.App.session;
import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.*;
import static com.profesor.gestiondeStarwars.dbHelpers.Imprimir.imprimirDatosDeTablaPlanets;

/**
 * Clase que realiza las distintas ediciones
 *
 * @author: Steven Scalzo
 */
public class Edicion {
    public static void editarContenido(int codigo, String tabla) {
        tabla = volverPrimeraLetraMayuscula(tabla);
        try {
            Query consulta = session.createQuery("FROM " + tabla + "Entity tabla WHERE id = " + codigo);
            teclado.nextLine();
            switch (tabla) {
                case "People":
                    editarPersonaje(consulta, session);
                    break;
                case "Planets":
                    editarPlanetas(consulta, session);
                    break;
                case "Species":
                    editarEspecies(consulta, session);
                    break;
                case "Films":
                    editarPeliculas(consulta, session);
                    break;
                case "Starships":
                    editarNaveEspacial(consulta, session);
                    break;
                case "Vehicles":
                    editarVehiculos(consulta, session);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error en la busqueda en la tabla " + tabla);
        }
    }

    private static String editarDato(String campo, String valorAnterior) {
        String valorNuevo = valorAnterior;

        System.out.println(campo + " anterior: " + valorAnterior);

        if (campo.equals("Mundo natal")) {
            int homeworldId = 0;
            System.out.println("Homeworld (Seleccione el id de uno de los siguientes): ");

            Query planets = obtenerTodosLosRegistrosDeLaTabla("planets");
            List<PlanetsEntity> planetsEntityList = planets.list();
            imprimirDatosDeTablaPlanets(planetsEntityList);
            homeworldId = teclado.nextInt();

            if (homeworldId > 0 && homeworldId <= planetsEntityList.size()) {
                valorNuevo = homeworldId + "";
            }
        } else {

            System.out.println("Indique nuevo valor: ");
            String valorLeido = teclado.nextLine();

            if (!valorLeido.equals("")) {
                valorNuevo = valorLeido;
            }

        }
        return valorNuevo;
    }

    private static void editarEntityEnBaseDeDatos(Object entity) {
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        System.out.println("El elemento se ha modificado correctamente");
    }

    private static void editarPersonaje(Query consulta, Session session) {

        PeopleEntity people = (PeopleEntity) consulta.getSingleResult();

        people.setName(editarDato("Nombre", people.getName()));
        people.setBirthYear(editarDato("Cumpleaños", people.getBirthYear()));
        people.setEyeColor(editarDato("Color de ojos", people.getEyeColor()));
        people.setGender(editarDato("Genero", people.getGender()));
        people.setHairColor(editarDato("Color de cabello", people.getHairColor()));
        people.setHeight(editarDato("Altura", people.getHeight()));
        people.setMass(editarDato("Peso", people.getMass()));
        people.setSkinColor(editarDato("Color de piel", people.getSkinColor()));
        people.setHomeworld(Integer.parseInt(editarDato("Mundo natal", people.getHomeworld().toString())));
        people.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(people);
    }

    private static void editarPlanetas(Query consulta, Session session) {
        PlanetsEntity planets = (PlanetsEntity) consulta.getSingleResult();

        planets.setName(editarDato("Nombre", planets.getName()));
        planets.setDiameter(editarDato("Diametro", planets.getDiameter()));
        planets.setRotationPeriod(editarDato("Periodo de rotación", planets.getRotationPeriod()));
        planets.setOrbitalPeriod(editarDato("Periodo de orbitación", planets.getOrbitalPeriod()));
        planets.setGravity(editarDato("Gravedad", planets.getGravity()));
        planets.setPopulation(editarDato("Población", planets.getPopulation()));
        planets.setClimate(editarDato("Clima", planets.getClimate()));
        planets.setTerrain(editarDato("Terreno", planets.getTerrain()));
        planets.setSurfaceWater(editarDato("Superficie de agua", planets.getSurfaceWater()));
        planets.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(planets);
    }

    private static void editarEspecies(Query consulta, Session session) {
        SpeciesEntity species = (SpeciesEntity) consulta.getSingleResult();

        species.setName(editarDato("Nombre", species.getName()));
        species.setClassification(editarDato("Clasificación", species.getClassification()));
        species.setDesignation(editarDato("Designación", species.getDesignation()));
        species.setAverageHeight(editarDato("Altura promedio", species.getAverageHeight()));
        species.setAverageLifespan(editarDato("Esperanza de vida promedio", species.getAverageLifespan()));
        species.setEyeColors(editarDato("Color de ojos", species.getEyeColors()));
        species.setHairColors(editarDato("Color de pelo", species.getHairColors()));
        species.setSkinColors(editarDato("Color de piel", species.getSkinColors()));
        species.setLanguage(editarDato("Idioma", species.getLanguage()));
        species.setHomeworld(Integer.parseInt(editarDato("Mundo natal", species.getHomeworld().toString())));
        species.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(species);
    }

    private static void editarPeliculas(Query consulta, Session session) {
        FilmsEntity films = (FilmsEntity) consulta.getSingleResult();

        films.setTitle(editarDato("Titulo", films.getTitle()));
        films.setEpisodeId(editarDato("Id del episodio", films.getOpeningCrawl()));
        films.setOpeningCrawl(editarDato("Rastreo de apertura", films.getOpeningCrawl()));
        films.setDirector(editarDato("Director", films.getDirector()));
        films.setProducer(editarDato("Productor", films.getProducer()));
        films.setReleaseDate(editarDato("Fecha de lanzamiento", films.getReleaseDate()));
        films.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(films);
    }

    private static void editarNaveEspacial(Query consulta, Session session) {
        StarshipsEntity starships = (StarshipsEntity) consulta.getSingleResult();

        starships.setName(editarDato("Nombre", starships.getName()));
        starships.setModel(editarDato("Modelo", starships.getModel()));
        starships.setStarshipClass(editarDato("Clase", starships.getStarshipClass()));
        starships.setManufacturer(editarDato("Fabricante", starships.getManufacturer()));
        starships.setCostInCredits(editarDato("Costo en creditos", starships.getCostInCredits()));
        starships.setLength(editarDato("Longitud", starships.getLength()));
        starships.setCrew(editarDato("Tripulación", starships.getCrew()));
        starships.setPassengers(editarDato("Pasajeros", starships.getPassengers()));
        starships.setMaxAtmospheringSpeed(editarDato("Velocidad máxima atmosférica", starships.getMaxAtmospheringSpeed()));
        starships.setHyperdriveRating(editarDato("Calificación hyperdrive", starships.getHyperdriveRating()));
        starships.setMglt(editarDato("Megaluz por hora", starships.getMglt()));
        starships.setCargoCapacity(editarDato("Capacidad de carga", starships.getCargoCapacity()));
        starships.setConsumables(editarDato("Consumibles", starships.getConsumables()));
        starships.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(starships);
    }

    private static void editarVehiculos(Query consulta, Session session) {
        VehiclesEntity vehicles = (VehiclesEntity) consulta.getSingleResult();

        vehicles.setName(editarDato("Nombre", vehicles.getName()));
        vehicles.setModel(editarDato("Modelo", vehicles.getModel()));
        vehicles.setVehicleClass(editarDato("Clase", vehicles.getVehicleClass()));
        vehicles.setManufacturer(editarDato("Fabricante", vehicles.getManufacturer()));
        vehicles.setLength(editarDato("Longitud", vehicles.getLength()));
        vehicles.setCostInCredits(editarDato("Costo en creditos", vehicles.getCostInCredits()));
        vehicles.setCrew(editarDato("Tripulación", vehicles.getCrew()));
        vehicles.setPassengers(editarDato("Pasajeros", vehicles.getPassengers()));
        vehicles.setMaxAtmospheringSpeed(editarDato("Velocidad máxima atmosférica", vehicles.getMaxAtmospheringSpeed()));
        vehicles.setCargoCapacity(editarDato("Capacidad de carga", vehicles.getCargoCapacity()));
        vehicles.setConsumables(editarDato("Consumibles", vehicles.getConsumables()));
        vehicles.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));

        editarEntityEnBaseDeDatos(vehicles);
    }
}
