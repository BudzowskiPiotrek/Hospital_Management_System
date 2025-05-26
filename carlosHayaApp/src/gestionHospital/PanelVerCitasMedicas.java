package gestionHospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.DBConnection;
import clases.Turno;

import java.awt.*;
import java.util.ArrayList;

	public class PanelVerCitasMedicas extends JPanel {
		private DBConnection db;
		
	    public PanelVerCitasMedicas() {
	    	db = new DBConnection();
	    	
	        setLayout(new BorderLayout());
	        setBackground(Color.WHITE);

	        // Título
	        JLabel titulo = new JLabel("Listado de Citas Médicas", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 24));
	        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	        add(titulo, BorderLayout.NORTH);

	        // Datos de ejemplo
	        String[] columnas = { "DNI Paciente", "Día", "Hora Comienzo", "Hora Final" };
	        
	        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
	        JTable tabla = new JTable(modelo);
	        JScrollPane scrollPane = new JScrollPane(tabla);
	        cargarDatosCitas(modelo);
	        
	        add(scrollPane, BorderLayout.CENTER);
	    }

		private void cargarDatosCitas(DefaultTableModel modelo) {
			modelo.setRowCount(0);
			ArrayList<Turno> citas = (ArrayList<Turno>) db.obtenerCitasMedicos(Sesion.getUsuarioLogueado());
			for(Turno t : citas) {
				modelo.addRow(new Object[] {t.getPacienteDni(),t.getDia(),t.getHoraInicio(),t.getHoraFin()});
			}
			
		}
	}