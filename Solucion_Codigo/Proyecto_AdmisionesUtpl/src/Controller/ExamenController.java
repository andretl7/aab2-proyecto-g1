package Controller;

import Model.*;

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
    }
}