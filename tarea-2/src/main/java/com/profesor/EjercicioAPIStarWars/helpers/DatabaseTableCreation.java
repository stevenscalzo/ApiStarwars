package com.profesor.EjercicioAPIStarWars.helpers;

import com.profesor.EjercicioAPIStarWars.models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.profesor.EjercicioAPIStarWars.App.getIdFromUrl;
import static com.profesor.EjercicioAPIStarWars.App.getSafeOfNull;

public class DatabaseTableCreation {

    public static void setUpPeopleTable(Connection con, ArrayList<People> peopleArrayList) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();
        statement.executeUpdate("DELETE FROM starwars.people");
        for (People people : peopleArrayList) {
            sentenciaSQL.append("INSERT INTO starwars.people VALUES (")
                    .append("DEFAULT").append(",'").append(people.getName()).append("','")
                    .append(people.getHeight()).append("','").append(people.getMass()).append("','")
                    .append(people.getHair_color()).append("','").append(people.getSkin_color()).append("','")
                    .append(people.getBirth_year()).append("','").append(people.getGender()).append("',")
                    .append(getIdFromUrl(people.getHomeworld())).append(",")
                    .append("ARRAY").append(getArrayFromURL(people.getFilms())).append(",")
                    .append("ARRAY").append(getArrayFromURL(people.getSpecies())).append(",")
                    .append("ARRAY").append(getArrayFromURL(people.getVehicles())).append(",")
                    .append("ARRAY").append(getArrayFromURL(people.getStarships())).append(",'")
                    .append(people.getCreated()).append("','")
                    .append(people.getEdited()).append("','").append(people.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }

    public static void setUpFilmsTable(Connection con, ArrayList<Film> films) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();

        statement.executeUpdate("DELETE FROM starwars.films");
        for (Film film : films) {
            sentenciaSQL.append("INSERT INTO starwars.films VALUES (")
                    .append(film.getEpisode_id()).append(",'").append(film.getTitle()).append("','")
                    .append(film.getOpening_crawl().replace("'", "")).append("','").append(film.getDirector()).append("','")
                    .append(film.getProducer()).append("','").append(film.getReleaseDate()).append("',")
                    .append("ARRAY").append(getArrayFromURL(film.getCharacters())).append(",")
                    .append("ARRAY").append(getArrayFromURL(film.getPlanets())).append(",")
                    .append("ARRAY").append(getArrayFromURL(film.getStartships())).append(",")
                    .append("ARRAY").append(getArrayFromURL(film.getVehicles())).append(",")
                    .append("ARRAY").append(getArrayFromURL(film.getSpecies())).append(",'")
                    .append(film.getCreated()).append("','")
                    .append(film.getEdited()).append("','").append(film.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }


    public static void setUpPlanetsTable(Connection con, ArrayList<Planets> planetsArrayList) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();
        statement.executeUpdate("DELETE FROM starwars.planets");
        for (Planets planet : planetsArrayList) {
            sentenciaSQL.append("INSERT INTO starwars.planets VALUES (")
                    .append(getIdFromUrl(planet.getUrl())).append(",'").append(planet.getName()).append("','")
                    .append(planet.getRotation_period()).append("','").append(planet.getOrbital_period()).append("','")
                    .append(planet.getDiameter()).append("','").append(planet.getClimate()).append("','")
                    .append(planet.getGravity()).append("','").append(planet.getTerrain()).append("','")
                    .append(planet.getSurface_water()).append("','").append(planet.getPopulation()).append("',")
                    .append("ARRAY").append(getArrayFromURL(planet.getResidents())).append(",")
                    .append("ARRAY").append(getArrayFromURL(planet.getFilms())).append(",'")
                    .append(planet.getCreated()).append("','")
                    .append(planet.getEdited()).append("','").append(planet.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }

    public static void setUpSpeciesTable(Connection con, ArrayList<Species> speciesArrayList) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();
        statement.executeUpdate("DELETE FROM starwars.species");
        for (Species specie : speciesArrayList) {
            sentenciaSQL.append("INSERT INTO starwars.species VALUES (")
                    .append(getIdFromUrl(specie.getUrl())).append(",'").append(specie.getName().replace("'", "")).append("','")
                    .append(specie.getClassification()).append("','").append(specie.getDesignation()).append("','")
                    .append(specie.getAverage_height()).append("','")
                    .append(specie.getSkin_colors()).append("','").append(specie.getHair_colors()).append("','")
                    .append(specie.getEye_colors()).append("','").append(specie.getAverage_lifespan()).append("',")
                    .append(getIdFromUrl(specie.getHomeworld())).append(",'")
                    .append(specie.getLanguage().replace("'", "")).append("',")
                    .append("ARRAY").append(getArrayFromURL(specie.getPeople())).append(",")
                    .append("ARRAY").append(getArrayFromURL(specie.getFilms())).append(",'")
                    .append(specie.getCreated()).append("','")
                    .append(specie.getEdited()).append("','").append(specie.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }

    public static void setUpVehiclesTable(Connection con, ArrayList<Vehicles> vehiclesArrayList) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();
        statement.executeUpdate("DELETE FROM starwars.vehicles");
        for (Vehicles vehicles : vehiclesArrayList) {
            sentenciaSQL.append("INSERT INTO starwars.vehicles VALUES (")
                    .append(getIdFromUrl(vehicles.getUrl())).append(",'").append(vehicles.getName().replace("'", "")).append("','")
                    .append(vehicles.getModel()).append("','").append(vehicles.getManufacturer()).append("','")
                    .append(vehicles.getCost_in_credits()).append("','")
                    .append(vehicles.getLength()).append("','").append(vehicles.getMax_atmosphering_speed()).append("','")
                    .append(vehicles.getCrew()).append("','").append(vehicles.getPassengers()).append("','")
                    .append(vehicles.getCargo_capacity()).append("','").append(vehicles.getConsumables()).append("','")
                    .append(vehicles.getVehicle_class()).append("',")
                    .append("ARRAY").append(getArrayFromURL(vehicles.getPilots())).append(",")
                    .append("ARRAY").append(getArrayFromURL(vehicles.getFilms())).append(",'")
                    .append(vehicles.getCreated()).append("','")
                    .append(vehicles.getEdited()).append("','").append(vehicles.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }


    public static void setUpStarshipsTable(Connection con, ArrayList<Starships> starshipsArrayList) throws SQLException {
        Statement statement = con.createStatement();
        StringBuilder sentenciaSQL = new StringBuilder();
        statement.executeUpdate("DELETE FROM starwars.starships");
        for (Starships starships : starshipsArrayList) {
            sentenciaSQL.append("INSERT INTO starwars.starships VALUES (")
                    .append(getIdFromUrl(starships.getUrl())).append(",'").append(starships.getName().replace("'", "")).append("','")
                    .append(starships.getModel()).append("','").append(starships.getManufacturer()).append("','")
                    .append(starships.getCost_in_credits()).append("','")
                    .append(starships.getLength()).append("','").append(starships.getMax_atmosphering_speed()).append("','")
                    .append(starships.getCrew()).append("','").append(starships.getPassengers()).append("','")
                    .append(starships.getCargo_capacity()).append("','").append(starships.getConsumables()).append("','")
                    .append(starships.getHyperdrive_rating()).append("','").append(starships.getMGLT()).append("','")
                    .append(starships.getStarship_class()).append("',")
                    .append("ARRAY").append(getArrayFromURL(starships.getPilots())).append(",")
                    .append("ARRAY").append(getArrayFromURL(starships.getFilms())).append(",'")
                    .append(starships.getCreated()).append("','")
                    .append(starships.getEdited()).append("','").append(starships.getUrl()).append("');\n");
        }
        statement.executeUpdate(sentenciaSQL.toString());
    }

    public static ArrayList<Integer> getArrayFromURL(ArrayList<String> array) {
        ArrayList<Integer> arrayOfId = new ArrayList<Integer>();
        if (!array.isEmpty()) {
            for (String url : array) {
                arrayOfId.add(getIdFromUrl(url));
            }
        } else {
            arrayOfId.add(0);
        }
        return arrayOfId;
    }


}
