package Model;

public class Postulante_Online extends Postulante {
    private boolean esBachiller;
    private String estadoOpc1;
    private String estadoOpc2;

    public Postulante_Online(String cedula, String nombre, String opcion1, String opcion2,
                              boolean esAbanderado, boolean bachilleratoAfin,
                              boolean capacidadEspecial, int porcentajeCapacidadEspecial,
                              boolean esBachiller) {
        super(cedula, nombre, opcion1, opcion2, esAbanderado, bachilleratoAfin,
              capacidadEspecial, porcentajeCapacidadEspecial);
        this.esBachiller = esBachiller;
        this.estadoOpc1 = "PENDIENTE";
        this.estadoOpc2 = "PENDIENTE";
    }

    public boolean isEsBachiller() {
        return esBachiller;
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
    public void procesarAdmision(CarreraDAO carreraDAO) {
        if (!esBachiller) {
            estadoOpc1 = "RECHAZADO POR REQUISITO";
            estadoOpc2 = "RECHAZADO POR REQUISITO";
            return;
        }

        Carrera carrOpc1 = carreraDAO.buscarCarrera(getOpcion1());
        if (carrOpc1 != null) {
            if (carrOpc1.hayCupoDisponible()) {
                carrOpc1.tomarCupo();
                estadoOpc1 = "ADMITIDO";
            } else {
                estadoOpc1 = "RECHAZADO SIN CUPOS DISPONIBLES";
            }
        }

        Carrera carrOpc2 = carreraDAO.buscarCarrera(getOpcion2());
        if (carrOpc2 != null) {
            if (carrOpc2.hayCupoDisponible()) {
                carrOpc2.tomarCupo();
                estadoOpc2 = "ADMITIDO";
            } else {
                estadoOpc2 = "RECHAZADO SIN CUPOS DISPONIBLES";
            }
        }
    }
}