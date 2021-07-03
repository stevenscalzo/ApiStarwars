package com.profesor.gestiondeStarwars.menus;

import static com.profesor.gestiondeStarwars.dbHelpers.Busqueda.buscarPeopleSinSpecie;
import static com.profesor.gestiondeStarwars.dbHelpers.Busqueda.buscarPeopleMasPeliculas;
import static com.profesor.gestiondeStarwars.dbHelpers.Busqueda.buscarColorPeloMasRepetido;
import static com.profesor.gestiondeStarwars.dbHelpers.Busqueda.buscarPorNombre;
import static com.profesor.gestiondeStarwars.dbHelpers.Insertar.insertarEnTabla;
import static com.profesor.gestiondeStarwars.menus.Menus.*;

/**
 * Clase que procesa la opción del cliente y llama a la función correspondiente
 *
 * @author: Steven Scalzo
 */

public class SeleccionarOpciones {

    public static void seleccionarOpcionMenuPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                menuTabla("people");
                break;
            case 2:
                menuTabla("planets");
                break;
            case 3:
                menuTabla("species");
                break;
            case 4:
                menuTabla("films");
                break;
            case 5:
                menuTabla("starships");
                break;
            case 6:
                menuTabla("vehicles");
                break;
            case 0:
                System.out.println("Adios...");
                break;
            default:
                System.out.println("Opción no valida...");
                break;
        }
    }

    public static void seleccionarOpcionesMenuTabla(int opcion, String tabla) {
        switch (opcion) {
            case 1:
                menuConsulta(tabla);
                break;
            case 2:
                insertarEnTabla(tabla);
                break;
            case 3:
                menuModificar(tabla);
                break;
            case 4:
                menuBorrar(tabla);
                break;
            case 0:
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("Opción no valida...");
                break;
        }
    }

    public static void seleccionarOpcionesMenuConsulta(int opcion, String tabla) {
        try {
            switch (opcion) {
                case 1:
                    buscarPorNombre(tabla);
                    break;
                case 2:
                    buscarPeopleSinSpecie();
                    break;
                case 3:
                    buscarPeopleMasPeliculas();
                    break;
                case 4:
                    buscarColorPeloMasRepetido();
                    break;
                case 0:
                    System.out.println("Volviendo al menu de la tabla " + tabla);
                    break;
                default:
                    System.out.println("Opción no valida...");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error intente de nuevo");
        }
    }
}
