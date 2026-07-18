package View;

import Controller.*;
import Model.*;
import DB.*;
import java.sql.Connection;

public class EjecutorAdmisionUtpl {
    public static void main(String[] args) {

        // CONFIGURAR BASE DE DATOS
        ConexionDB conexionBD = new ConexionDB();
        conexionBD.inicializarBaseDatos();
        Connection conexion = conexionBD.getConexion();

        // CREAR DAOs
        CarreraDAO carreraDAO = new CarreraDAO(conexion);
        PostulanteDAO postulanteDAO = new PostulanteDAO(conexion);
        ExamenDAO examenDAO = new ExamenDAO(conexion);

        // LEER DATOS (orden obligatorio: Carrera y Postulante antes que Examen)
        carreraDAO.leerCarreras();
        postulanteDAO.leerPostulantes();
        examenDAO.cargarExamenes(postulanteDAO, carreraDAO);

        // ASIGNAR MERITOS
        PostulanteController postulanteController = new PostulanteController(postulanteDAO);
        postulanteController.asignarMeritos();

        // PROCESAR ADMISIONES (presenciales y virtuales, mismo metodo)
        ExamenController examenController = new ExamenController(postulanteDAO, carreraDAO);
        examenController.procesarAdmisiones();

        // GUARDAR RESULTADOS DETALLADOS EN LA BASE DE DATOS
        ResultadosDB resultadoDAO = new ResultadosDB(conexion);
        resultadoDAO.guardarResultados(postulanteDAO.getPostulantes());

        // REPORTES
        ReporteController reporteController = new ReporteController(postulanteDAO, carreraDAO);

        System.out.println("=== CARRERAS BAJO 50% DE CAPACIDAD ===");
        for (Carrera carr : reporteController.carrerasBajoCapacidad()) {
            System.out.println(carr.getNombre() + " - Cupos tomados: " + carr.getCuposTomados() + "/" + carr.getCuposDisponibles());
        }

        System.out.println("\n=== CARRERAS CON RECHAZOS POR CUPOS ===");
        for (Carrera carr : reporteController.carrerasConRechazos()) {
            System.out.println(carr.getNombre());
        }

        System.out.println("\n=== ESTADISTICAS GENERALES ===");
        int[] stats = reporteController.mostrarEstadisticas();
        System.out.println("Admitidos: " + stats[0]);
        System.out.println("Rechazados: " + stats[1]);
        System.out.println("Nivelacion: " + stats[2]);

        // CERRAR CONEXION
        conexionBD.cerrarConexion();
    }
}