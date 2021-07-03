package com.profesor.gestiondeStarwars.dbHelpers;

import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import static com.profesor.gestiondeStarwars.App.session;
import static com.profesor.gestiondeStarwars.App.teclado;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.getEntityName;
import static com.profesor.gestiondeStarwars.dbHelpers.FuncionesDeAyuda.volverPrimeraLetraMayuscula;


/**
 * Clase que borra elementos
 *
 * @author: Steven Scalzo
 */
public class Borrado {
    public static void borrarContenido(int codigo, String tabla) {
        Transaction transaction = null;
        try {
            teclado.nextLine();
            transaction = session.beginTransaction();
            tabla = volverPrimeraLetraMayuscula(tabla);
            Query entity = session.createQuery("FROM " + tabla + "Entity tabla WHERE id = " + codigo);

            System.out.println("¿Realmente desea borrar el elemento de la tabla \"" + getEntityName(entity.getSingleResult()) + "\"? S/N");
            String respuesta = teclado.nextLine().toLowerCase();

            if ((respuesta.toLowerCase().equals("s"))) {
                session.delete(entity.getSingleResult());
                transaction.commit();
                System.out.println("Elemento borrado de la tabla " + tabla);
            } else {
                System.out.println("No se ha borrado el elemento");
            }
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("No se ha podido borrar el elemento de id " + codigo +
                    " de la tabla " + tabla + " ya que este mismo esta referenciado a otras tablas");
            e.printStackTrace();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("No se ha podido borrar el elemento");
            e.printStackTrace();
        }
    }
}
