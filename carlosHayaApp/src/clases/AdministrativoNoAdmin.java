package clases;

import java.time.LocalDateTime;

public class AdministrativoNoAdmin extends Empleado {
    public AdministrativoNoAdmin(String nombre, String DNI, String rol, String turno) {
        super(nombre, DNI, rol, turno);
    }

    // POR DESAROLLAR
    public void consultarDatosHospital() {

    }

    public void asignarPacienteMedico(Paciente paciente, Medico medico) {

    }

    public void darCita(Paciente paciente, Medico medico, LocalDateTime fecha) {

    }
}
