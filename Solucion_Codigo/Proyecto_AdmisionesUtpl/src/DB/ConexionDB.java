package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    private static final String URL = "jdbc:sqlite:db/admisionUTPL.db";
    private Connection conexion;

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

   
    public void inicializarBaseDatos() {
        String sqlCarrera =
            "CREATE TABLE IF NOT EXISTS Carrera (" +
            "  nombre TEXT PRIMARY KEY," +
            "  modalidad TEXT NOT NULL," +
            "  puntajeMinimo INTEGER," +
            "  limiteNivelacion INTEGER," +
            "  cuposDisponibles INTEGER NOT NULL," +
            "  cuposTomados INTEGER NOT NULL DEFAULT 0" +
            ")";

        String sqlPostulante =
            "CREATE TABLE IF NOT EXISTS Postulante (" +
            "  cedula TEXT PRIMARY KEY," +
            "  nombre TEXT NOT NULL," +
            "  opcion1 TEXT," +
            "  opcion2 TEXT," +
            "  modalidad TEXT NOT NULL," +
            "  esAbanderado INTEGER NOT NULL DEFAULT 0," +
            "  bachilleratoAfin INTEGER NOT NULL DEFAULT 0," +
            "  capacidadEspecial INTEGER NOT NULL DEFAULT 0," +
            "  porcentajeCapacidadEspecial INTEGER NOT NULL DEFAULT 0," +
            "  esBachiller INTEGER NOT NULL DEFAULT 0" +
            ")";

        String sqlIndicadorMerito =
            "CREATE TABLE IF NOT EXISTS IndicadorMerito (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  cedulaPostulante TEXT NOT NULL," +
            "  tipo TEXT," +
            "  descripcion TEXT," +
            "  puntajeAdicional INTEGER," +
            "  FOREIGN KEY (cedulaPostulante) REFERENCES Postulante(cedula)" +
            ")";

        String sqlExamen =
            "CREATE TABLE IF NOT EXISTS Examen (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  cedulaPostulante TEXT NOT NULL," +
            "  numeroOpcion INTEGER NOT NULL," +
            "  nombreCarrera TEXT NOT NULL," +
            "  tipoExamen TEXT NOT NULL," +
            "  fecha TEXT," +
            "  hora TEXT," +
            "  lugar TEXT," +
            "  aula TEXT," +
            "  puntajeObtenido REAL," +
            "  FOREIGN KEY (cedulaPostulante) REFERENCES Postulante(cedula)," +
            "  FOREIGN KEY (nombreCarrera) REFERENCES Carrera(nombre)" +
            ")";

        String sqlResultados =
            "CREATE TABLE IF NOT EXISTS Resultados (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  cedula TEXT NOT NULL," +
            "  nombre TEXT NOT NULL," +
            "  opcion1 TEXT," +
            "  estadoOpcion1 TEXT," +
            "  opcion2 TEXT," +
            "  estadoOpcion2 TEXT" +
            ")";

        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute(sqlCarrera);
            stmt.execute(sqlPostulante);
            stmt.execute(sqlIndicadorMerito);
            stmt.execute(sqlExamen);
            stmt.execute(sqlResultados);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear las tablas: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }
}