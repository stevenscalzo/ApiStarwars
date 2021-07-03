package com.profesor.EjercicioAPIStarWars.dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public DBConnector() {
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/";
        String usuario = "postgres";
        String password = "admin";
        return DriverManager.getConnection(url, usuario, password);
    }
}
