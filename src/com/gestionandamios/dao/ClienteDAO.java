package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la gestión de Clientes.
 * Cumple con los estándares de nombramiento y operaciones CRUD.
 */
public class ClienteDAO {

    // Se recomienda usar nombres de columnas sin caracteres especiales (ñ o tildes)
    private static final String INSERT_SQL = "INSERT INTO clientes (nombre, apellido, cedula, telefono, direccion, correo_electronico, fecha_nacimiento, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM clientes";
    private static final String UPDATE_SQL = "UPDATE clientes SET nombre=?, apellido=?, cedula=?, telefono=?, direccion=?, correo_electronico=?, fecha_nacimiento=?, contrasena=? WHERE id_cliente=?";
    private static final String DELETE_SQL = "DELETE FROM clientes WHERE id_cliente=?";

    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param c Objeto Cliente con los datos a insertar.
     */
    public void insertar(Cliente c) {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getCedula());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCorreoElectronico());
            ps.setDate(7, c.getFechaNacimiento());
            ps.setString(8, c.getContrasena());

            ps.executeUpdate();
            System.out.println("✅ Cliente insertado correctamente");

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista completa de clientes.
     * @return List de objetos Cliente.
     */
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCedula(rs.getString("cedula"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreoElectronico(rs.getString("correo_electronico"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setContrasena(rs.getString("contrasena"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza los datos de un cliente existente.
     * @param c Objeto Cliente con datos actualizados.
     * @return true si se actualizó, false en caso contrario.
     */
    public boolean actualizar(Cliente c) {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getCedula());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCorreoElectronico());
            ps.setDate(7, c.getFechaNacimiento());
            ps.setString(8, c.getContrasena());
            ps.setInt(9, c.getIdCliente());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un cliente por su ID.
     * @param idCliente ID del cliente.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean eliminar(int idCliente) {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}