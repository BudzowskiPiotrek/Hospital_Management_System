package clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
	private int medicoId;
	private int pacienteId;
	private LocalDate dia; // yyyy-MM-dd
	private LocalTime horaInicio; // HH:mm
	private LocalTime horaFin; // HH:mm

	public Turno(int medicoId, int pacienteId, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
		this.medicoId = medicoId;
		this.pacienteId = pacienteId;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public int getPacienteId() {
		return pacienteId;
	}

	public LocalDate getDia() {
		return dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	
}
