package Controller;

import Model.*;
import java.util.ArrayList;

public class ReporteController {
    private PostulanteDAO postulanteDAO;
    private CarreraDAO carreraDAO;

    public ReporteController(PostulanteDAO postulanteDAO, CarreraDAO carreraDAO) {
        this.postulanteDAO = postulanteDAO;
        this.carreraDAO = carreraDAO;
    }

    public ArrayList<Carrera> carrerasBajoCapacidad() {
        ArrayList<Carrera> carrerasBajoC = new ArrayList<>();
        for (Carrera carr : carreraDAO.getCarreras()) {
            if (!carr.tieneCapacidadLimitada()) {
                continue; 
            }
            if ((carr.getCuposTomados() * 100) / carr.getCuposDisponibles() < 50) {
                carrerasBajoC.add(carr);
            }
        }
        return carrerasBajoC;
    }

    public ArrayList<Carrera> carrerasConRechazos() {
        ArrayList<Carrera> carrerasRechazos = new ArrayList<>();
        for (Postulante post : postulanteDAO.getPostulantes()) {

            if (post.getEstadoOpc1().equals("RECHAZADO SIN CUPOS DISPONIBLES")) {
                Carrera c1 = carreraDAO.buscarCarrera(post.getOpcion1(), post.getModalidad());
                if (c1 != null && !carrerasRechazos.contains(c1)) {
                    carrerasRechazos.add(c1);
                }
            }
            if (post.getEstadoOpc2().equals("RECHAZADO SIN CUPOS DISPONIBLES")) {
                Carrera c2 = carreraDAO.buscarCarrera(post.getOpcion2(), post.getModalidad());
                if (c2 != null && !carrerasRechazos.contains(c2)) {
                    carrerasRechazos.add(c2);
                }
            }
        }
        return carrerasRechazos;
    }

    public int[] mostrarEstadisticas() {
        int[] estadisticas = new int[3]; 

        for (Postulante post : postulanteDAO.getPostulantes()) {
            String opc1 = post.getEstadoOpc1();
            String opc2 = post.getEstadoOpc2();

            boolean admitido = opc1.equals("ADMITIDO") || opc2.equals("ADMITIDO");
            boolean nivelacion = opc1.equals("NIVELACION") || opc2.equals("NIVELACION");

            if (admitido) {
                estadisticas[0]++;
            } else if (nivelacion) {
                estadisticas[2]++;
            } else {
                estadisticas[1]++;
            }
        }
        return estadisticas;
    }
}