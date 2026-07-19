package Controller;

import Model.*;
import java.util.ArrayList;

public class ExamenController {
    private PostulanteDAO postulanteDAO;
    private CarreraDAO carreraDAO;

    public ExamenController(PostulanteDAO postulanteDAO, CarreraDAO carreraDAO) {
        this.postulanteDAO = postulanteDAO;
        this.carreraDAO = carreraDAO;
    }

    public void procesarAdmisiones() {
       
        for (Postulante post : postulanteDAO.getPostulantes()) {
            post.procesarAdmision(carreraDAO);
        }

        asignarCuposPorRanking();
    }

    private void asignarCuposPorRanking() {
        for (Carrera carrera : carreraDAO.getCarreras()) {
            ArrayList<Examen> candidatos = candidatosPendientesDe(carrera);

            candidatos.sort((a, b) -> Double.compare(b.getPuntajeFinal(), a.getPuntajeFinal()));

            for (Examen exam : candidatos) {
                if (carrera.hayCupoDisponible()) {
                    exam.otorgarCupo();
                } else {
                    exam.rechazarPorFaltaCupo();
                }
            }
        }
    }

    private ArrayList<Examen> candidatosPendientesDe(Carrera carrera) {
        ArrayList<Examen> candidatos = new ArrayList<>();
        for (Postulante post : postulanteDAO.getPostulantes()) {
            for (Examen exam : post.getExamenesPendientesDeCupo()) {
                if (exam.getCarrera() == carrera) {
                    candidatos.add(exam);
                }
            }
        }
        return candidatos;
    }
}