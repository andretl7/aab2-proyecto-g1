package Model;

public class Carrera_Presencial extends Carrera {
    private int puntajeMinimo;      
    private int limiteNivelacion;   

    public Carrera_Presencial(String nombre, int cuposDisponibles, int puntajeMinimo, int limiteNivelacion) {
        super(nombre, cuposDisponibles);
        this.puntajeMinimo = puntajeMinimo;
        this.limiteNivelacion = limiteNivelacion;
    }

    @Override
    public boolean hayCupoDisponible() {
        return getCuposTomados() < getCuposDisponibles();
    }

    @Override
    public boolean tieneCapacidadLimitada() {
        return true;
    }

    public int getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public int getLimiteNivelacion() {
        return limiteNivelacion;
    }
    
    //Devuelve presencial
    @Override
    public String getModalidad() {
        return "PRESENCIAL";
    }
}