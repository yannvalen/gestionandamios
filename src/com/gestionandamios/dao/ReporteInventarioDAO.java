package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.ReporteInventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteInventarioDAO {

    public List<ReporteInventario> listar() {

        List<ReporteInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte_inventario";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ReporteInventario r = new ReporteInventario();

                r.setIdAndamio(rs.getInt("id_andamio"));
                r.setTipo(rs.getString("tipo"));
                r.setEstado(rs.getString("estado"));
                r.setUbicacion(rs.getString("ubicacion"));
                r.setFechaMantenimiento(rs.getDate("fecha_mantenimiento"));
                r.setDisponible(rs.getBoolean("disponible"));

                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("Error al listar inventario");
        }

        return lista;
    }
}
