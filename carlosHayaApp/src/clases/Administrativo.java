package clases;

public class Administrativo extends Empleado {
	public Administrativo(String nombre, String contrasena, String apellido, String dni) {
		super(nombre, contrasena, apellido, dni, "administrativo");
	}
}
