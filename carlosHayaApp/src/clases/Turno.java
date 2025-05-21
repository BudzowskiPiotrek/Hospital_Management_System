package clases;

public class Turno {
	private int id;
	private String medicoDni;
	private String PacienteDni;
	private String dia; // yyyy-MM-dd
	private String horaInicio; // HH:mm
	private String horaFin; // HH:mm

	public Turno(int id, String medicoDni, String pacienteDni, String dia, String horaInicio, String horaFin) {
		this.id = id;
		this.medicoDni = medicoDni;
		PacienteDni = pacienteDni;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicoDni() {
		return medicoDni;
	}

	public void setMedicoDni(String medicoDni) {
		this.medicoDni = medicoDni;
	}

	public String getPacienteDni() {
		return PacienteDni;
	}

	public void setPacienteDni(String pacienteDni) {
		PacienteDni = pacienteDni;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

}
