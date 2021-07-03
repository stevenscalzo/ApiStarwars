package com.profesor.gestiondeStarwars;

import org.hibernate.Session;

import java.util.Scanner;

import static com.profesor.gestiondeStarwars.dbHelpers.ConexionDB.crearSessionDeDB;
import static com.profesor.gestiondeStarwars.menus.Menus.menuPrincipal;

/**
 * Clase App, contiene el main y se encarga de llamar al menú principal al ejecutar el programa
 * @author: Steven Scalzo
 */

public class App {
    public static Scanner teclado = new Scanner(System.in);
    public static Session session = crearSessionDeDB();;

    public static void main(String[] args) {
        menuPrincipal();
    }

}