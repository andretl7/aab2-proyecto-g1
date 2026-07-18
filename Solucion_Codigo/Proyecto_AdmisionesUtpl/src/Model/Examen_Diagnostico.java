package Model;

public class Examen_Diagnostico extends Examen {

    public Examen_Diagnostico(Carrera carrera, Horario horario, double puntajeObtenido) {
        super(carrera, horario, puntajeObtenido);
    }

    @Override
    public String evaluar(double puntajeConMeritos) {
        Carrera_Presencial carr = (Carrera_Presencial) getCarrera();

        
        if (!carr.hayCupoDisponible()) {
            setEstado("RECHAZADO SIN CUPOS DISPONIBLES");
            return getEstado();
        }
        carr.tomarCupo();
        if (puntajeConMeritos >= carr.getLimiteNivelacion()) {
            setEstado("ADMITIDO");
        } else {
            setEstado("NIVELACION");
        }
        return getEstado();
    }
}
