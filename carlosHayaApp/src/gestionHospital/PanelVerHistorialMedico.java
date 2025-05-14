package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelVerHistorialMedico extends JPanel {

    private Color colorbg = Color.decode("#212f3d"); // Color de fondo para el panel
    private JTable tablaHistorial;

    public PanelVerHistorialMedico() {
        setLayout(new BorderLayout());
        setBackground(colorbg);
        initComponents();
    }

    private void initComponents() {
        JLabel titulo = new JLabel("Historial Médico de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Columnas de la tabla para mostrar el historial médico
        String[] columnas = { "ID", "Nombre", "Diagnóstico", "Fecha Diagnóstico", "Tratamiento", "Estado" };

        // Datos de ejemplo (en un escenario real estos datos pueden ser traídos de una base de datos)
        Object[][] datos = {
            { "P001", "Juan Pérez", "Hipertensión", "15/03/2023", "Medicamentos A, B", "En tratamiento" },
            { "P002", "María López", "Diabetes", "25/06/2024", "Insulina", "Controlado" },
            { "P003", "Carlos Ruiz", "Fractura", "02/08/2024", "Yeso, Rehabilitación", "En recuperación" }
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            // Evitar que las celdas sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHistorial = new JTable(modelo);
        tablaHistorial.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaHistorial.setRowHeight(25);
        tablaHistorial.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        add(scrollPane, BorderLayout.CENTER);
    }
}
