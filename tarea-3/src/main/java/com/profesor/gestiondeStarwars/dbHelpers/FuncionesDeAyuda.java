package com.profesor.gestiondeStarwars.dbHelpers;

import com.profesor.gestiondeStarwars.hibernate.PlanetsEntity;
import org.hibernate.query.Query;

import java.util.List;

import static com.profesor.gestiondeStarwars.App.session;
import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.Imprimir.imprimirDatosDeTablaPlanets;

/**
 * Clase que proporciona funciones de ayuda
 *
 * @author: Steven Scalzo
 */
public class FuncionesDeAyuda {

    static int obtenerUltimoCodigoEnLaTabla(String tabla) {
        int maxCodigo = (int) session.createSQLQuery(
                "SELECT " + tabla +
                        ".codigo FROM public." + tabla +
                        " order by codigo desc")
                .getResultList().get(0);
        return ++maxCodigo;
    }

    static String getEntityName(Object entity) {
        return entity.getClass().toString().split("\\.")[4].replace("Entity", "");
    }

    static int obtenerHomeworldId() {
        int homeworldId = 0;
        Query planets = obtenerTodosLosRegistrosDeLaTabla("planets");
        List<PlanetsEntity> planetsEntityList = planets.list();
        while (homeworldId == 0) {
            try {
                System.out.println("Homeworld (Seleccione el id de uno de los siguientes): ");
                imprimirDatosDeTablaPlanets(planetsEntityList);
                homeworldId = teclado.nextInt();
                if (!(homeworldId > 0 && homeworldId <= planetsEntityList.size())) {
                    System.out.println("El id seleccionado no es validado, porfavor introduzca un id valido \n");
                    homeworldId = 0;
                }
            } catch (Exception e) {
                System.out.println("Ha insertado un valor no valido, por favor inserte un numero");
                teclado.nextLine();
            }
        }
        return homeworldId;
    }


    public static Query obtenerTodosLosRegistrosDeLaTabla(String tabla) {
        tabla = volverPrimeraLetraMayuscula(tabla);
        return session.createQuery("FROM " + tabla + "Entity");
    }

    public static String volverPrimeraLetraMayuscula(String tabla) {
        tabla = tabla.substring(0, 1).toUpperCase() + tabla.substring(1);
        return tabla;
    }

}
