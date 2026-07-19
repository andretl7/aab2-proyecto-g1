package Model;

public class Examen_Admision extends Examen {

    public Examen_Admision(Carrera carrera, Horario horario, double puntajeObtenido) {
        super(carrera, horario, puntajeObtenido);
    }

    @Override
    public String evaluar(double puntajeConMeritos) {
        setPuntajeFinal(puntajeConMeritos);
        Carrera_Presencial carr = (Carrera_Presencial) getCarrera();

        if (puntajeConMeritos < carr.getPuntajeMinimo()) {
            setEstado("RECHAZADO POR PUNTAJE");
        } else {
            setEstado("PENDIENTE_CUPO");
        }
        return getEstado();
    }
}
