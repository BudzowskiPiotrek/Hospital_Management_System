package clases;

public class Receta {
	private int id;
	private int pacienteId;
	private int medicoId;
	private String medicamentos; // lista como texto simple (ej: "Paracetamol, Ibuprofeno")
	private String fecha; // yyyy-MM-dd

	public Receta(int id, int pacienteId, int medicoId, String medicamentos, String fecha) {
		this.id = id;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.medicamentos = medicamentos;
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

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
