package clases;

import java.util.List;

public class Medico extends Empleado {
    public Medico(String nombre, String DNI, String rol, String turno) {
        super(nombre, DNI, rol, turno);
    }

    // POR DESAROLLAR
    public void registrarDiagnostico(Paciente paciente, String diagnostico) {

    }

    public void consultarHistorialMedico(Paciente paciente) {

    }

    public void registrarReceta(Paciente paciente, String receta) {

    }

    public List<Paciente> verPacientesAsignados() {
        return null;

    }
}
