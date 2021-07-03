package com.profesor.EjercicioAPIStarWars.mappers;

import com.profesor.EjercicioAPIStarWars.models.*;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;

public class Mappers {

    public static People mapearPeople(JsonObject obj) {
        People people = new People();
        people.setName(obj.getString("name"));
        people.setHeight(obj.getString("height"));
        people.setMass(obj.getString("mass"));
        people.setHair_color(obj.getString("hair_color"));
        people.setSkin_color(obj.getString("skin_color"));
        people.setEye_color(obj.getString("eye_color"));
        people.setBirth_year(obj.getString("birth_year"));
        people.setGender(obj.getString("gender"));
        people.setHomeworld(obj.getString("homeworld"));
        people.setFilms(getArrayFromJsonArray(obj.getJsonArray("films")));
        people.setVehicles(getArrayFromJsonArray(obj.getJsonArray("vehicles")));
        people.setStarships(getArrayFromJsonArray(obj.getJsonArray("starships")));
        people.setSpecies(getArrayFromJsonArray(obj.getJsonArray("species")));
        people.setUrl(obj.getString("url"));
        people.setCreated(obj.getString("created"));
        people.setEdited(obj.getString("edited"));
        return people;
    }

    public static Film mapearFilm(JsonObject obj) {
        Film film = new Film();
        film.setTitle(obj.getString("title"));
        film.setEpisode_id(obj.getInt("episode_id"));
        film.setOpening_crawl(obj.getString("opening_crawl"));
        film.setDirector(obj.getString("director"));
        film.setProducer(obj.getString("producer"));
        film.setReleaseDate(obj.getString("release_date"));
        film.setSpecies(getArrayFromJsonArray(obj.getJsonArray("species")));
        film.setStarships(getArrayFromJsonArray(obj.getJsonArray("starships")));
        film.setVehicles(getArrayFromJsonArray(obj.getJsonArray("vehicles")));
        film.setCharacters(getArrayFromJsonArray(obj.getJsonArray("characters")));
        film.setPlanets(getArrayFromJsonArray(obj.getJsonArray("planets")));
        film.setUrl(obj.getString("url"));
        film.setCreated(obj.getString("created"));
        film.setEdited(obj.getString("edited"));
        return film;
    }

    public static Planets mapearPlanets(JsonObject obj) {
        Planets planets = new Planets();
        planets.setName(obj.getString("name"));
        planets.setRotation_period(obj.getString("rotation_period"));
        planets.setOrbital_period(obj.getString("orbital_period"));
        planets.setDiameter(obj.getString("diameter"));
        planets.setClimate(obj.getString("climate"));
        planets.setGravity(obj.getString("gravity"));
        planets.setTerrain(obj.getString("terrain"));
        planets.setSurface_water(obj.getString("surface_water"));
        planets.setPopulation(obj.getString("population"));
        planets.setResidents(getArrayFromJsonArray(obj.getJsonArray("residents")));
        planets.setFilms(getArrayFromJsonArray(obj.getJsonArray("films")));
        planets.setUrl(obj.getString("url"));
        planets.setCreated(obj.getString("created"));
        planets.setEdited(obj.getString("edited"));
        return planets;
    }

    public static Species mapearSpecies(JsonObject obj) {
        Species species = new Species();
        species.setName(obj.getString("name"));
        species.setClassification(obj.getString("classification"));
        species.setDesignation(obj.getString("designation"));
        species.setAverage_height(obj.getString("average_height"));
        species.setSkin_colors(obj.getString("skin_colors"));
        species.setHair_colors(obj.getString("hair_colors"));
        species.setEye_colors(obj.getString("eye_colors"));
        species.setAverage_lifespan(obj.getString("average_lifespan"));
        species.setHomeworld((tryToGet(obj, "homeworld")));
        species.setLanguage(obj.getString("language"));
        species.setPeople(getArrayFromJsonArray(obj.getJsonArray("people")));
        species.setFilms(getArrayFromJsonArray(obj.getJsonArray("films")));
        species.setUrl(obj.getString("url"));
        species.setCreated(obj.getString("created"));
        species.setEdited(obj.getString("edited"));
        return species;
    }

    public static String tryToGet(JsonObject jsonObj, String key) {
        if (jsonObj.isNull(key)) {
            return "0";
        }
        return jsonObj.getString(key);
    }


    public static Starships mapearStarships(JsonObject obj) {
        Starships starships = new Starships();
        starships.setName(obj.getString("name"));
        starships.setModel(obj.getString("model"));
        starships.setManufacturer(obj.getString("manufacturer"));
        starships.setCost_in_credits(obj.getString("cost_in_credits"));
        starships.setLength(obj.getString("length"));
        starships.setMax_atmosphering_speed(obj.getString("max_atmosphering_speed"));
        starships.setCrew(obj.getString("crew"));
        starships.setPassengers(obj.getString("passengers"));
        starships.setCargo_capacity(obj.getString("cargo_capacity"));
        starships.setConsumables(obj.getString("consumables"));
        starships.setHyperdrive_rating(obj.getString("hyperdrive_rating"));
        starships.setMGLT(obj.getString("MGLT"));
        starships.setStarship_class(obj.getString("starship_class"));
        starships.setPilots(getArrayFromJsonArray(obj.getJsonArray("pilots")));
        starships.setFilms(getArrayFromJsonArray(obj.getJsonArray("films")));
        starships.setUrl(obj.getString("url"));
        starships.setCreated(obj.getString("created"));
        starships.setEdited(obj.getString("edited"));
        return starships;
    }

    public static Vehicles mapearVehicles(JsonObject obj) {
        Vehicles vehicles = new Vehicles();
        vehicles.setName(obj.getString("name"));
        vehicles.setModel(obj.getString("model"));
        vehicles.setManufacturer(obj.getString("manufacturer"));
        vehicles.setCost_in_credits(obj.getString("cost_in_credits"));
        vehicles.setLength(obj.getString("length"));
        vehicles.setMax_atmosphering_speed(obj.getString("max_atmosphering_speed"));
        vehicles.setCrew(obj.getString("crew"));
        vehicles.setPassengers(obj.getString("passengers"));
        vehicles.setCargo_capacity(obj.getString("cargo_capacity"));
        vehicles.setConsumables(obj.getString("consumables"));
        vehicles.setVehicle_class(obj.getString("vehicle_class"));
        vehicles.setPilots(getArrayFromJsonArray(obj.getJsonArray("pilots")));
        vehicles.setFilms(getArrayFromJsonArray(obj.getJsonArray("films")));
        vehicles.setUrl(obj.getString("url"));
        vehicles.setCreated(obj.getString("created"));
        vehicles.setEdited(obj.getString("edited"));
        return vehicles;
    }

    public static ArrayList<String> getArrayFromJsonArray(JsonArray jsonArray) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(jsonArray.getString(i));
        }
        return arrayList;

    }


}
