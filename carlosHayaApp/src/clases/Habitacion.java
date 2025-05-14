package clases;

public class Habitacion extends Sala {
    private int numeroCama;

    public Habitacion(String numero, boolean disponibilidad, int numeroCama) {
        super(numero, disponibilidad);
        this.numeroCama = numeroCama;
    }

    public int getNumeroCama() {
        return numeroCama;
    }

    public void setNumeroCama(int numeroCama) {
        this.numeroCama = numeroCama;
    }

}