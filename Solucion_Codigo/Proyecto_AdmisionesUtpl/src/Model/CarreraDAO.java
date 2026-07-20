package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarreraDAO {
    private Connection conexion;
    private ArrayList<Carrera> carreras;

    public CarreraDAO(Connection conexion) {
        this.conexion = conexion;
        this.carreras = new ArrayList<Carrera>();
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public ArrayList<Carrera> leerCarreras() {
        carreras.clear();
        String sql = "SELECT * FROM Carrera";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Carrera carr;
                String modalidad = rs.getString("modalidad");

                if (modalidad.equalsIgnoreCase("VIRTUAL")) {
                    carr = new Carrera_Online(rs.getString("nombre"));
                } else {
                    carr = new Carrera_Presencial(
                        rs.getString("nombre"),
                        rs.getInt("cuposDisponibles"),
                        rs.getInt("puntajeMinimo"),
                        rs.getInt("limiteNivelacion")
                    );
                }
                carreras.add(carr);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer carreras: " + e.getMessage());
        }
        return carreras;
    }

    public Carrera buscarCarrera(String nombre, String modalidad) {
        //Busca tambien de acuerdo a la modalidad
        for (Carrera c : carreras) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getModalidad().equalsIgnoreCase(modalidad)) {
                return c;
            }
        }
        return null;
    }
}
