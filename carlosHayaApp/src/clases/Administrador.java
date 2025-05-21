package clases;

public class Administrador extends Empleado {

	public Administrador(String nombre, String contrasena, String apellido, String dni) {
		super(nombre, contrasena, apellido, dni, "administrador");
	}
}
