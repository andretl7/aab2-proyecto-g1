package Model;

import java.util.ArrayList;

public class Postulante_Online extends Postulante {
    private String estadoOpc1;
    private String estadoOpc2;

    public Postulante_Online(String cedula, String nombre, String opcion1, String opcion2,
                              boolean esAbanderado, boolean bachilleratoAfin,
                              boolean capacidadEspecial, int porcentajeCapacidadEspecial,
                              boolean esBachiller) {
        super(cedula, nombre, opcion1, opcion2, esAbanderado, bachilleratoAfin,
              capacidadEspecial, porcentajeCapacidadEspecial, esBachiller);
        this.estadoOpc1 = "PENDIENTE";
        this.estadoOpc2 = "PENDIENTE";
    }

    @Override
    public String getEstadoOpc1() {
        return estadoOpc1;
    }

    @Override
    public String getEstadoOpc2() {
        return estadoOpc2;
    }

    @Override
    public ArrayList<Examen> getExamenesPendientesDeCupo() {
        return new ArrayList<>(); // Virtual nunca compite por ranking, no tiene Examen
    }

    @Override
    public void procesarAdmision(CarreraDAO carreraDAO) {
        if (!isEsBachiller()) {
            estadoOpc1 = "RECHAZADO POR REQUISITO(SER BACHILLER)";
            estadoOpc2 = "RECHAZADO POR REQUISITO(SER BACHILLER)";
            return;
        }

        Carrera carrOpc1 = carreraDAO.buscarCarrera(getOpcion1(), "VIRTUAL");
        if (carrOpc1 != null) {
            if (carrOpc1.hayCupoDisponible()) {
                carrOpc1.tomarCupo();
                estadoOpc1 = "ADMITIDO";
            } else {
                estadoOpc1 = "RECHAZADO SIN CUPOS DISPONIBLES";
            }
        }

        Carrera carrOpc2 = carreraDAO.buscarCarrera(getOpcion2(), "VIRTUAL");
        if (carrOpc2 != null) {
            if (carrOpc2.hayCupoDisponible()) {
                carrOpc2.tomarCupo();
                estadoOpc2 = "ADMITIDO";
            } else {
                estadoOpc2 = "RECHAZADO SIN CUPOS DISPONIBLES";
            }
        }
    }
    
    @Override
    public String getModalidad() {
        return "VIRTUAL";
    }
}