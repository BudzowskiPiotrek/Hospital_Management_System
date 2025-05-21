package clases;

public class Diagnostico {
	private int id;
	private int pacienteId;
	private int medicoId;
	private String descripcion;
	private String fecha; // yyyy-MM-dd

	public Diagnostico() {
	}

	public Diagnostico(int id, int pacienteId, int medicoId, String descripcion, String fecha) {
		this.id = id;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(int pacienteId) {
		this.pacienteId = pacienteId;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(int medicoId) {
		this.medicoId = medicoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
