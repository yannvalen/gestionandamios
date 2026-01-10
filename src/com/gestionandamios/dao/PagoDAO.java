package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Pago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    // INSERTAR
    public void insertar(Pago pago) {

        String sql = "INSERT INTO pagos (id_alquiler, monto, fecha_pago, metodo_pago) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdAlquiler());
            ps.setDouble(2, pago.getMonto());
            ps.setDate(3, pago.getFechaPago());
            ps.setString(4, pago.getMetodoPago());

            ps.executeUpdate();
            System.out.println("✔ Pago registrado correctamente");

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar pago: " + e.getMessage());
        }
    }

    // LISTAR
    public List<Pago> listar() {

        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagos";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pago p = new Pago();
                p.setIdPago(rs.getInt("id_pago"));
                p.setIdAlquiler(rs.getInt("id_alquiler"));
                p.setMonto(rs.getDouble("monto"));
                p.setFechaPago(rs.getDate("fecha_pago"));
                p.setMetodoPago(rs.getString("metodo_pago"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pagos: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR
    public void actualizar(Pago pago) {

        String sql = """
            UPDATE pagos 
            SET id_alquiler=?, monto=?, fecha_pago=?, metodo_pago=? 
            WHERE id_pago=?
        """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdAlquiler());
            ps.setDouble(2, pago.getMonto());
            ps.setDate(3, pago.getFechaPago());
            ps.setString(4, pago.getMetodoPago());
            ps.setInt(5, pago.getIdPago());

            if (ps.executeUpdate() > 0) {
                System.out.println("✔ Pago actualizado correctamente");
            } else {
                System.out.println("⚠ No se encontró el pago");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar pago: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int idPago) {

        String sql = "DELETE FROM pagos WHERE id_pago=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPago);

            if (ps.executeUpdate() > 0) {
                System.out.println("✔ Pago eliminado correctamente");
            } else {
                System.out.println("⚠ No se encontró el pago");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar pago: " + e.getMessage());
        }
    }
}
