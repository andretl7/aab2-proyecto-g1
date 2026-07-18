package Controller;

import Model.*;

public class PostulanteController {
    private PostulanteDAO postulanteDAO;

    public PostulanteController(PostulanteDAO postulanteDAO) {
        this.postulanteDAO = postulanteDAO;
    }

    public void asignarMeritos() {
        for (Postulante post : postulanteDAO.getPostulantes()) {

            if (post.isEsAbanderado()) {
                post.getMeritos().add(new IndicadorMerito("ACADEMICO", "Abanderado en bachillerato", 5));
            }

            if (post.isBachilleratoAfin()) {
                post.getMeritos().add(new IndicadorMerito("ACADEMICO", "Bachillerato afin a la carrera", 2));
            }

            if (post.isCapacidadEspecial() && post.getPorcentajeCapacidadEspecial() > 35) {
                post.getMeritos().add(new IndicadorMerito("CAPACIDAD_ESPECIAL", "Capacidad especial mayor al 35%", 3));
            }
        }
    }
}