package clases;

public abstract class Empleado {
    private String nombre;
    private String DNI;
    private String rol;
    private String turno;

    public Empleado(String nombre, String DNI, String rol, String turno) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.rol = rol;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public String getRol() {
        return rol;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}