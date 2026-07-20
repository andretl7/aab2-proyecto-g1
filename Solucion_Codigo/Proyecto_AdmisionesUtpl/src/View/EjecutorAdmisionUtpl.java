package View;

import Controller.*;
import Model.*;
import DB.*;
import java.sql.Connection;

public class EjecutorAdmisionUtpl {
    public static void main(String[] args) {

        ConexionDB conexionBD = new ConexionDB();
        conexionBD.inicializarBaseDatos();
        conexionBD.limpiarDatosAnteriores();
        Connection conexion = conexionBD.getConexion();

        CarreraDAO carreraDAO = new CarreraDAO(conexion);
        PostulanteDAO postulanteDAO = new PostulanteDAO(conexion);
        ExamenDAO examenDAO = new ExamenDAO(conexion);

        carreraDAO.leerCarreras();
        postulanteDAO.leerPostulantes();
        examenDAO.cargarExamenes(postulanteDAO, carreraDAO);  
        
        PostulanteController postulanteController = new PostulanteController(postulanteDAO);
        postulanteController.asignarMeritos();

        ResultadosDB resultadoDB = new ResultadosDB(conexion);
        resultadoDB.guardarMeritos(postulanteDAO.getPostulantes());

        ExamenController examenController = new ExamenController(postulanteDAO, carreraDAO);
        examenController.procesarAdmisiones();

       
        resultadoDB.guardarResultados(postulanteDAO.getPostulantes());

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
        

        conexionBD.cerrarConexion();
    }
}