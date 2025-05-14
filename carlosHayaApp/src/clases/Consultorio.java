package clases;

public class Consultorio extends Sala {
    private String especialidad;

    public Consultorio(String numero, boolean disponibilidad, String especialidad) {
        super(numero, disponibilidad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
