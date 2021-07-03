package com.profesor.EjercicioAPIStarWars.helpers;

import com.profesor.EjercicioAPIStarWars.models.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static com.profesor.EjercicioAPIStarWars.App.setNextUrl;
import static com.profesor.EjercicioAPIStarWars.mappers.Mappers.*;

public class ObtenerArrays {
    public static ArrayList<Film> getFilmsArray(URL url) {
        ArrayList<Film> films = new ArrayList<>();
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();

            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                films.add(mapearFilm(datos.getJsonObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    public static ArrayList<Film> getFilmsArrayList(URL url) {
        ArrayList<Film> films = new ArrayList<>();
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();

            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                films.add(mapearFilm(datos.getJsonObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }


    public static ArrayList<People> getPeopleArrayList(URL url) {
        ArrayList<People> peopleArrayList = new ArrayList<>();
        String nextUrl = "";
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();
            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                peopleArrayList.add(mapearPeople(datos.getJsonObject(i)));
            }
            nextUrl = obj.getString("next");
            while (!nextUrl.equals("null")) {
                
                try (InputStream isNext = new URL(nextUrl.replace("http", "https")).openStream();
                     JsonReader rdrNext = Json.createReader(isNext)) {
                    JsonObject objNext = rdrNext.readObject();
                    JsonArray datosNext = objNext.getJsonArray("results");
                    nextUrl = setNextUrl(nextUrl, objNext);
                    for (int i = 0; i < datosNext.size(); i++) {
                        peopleArrayList.add(mapearPeople(datosNext.getJsonObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleArrayList;
    }


    public static ArrayList<Planets> getPlanetsArrayList(URL url) {
        ArrayList<Planets> planetsArrayList = new ArrayList<>();
        String nextUrl = "";
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();
            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                planetsArrayList.add(mapearPlanets(datos.getJsonObject(i)));
            }
            nextUrl = obj.getString("next");
            while (!nextUrl.equals("null")) {
    
                try (InputStream isNext = new URL(nextUrl.replace("http", "https")).openStream();
                     JsonReader rdrNext = Json.createReader(isNext)) {
                    JsonObject objNext = rdrNext.readObject();
                    JsonArray datosNext = objNext.getJsonArray("results");
                    nextUrl = setNextUrl(nextUrl, objNext);
                    for (int i = 0; i < datosNext.size(); i++) {
                        planetsArrayList.add(mapearPlanets(datosNext.getJsonObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planetsArrayList;
    }

    public static ArrayList<Species> getSpeciesArrayList(URL url) {
        ArrayList<Species> speciesArrayList = new ArrayList<>();
        String nextUrl = "";
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();
            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                speciesArrayList.add(mapearSpecies(datos.getJsonObject(i)));
            }
            nextUrl = obj.getString("next");
            while (!nextUrl.equals("null")) {
    
                try (InputStream isNext = new URL(nextUrl.replace("http", "https")).openStream();
                     JsonReader rdrNext = Json.createReader(isNext)) {
                    JsonObject objNext = rdrNext.readObject();
                    JsonArray datosNext = objNext.getJsonArray("results");
                    nextUrl = setNextUrl(nextUrl, objNext);
                    for (int i = 0; i < datosNext.size(); i++) {
                        speciesArrayList.add(mapearSpecies(datosNext.getJsonObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return speciesArrayList;
    }

    public static ArrayList<Vehicles> getVehiclesArrayList(URL url) {
        ArrayList<Vehicles> vehiclesArrayList = new ArrayList<>();
        String nextUrl = "";
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();
            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                vehiclesArrayList.add(mapearVehicles(datos.getJsonObject(i)));
            }
            nextUrl = obj.getString("next");
            while (!nextUrl.equals("null")) {
    
                try (InputStream isNext = new URL(nextUrl.replace("http", "https")).openStream();
                     JsonReader rdrNext = Json.createReader(isNext)) {
                    JsonObject objNext = rdrNext.readObject();
                    JsonArray datosNext = objNext.getJsonArray("results");
                    nextUrl = setNextUrl(nextUrl, objNext);
                    for (int i = 0; i < datosNext.size(); i++) {
                        vehiclesArrayList.add(mapearVehicles(datosNext.getJsonObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiclesArrayList;
    }

    public static ArrayList<Starships> getStarshipsArrayList(URL url) {
        ArrayList<Starships> starshipsArrayList = new ArrayList<>();
        String nextUrl = "";
        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            rdr.close();
            is.close();
            JsonArray datos = obj.getJsonArray("results");
            for (int i = 0; i < datos.size(); i++) {
                starshipsArrayList.add(mapearStarships(datos.getJsonObject(i)));
            }
            nextUrl = obj.getString("next");
            while (!nextUrl.equals("null")) {
    
                try (InputStream isNext = new URL(nextUrl.replace("http", "https")).openStream();
                     JsonReader rdrNext = Json.createReader(isNext)) {
                    JsonObject objNext = rdrNext.readObject();
                    JsonArray datosNext = objNext.getJsonArray("results");
                    nextUrl = setNextUrl(nextUrl, objNext);
                    for (int i = 0; i < datosNext.size(); i++) {
                        starshipsArrayList.add(mapearStarships(datosNext.getJsonObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starshipsArrayList;
    }

}


