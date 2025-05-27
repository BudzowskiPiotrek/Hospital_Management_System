package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PanelVerPacientesAsignados extends JPanel {

  // --- Paleta de Colores ---
  // Un azul grisáceo oscuro para el fondo principal
  private final Color primaryBackgroundColor = Color.decode("#2C3E50");
  // Un azul vibrante para acentos y resaltados de selección
  private final Color accentColor = Color.decode("#3498DB");
  // Un color más oscuro para el encabezado de la tabla
  private final Color tableHeaderColor = Color.decode("#34495E");
  // Gris claro para las filas pares de la tabla (efecto cebra)
  private final Color tableRowEvenColor = Color.decode("#ECF0F1");
  // Blanco para las filas impares de la tabla (efecto cebra)
  private final Color tableRowOddColor = Color.decode("#FFFFFF");
  // Blanco para títulos y texto sobre fondos oscuros
  private final Color textColor = Color.WHITE;
  // Negro para el texto dentro de las celdas de la tabla
  private final Color tableTextColor = Color.BLACK;

  private JTable tablaPacientes;
  private DefaultTableModel modelo; // Se mantiene una referencia al modelo para futuras modificaciones (ej.
                                    // añadir/eliminar datos)

  public PanelVerPacientesAsignados() {
    // Establece el gestor de diseño principal con un espacio entre componentes
    setLayout(new BorderLayout(10, 10));
    // Establece el color de fondo del panel
    setBackground(primaryBackgroundColor);
    // Define el tamaño preferido del panel
    setPreferredSize(new Dimension(800, 600));
    // Inicializa y configura todos los componentes
    initComponents();
  }

  private void initComponents() {
    // --- Configuración del Panel del Título ---
    JPanel titlePanel = new JPanel();
    // El fondo del panel del título coincide con el del panel principal
    titlePanel.setBackground(primaryBackgroundColor);
    // Añade relleno alrededor del título
    titlePanel.setBorder(new EmptyBorder(20, 20, 10, 20));
    // Centra el título horizontalmente
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel titulo = new JLabel("Pacientes Asignados");
    // Fuente moderna, más grande y en negrita para el título
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
    // Color de texto blanco para contraste
    titulo.setForeground(textColor);
    titlePanel.add(titulo);
    // Coloca el panel del título en la parte superior (Norte) del BorderLayout
    add(titlePanel, BorderLayout.NORTH);

    // --- Configuración de Datos y Modelo de la Tabla ---
    String[] columnas = { "ID", "Nombre", "Edad", "Diagnóstico", "Habitación" };
    // Datos de ejemplo; estos datos se cargarían normalmente desde una base de
    // datos o fuente externa
    Object[][] datos = {
        { "P001", "Juan Pérez", 45, "Hipertensión", "101A" },
        { "P002", "María López", 30, "Diabetes", "102B" },
        { "P003", "Carlos Ruiz", 65, "Fractura", "103C" },
        { "P004", "Ana García", 28, "Gripe", "104D" },
        { "P005", "Pedro Martínez", 72, "Artritis", "105E" },
        { "P006", "Laura Sánchez", 50, "Asma", "106F" },
        { "P007", "Miguel Torres", 12, "Fiebre", "107G" }
    };

    modelo = new DefaultTableModel(datos, columnas) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Hace que todas las celdas de la tabla no sean editables
      }
    };

    tablaPacientes = new JTable(modelo);

    // --- Estilo Visual de la Tabla ---
    // Fuente para el contenido de las celdas de la tabla
    tablaPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
    // Aumenta la altura de las filas para mejor espaciado
    tablaPacientes.setRowHeight(30);
    // Color claro para las líneas de la cuadrícula de la tabla
    tablaPacientes.setGridColor(new Color(220, 220, 220));
    // Resalta la fila seleccionada con el color de acento
    tablaPacientes.setSelectionBackground(accentColor);
    // Texto blanco en la fila seleccionada para contraste
    tablaPacientes.setSelectionForeground(Color.WHITE);

    // Renderizador personalizado para aplicar colores alternos a las filas y
    // centrar el contenido
    tablaPacientes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
          int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Aplica colores de fondo alternos para las filas (efecto cebra)
        c.setBackground(row % 2 == 0 ? tableRowEvenColor : tableRowOddColor);
        // Establece el color de texto por defecto para las celdas
        c.setForeground(tableTextColor);
        // Centra el contenido en todas las celdas
        setHorizontalAlignment(JLabel.CENTER);

        // Sobrescribe los colores si la fila está seleccionada
        if (isSelected) {
          c.setBackground(accentColor);
          c.setForeground(Color.WHITE);
        }
        return c;
      }
    });

    // --- Estilo del Encabezado de la Tabla ---
    JTableHeader tableHeader = tablaPacientes.getTableHeader();
    // Fuente en negrita y más grande para los encabezados
    tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
    // Fondo más oscuro para el encabezado
    tableHeader.setBackground(tableHeaderColor);
    // Texto blanco para los encabezados
    tableHeader.setForeground(textColor);
    // Hace el encabezado más alto
    tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 40));
    // Centra el texto en las celdas del encabezado
    ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    // --- Panel Desplazable para la Tabla ---
    JScrollPane scrollPane = new JScrollPane(tablaPacientes);
    // Añade un borde alrededor del panel desplazable usando el color de acento
    scrollPane.setBorder(BorderFactory.createLineBorder(accentColor, 2));
    // Asegura que el fondo del área de visualización del scroll pane coincida con
    // el color de las filas
    scrollPane.getViewport().setBackground(tableRowOddColor);

    // --- Panel de Contenido para la Tabla (para aplicar relleno) ---
    JPanel contentPanel = new JPanel(new BorderLayout());
    // El fondo de este panel coincide con el del panel principal
    contentPanel.setBackground(primaryBackgroundColor);
    // Relleno alrededor del panel desplazable
    contentPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    // Añade el panel de contenido al centro del panel principal
    add(contentPanel, BorderLayout.CENTER);
  }
}