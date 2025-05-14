package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Administrador extends Empleado {
    public Administrador(String nombre, String DNI, String rol, String turno) {
        super(nombre, DNI, rol, turno);
    }

    public void gestionarEmpleado(Empleado empleado, String operacion) {
        String sql = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            switch (operacion.toLowerCase()) {
                case "registrar":
                    sql = "INSERT INTO empleados (nombre, DNI, rol, turno) VALUES (?, ?, ?, ?)";
                    pstmt.setString(1, empleado.getNombre());
                    pstmt.setString(2, empleado.getDNI());
                    pstmt.setString(3, empleado.getRol());
                    pstmt.setString(4, empleado.getTurno());
                    pstmt.executeUpdate();
                    break;
                case "eliminar":
                    sql = "DELETE FROM empleados WHERE DNI = ?";
                    pstmt.setString(1, empleado.getDNI());
                    pstmt.executeUpdate();
                    break;
                case "modificar":
                    sql = "UPDATE empleados SET nombre = ?, rol = ?, turno = ? WHERE DNI = ?";
                    pstmt.setString(1, empleado.getNombre());
                    pstmt.setString(2, empleado.getRol());
                    pstmt.setString(3, empleado.getTurno());
                    pstmt.setString(4, empleado.getDNI());
                    pstmt.executeUpdate();
                    break;
            }
            System.out.println("Empleado " + empleado.getNombre() + " (" + operacion + ").");
        } catch (SQLException e) {
            System.err.println("Error al gestionar empleado: " + e.getMessage());
        }
    }

    public void gestionarPaciente(Paciente paciente, String operacion) {
        String sql = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            switch (operacion.toLowerCase()) {
                case "registrar":
                    sql = "INSERT INTO pacientes (nombre, DNI, contacto, obraSocial) VALUES (?, ?, ?, ?)";
                    pstmt.setString(1, paciente.getNombre());
                    pstmt.setString(2, paciente.getDNI());
                    pstmt.setString(3, paciente.getContacto());
                    pstmt.setString(4, paciente.getObraSocial());
                    pstmt.executeUpdate();
                    break;
                case "eliminar":
                    sql = "DELETE FROM pacientes WHERE DNI = ?";
                    pstmt.setString(1, paciente.getDNI());
                    pstmt.executeUpdate();
                    break;
                case "modificar":
                    sql = "UPDATE pacientes SET nombre = ?, contacto = ?, obraSocial = ? WHERE DNI = ?";
                    pstmt.setString(1, paciente.getNombre());
                    pstmt.setString(2, paciente.getContacto());
                    pstmt.setString(3, paciente.getObraSocial());
                    pstmt.setString(4, paciente.getDNI());
                    pstmt.executeUpdate();
                    break;
            }
            System.out.println("Paciente " + paciente.getNombre() + " (" + operacion + ").");
        } catch (SQLException e) {
            System.err.println("Error al gestionar paciente: " + e.getMessage());
        }
    }

    public void gestionarSala(Sala sala, String operacion) {
        String sql = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            switch (operacion.toLowerCase()) {
                case "registrar":
                    sql = "INSERT INTO salas (numero, tipo, disponibilidad) VALUES (?, ?, ?)";
                    pstmt.setString(1, sala.getNumero());
                    pstmt.setString(2, sala.getClass().getSimpleName().replace("Habitacion", "habitacion")
                            .replace("Quirofano", "quirofano").replace("Consultorio", "consultorio"));
                    pstmt.setBoolean(3, sala.isDisponibilidad());
                    pstmt.executeUpdate();
                    break;
                case "eliminar":
                    sql = "DELETE FROM salas WHERE numero = ?";
                    pstmt.setString(1, sala.getNumero());
                    pstmt.executeUpdate();
                    break;
                case "modificar":
                    sql = "UPDATE salas SET tipo = ?, disponibilidad = ? WHERE numero = ?";
                    pstmt.setString(1, sala.getClass().getSimpleName().replace("Habitacion", "habitacion")
                            .replace("Quirofano", "quirofano").replace("Consultorio", "consultorio"));
                    pstmt.setBoolean(2, sala.isDisponibilidad());
                    pstmt.setString(3, sala.getNumero());
                    pstmt.executeUpdate();
                    break;
            }
            System.out.println("Sala " + sala.getNumero() + " (" + operacion + ").");
        } catch (SQLException e) {
            System.err.println("Error al gestionar sala: " + e.getMessage());
        }
    }

    // POR DESAROLLAR
    public List<Paciente> listarPacientesInternados() {
        return null;

    }

    public List<Habitacion> mostrarDisponibilidadHabitaciones() {
        return null;

    }

    public String generarReporteActividadMedico(Medico medico) {
        return null;

    }

    public String generarReporteActividadEnfermero(Enfermero enfermero) {
        return null;

    }

    public HistorialMedico mostrarHistorialClinicoPaciente(Paciente paciente) {
        return null;
    
    }

}
