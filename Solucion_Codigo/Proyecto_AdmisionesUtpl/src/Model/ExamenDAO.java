package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamenDAO {
    private Connection conexion;

    public ExamenDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void cargarExamenes(PostulanteDAO postulanteDAO, CarreraDAO carreraDAO) {
        String sql = "SELECT * FROM Examen";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String cedula = rs.getString("cedulaPostulante");
                Postulante post = postulanteDAO.buscarPostulante(cedula);
                
                
                if (!(post instanceof Postulante_Presencial)) {
                    continue; // los virtuales no tienen examen, se ignora la fila
                }
                Postulante_Presencial postPres = (Postulante_Presencial) post;

                Carrera carrera = carreraDAO.buscarCarrera(rs.getString("nombreCarrera"), "PRESENCIAL"); //Siempre es para carreras presenciales
                Horario horario = new Horario(
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("lugar"),
                    rs.getString("aula")
                );
                double puntaje = rs.getDouble("puntajeObtenido");
                String tipoExamen = rs.getString("tipoExamen");

                Examen exam;
                if (tipoExamen.equalsIgnoreCase("ADMISION")) {
                    exam = new Examen_Admision(carrera, horario, puntaje);
                } else {
                    exam = new Examen_Diagnostico(carrera, horario, puntaje);
                }

                int numeroOpcion = rs.getInt("numeroOpcion");
                if (numeroOpcion == 1) {
                    postPres.setExamenOpc1(exam);
                } else {
                    postPres.setExamenOpc2(exam);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer examenes: " + e.getMessage());
        }
    }
}
