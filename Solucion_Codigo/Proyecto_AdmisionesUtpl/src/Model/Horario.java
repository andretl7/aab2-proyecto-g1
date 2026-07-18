package Model;

public class Horario {
    private String fecha;
    private String hora;
    private String lugar;
    private String aula;

    public Horario(String fecha, String hora, String lugar, String aula) {
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.aula = aula;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getAula() {
        return aula;
    }
}