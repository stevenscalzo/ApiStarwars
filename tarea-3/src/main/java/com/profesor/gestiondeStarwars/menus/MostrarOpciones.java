package com.profesor.gestiondeStarwars.menus;

import static com.profesor.gestiondeStarwars.dbHelpers.Busqueda.buscarContenido;

/**
 * Clase que muestra las opciones de los distintos menús
 *
 * @author: Steven Scalzo
 */
public class MostrarOpciones {

    public static void mostrarOpcionesMenuPrincipal() {
        System.out.println("Bienvenido, indique una opción:");
        System.out.println("1. Gestión de people");
        System.out.println("2. Gestión de planets");
        System.out.println("3. Gestión de species");
        System.out.println("4. Gestión de films");
        System.out.println("5. Gestión de starships");
        System.out.println("6. Gestión de vehicles");
        System.out.println("0. Salir");
    }

    public static void mostrarOpcionesMenuTabla(String tabla) {
        System.out.println("1. Consultas");
        System.out.println("2. Insertar " + tabla);
        System.out.println("3. Modificar " + tabla);
        System.out.println("4. Borrar " + tabla);
        System.out.println("0. Salir");
    }

    public static void mostrarMenuConsulta(String tabla) {
        System.out.println("1. Buscar " + tabla + " por name");
        if (tabla.equals("people")) {
            System.out.println("2. Buscar " + tabla + " sin especie");
            System.out.println("3. Buscar " + tabla + " en mas peliculas");
            System.out.println("4. Buscar color de pelo mas repetido");
        }
        System.out.println("0. Salir");
    }

    public static void mostrarContenidoTabla(String tabla) {
        buscarContenido(tabla);
        System.out.println("0. Salir");
    }
}
