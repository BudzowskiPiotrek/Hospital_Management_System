package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
class PanelConsultarDatosHospital extends JPanel {
  private JComboBox<String> dataTypeComboBox;
  private JTable dataTable;
  private DefaultTableModel tableModel;
  private JLabel titleLabel;
  private JPanel panelLabel;
  private JPanel panelTable;
  private JPanel panelFilter;

  public PanelConsultarDatosHospital() {
    setLayout(new BorderLayout()); // Eliminado el espaciado horizontal y vertical
    setBackground(Color.decode("#212f3d")); // Color de fondo general del panel
    setBorder(new EmptyBorder(15, 15, 15, 15)); // Más espaciado interno

    // 1. Panel para el título (panelLabel)
    panelLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelLabel.setBackground(Color.decode("#E3242B")); // Color de fondo para el título
    titleLabel = new JLabel("Consulta de Datos del Hospital", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
    titleLabel.setForeground(Color.white); // Color del texto del título
    panelLabel.add(titleLabel);
    panelLabel.setPreferredSize(new Dimension(panelLabel.getPreferredSize().width, 60)); // Altura de 60px
    add(panelLabel, BorderLayout.NORTH); // Añadir el panel del título al NORTH

    // 2. Panel para la tabla (panelTable)
    panelTable = new JPanel(new BorderLayout());
    panelTable.setBackground(Color.decode("#212f3d")); // Color de fondo de la tabla a colorbg
    tableModel = new DefaultTableModel();
    dataTable = new JTable(tableModel);
    dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
    dataTable.setRowHeight(25);
    dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
    dataTable.getTableHeader().setBackground(Color.decode("#006D77"));
    dataTable.getTableHeader().setForeground(Color.white);
    dataTable.setFillsViewportHeight(true); // La tabla llena la altura del JScrollPane

    JScrollPane scrollPane = new JScrollPane(dataTable);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1)); // Borde para el scroll
    panelTable.add(scrollPane, BorderLayout.CENTER); // Añadir el scrollPane al panelTable
    add(panelTable, BorderLayout.CENTER); // Añadir el panelTable al CENTER (consumirá el espacio restante)

    // 3. Panel para los controles de filtro (panelFilter)
    panelFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panelFilter.setBackground(Color.decode("#728C69")); // Color de fondo para el filtro
    JLabel selectLabel = new JLabel("Seleccionar Tipo de Dato:");
    selectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    selectLabel.setForeground(Color.white); // Color de texto para el filtro
    panelFilter.add(selectLabel);

    String[] dataTypes = { "Empleados", "Pacientes", "Salas", "Citas" };
    dataTypeComboBox = new JComboBox<>(dataTypes);
    dataTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
    dataTypeComboBox.setBackground(Color.decode("#ECF0F1"));
    panelFilter.add(dataTypeComboBox);

    JButton loadButton = new JButton("Cargar Datos");
    styleButton(loadButton, Color.decode("#006D77")); // Aplicar estilo consistente
    panelFilter.add(loadButton);

    panelFilter.setPreferredSize(new Dimension(panelFilter.getPreferredSize().width, 80)); // Altura de 80px
    add(panelFilter, BorderLayout.SOUTH); // Añadir el panelFilter al SOUTH

    // Listener para el botón de cargar datos
    loadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        loadData(dataTypeComboBox.getSelectedItem().toString());
      }
    });

    // Cargar datos por defecto al iniciar (ej. Empleados)
    loadData("Empleados");
  }

  /**
   * Método para cargar datos en la tabla según el tipo seleccionado. En una
   * aplicación real, esto interactuaría con una base de datos o un modelo de
   * datos.
   *
   * @param dataType El tipo de datos a cargar (e.g., "Empleados", "Pacientes").
   */
  private void loadData(String dataType) {
    tableModel.setRowCount(0); // Limpiar datos existentes
    tableModel.setColumnCount(0); // Limpiar columnas existentes

    // Actualizar el texto del título según el tipo de dato cargado
    titleLabel.setText("Consulta de Datos de " + dataType);

    switch (dataType) {
      case "Empleados":
        tableModel.setColumnIdentifiers(new String[] { "ID Empleado", "Nombre", "Puesto", "Departamento" });
        // Datos de ejemplo
        tableModel.addRow(new Object[] { "E001", "Dr. Juan Pérez", "Médico", "Cardiología" });
        tableModel.addRow(new Object[] { "E002", "Enf. Ana García", "Enfermera", "UCI" });
        tableModel.addRow(new Object[] { "E003", "Adm. Laura López", "Administrativo", "Recepción" });
        break;
      case "Pacientes":
        tableModel.setColumnIdentifiers(new String[] { "ID Paciente", "Nombre", "Edad", "Médico Asignado" });
        // Datos de ejemplo
        tableModel.addRow(new Object[] { "P001", "María Fernández", 45, "Dr. Juan Pérez" });
        tableModel.addRow(new Object[] { "P002", "Carlos Ruiz", 28, "Dr. Elena Soto" });
        break;
      case "Salas":
        tableModel.setColumnIdentifiers(new String[] { "ID Sala", "Tipo", "Capacidad", "Disponibilidad" });
        // Datos de ejemplo
        tableModel.addRow(new Object[] { "S101", "Consulta", 1, "Disponible" });
        tableModel.addRow(new Object[] { "S205", "Quirófano", 5, "Ocupado" });
        break;
      case "Citas":
        tableModel.setColumnIdentifiers(new String[] { "ID Cita", "Paciente", "Médico", "Fecha", "Hora" });
        // Datos de ejemplo
        tableModel.addRow(new Object[] { "C001", "María Fernández", "Dr. Juan Pérez", "2025-05-22", "10:00" });
        tableModel.addRow(new Object[] { "C002", "Carlos Ruiz", "Dr. Elena Soto", "2025-05-22", "11:30" });
        break;
    }
  }

  /**
   * Aplica un estilo consistente a los botones dentro de este panel.
   *
   * @param button  El JButton al que se aplicará el estilo.
   * @param bgColor El color de fondo del botón.
   */
  private void styleButton(JButton button, Color bgColor) {
    button.setBackground(bgColor);
    button.setForeground(Color.white);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    button.setPreferredSize(new Dimension(150, 35));
  }
}
