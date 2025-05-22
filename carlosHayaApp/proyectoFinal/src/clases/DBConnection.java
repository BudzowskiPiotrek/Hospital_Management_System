package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class DBConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/carloshaya";//appcarloshaya
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

	// ########################### CLASE USUARIO ###########################

	// -------------- GESTION DE LOGIN --------------

	public String iniciarSesion(String dni, String contrasena) {
		String sql = "SELECT u.rol FROM Usuario u " + "JOIN Empleado e ON u.dni = e.usuario_dni "
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

	// -------------- GESTION DE REGISTRAR --------------

	// ########################### CLASE ADMINISTRADOR ###########################

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

	// ########################### CLASE ADMINISTRATIVO ###########################
	
	/*public  boolean asignarPacienteMedico(Paciente p1, Medico m1){
        try{
        	conectar();
        	Statement stat = conn.createStatement();
            String sql= "INSERT INTO turno(empleado_id,paciente_id,dia,hora_inicio,hora_fin) VALUE ("+m1.getDni()+")";
            int filas = stat.executeUpdate(sql);
            return filas > 0; 
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        } finally {
        	desconectar();
        }
    }*/
	
	 public boolean darCitaMedica(Turno t){
	        try{
	        	conectar();
	        	Statement stat = conn.createStatement();
	            String sql = "INSERT INTO turno(empleado_id,paciente_id,dia,hora_inicio,hora_fin) VALUE ('"+t.getMedicoId()+"','"+t.getPacienteId()+"','"+t.getDia()+"','"+t.getHoraInicio()+"'"+t.getHoraFin()+"');";
	          int filas = stat.executeUpdate(sql);
	            return filas>0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	        	desconectar();
	        }
	    }
	 
	 // ######################## CLASE MEDICO #######################################
	 
	 public boolean registrarDiagnostico(Diagnostico d) {
		 try {
			 conectar();
			Statement stat = conn.createStatement();
			String sql="INSERT INTO diagnostico(paciente_dni,medico_dni,descripcion,fecha) VALUE ('"+d.getPacienteId()+"','"+d.getMedicoId()+"','"+d.getDescripcion()+"','"+d.getFecha()+"')";
			int filas = stat.executeUpdate(sql);
			return filas>0;
		} catch (SQLException e) {
			e.printStackTrace();
			 return false;
		} finally {
			desconectar();
		}
		
	 }
	 
	 public boolean registrarReceta(Receta r) {
		 try {
			 conectar();
			Statement stat = conn.createStatement();
			String sql="INSERT INTO receta(paciente_dni,medico_dni,medicamentos,fecha) VALUE ('"+r.getPacienteId()+"','"+r.getMedicoId()+"','"+r.getMedicamentos()+"','"+r.getFecha()+"')";
			int filas = stat.executeUpdate(sql);
			return filas>0;
		} catch (SQLException e) {
			e.printStackTrace();
			 return false;
		} finally {
			desconectar();
		}
		
	 }
	 
	public ArrayList<Object[][]> mostrarHistorialPaciente(String idMedico) {
		String dni="", nombre="", apellido="",contacto="",obra="",medicamentos="",fecha_receta="",descripcion="", fecha_diagnostico="";
		ArrayList<Object[][]> datosObjectos = new ArrayList<>();
		try {
			conectar();
				Statement stat = conn.createStatement();
				String sql1 = "SELECT usuario.dni, usuario.nombre, usuario.apellido, paciente.contacto, paciente.obra_social, receta.medicamentos, receta.fecha, diagnostico.descripcion, diagnostico.fecha\r\n"
						+ "FROM usuario INNER JOIN paciente\r\n"
						+ "ON usuario.dni=paciente.usuario_dni\r\n"
						+ "INNER JOIN receta\r\n"
						+ "ON paciente.usuario_dni=receta.paciente_dni\r\n"
						+ "INNER JOIN diagnostico\r\n"
						+ "ON paciente.usuario_dni = diagnostico.paciente_dni\r\n"
						+ "INNER JOIN turno\r\n"
						+ "ON paciente.usuario_dni = turno.paciente_dni\r\n"
						+ "WHERE turno.empleado_dni = '"+idMedico+"';";
				Statement stat1 = conn.createStatement();
				ResultSet rs1 = stat1.executeQuery(sql1);
			
			
			
			
			while(rs1.next()) {
				dni =  rs1.getString("usuario.dni");
				nombre = rs1.getString("usuario.nombre");
				apellido = rs1.getString("usuario.apellido");
				contacto = rs1.getString("paciente.contacto");
				obra = rs1.getString("paciente.obra_social");
				medicamentos = rs1.getString("receta.medicamentos");
				fecha_receta = rs1.getString("receta.fecha");
				descripcion = rs1.getString("diagnostico.descripcion");
				fecha_diagnostico = rs1.getString("diagnostico.fecha");
				Object[][] datos = {{dni,nombre,apellido,contacto,obra,medicamentos,fecha_receta,descripcion,fecha_diagnostico}};
				datosObjectos.add(datos);
			}
			rs1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return  datosObjectos;
	}
	
	public ArrayList<Object[][]> pacientesAsignados(String idMedico) {
		String dni="", nombre="", apellido="",contacto="",obra="",salaId="";
		ArrayList<Object[][]> datosObjectos = new ArrayList<>();
		try {
			conectar();
				Statement stat = conn.createStatement();
				String sql1 = "SELECT usuario.dni, usuario.nombre, usuario.apellido, paciente.contacto, paciente.obra_social, paciente.salaId\r\n"
						+ "FROM usuario INNER JOIN paciente\r\n"
						+ "ON usuario.dni=paciente.usuario_dni\r\n"
						+ "INNER JOIN turno\r\n"
						+ "ON paciente.usuario_dni = turno.paciente_dni\r\n"
						+ "WHERE turno.empleado_dni = '"+idMedico+"';";
				Statement stat1 = conn.createStatement();
				ResultSet rs1 = stat1.executeQuery(sql1);
			
			
			
			
			while(rs1.next()) {
				dni =  rs1.getString("usuario.dni");
				nombre = rs1.getString("usuario.nombre");
				apellido = rs1.getString("usuario.apellido");
				contacto = rs1.getString("paciente.contacto");
				obra = rs1.getString("paciente.obra_social");
				salaId = rs1.getString("paciente.salaId");
				Object[][] datos = {{dni,nombre,apellido,contacto,obra,salaId}};
				datosObjectos.add(datos);
			}
			rs1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return  datosObjectos;
	}

}