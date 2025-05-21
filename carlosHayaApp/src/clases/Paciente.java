package clases;

public class Paciente extends Usuario {
	private String contacto;
	private String obraSocial;

	public Paciente(String nombre, String apellido, String dni, String contacto, String obraSocial) {
		super(nombre, apellido, dni, "paciente");
		this.contacto = contacto;
		this.obraSocial = obraSocial;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

}
