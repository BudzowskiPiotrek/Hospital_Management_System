package clases;

public class Medico extends Empleado {

	public Medico(String nombre,String contrasena, String apellido, String dni) {
		super(nombre,contrasena, apellido, dni, "medico");
	}
}
