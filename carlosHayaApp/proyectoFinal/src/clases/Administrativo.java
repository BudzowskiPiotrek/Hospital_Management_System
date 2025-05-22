package clases;

public class Administrativo extends Empleado {
	public Administrativo(String nombre, String apellido, String dni, String contrasena) {
		super( nombre, apellido, dni, "administrativo",contrasena);
	}
	
	public void asignarPacienteMedico(Paciente p, Medico m) {
		m.getPacientes().add(p);
	}
}
