package com.gestionandamios.dao;

// Importaciones necesarias para manejar SQL y tu modelo de datos
import com.gestionandamios.modelo.SeccionAndamio; 
import com.gestionandamios.conexion.ConexionDB; 
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Data Access Object (DAO) para la entidad SeccionAndamio.
 * Contiene todos los métodos CRUD (Crear, Leer, Actualizar, Eliminar) 
 * para interactuar con la tabla 'secciones_andamio' en la base de datos.
 */
public class SeccionesAndamioDAO {

    /**
     * Obtiene una conexión a la base de datos. 
     * Asumimos que tu clase de conexión se llama ConexionDB y tiene un método conectar().
     */
    private Connection getConnection() throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SeccionesAndamioDAO.class.getName()).log(Level.SEVERE, "No se encontró el Driver de MySQL", ex);
        }
        // Llamada a tu clase de conexión:
        return new ConexionDB().conectar(); 
    }

    // =========================================================================
    // 1. CREAR (C) - Inserta una nueva sección de andamio en la BD.
    // =========================================================================
    public boolean agregarSeccion(SeccionAndamio seccion) {
        String sql = "INSERT INTO secciones_andamio (codigoSeccion, tipo, alturaMetros, estado, ubicacion, precio) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna los valores del objeto a los '?' del SQL
            ps.setString(1, seccion.getCodigoSeccion());
            ps.setString(2, seccion.getTipo());
            ps.setDouble(3, seccion.getAlturaMetros());
            ps.setString(4, seccion.getEstado());
            ps.setString(5, seccion.getUbicacion());
            ps.setDouble(6, seccion.getPrecio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            Logger.getLogger(SeccionesAndamioDAO.class.getName()).log(Level.SEVERE, "Error SQL al agregar sección", e);
            return false;
        }
    }

    // =========================================================================
    // 2. LEER (R) - Obtiene todas las secciones de andamio de la BD. (Consultar)
    // =========================================================================
    public List<SeccionAndamio> obtenerTodasLasSecciones() {
        List<SeccionAndamio> secciones = new ArrayList<>();
        String sql = "SELECT idSeccion, codigoSeccion, tipo, alturaMetros, estado, ubicacion, precio FROM secciones_andamio";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { 

            while (rs.next()) { 
                SeccionAndamio seccion = new SeccionAndamio();
                // Mapea las columnas de la BD al objeto Java
                seccion.setIdSeccion(rs.getInt("idSeccion"));
                seccion.setCodigoSeccion(rs.getString("codigoSeccion"));
                seccion.setTipo(rs.getString("tipo"));
                seccion.setAlturaMetros(rs.getDouble("alturaMetros"));
                seccion.setEstado(rs.getString("estado"));
                seccion.setUbicacion(rs.getString("ubicacion"));
                seccion.setPrecio(rs.getDouble("precio"));
                secciones.add(seccion);
            }
        } catch (SQLException e) {
            Logger.getLogger(SeccionesAndamioDAO.class.getName()).log(Level.SEVERE, "Error SQL al obtener secciones", e);
        }
        return secciones;
    }
    
    // =========================================================================
    // 3. ACTUALIZAR (U) - Modifica una sección existente. (Editar)
    // =========================================================================
    public boolean actualizarSeccion(SeccionAndamio seccion) {
        String sql = "UPDATE secciones_andamio SET codigoSeccion=?, tipo=?, alturaMetros=?, estado=?, ubicacion=?, precio=? WHERE idSeccion=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Mapea los nuevos datos
            ps.setString(1, seccion.getCodigoSeccion());
            ps.setString(2, seccion.getTipo());
            ps.setDouble(3, seccion.getAlturaMetros());
            ps.setString(4, seccion.getEstado());
            ps.setString(5, seccion.getUbicacion());
            ps.setDouble(6, seccion.getPrecio());
            ps.setInt(7, seccion.getIdSeccion()); // ID para el WHERE

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            Logger.getLogger(SeccionesAndamioDAO.class.getName()).log(Level.SEVERE, "Error SQL al actualizar sección", e);
            return false;
        }
    }

    // =========================================================================
    // 4. ELIMINAR (D) - Elimina una sección por su ID. (Eliminar)
    // =========================================================================
    public boolean eliminarSeccion(int idSeccion) {
        String sql = "DELETE FROM secciones_andamio WHERE idSeccion = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSeccion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            Logger.getLogger(SeccionesAndamioDAO.class.getName()).log(Level.SEVERE, "Error SQL al eliminar sección", e);
            return false;
        }
    }
}