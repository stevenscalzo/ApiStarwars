package com.profesor.EjercicioAPIStarWars.Models;

import java.util.ArrayList;

public class Personaje {

	public ArrayList<String> arrayDePersonajes;

	
	public Personaje() {
		arrayDePersonajes = new ArrayList<String>();
	}

	public ArrayList<String> getArrayDePersonajes() {
		return arrayDePersonajes;
	}

	public void setArrayDePersonajes(ArrayList<String> arrayDePersonajes) {
		this.arrayDePersonajes = arrayDePersonajes;
	}
	
	
}
