package Model;

import java.util.ArrayList;

public abstract class Postulante {
    private String cedula;
    private String nombre;
    private String opcion1;
    private String opcion2;
    private boolean esAbanderado;
    private boolean bachilleratoAfin;
    private boolean capacidadEspecial;
    private int porcentajeCapacidadEspecial;
    private ArrayList<IndicadorMerito> meritos;

    public Postulante(String cedula, String nombre, String opcion1, String opcion2,
                       boolean esAbanderado, boolean bachilleratoAfin,
                       boolean capacidadEspecial, int porcentajeCapacidadEspecial) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.esAbanderado = esAbanderado;
        this.bachilleratoAfin = bachilleratoAfin;
        this.capacidadEspecial = capacidadEspecial;
        this.porcentajeCapacidadEspecial = porcentajeCapacidadEspecial;
        this.meritos = new ArrayList<IndicadorMerito>();
    }

    public abstract void procesarAdmision(CarreraDAO carreraDAO);

    public abstract String getEstadoOpc1();
    public abstract String getEstadoOpc2();

    public abstract ArrayList<Examen> getExamenesPendientesDeCupo();

    public int sumaMeritos() {
        int suma = 0;
        for (IndicadorMerito m : meritos) {
            suma += m.getPuntajeAdicional();
        }
        return suma;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public boolean isEsAbanderado() {
        return esAbanderado;
    }

    public boolean isBachilleratoAfin() {
        return bachilleratoAfin;
    }

    public boolean isCapacidadEspecial() {
        return capacidadEspecial;
    }

    public int getPorcentajeCapacidadEspecial() {
        return porcentajeCapacidadEspecial;
    }

    public ArrayList<IndicadorMerito> getMeritos() {
        return meritos;
    }
}