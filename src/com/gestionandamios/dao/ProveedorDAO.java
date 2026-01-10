package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores(nombre, telefono, direccion, correo) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getCorreo());

            ps.executeUpdate();
            System.out.println("✔ Proveedor registrado correctamente");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar proveedor: " + e.getMessage());
        }
    }

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre=?, telefono=?, direccion=?, correo=? WHERE id_proveedor=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getCorreo());
            ps.setInt(5, proveedor.getIdProveedor());

            ps.executeUpdate();
            System.out.println("✔ Proveedor actualizado");

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminar(int idProveedor) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            ps.executeUpdate();
            System.out.println("✔ Proveedor eliminado");

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar proveedor: " + e.getMessage());
        }
    }
}
