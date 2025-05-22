package clases;

import java.util.ArrayList;


public class Medico extends Empleado {
	private ArrayList<Paciente> pacientes;
	
	public Medico(String nombre,String contrasena, String apellido, String dni) {
		super(nombre,contrasena, apellido, dni, "medico");
		this.pacientes=new ArrayList<>();
	}
	
	
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	
	public void pacientesAsignados(Paciente p) {
		pacientes.add(p);
	}
	

}
