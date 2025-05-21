package clases;

public class Empleado extends Usuario {
	private String contrasena;
	private int salaId;

	public Empleado(int salaId, String nombre, String apellido, String dni, String rol, String contrasena) {
		super(nombre, apellido, dni, rol);
		this.contrasena = contrasena;
		this.salaId = salaId;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
