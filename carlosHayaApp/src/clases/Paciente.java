package clases;

public class Paciente {
    private String nombre;
    private String DNI;
    private String contacto;
    private String obraSocial;

    public Paciente(String nombre, String DNI, String contacto, String obraSocial) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.contacto = contacto;
        this.obraSocial = obraSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public String getContacto() {
        return contacto;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

}
