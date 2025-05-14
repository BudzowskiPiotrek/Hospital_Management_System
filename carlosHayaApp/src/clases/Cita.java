package clases;

import java.time.LocalDateTime;

public class Cita {
    private String pacienteDni;
    private String medicoDni;
    private LocalDateTime fechaHora;

    public Cita(String pacienteDni, String medicoDni, LocalDateTime fechaHora) {
        this.pacienteDni = pacienteDni;
        this.medicoDni = medicoDni;
        this.fechaHora = fechaHora;
    }

    public String getPacienteId() {
        return pacienteDni;
    }

    public String getMedicoId() {
        return medicoDni;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setPacienteId(String pacienteDni) {
        this.pacienteDni = pacienteDni;
    }

    public void setMedicoId(String medicoDni) {
        this.medicoDni = medicoDni;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    // POR DESAROLLAR
    public void guardarCitaEnBD() {
        
    }
}