package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/"; // FALTA AÑADIR EL NOMBRE DE BASE DE DATOS
    private static final String DB_USUARIO = ""; // CREO QUE ES ROOT POR DEFECTO PERO LO QUE PONDRA JAVI
    private static final String DB_CONTRASENIA = ""; // CONTRASEÑA DE BASE DATOS

    private static Connection connection = null; // PARA TENER UNA UNICA CONEXION (MODO CONTROL)

    private DatabaseConnection() {
        // SE HACE UN CONSTRUCTOR VACIO PARA EVITAR LA ISTANCIA DIRECTA
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {  // VERIFICA SI NO SE ESTABLECIO Y ESTA CERRADA LA CONEXION
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // cARGA DRIVER DE MYSQL
                connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_CONTRASENIA); // SE ESTABLECE CONEXION
                System.out.println("Conexión a app Carlos haya establecida.");
            } catch (ClassNotFoundException e) {
                System.err.println("No se encontró el driver de MySQL.");
                throw new SQLException("Error al conectar a la base de datos");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close(); // SE APAGA LA CONEXION
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: ");
            }
        }
    }
}