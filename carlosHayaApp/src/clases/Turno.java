package clases;

public class Turno {
	private int id;
	private int empleadoId;
	private String dia; // yyyy-MM-dd
	private String horaInicio; // HH:mm
	private String horaFin; // HH:mm

	public Turno(int id, int empleadoId, String dia, String horaInicio, String horaFin) {
		this.id = id;
		this.empleadoId = empleadoId;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
}
