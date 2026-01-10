package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.DetalleAlquiler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleAlquilerDAO {

    // INSERTAR
    public boolean insertar(DetalleAlquiler d) {

        String sql = "INSERT INTO detalle_alquiler (id_alquiler, id_seccion, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getIdAlquiler());
            ps.setInt(2, d.getIdSeccion());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecioUnitario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar detalle de alquiler");
            return false;
        }
    }

    // LISTAR
    public List<DetalleAlquiler> listar() {

        List<DetalleAlquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_alquiler";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DetalleAlquiler d = new DetalleAlquiler();

                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdAlquiler(rs.getInt("id_alquiler"));
                d.setIdSeccion(rs.getInt("id_seccion"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));

                lista.add(d);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar detalles de alquiler");
        }

        return lista;
    }

    // ELIMINAR
    public boolean eliminar(int idDetalle) {

        String sql = "DELETE FROM detalle_alquiler WHERE id_detalle=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetalle);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar detalle");
            return false;
        }
    }
}
