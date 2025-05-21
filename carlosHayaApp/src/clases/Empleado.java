package clases;

public class Empleado extends Usuario {
	private String contrasena;

	public Empleado(String nombre, String apellido, String dni, String rol, String contrasena) {
		super(nombre, apellido, dni, rol);
		this.contrasena = contrasena;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
