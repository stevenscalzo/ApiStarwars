package com.profesor.EjercicioAPIStarWars;

import com.profesor.EjercicioAPIStarWars.dbConnector.DBConnector;
import com.profesor.EjercicioAPIStarWars.models.*;

import javax.json.JsonObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.*;

import static com.profesor.EjercicioAPIStarWars.helpers.DatabaseTableCreation.*;
import static com.profesor.EjercicioAPIStarWars.helpers.ObtenerArrays.*;

/**
 * Hello world!
 */

public class App {
    public static DBConnector dbConnector = new DBConnector();

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        int opcion;
        Scanner teclado = new Scanner(System.in);

        Connection connect = dbConnector.connect();


        do {
            Menu();

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    LimpiarBaseDeDatos(connect);
//                    CrearFK(connect);
                    CargarDatos("films", connect);
                    CargarDatos("people", connect);
                    CargarDatos("planets", connect);
                    CargarDatos("species", connect);
                    CargarDatos("vehicles", connect);
                    CargarDatos("starships", connect);
                    crearMetodoBuscarPersonaje(connect);
                    crearMetodoBuscarPersonajeSinEspecie(connect);
//                    CrearProcedures(connect);
                    break;
                case 2:
                    anyadirPersonajeStatement(teclado, connect);
                    break;
                case 3:
                    anyadirPersonajePrepareStatement(teclado, connect);
                    break;
                case 4:
                    BusquedaPorNombre(teclado, connect);
                    break;
                case 5:
                    PersonajesSinEspecie(connect);
                    break;
                case 6:
                    System.out.println("Adios...");
                    break;
                default:
                    System.out.println("Opción no valida...");
                    break;
            }

        } while (opcion != 6);

    }

    private static void CrearFK(Connection connect) throws SQLException, IOException {
        ejecutarSqlDesdeArchivo(connect, "DBScripts-FK.sql");
    }
    private static void CrearProcedures(Connection connect) throws SQLException, IOException {
        ejecutarSqlDesdeArchivo(connect, "DBScripts-procedure_buscarPersonaje.sql");
    }
    private static void LimpiarBaseDeDatos(Connection connect) throws SQLException, IOException {
        ejecutarSqlDesdeArchivo(connect, "DBScripts.sql");
    }


    private static void ejecutarSqlDesdeArchivo(Connection connect, String path) throws SQLException, IOException {
        Statement statement = connect.createStatement();
        List<String> readAllLines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        for (String readAllLine : readAllLines) {
            sb.append(readAllLine).append("\n");
        }
        String str = sb.toString();
        statement.executeUpdate(str);
    }

    protected static void CargarDatos(String tabla, Connection connect) throws MalformedURLException, SQLException, ClassNotFoundException {
        URL url = new URL("https://swapi.dev/api/" + tabla + "/?format=json");
        switch (tabla) {
            case "films": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<Film> films = getFilmsArrayList(url);
                setUpFilmsTable(connect, films);
                break;
            }
            case "people": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<People> peopleArrayList = getPeopleArrayList(url);
                setUpPeopleTable(connect, peopleArrayList);
                break;
            }
            case "planets": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<Planets> planetsArrayList = getPlanetsArrayList(url);
                setUpPlanetsTable(connect, planetsArrayList);
                break;
            }
            case "species": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<Species> speciesArrayList = getSpeciesArrayList(url);
                setUpSpeciesTable(connect, speciesArrayList);
                break;
            }
            case "vehicles": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<Vehicles> vehiclesArrayList = getVehiclesArrayList(url);
                setUpVehiclesTable(connect, vehiclesArrayList);
                break;
            }
            case "starships": {
                System.out.println("Creando tabla:" + tabla);
                ArrayList<Starships> starshipsArrayList = getStarshipsArrayList(url);
                setUpStarshipsTable(connect, starshipsArrayList);
                break;
            }
        }
    }

    public static int getSafeOfNull(ArrayList<String> urls) {
        return (urls.isEmpty()) ? 0 : getIdFromUrl(urls.get(0));
    }

    public static int getIdFromUrl(String url) {
        String id = url.replaceAll("\\D+", "");
        return id.equals("") ? 0 : Integer.parseInt(id);
    }

    protected static void PersonajesSinEspecie(Connection connect)
            throws SQLException {

        connect.setAutoCommit(false);
        CallableStatement data = connect.prepareCall("{call buscarPersonajesSinEspecies()}");
        ResultSet resultSet = data.executeQuery();
        while(resultSet.next()){
            System.out.println(
                    "ID =" + resultSet.getString(2) + ", Nombre = " + resultSet.getString(1));
        }

    }

    protected static void BusquedaPorNombre(Scanner teclado, Connection connect)
            throws SQLException {
        String name;
        System.out.println("Bienvenido, indique el nombre del personaje:");
        teclado.nextLine();
        name = teclado.nextLine();
        connect.setAutoCommit(false);
        String SQL = "{call buscarPersonaje('" + name + "')}";
        CallableStatement data = connect.prepareCall(SQL);
        ResultSet resultSet = data.executeQuery();
        while(resultSet.next()){
            System.out.println(
                "ID =" + resultSet.getString(2) + ", Nombre = " + resultSet.getString(1));
        }

    }

    private static void crearMetodoBuscarPersonaje(Connection connect) throws SQLException {
        String sql = "CREATE OR REPLACE FUNCTION buscarPersonaje(personaje varchar)\n" +
                "\n" +
                "RETURNS TABLE(nombre VARCHAR(50), codigo VARCHAR(50)) AS\n" +
                "$BODY$\n" +
                "DECLARE    \n" +
                "\treg RECORD;\n" +
                "BEGIN    \n" +
                "\tFOR REG IN SELECT \"name\", people_id\n" +
                "FROM starwars.people WHERE LOWER(\"name\") LIKE LOWER('%'||personaje||'%')  LOOP      \n" +
                "\t\tcodigo := reg.people_id;\n" +
                "\t\tnombre := reg.name;\n" +
                "\t\tRETURN NEXT;    \n" +
                "\tEND LOOP;    \n" +
                "\tRETURN;\n" +
                "END\n" +
                "$BODY$ LANGUAGE 'plpgsql'";
        Statement statement = connect.createStatement();
        statement.execute(sql);
    }
    private static void crearMetodoBuscarPersonajeSinEspecie(Connection connect) throws SQLException {
        String sql = "CREATE OR REPLACE FUNCTION buscarPersonajesSinEspecies()\n" +
                "\n" +
                "RETURNS TABLE(nombre VARCHAR(50), codigo VARCHAR(50)) AS\n" +
                "$BODY$\n" +
                "DECLARE    \n" +
                "\treg RECORD;\n" +
                "BEGIN    \n" +
                "\tFOR REG IN SELECT \"name\", people_id\n" +
                "FROM starwars.people WHERE species = '{0}'  LOOP      \n" +
                "\t\tcodigo := reg.people_id;\n" +
                "\t\tnombre := reg.name;\n" +
                "\t\tRETURN NEXT;    \n" +
                "\tEND LOOP;    \n" +
                "\tRETURN;\n" +
                "END\n" +
                "$BODY$ LANGUAGE 'plpgsql'";
        Statement statement = connect.createStatement();
        statement.execute(sql);
    }


    protected static void anyadirPersonajeStatement(Scanner teclado, Connection connect)
            throws SQLException {
        People nuevoPersonaje = NuevoPersonaje(teclado);
        Statement statement = connect.createStatement();

        String sentenciaSQL = "INSERT INTO starwars.people VALUES (DEFAULT,'" + nuevoPersonaje.getName() + "','" +
                nuevoPersonaje.getHeight() + "','" + nuevoPersonaje.getMass() + "','" +
                nuevoPersonaje.getHair_color() + "','" + nuevoPersonaje.getSkin_color() + "','" +
                nuevoPersonaje.getBirth_year() + "','" + nuevoPersonaje.getGender() + "'," +
                getIdFromUrl(nuevoPersonaje.getHomeworld()) + "," +
                "ARRAY" + getArrayFromURL(nuevoPersonaje.getFilms()) + "," +
                "ARRAY" + getArrayFromURL(nuevoPersonaje.getSpecies()) + "," +
                "ARRAY" + getArrayFromURL(nuevoPersonaje.getVehicles()) + "," +
                "ARRAY" + getArrayFromURL(nuevoPersonaje.getStarships()) + ",'" +
                nuevoPersonaje.getCreated() + "','" +
                nuevoPersonaje.getEdited() + "','" + nuevoPersonaje.getUrl() + "');\n";

        statement.executeUpdate(sentenciaSQL);
    }

    private static People NuevoPersonaje(Scanner teclado) {
        String gender;
        String hair_color;
        String skin_color;
        String birth_year;
        String speciesRead;
        String eye_color;
        String name;
        String filmsRead;
        String mass;
        String height;
        String homeworld;
        ArrayList<String> films;
        ArrayList<String> species;
        ArrayList<String> vehicles = new ArrayList<>();
        ArrayList<String> starships = new ArrayList<>();

        People nuevoPersonaje = new People();

        System.out.println("Bienvenido, indique los datos del personaje:");
        teclado.nextLine();
        System.out.println("Nombre: ");
        name = teclado.nextLine();
        System.out.println("Color de cabello: ");
        hair_color = teclado.nextLine();
        System.out.println("Color de piel: ");
        skin_color = teclado.nextLine();
        System.out.println("Color de ojos: ");
        eye_color = teclado.nextLine();
        System.out.println("Fecha de nacimiento: ");
        birth_year = teclado.nextLine();
        System.out.println("Genero: ");
        gender = teclado.nextLine();
        System.out.println("Altura: ");
        height = teclado.nextLine();
        System.out.println("Peso: ");
        mass = teclado.nextLine();
        System.out.println("Hogar: ");
        homeworld = teclado.nextLine();
        System.out.println("Peliculas en las que aparece (separadas por ';'):");
        filmsRead = teclado.nextLine();
        System.out.println("Especies (Separadas por ';'):");
        speciesRead = teclado.nextLine();

        films = new ArrayList<>(Arrays.asList(filmsRead.split(";")));
        species = new ArrayList<>(Arrays.asList(speciesRead.split(";")));

        nuevoPersonaje.setName(name);
        nuevoPersonaje.setHair_color(hair_color);
        nuevoPersonaje.setSkin_color(skin_color);
        nuevoPersonaje.setEye_color(eye_color);
        nuevoPersonaje.setGender(gender);
        nuevoPersonaje.setHeight(height);
        nuevoPersonaje.setMass(mass);
        nuevoPersonaje.setBirth_year(birth_year);
        nuevoPersonaje.setFilms(films);
        nuevoPersonaje.setSpecies(species);
        nuevoPersonaje.setStarships(starships);
        nuevoPersonaje.setVehicles(vehicles);
        nuevoPersonaje.setHomeworld(homeworld);
        nuevoPersonaje.setCreated(String.valueOf(new Date()));
        nuevoPersonaje.setEdited(String.valueOf(new Date()));
        nuevoPersonaje.setUrl("88");

        return nuevoPersonaje;
    }

    protected static void anyadirPersonajePrepareStatement(Scanner teclado, Connection connect)
            throws SQLException {
        People nuevoPersonaje = NuevoPersonaje(teclado);

        String query = "INSERT INTO starwars.people(name, height, mass, hair_color, skin_color, " +
                "birth_year, gender, homeworld, film, species, vehicles, starships, " +
                "created, updated, url) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query);

        Array films = connect.createArrayOf("int", nuevoPersonaje.getFilms().toArray());
        Array species = connect.createArrayOf("int", nuevoPersonaje.getSpecies().toArray());
        Array vehicles = connect.createArrayOf("int", nuevoPersonaje.getVehicles().toArray());
        Array starships = connect.createArrayOf("int", nuevoPersonaje.getStarships().toArray());

        preparedStatement.setString(1, nuevoPersonaje.getName());
        preparedStatement.setString(2, nuevoPersonaje.getHeight());
        preparedStatement.setString(3, nuevoPersonaje.getMass());
        preparedStatement.setString(4, nuevoPersonaje.getHair_color());
        preparedStatement.setString(5, nuevoPersonaje.getSkin_color());
        // preparedStatement.setString(6, nuevoPersonaje.getEye_color());
        preparedStatement.setString(6, nuevoPersonaje.getBirth_year());
        preparedStatement.setString(7, nuevoPersonaje.getGender());
        preparedStatement.setInt(8, Integer.parseInt(nuevoPersonaje.getHomeworld()));
        preparedStatement.setArray(9, films);
        preparedStatement.setArray(10, species);
        preparedStatement.setArray(11, vehicles);
        preparedStatement.setArray(12, starships);
        preparedStatement.setString(13, nuevoPersonaje.getCreated());
        preparedStatement.setString(14, nuevoPersonaje.getEdited());
        preparedStatement.setString(15, nuevoPersonaje.getUrl());
        preparedStatement.executeUpdate();
    }

    protected static void Menu() {
        System.out.println("Bienvenido, indique una opción:");
        System.out.println("1. Resetear Base de datos");
        System.out.println("2. Añadir personaje Statement");
        System.out.println("3. Añadir personaje PreparedStatement");
        System.out.println("4. Búsqueda por nombre");
        System.out.println("5. Personajes sin especie");
        System.out.println("6. Salir");
    }

    public static String setNextUrl(String nextUrl, JsonObject objNext) {
        try {
            nextUrl = objNext.getString("next");
        } catch (ClassCastException ex) {
            nextUrl = "null";
        }
        return nextUrl;
    }

}
