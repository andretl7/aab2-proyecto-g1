package Model;

import java.util.ArrayList;

public class Postulante_Presencial extends Postulante {
    private Examen examenOpc1;
    private Examen examenOpc2;

    public Postulante_Presencial(String cedula, String nombre, String opcion1, String opcion2,
                                 boolean esAbanderado, boolean bachilleratoAfin,
                                 boolean capacidadEspecial, int porcentajeCapacidadEspecial) {
        super(cedula, nombre, opcion1, opcion2, esAbanderado, bachilleratoAfin,
              capacidadEspecial, porcentajeCapacidadEspecial);
    }

    public Examen getExamenOpc1() {
        return examenOpc1;
    }

    public void setExamenOpc1(Examen examenOpc1) {
        this.examenOpc1 = examenOpc1;
    }

    public Examen getExamenOpc2() {
        return examenOpc2;
    }

    public void setExamenOpc2(Examen examenOpc2) {
        this.examenOpc2 = examenOpc2;
    }

    @Override
    public void procesarAdmision(CarreraDAO carreraDAO) {
        if (examenOpc1 != null) {
            double puntajeFinal1 = examenOpc1.getPuntajeObtenido() + sumaMeritos();
            examenOpc1.evaluar(puntajeFinal1);
        }
        if (examenOpc2 != null) {
            double puntajeFinal2 = examenOpc2.getPuntajeObtenido() + sumaMeritos();
            examenOpc2.evaluar(puntajeFinal2);
        }
    }

    @Override
    public String getEstadoOpc1() {
        return examenOpc1 != null ? examenOpc1.getEstado() : "PENDIENTE";
    }

    @Override
    public String getEstadoOpc2() {
        return examenOpc2 != null ? examenOpc2.getEstado() : "PENDIENTE";
    }

    @Override
    public ArrayList<Examen> getExamenesPendientesDeCupo() {
        ArrayList<Examen> pendientes = new ArrayList<>();
        if (examenOpc1 != null && examenOpc1.getEstado().equals("PENDIENTE_CUPO")) {
            pendientes.add(examenOpc1);
        }
        if (examenOpc2 != null && examenOpc2.getEstado().equals("PENDIENTE_CUPO")) {
            pendientes.add(examenOpc2);
        }
        return pendientes;
    }
}