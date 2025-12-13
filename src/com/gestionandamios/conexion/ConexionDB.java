package com.gestionandamios.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para manejar la conexión a la base de datos MySQL.
 */
public class ConexionDB {

   
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_formativo";
    private static final String USER = "root";       
    private static final String PASS = "Yann1s$VaLent1n@"; 

    /**
     * Establece la conexión con la base de datos MySQL.
     * @return Objeto Connection si la conexión es exitosa, o null si falla.
     */
    public Connection conectar() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, 
                    "Error al conectar con la BD. Revise la URL, USER, y PASS.", e);
        }
        return conn;
    }

    /**
     * Cierra la conexión.
     */
    public void cerrar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión.", e);
            }
        }
    }
}