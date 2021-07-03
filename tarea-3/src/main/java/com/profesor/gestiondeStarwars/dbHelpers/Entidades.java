package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.*;

import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.obtenerHomeworldId;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.obtenerUltimoCodigoEnLaTabla;


/**
 * Clase que crea las distintas entidades
 *
 * @author: Steven Scalzo
 */
public class Entidades {

    public static PeopleEntity crearPeopleEntity() {
        System.out.println("Proporcione los datos de la nueva especie");
        PeopleEntity peopleEntity = new PeopleEntity();
        teclado.nextLine();
        System.out.println("Name: ");
        peopleEntity.setName(teclado.nextLine());
        System.out.println("Birth Year: ");
        peopleEntity.setBirthYear(teclado.nextLine());
        System.out.println("Eye Color: ");
        peopleEntity.setEyeColor(teclado.nextLine());
        System.out.println("Gender: ");
        peopleEntity.setGender(teclado.nextLine());
        System.out.println("Hair Color: ");
        peopleEntity.setHairColor(teclado.nextLine());
        System.out.println("Height: ");
        peopleEntity.setHeight(teclado.nextLine());
        System.out.println("Mass: ");
        peopleEntity.setMass(teclado.nextLine());
        System.out.println("Skin Color: ");
        peopleEntity.setSkinColor(teclado.nextLine());
        peopleEntity.setHomeworld(obtenerHomeworldId());
        teclado.nextLine();
        peopleEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("people")));
        peopleEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        peopleEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return peopleEntity;
    }

    public static SpeciesEntity crearSpecieEntity() {
        System.out.println("Proporcione los datos de la nueva especie");
        SpeciesEntity speciesEntity = new SpeciesEntity();
        teclado.nextLine();
        System.out.println("Name: ");
        speciesEntity.setName(teclado.nextLine());
        System.out.println("Classification: ");
        speciesEntity.setClassification(teclado.nextLine());
        System.out.println("Designation: ");
        speciesEntity.setDesignation(teclado.nextLine());
        System.out.println("Average Height: ");
        speciesEntity.setAverageHeight(teclado.nextLine());
        System.out.println("Average Lifespan: ");
        speciesEntity.setAverageLifespan(teclado.nextLine());
        System.out.println("Eye Colors: ");
        speciesEntity.setEyeColors(teclado.nextLine());
        System.out.println("Hair Colors: ");
        speciesEntity.setHairColors(teclado.nextLine());
        System.out.println("Skin Colors: ");
        speciesEntity.setSkinColors(teclado.nextLine());
        System.out.println("Language: ");
        speciesEntity.setLanguage(teclado.nextLine());
        speciesEntity.setHomeworld(obtenerHomeworldId());
        speciesEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("species")));
        speciesEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        speciesEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return speciesEntity;
    }

    public static StarshipsEntity crearNuevaStarship() {
        System.out.println("Proporcione los datos de la nueva Starship");
        StarshipsEntity starshipsEntity = new StarshipsEntity();
        teclado.nextLine();
        System.out.println("Name: ");
        starshipsEntity.setName(teclado.nextLine());
        System.out.println("Model: ");
        starshipsEntity.setModel(teclado.nextLine());
        System.out.println("Starship Class: ");
        starshipsEntity.setStarshipClass(teclado.nextLine());
        System.out.println("manufacturer: ");
        starshipsEntity.setManufacturer(teclado.nextLine());
        System.out.println("Cost in credits: ");
        starshipsEntity.setCostInCredits(teclado.nextLine());
        System.out.println("Length: ");
        starshipsEntity.setLength(teclado.nextLine());
        System.out.println("Crew: ");
        starshipsEntity.setCrew(teclado.nextLine());
        System.out.println("Passengers: ");
        starshipsEntity.setPassengers(teclado.nextLine());
        System.out.println("Max Atmospheric Speed: ");
        starshipsEntity.setMaxAtmospheringSpeed(teclado.nextLine());
        System.out.println("Hyperdrive Rating: ");
        starshipsEntity.setHyperdriveRating(teclado.nextLine());
        System.out.println("Mglt: ");
        starshipsEntity.setMglt(teclado.nextLine());
        System.out.println("Cargo Capacity: ");
        starshipsEntity.setCargoCapacity(teclado.nextLine());
        System.out.println("Consumables: ");
        starshipsEntity.setConsumables(teclado.nextLine());
        starshipsEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("starships")));
        starshipsEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        starshipsEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return starshipsEntity;
    }

    public static VehiclesEntity crearVehicleEntity() {
        System.out.println("Proporcione los datos de la nueva Starship");
        VehiclesEntity vehiclesEntity = new VehiclesEntity();
        teclado.nextLine();
        System.out.println("Name: ");
        vehiclesEntity.setName(teclado.nextLine());
        System.out.println("Model: ");
        vehiclesEntity.setModel(teclado.nextLine());
        System.out.println("Vehicle Class: ");
        vehiclesEntity.setVehicleClass(teclado.nextLine());
        System.out.println("manufacturer: ");
        vehiclesEntity.setManufacturer(teclado.nextLine());
        System.out.println("Length: ");
        vehiclesEntity.setLength(teclado.nextLine());
        System.out.println("Cost in credits: ");
        vehiclesEntity.setCostInCredits(teclado.nextLine());
        System.out.println("Crew: ");
        vehiclesEntity.setCrew(teclado.nextLine());
        System.out.println("Passengers: ");
        vehiclesEntity.setPassengers(teclado.nextLine());
        System.out.println("Max Atmospheric Speed: ");
        vehiclesEntity.setMaxAtmospheringSpeed(teclado.nextLine());
        System.out.println("Cargo Capacity: ");
        vehiclesEntity.setCargoCapacity(teclado.nextLine());
        System.out.println("Consumables: ");
        vehiclesEntity.setConsumables(teclado.nextLine());
        vehiclesEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("vehicles")));
        vehiclesEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        vehiclesEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return vehiclesEntity;
    }


    public static PlanetsEntity crearPlanetEntity() {
        System.out.println("Proporcione los datos del nuevo Planeta");
        PlanetsEntity planetsEntity = new PlanetsEntity();
        teclado.nextLine();
        System.out.println("Name: ");
        planetsEntity.setName(teclado.nextLine());
        System.out.println("Diameter: ");
        planetsEntity.setDiameter(teclado.nextLine());
        System.out.println("Rotation Period: ");
        planetsEntity.setRotationPeriod(teclado.nextLine());
        System.out.println("Orbital Period: ");
        planetsEntity.setOrbitalPeriod(teclado.nextLine());
        System.out.println("Gravity: ");
        planetsEntity.setGravity(teclado.nextLine());
        System.out.println("Population: ");
        planetsEntity.setPopulation(teclado.nextLine());
        System.out.println("Climate: ");
        planetsEntity.setClimate(teclado.nextLine());
        System.out.println("Terrain: ");
        planetsEntity.setTerrain(teclado.nextLine());
        System.out.println("Surface Water: ");
        planetsEntity.setSurfaceWater(teclado.nextLine());
        planetsEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("planets")));
        planetsEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        planetsEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return planetsEntity;

    }

    static FilmsEntity crearFilmEntity() {
        System.out.println("Proporcione los datos del nuevo Film");
        FilmsEntity filmsEntity = new FilmsEntity();
        teclado.nextLine();
        System.out.println("Title: ");
        filmsEntity.setTitle(teclado.nextLine());
        System.out.println("Episode Id: ");
        filmsEntity.setEpisodeId(teclado.nextLine());
        System.out.println("Opening Crawl: ");
        filmsEntity.setOpeningCrawl(teclado.nextLine());
        System.out.println("Director: ");
        filmsEntity.setDirector(teclado.nextLine());
        System.out.println("Producer: ");
        filmsEntity.setProducer(teclado.nextLine());
        System.out.println("Release Date (Formato YYYY-MM-DD): ");
        filmsEntity.setReleaseDate(teclado.nextLine());
        filmsEntity.setCodigo((obtenerUltimoCodigoEnLaTabla("films")));
        filmsEntity.setCreated(String.valueOf(java.time.Clock.systemUTC().instant()));
        filmsEntity.setEdited(String.valueOf(java.time.Clock.systemUTC().instant()));
        return filmsEntity;


    }

}
