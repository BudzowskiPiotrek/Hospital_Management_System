package clases;

public class Administrador extends Empleado {
	
	public Administrador( String nombre, String apellido, String dni, String contrasena) {
		super(nombre, apellido, dni, "administrador",contrasena);
	}
}
