package DB;

import Model.IndicadorMerito;
import Model.Postulante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultadosDB {
    private Connection conexion;

    public ResultadosDB(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardarResultados(ArrayList<Postulante> postulantes) {
        String sql = "INSERT INTO Resultados (cedula, nombre, opcion1, estadoOpcion1, opcion2, estadoOpcion2) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Postulante post : postulantes) {
                ps.setString(1, post.getCedula());
                ps.setString(2, post.getNombre());
                ps.setString(3, post.getOpcion1());
                ps.setString(4, post.getEstadoOpc1());
                ps.setString(5, post.getOpcion2());
                ps.setString(6, post.getEstadoOpc2());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar resultados: " + e.getMessage());
        }
    }

    public void guardarMeritos(ArrayList<Postulante> postulantes) {
        String sql = "INSERT INTO IndicadorMerito (cedulaPostulante, tipo, descripcion, puntajeAdicional) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (Postulante post : postulantes) {
                for (IndicadorMerito merito : post.getMeritos()) {
                    ps.setString(1, post.getCedula());
                    ps.setString(2, merito.getTipo());
                    ps.setString(3, merito.getDescripcion());
                    ps.setInt(4, merito.getPuntajeAdicional());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar meritos: " + e.getMessage());
        }
    }
}
