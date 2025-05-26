package gestionHospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

	public class PanelVerCitasMedicas extends JPanel {
	    public PanelVerCitasMedicas() {
	        setLayout(new BorderLayout());
	        setBackground(Color.WHITE);

	        // Título
	        JLabel titulo = new JLabel("Listado de Citas Médicas", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 24));
	        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	        add(titulo, BorderLayout.NORTH);

	        // Datos de ejemplo
	        String[] columnas = { "DNI Paciente", "Día", "Hora Comienzo", "Hora Final" };
	        Object[][] datos = {
	            { "12345678A", "2025-06-01", "10:00", "10:30" },
	            { "98765432B", "2025-06-01", "11:00", "11:45" },
	            { "55555555C", "2025-06-02", "09:00", "09:30" }
	        };

	        JTable tabla = new JTable(new DefaultTableModel(datos, columnas));
	        JScrollPane scrollPane = new JScrollPane(tabla);

	        add(scrollPane, BorderLayout.CENTER);
	    }
	}