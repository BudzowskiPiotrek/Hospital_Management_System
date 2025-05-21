package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/appcarloshaya"; 																
	private static final String DB_USUARIO = "root"; // CREO QUE ES ROOT POR DEFECTO PERO LO QUE PONDRA JAVI
	private static final String DB_CONTRASENIA = ""; // CONTRASEÑA DE BASE DATOS

	private static Connection conn = null; // PARA TENER UNA UNICA CONEXION (MODO CONTROL)

	public DBConnection() {
		// SE HACE UN CONSTRUCTOR VACIO PARA EVITAR LA ISTANCIA DIRECTA
	}

	public void conectar() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_CONTRASENIA);
				System.out.println("Conexión a app Carlos haya establecida.");
			} catch (SQLException e) {
				System.out.println("Error al conectar a la base de datos");
			}
		}
	}

	public void desconectar() {
		conn = null;
	}

	// CLASE USUARIO --------------

	// -------------- GESTION DE LOGIN --------------

	public String iniciarSesion(String dni, String contrasena) {
		String sql = "SELECT u.rol FROM Usuario u " + "JOIN Empleado e ON u.id = e.usuario_id "
				+ "WHERE u.dni = ? AND e.contrasena = ?";
		try {
			conectar();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dni);
			ps.setString(2, contrasena);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("rol");
			} else {
				return null; // No coincide dni o contraseña
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			desconectar();
		}
	}

	// CLASE ADMINISTRADOR --------------

	// -------------- GESTION DE EMPLEADO --------------

	public boolean agregarEmpleado(Empleado empleado) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			// SE INSERTA PRIMERO EN TABLA DE USUARIO
			String sqlUsuario = "INSERT INTO Usuario (nombre, apellido, dni, rol) VALUES ('" + empleado.getNombre()
					+ "', '" + empleado.getApellido() + "', '" + empleado.getDni() + "', '" + empleado.getRol() + "')";
			int filasUsuario = stmt.executeUpdate(sqlUsuario);

			if (filasUsuario == 0) {
				return false;
			}
			// POSTERIORMENTE SE INGRESA EN LA TABLA EMPLEADO (USUARIO_ID ES EL DNI)
			String sqlEmpleado = "INSERT INTO Empleado (usuario_id) VALUES ('" + empleado.getDni() + "')";
			int filasEmpleado = stmt.executeUpdate(sqlEmpleado);

			return filasEmpleado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean modificarEmpleado(Empleado empleado) {
		try {
			conectar();
			Statement stmt = conn.createStatement();
			// SE MODIFICA SOLO LA TABLA USUARIO, YA QUE EL DNI ES LA ID Y NO ES
			// MODIFICABLE, Y SIEMPRE SERA DE TIPO EMPLEADO
			String sql = "UPDATE Usuario SET " + "nombre = '" + empleado.getNombre() + "', " + "apellido = '"
					+ empleado.getApellido() + "', " + "rol = '" + empleado.getRol() + "' " + "WHERE dni = '"
					+ empleado.getDni() + "'";

			int filas = stmt.executeUpdate(sql);
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean eliminarEmpleado(String dni) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM Usuario WHERE dni = '" + dni + "'";

			int filas = stmt.executeUpdate(sql);
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	// -------------- GESTION DE PACIENTE --------------

	public boolean agregarPaciente(Paciente paciente) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			// SE INSERTA PRIMERO EN TABLA DE USUARIO
			String sqlUsuario = "INSERT INTO Usuario (nombre, apellido, dni, rol) VALUES ('" + paciente.getNombre()
					+ "', '" + paciente.getApellido() + "', '" + paciente.getDni() + "', 'paciente')";
			int filasUsuario = stmt.executeUpdate(sqlUsuario);

			if (filasUsuario == 0) {
				return false;
			}

			// POSTERIORMENTE SE INGRESA EN LA TABLA PACIENTE (USUARIO_ID ES EL DNI)
			String sqlPaciente = "INSERT INTO Paciente (usuario_id, contacto, obra_social) VALUES ('"
					+ paciente.getDni() + "', '" + paciente.getContacto() + "', '" + paciente.getObraSocial() + "')";
			int filasPaciente = stmt.executeUpdate(sqlPaciente);

			return filasPaciente > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean modificarPaciente(Paciente paciente) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			// PRIMERO SE MODIFICA LA APRTE DE USUARIO
			String sqlUsuario = "UPDATE Usuario SET " + "nombre = '" + paciente.getNombre() + "', " + "apellido = '"
					+ paciente.getApellido() + "' " + "WHERE dni = '" + paciente.getDni() + "'";

			int filasUsuario = stmt.executeUpdate(sqlUsuario);

			// SEGUNDO SE MODIFICA LA PARTE DE PACIENTE
			String sqlPaciente = "UPDATE Paciente SET " + "contacto = '" + paciente.getContacto() + "', "
					+ "obra_social = '" + paciente.getObraSocial() + "' " + "WHERE usuario_id = '" + paciente.getDni()
					+ "'";

			int filasPaciente = stmt.executeUpdate(sqlPaciente);

			return (filasUsuario > 0 && filasPaciente > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean eliminarPaciente(String dni) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM Usuario WHERE dni = '" + dni + "'";

			int filas = stmt.executeUpdate(sql);
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	// -------------- GESTION DE SALAS --------------

	public boolean agregarSala(String tipo, boolean disponibilidad) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO Sala (tipo, disponibilidad) VALUES ('" + tipo + "', " + (disponibilidad ? 1 : 0)
					+ ")";
			int filas = stmt.executeUpdate(sql);

			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean modificarSala(int id, String nuevoTipo, boolean nuevaDisponibilidad) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			String sql = "UPDATE Sala SET tipo = '" + nuevoTipo + "', disponibilidad = " + (nuevaDisponibilidad ? 1 : 0)
					+ " WHERE id = " + id;
			int filas = stmt.executeUpdate(sql);

			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

	public boolean eliminarSala(int id) {
		try {
			conectar();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM Sala WHERE id = " + id;
			int filas = stmt.executeUpdate(sql);

			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar();
		}
	}

}