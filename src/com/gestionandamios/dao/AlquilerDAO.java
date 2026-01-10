package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Alquiler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO {

    // INSERTAR
    public boolean insertar(Alquiler a) {

        String sql = "INSERT INTO alquileres (id_cliente, fecha_inicio, fecha_fin_estimada, fecha_fin_real, costo_total) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdCliente());
            ps.setDate(2, a.getFechaInicio());
            ps.setDate(3, a.getFechaFinEstimada());
            ps.setDate(4, a.getFechaFinReal());
            ps.setDouble(5, a.getCostoTotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar alquiler");
            return false;
        }
    }

    // LISTAR
    public List<Alquiler> listar() {

        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM alquileres";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Alquiler a = new Alquiler();

                a.setIdAlquiler(rs.getInt("id_alquiler"));
                a.setIdCliente(rs.getInt("id_cliente"));
                a.setFechaInicio(rs.getDate("fecha_inicio"));
                a.setFechaFinEstimada(rs.getDate("fecha_fin_estimada"));
                a.setFechaFinReal(rs.getDate("fecha_fin_real"));
                a.setCostoTotal(rs.getDouble("costo_total"));

                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar alquileres");
        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(Alquiler a) {

        String sql = "UPDATE alquileres SET id_cliente=?, fecha_inicio=?, fecha_fin_estimada=?, fecha_fin_real=?, costo_total=? " +
                     "WHERE id_alquiler=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdCliente());
            ps.setDate(2, a.getFechaInicio());
            ps.setDate(3, a.getFechaFinEstimada());
            ps.setDate(4, a.getFechaFinReal());
            ps.setDouble(5, a.getCostoTotal());
            ps.setInt(6, a.getIdAlquiler());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar alquiler");
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int idAlquiler) {

        String sql = "DELETE FROM alquileres WHERE id_alquiler=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlquiler);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar alquiler");
            return false;
        }
    }
}
