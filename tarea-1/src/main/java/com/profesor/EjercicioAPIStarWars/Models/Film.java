package com.profesor.EjercicioAPIStarWars.Models;

import java.util.ArrayList;
import java.util.Date;

public class Film {

	private String title;
	private int episode_id;
	private String opening_crawl;
	private String director;
	private String producer;
	private Date releaseDate;
	private ArrayList<String> species;
	private ArrayList<String> starships;
	private ArrayList<String> vehicles;
	private ArrayList<String> characters;
	private ArrayList<String> planets;
	private String url;
	private String created;
	private String edited;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getEpisode_id() {
		return episode_id;
	}
	public void setEpisode_id(int episode_id) {
		this.episode_id = episode_id;
	}
	public String getOpening_crawl() {
		return opening_crawl;
	}
	public void setOpening_crawl(String opening_crawl) {
		this.opening_crawl = opening_crawl;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public ArrayList<String> getSpecies() {
		return species;
	}
	public void setSpecies(ArrayList<String> species) {
		this.species = species;
	}
	public ArrayList<String> getStartships() {
		return starships;
	}
	public void setStarships(ArrayList<String> startships) {
		this.starships = startships;
	}
	public ArrayList<String> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<String> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<String> getCharacters() {
		return characters;
	}
	public void setCharacters(ArrayList<String> characters) {
		this.characters = characters;
	}
	public ArrayList<String> getPlanets() {
		return planets;
	}
	public void setPlanets(ArrayList<String> planets) {
		this.planets = planets;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}

}
