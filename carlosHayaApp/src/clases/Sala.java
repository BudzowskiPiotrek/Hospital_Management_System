package clases;

public abstract class Sala {
    private String numero;
    private boolean disponibilidad;

    public Sala(String numero, boolean disponibilidad) {
        this.numero = numero;
        this.disponibilidad = disponibilidad;
    }

    public String getNumero() {
        return numero;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}

