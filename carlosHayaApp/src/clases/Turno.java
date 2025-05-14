package clases;

import java.time.LocalDateTime;

public class Turno {
    private String empleadoDni;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Turno(String empleadoDni, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.empleadoDni = empleadoDni;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getEmpleadoId() {
        return empleadoDni;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setEmpleadoId(String empleadoDni) {
        this.empleadoDni = empleadoDni;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    // POR DESAROLLAR 
    public void guardarTurnoEnBD() {
    }
}