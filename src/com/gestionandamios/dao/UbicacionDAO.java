package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Ubicacion;
import java.sql.*;
import java.util.*;

public class UbicacionDAO {

    public List<Ubicacion> listar() {
        List<Ubicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubicaciones";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Ubicacion u = new Ubicacion();
                u.setIdUbicacion(rs.getInt("id_ubicacion"));
                u.setCliente(rs.getString("cliente"));
                u.setDireccion(rs.getString("direccion"));
                u.setEstado(rs.getString("estado"));
                u.setFechaInicio(rs.getDate("fecha_inicio"));
                u.setFechaFin(rs.getDate("fecha_fin"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error ubicaciones: " + e);
        }
        return lista;
    }
}
