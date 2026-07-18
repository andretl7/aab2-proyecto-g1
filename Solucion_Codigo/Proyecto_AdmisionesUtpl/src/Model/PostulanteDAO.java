package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostulanteDAO {
    private Connection conexion;
    private ArrayList<Postulante> postulantes;

    public PostulanteDAO(Connection conexion) {
        this.conexion = conexion;
        this.postulantes = new ArrayList<Postulante>();
    }

    public ArrayList<Postulante> getPostulantes() {
        return postulantes;
    }

    
    public ArrayList<Postulante> leerPostulantes() {
        String sql = "SELECT * FROM Postulante";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Postulante post;
                String modalidad = rs.getString("modalidad");

                if (modalidad.equalsIgnoreCase("VIRTUAL")) {
                    post = new Postulante_Online(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("opcion1"),
                        rs.getString("opcion2"),
                        rs.getInt("esAbanderado") == 1,
                        rs.getInt("bachilleratoAfin") == 1,
                        rs.getInt("capacidadEspecial") == 1,
                        rs.getInt("porcentajeCapacidadEspecial"),
                        rs.getInt("esBachiller") == 1
                    );
                } else {
                    post = new Postulante_Presencial(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("opcion1"),
                        rs.getString("opcion2"),
                        rs.getInt("esAbanderado") == 1,
                        rs.getInt("bachilleratoAfin") == 1,
                        rs.getInt("capacidadEspecial") == 1,
                        rs.getInt("porcentajeCapacidadEspecial")
                    );
                }
                postulantes.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer postulantes: " + e.getMessage());
        }
        return postulantes;
    }

    public Postulante buscarPostulante(String cedula) {
        for (Postulante p : postulantes) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }
}