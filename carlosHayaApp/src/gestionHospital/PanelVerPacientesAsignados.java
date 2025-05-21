package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelVerPacientesAsignados extends JPanel {  // Cambiado de Component a JPanel

    private Color colorbg = Color.decode("#212f3d");
    private JTable tablaPacientes;

    public PanelVerPacientesAsignados() {
        setBackground(colorbg);
        setPreferredSize(new Dimension(800, 600));  // Asegúrate de darle un tamaño adecuado
        initComponents();
    }

    private void initComponents() {
        // Crear el título
        JLabel titulo = new JLabel("Pacientes Asignados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);  // Usa add() directamente de JPanel

        // Columnas de la tabla
        String[] columnas = { "ID", "Nombre", "Edad", "Diagnóstico", "Habitación" };

        // Datos de ejemplo
        Object[][] datos = {
            { "P001", "Juan Pérez", 45, "Hipertensión", "101A" },
            { "P002", "María López", 30, "Diabetes", "102B" },
            { "P003", "Carlos Ruiz", 65, "Fractura", "103C" }
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            // Evitar que las celdas sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPacientes = new JTable(modelo);
        tablaPacientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaPacientes.setRowHeight(25);
        tablaPacientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Crear un JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Añadir el JScrollPane al centro del panel
        add(scrollPane, BorderLayout.CENTER);  // Usa add() directamente de JPanel
    }
}
