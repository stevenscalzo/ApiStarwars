package com.profesor.gestiondeStarwars.menus;

import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.Borrado.borrarContenido;
import static com.profesor.gestiondeStarwars.dbHelpers.Edicion.editarContenido;
import static com.profesor.gestiondeStarwars.menus.MostrarOpciones.*;
import static com.profesor.gestiondeStarwars.menus.SeleccionarOpciones.*;

/**
 * Clase que gestiona los distintos menús
 *
 * @author: Steven Scalzo
 */
public class Menus {

    public static void menuPrincipal() {
        int opcion;
        do {
            mostrarOpcionesMenuPrincipal();
            opcion = teclado.nextInt();
            seleccionarOpcionMenuPrincipal(opcion);

        } while (opcion != 0);
    }

    public static void menuTabla(String tabla) {
        int opcion;
        do {
            mostrarOpcionesMenuTabla(tabla);
            opcion = teclado.nextInt();
            seleccionarOpcionesMenuTabla(opcion, tabla);

        } while (opcion != 0);
    }

    public static void menuConsulta(String tabla) {
        int opcion;
        do {
            mostrarMenuConsulta(tabla);
            opcion = teclado.nextInt();
            seleccionarOpcionesMenuConsulta(opcion, tabla);

        } while (opcion != 0);
    }

    public static void menuBorrar(String tabla) {
        int codigo;
        do {
            mostrarContenidoTabla(tabla);
            codigo = teclado.nextInt();
            if (codigo != 0) {
                borrarContenido(codigo, tabla);
            }
        } while (codigo != 0);
    }

    public static void menuModificar(String tabla) {
        int codigo;
        do {
            mostrarContenidoTabla(tabla);
            codigo = teclado.nextInt();
            if (codigo != 0) {
                editarContenido(codigo, tabla);
            }
        } while (codigo != 0);
    }
}
