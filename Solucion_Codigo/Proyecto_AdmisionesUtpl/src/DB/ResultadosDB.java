package DB;

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
}
