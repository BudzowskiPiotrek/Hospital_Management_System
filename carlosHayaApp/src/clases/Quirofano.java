package clases;

import java.util.List;

public class Quirofano extends Sala {
    private List<String> equipamiento;

    public Quirofano(String numero, boolean disponibilidad, List<String> equipamiento) {
        super(numero, disponibilidad);
        this.equipamiento = equipamiento;
    }

    public List<String> getEquipamiento() {
        return equipamiento;
    }
}
