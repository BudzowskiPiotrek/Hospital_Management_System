package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel; // Importar DefaultTableModel

@SuppressWarnings("serial")
public class PanelVerReportes extends JPanel {

    private JTable tablaEstadisticas;
    private DefaultTableModel modeloEstadisticas;

    // Colores y estilos extraídos directamente del PanelAdmin original para
    // consistencia
    // Se ajusta el color de fondo principal para que sea consistente con los
    // paneles de gestión
    private Color mainPanelBgColor = Color.decode("#E3242B"); // Fondo del panel principal (rojo)
    private Color titleFgColor = Color.white; // Color del texto del título principal

    private Color tableHeaderBg = Color.decode("#f2f2f2"); // Fondo del encabezado de la tabla
    private Color tableHeaderFg = Color.decode("#333"); // Color de texto del encabezado de la tabla

    public PanelVerReportes() {
        this.setLayout(new BorderLayout());
        this.setBackground(mainPanelBgColor); // Aplicar el color de fondo principal

        JLabel titulo = new JLabel("Estadísticas Generales", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(titleFgColor); // Aplicar color de texto del título
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        this.add(titulo, BorderLayout.NORTH);

        // Columnas de la tabla para mostrar las estadísticas basadas en el ERD
        String[] columnas = { "Tipo De Dato", "Cantidad" };

        // Datos de ejemplo de las estadísticas, basados en el ERD
        Object[][] datos = {
                { "Total Empleados", 150 },
                { "Médicos Registrados", 50 },
                { "Enfermeros Registrados", 70 },
                { "Personal de Mantenimiento", 15 },
                { "Administrativos (No Admin)", 15 },
                { "Pacientes Activos", 1200 },
                { "Citas Programadas (Hoy)", 85 },
                { "Citas Programadas (Semana)", 420 },
                { "Salas Disponibles", 10 },
                { "Habitaciones Ocupadas", 25 },
                { "Quirófanos Ocupados", 3 },
                { "Consultorios en Uso", 8 },
                { "Diagnósticos Registrados", 5000 },
                { "Tratamientos en Curso", 350 },
                { "Recetas Emitidas (Hoy)", 180 },
                { "Altas Hospitalarias (Hoy)", 15 },
                { "Turnos Activos", 140 }
        };

        modeloEstadisticas = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        tablaEstadisticas = new JTable(modeloEstadisticas);
        tablaEstadisticas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEstadisticas.setRowHeight(25);
        tablaEstadisticas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaEstadisticas.setBackground(Color.WHITE); // Fondo blanco para la tabla
        tablaEstadisticas.setForeground(Color.BLACK); // Texto negro para la tabla
        tablaEstadisticas.getTableHeader().setBackground(tableHeaderBg); // Fondo del encabezado
        tablaEstadisticas.getTableHeader().setForeground(tableHeaderFg); // Color de texto del encabezado

        JScrollPane scrollPane = new JScrollPane(tablaEstadisticas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // Margen alrededor de la tabla
        scrollPane.getViewport().setBackground(Color.WHITE); // Asegura que el fondo del viewport sea blanco

        this.add(scrollPane, BorderLayout.CENTER);
    }
}
