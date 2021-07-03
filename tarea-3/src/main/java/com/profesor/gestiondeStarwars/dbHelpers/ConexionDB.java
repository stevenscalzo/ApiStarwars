package com.profesor.gestiondeStarwars.dbHelpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase que realiza la conexión con la base de datos
 *
 * @author: Steven Scalzo
 */
public class ConexionDB {
    public static Session session;

    public static Session crearSessionDeDB() {
        if (session == null) {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Estableciendo conexion a la base de datos...");
            if (session == null) {
                System.out.println("Error en la conexion con la base de datos");
            } else {
                System.out.println("Conexion establecida con la base de datos");
            }
        }
        return session;
    }
}
