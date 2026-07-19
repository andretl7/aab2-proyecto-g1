package Model;

public abstract class Examen {
    private Carrera carrera;
    private Horario horario;
    private double puntajeObtenido;
    private double puntajeFinal;
    private String estado;

    public Examen(Carrera carrera, Horario horario, double puntajeObtenido) {
        this.carrera = carrera;
        this.horario = horario;
        this.puntajeObtenido = puntajeObtenido;
        this.estado = "PENDIENTE";
    }

    public abstract String evaluar(double puntajeConMeritos);

    public void otorgarCupo() {
        carrera.tomarCupo();
        setEstado("ADMITIDO");
    }

    public void rechazarPorFaltaCupo() {
        setEstado("RECHAZADO SIN CUPOS DISPONIBLES");
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public Horario getHorario() {
        return horario;
    }

    public double getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public double getPuntajeFinal() {
        return puntajeFinal;
    }

    protected void setPuntajeFinal(double puntajeFinal) {
        this.puntajeFinal = puntajeFinal;
    }

    public String getEstado() {
        return estado;
    }

    protected void setEstado(String estado) {
        this.estado = estado;
    }
}