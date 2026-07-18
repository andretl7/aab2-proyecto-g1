package Model;

public class Carrera_Online extends Carrera {

    public Carrera_Online(String nombre) {
        super(nombre, Integer.MAX_VALUE); // cupos "ilimitados"
    }

    @Override
    public boolean hayCupoDisponible() {
        return true; // nunca se agota el cupo en modalidad virtual
    }

    @Override
    public boolean tieneCapacidadLimitada() {
        return false;
    }
}
