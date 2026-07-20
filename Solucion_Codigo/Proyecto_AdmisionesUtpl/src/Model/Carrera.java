package Model;

public abstract class Carrera {
    private String nombre;
    private int cuposDisponibles;
    private int cuposTomados;

    public Carrera(String nombre, int cuposDisponibles) {
        this.nombre = nombre;
        this.cuposDisponibles = cuposDisponibles;
        this.cuposTomados = 0;
    }

    public abstract boolean hayCupoDisponible();


    public abstract boolean tieneCapacidadLimitada();

    public void tomarCupo() {
        cuposTomados++;
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract String getModalidad();

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public int getCuposTomados() {
        return cuposTomados;
    }
}