package Model;

public class IndicadorMerito {
    private String tipo;
    private String descripcion;
    private int puntajeAdicional;

    public IndicadorMerito(String tipo, String descripcion, int puntajeAdicional) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.puntajeAdicional = puntajeAdicional;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntajeAdicional() {
        return puntajeAdicional;
    }
}