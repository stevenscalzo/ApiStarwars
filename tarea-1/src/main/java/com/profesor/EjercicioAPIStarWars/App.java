package com.profesor.EjercicioAPIStarWars;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.profesor.EjercicioAPIStarWars.Models.Film;
import com.profesor.EjercicioAPIStarWars.Models.Personaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Hello world!
 *
 */

public class App {

	public static Personaje personajes = new Personaje();

	public static void main(String[] args) throws MalformedURLException {
		int opcion;
		Scanner teclado = new Scanner(System.in);

		do {
			Menu();

			opcion = teclado.nextInt();

			switch (opcion) {
			case 1:
				Conversor();
				break;
			case 2:
				AnyadirPersonaje();
				break;
			case 3:
				SalvarPersonajes();
				break;
			case 4:
				EspeciePersonaje();
				break;
			case 5:
				MostrarXML();
				break;
			case 6:
				System.out.println("Adios...");
				break;
			default:
				System.out.println("Opción no valida...");
				break;
			}

		} while (opcion != 6);
	}

	protected static void Menu() {
		System.out.println("Bienvenido, indique una opción:");
		System.out.println("1. Conversor");
		System.out.println("2. Añadir un personaje");
		System.out.println("3. Salvar personajes");
		System.out.println("4. Especie del personaje");
		System.out.println("5. Mostrar datos XML");
		System.out.println("6. Salir");
	}

	protected static void MostrarXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("pelicula.xml");
			Element film = document.getDocumentElement();
			System.out.println(film.getAttribute("title"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void EspeciePersonaje() throws MalformedURLException {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el código de un personaje:");
		String personaje = teclado.nextLine();

		URL url = new URL("https://swapi.dev/api/people/" + personaje + "/?format=json");

		try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
			JsonObject obj = rdr.readObject();
			rdr.close();
			is.close();
			for (String specieUrl : getArrayFromJsonArray(obj.getJsonArray("species"))) {
				try (InputStream isSpecie = new URL(specieUrl.replace("http", "https") + "?format=json").openStream();
						JsonReader rdrSpecie = Json.createReader(isSpecie)) {
					JsonObject species = rdrSpecie.readObject();
					System.out.println("Nombre de la specie: " + species.getString("name"));
					rdrSpecie.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void SalvarPersonajes() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			File archivo = new File("personaje.dat");
			fichero = new FileWriter("personaje.dat");
			pw = new PrintWriter(fichero);
			for (String personaje : personajes.getArrayDePersonajes()) {
				pw.println(personaje);
			}
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static void AnyadirPersonaje() {
		Boolean existe = false;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el código de un personaje: ");
		String codigoPersonaje = teclado.nextLine();
		for (String personaje : personajes.getArrayDePersonajes()) {
			if (personaje.equals(codigoPersonaje)) {
				existe = true;
				break;
			}
		}
		if (!existe) {
			System.out.println("Agregando personaje con codigo: " + codigoPersonaje);
			personajes.getArrayDePersonajes().add(codigoPersonaje);
		} else {
			System.out.println("El personaje " + codigoPersonaje + " ya existe");
		}
	}

	protected static void Conversor() throws MalformedURLException {
		FileWriter fichero = null;
		PrintWriter pw = null;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el código de una película:");
		String pelicula = teclado.nextLine();

		URL url = new URL("https://swapi.dev/api/films/" + pelicula + "/?format=json");

		try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
			JsonObject obj = rdr.readObject();
			Film film = mapearFilm(obj);
			String xml = generateXml(film).toString();
			File archivo = new File("pelicula.xml");
			fichero = new FileWriter("pelicula.xml");
			pw = new PrintWriter(fichero);
			pw.println(xml);
			fichero.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static StringBuilder generateXml(Film film) {

		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		builder.append("<Film>\n");
		builder.append(xmlFromStringValue(film.getTitle(), "title"));
		builder.append(xmlFromStringValue(String.valueOf(film.getEpisode_id()), "episode_id"));
		builder.append(xmlFromStringValue(film.getOpening_crawl(), "opening_crawl"));
		builder.append(xmlFromStringValue(film.getDirector(), "director"));
		builder.append(xmlFromStringValue(film.getProducer(), "producer"));
		builder.append(xmlFromStringValue(String.valueOf(film.getReleaseDate()), "release_date"));
		builder.append(xmlFromArray(film.getCharacters(), "characters"));
		builder.append(xmlFromArray(film.getPlanets(), "planets"));
		builder.append(xmlFromArray(film.getStartships(), "starships"));
		builder.append(xmlFromArray(film.getVehicles(), "vehicles"));
		builder.append(xmlFromArray(film.getSpecies(), "species"));
		builder.append(xmlFromStringValue(film.getCreated(), "created"));
		builder.append(xmlFromStringValue(film.getEdited(), "edited"));
		builder.append(xmlFromStringValue(film.getUrl(), "url"));
		builder.append("</Film>\n");
		return builder;
	}

	private static StringBuilder xmlFromArray(ArrayList<String> array, String xmlElement) {
		StringBuilder builder = new StringBuilder();
		for (String xmlValue : array) {
			builder.append("<" + xmlElement + ">\n");
			builder.append(xmlValue + "\n");
			builder.append("</" + xmlElement + ">\n");
		}
		return builder;
	}

	private static StringBuilder xmlFromStringValue(String xmlValue, String xmlElement) {
		StringBuilder builder = new StringBuilder();
		builder.append("<" + xmlElement + ">\n");
		builder.append(xmlValue + "\n");
		builder.append("</" + xmlElement + ">\n");
		return builder;
	}

	private static Film mapearFilm(JsonObject obj) {
		Film film = new Film();
		film.setTitle(obj.getString("title"));
		film.setEpisode_id(obj.getInt("episode_id"));
		film.setOpening_crawl(obj.getString("opening_crawl"));
		film.setDirector(obj.getString("director"));
		film.setProducer(obj.getString("producer"));
		film.setReleaseDate((Date) obj.getJsonObject("date"));
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

	private static ArrayList<String> getArrayFromJsonArray(JsonArray jsonArray) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < jsonArray.size(); i++) {
			arrayList.add(jsonArray.getString(i));
		}
		return arrayList;

	}
}
