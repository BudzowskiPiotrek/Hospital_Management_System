package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelGestionPacientes extends JPanel {

  private JPanel cardsPanel;
  private DefaultTableModel modeloPacientes;
  private JTable tablaPacientes;
  private JTextField txtDNIpaciente, txtNombrePaciente, txtFechaNacimientoPaciente, txtTelefonoPaciente,
      txtDireccionPaciente;

  // Fields for Historial and Habitacion forms
  private JTextArea txtHistorial; // For adding/editing patient history
  private JTextField txtAsignarHabitacion; // For assigning a room number

  // TextFields for general modification form (Historial and Habitacion display)
  private JTextField txtHistorialGeneral;
  private JTextField txtHabitacionGeneral;

  private int filaSeleccionadaPaciente = -1;

  // Colores y estilos extraídos directamente del PanelAdmin original para
  // consistencia
  private Color mainPanelBgColor = Color.decode("#E3242B"); // Fondo del panel principal de gestión
  private Color cardsPanelBgColor = Color.decode("#B0E0E6"); // Fondo del panel con CardLayout (tabla/formulario)
  private Color formPanelBgColor = Color.decode("#24e3dc"); // Fondo del panel del formulario
  private Color tableButtonsPanelBgColor = Color.decode("#212f3d"); // Fondo del panel de botones de la tabla

  private Color gestionButtonBgColor = Color.decode("#CD7F32"); // Color de fondo de los botones de gestión
  private Color gestionButtonFgColor = Color.white; // Color de texto de los botones de gestión
  private Font gestionButtonFont = new Font("Arial", Font.BOLD, 11); // Fuente de los botones de gestión
  private Border gestionButtonBorder = BorderFactory.createLineBorder(Color.decode("#CD7F32"), 1); // Borde de los
                                                                                                   // botones de gestión

  private Color titleFgColor = Color.white; // Color del texto del título principal del panel
  private Color labelFgColor = Color.DARK_GRAY; // Color del texto de las etiquetas del formulario

  private Color tableHeaderBg = Color.decode("#f2f2f2"); // Fondo del encabezado de la tabla
  private Color tableHeaderFg = Color.decode("#333"); // Color de texto del encabezado de la tabla

  public PanelGestionPacientes() {
    this.setLayout(new BorderLayout());
    this.setBackground(mainPanelBgColor); // Aplicar el color de fondo principal

    JLabel titulo = new JLabel("Gestión de Pacientes", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 30));
    titulo.setForeground(titleFgColor); // Aplicar color de texto del título
    titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
    this.add(titulo, BorderLayout.NORTH);

    cardsPanel = new JPanel(new CardLayout());
    cardsPanel.setBackground(cardsPanelBgColor); // Aplicar color de fondo de las cards

    // --- Vista de Tabla de Pacientes ---
    JPanel panelTablaPacientesLocal = new JPanel(new BorderLayout());
    panelTablaPacientesLocal.setBackground(cardsPanelBgColor); // Fondo de la tabla (dentro de cardsPanel)

    // MODIFIED: Added "Historial" and "Habitacion" columns
    String[] columnasPacientes = { "DNI", "Nombre", "F. Nacimiento", "Teléfono", "Dirección", "Historial",
        "Habitación" };
    Object[][] datosPacientes = {
        { "98765432X", "Ana García", "1990-05-10", "611223344", "C/ Falsa 123", "", "" },
        { "11223344Y", "Pedro Ruíz", "1985-11-20", "622334455", "Av. Siempre Viva 45", "", "" }
    };
    modeloPacientes = new DefaultTableModel(datosPacientes, columnasPacientes) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tablaPacientes = new JTable(modeloPacientes);
    tablaPacientes.setFont(new Font("Arial", Font.PLAIN, 14));
    tablaPacientes.setRowHeight(25);
    tablaPacientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    tablaPacientes.setBackground(Color.WHITE);
    tablaPacientes.setForeground(Color.BLACK);
    tablaPacientes.getTableHeader().setBackground(tableHeaderBg);
    tablaPacientes.getTableHeader().setForeground(tableHeaderFg);
    JScrollPane scrollTablaPacientes = new JScrollPane(tablaPacientes);
    scrollTablaPacientes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panelTablaPacientesLocal.add(scrollTablaPacientes, BorderLayout.CENTER);

    JPanel panelBotonesTablaPacientes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10)); // Reduced hgap
    panelBotonesTablaPacientes.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla

    JButton btnRegistrar = new JButton("Registrar Paciente");
    styleGestionButton(btnRegistrar);
    JButton btnEditar = new JButton("Editar Paciente");
    styleGestionButton(btnEditar);
    JButton btnEliminar = new JButton("Eliminar Paciente");
    styleGestionButton(btnEliminar);
    JButton btnAgreagrHistorial = new JButton("Agregar Historial");
    styleGestionButton(btnAgreagrHistorial);
    JButton btnAsignarHabitacion = new JButton("Asignar Habitacion");
    styleGestionButton(btnAsignarHabitacion);

    panelBotonesTablaPacientes.add(btnRegistrar);
    panelBotonesTablaPacientes.add(btnEditar);
    panelBotonesTablaPacientes.add(btnEliminar);
    panelBotonesTablaPacientes.add(btnAgreagrHistorial);
    panelBotonesTablaPacientes.add(btnAsignarHabitacion);
    panelTablaPacientesLocal.add(panelBotonesTablaPacientes, BorderLayout.SOUTH);

    // --- Vista de Formulario de Paciente (Add/Modify) ---
    JPanel panelFormularioPacienteLocal = new JPanel(new GridBagLayout());
    panelFormularioPacienteLocal.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioPacienteLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    // DNI
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblDNIpaciente = new JLabel("DNI:");
    lblDNIpaciente.setFont(labelFont);
    lblDNIpaciente.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblDNIpaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    txtDNIpaciente = new JTextField(20);
    txtDNIpaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtDNIpaciente, gbc);

    // Nombre
    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblNombrePaciente = new JLabel("Nombre:");
    lblNombrePaciente.setFont(labelFont);
    lblNombrePaciente.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblNombrePaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    txtNombrePaciente = new JTextField(20);
    txtNombrePaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtNombrePaciente, gbc);

    // Fecha de Nacimiento
    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblFechaNacimientoPaciente = new JLabel("F. Nacimiento:");
    lblFechaNacimientoPaciente.setFont(labelFont);
    lblFechaNacimientoPaciente.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblFechaNacimientoPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    txtFechaNacimientoPaciente = new JTextField(20);
    txtFechaNacimientoPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtFechaNacimientoPaciente, gbc);

    // Teléfono
    gbc.gridx = 0;
    gbc.gridy = 3;
    JLabel lblTelefonoPaciente = new JLabel("Teléfono:");
    lblTelefonoPaciente.setFont(labelFont);
    lblTelefonoPaciente.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblTelefonoPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    txtTelefonoPaciente = new JTextField(20);
    txtTelefonoPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtTelefonoPaciente, gbc);

    // Dirección
    gbc.gridx = 0;
    gbc.gridy = 4;
    JLabel lblDireccionPaciente = new JLabel("Dirección:");
    lblDireccionPaciente.setFont(labelFont);
    lblDireccionPaciente.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblDireccionPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    txtDireccionPaciente = new JTextField(20);
    txtDireccionPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtDireccionPaciente, gbc);

    // Historial (read-only for general modification)
    gbc.gridx = 0;
    gbc.gridy = 5;
    JLabel lblHistorialGeneral = new JLabel("Historial (solo lectura):");
    lblHistorialGeneral.setFont(labelFont);
    lblHistorialGeneral.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblHistorialGeneral, gbc);
    gbc.gridx = 1;
    gbc.gridy = 5;
    txtHistorialGeneral = new JTextField(20); // Make it read-only
    txtHistorialGeneral.setFont(textFieldFont);
    txtHistorialGeneral.setEditable(false);
    panelFormularioPacienteLocal.add(txtHistorialGeneral, gbc);

    // Habitación (read-only for general modification)
    gbc.gridx = 0;
    gbc.gridy = 6;
    JLabel lblHabitacionGeneral = new JLabel("Habitación (solo lectura):");
    lblHabitacionGeneral.setFont(labelFont);
    lblHabitacionGeneral.setForeground(labelFgColor);
    panelFormularioPacienteLocal.add(lblHabitacionGeneral, gbc);
    gbc.gridx = 1;
    gbc.gridy = 6;
    txtHabitacionGeneral = new JTextField(20); // Make it read-only
    txtHabitacionGeneral.setFont(textFieldFont);
    txtHabitacionGeneral.setEditable(false);
    panelFormularioPacienteLocal.add(txtHabitacionGeneral, gbc);

    // Botones del formulario de paciente
    gbc.gridx = 0;
    gbc.gridy = 7; // Adjusted gridy
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormularioPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormularioPaciente.setBackground(formPanelBgColor);
    JButton btnGuardarPaciente = new JButton("Guardar");
    styleGestionButton(btnGuardarPaciente);
    JButton btnCancelarPaciente = new JButton("Cancelar");
    styleGestionButton(btnCancelarPaciente);
    panelBotonesFormularioPaciente.add(btnGuardarPaciente);
    panelBotonesFormularioPaciente.add(btnCancelarPaciente);
    panelFormularioPacienteLocal.add(panelBotonesFormularioPaciente, gbc);

    // --- Vista de Formulario para Agregar Historial ---
    JPanel panelAgregarHistorial = new JPanel(new GridBagLayout());
    panelAgregarHistorial.setBackground(formPanelBgColor);
    panelAgregarHistorial.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbcHistorial = new GridBagConstraints();
    gbcHistorial.insets = new Insets(10, 5, 10, 5);
    gbcHistorial.fill = GridBagConstraints.BOTH; // Allow text area to expand

    // Patient DNI (read-only)
    gbcHistorial.gridx = 0;
    gbcHistorial.gridy = 0;
    JLabel lblDniHistorial = new JLabel("DNI Paciente:");
    lblDniHistorial.setFont(labelFont);
    lblDniHistorial.setForeground(labelFgColor);
    panelAgregarHistorial.add(lblDniHistorial, gbcHistorial);
    gbcHistorial.gridx = 1;
    gbcHistorial.gridy = 0;
    JTextField txtDniPacienteHistorial = new JTextField(20);
    txtDniPacienteHistorial.setFont(textFieldFont);
    txtDniPacienteHistorial.setEditable(false);
    panelAgregarHistorial.add(txtDniPacienteHistorial, gbcHistorial);

    // Patient Name (read-only)
    gbcHistorial.gridx = 0;
    gbcHistorial.gridy = 1;
    JLabel lblNombreHistorial = new JLabel("Nombre Paciente:");
    lblNombreHistorial.setFont(labelFont);
    lblNombreHistorial.setForeground(labelFgColor);
    panelAgregarHistorial.add(lblNombreHistorial, gbcHistorial);
    gbcHistorial.gridx = 1;
    gbcHistorial.gridy = 1;
    JTextField txtNombrePacienteHistorial = new JTextField(20);
    txtNombrePacienteHistorial.setFont(textFieldFont);
    txtNombrePacienteHistorial.setEditable(false);
    panelAgregarHistorial.add(txtNombrePacienteHistorial, gbcHistorial);

    // Historial input (JTextArea)
    gbcHistorial.gridx = 0;
    gbcHistorial.gridy = 2;
    JLabel lblHistorial = new JLabel("Historial:");
    lblHistorial.setFont(labelFont);
    lblHistorial.setForeground(labelFgColor);
    panelAgregarHistorial.add(lblHistorial, gbcHistorial);
    gbcHistorial.gridx = 1;
    gbcHistorial.gridy = 2;
    gbcHistorial.weightx = 1.0;
    gbcHistorial.weighty = 1.0; // Allow it to expand vertically
    txtHistorial = new JTextArea(10, 30); // 10 rows, 30 columns
    txtHistorial.setFont(textFieldFont);
    txtHistorial.setLineWrap(true); // Wrap lines
    txtHistorial.setWrapStyleWord(true); // Wrap at word boundaries
    JScrollPane scrollHistorial = new JScrollPane(txtHistorial);
    panelAgregarHistorial.add(scrollHistorial, gbcHistorial);

    // Buttons for Historial
    gbcHistorial.gridx = 0;
    gbcHistorial.gridy = 3;
    gbcHistorial.gridwidth = 2;
    gbcHistorial.weighty = 0.0; // Reset weight for buttons
    gbcHistorial.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesHistorial = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesHistorial.setBackground(formPanelBgColor);
    JButton btnGuardarHistorial = new JButton("Guardar Historial");
    styleGestionButton(btnGuardarHistorial);
    JButton btnCancelarHistorial = new JButton("Cancelar");
    styleGestionButton(btnCancelarHistorial);
    panelBotonesHistorial.add(btnGuardarHistorial);
    panelBotonesHistorial.add(btnCancelarHistorial);
    panelAgregarHistorial.add(panelBotonesHistorial, gbcHistorial);

    // --- Vista de Formulario para Asignar Habitación ---
    JPanel panelAsignarHabitacion = new JPanel(new GridBagLayout());
    panelAsignarHabitacion.setBackground(formPanelBgColor);
    panelAsignarHabitacion.setBorder(new EmptyBorder(50, 50, 50, 50));
    GridBagConstraints gbcHabitacion = new GridBagConstraints();
    gbcHabitacion.insets = new Insets(10, 5, 10, 5);
    gbcHabitacion.fill = GridBagConstraints.HORIZONTAL;

    // Patient DNI (read-only)
    gbcHabitacion.gridx = 0;
    gbcHabitacion.gridy = 0;
    JLabel lblDniHabitacion = new JLabel("DNI Paciente:");
    lblDniHabitacion.setFont(labelFont);
    lblDniHabitacion.setForeground(labelFgColor);
    panelAsignarHabitacion.add(lblDniHabitacion, gbcHabitacion);
    gbcHabitacion.gridx = 1;
    gbcHabitacion.gridy = 0;
    JTextField txtDniPacienteHabitacion = new JTextField(20);
    txtDniPacienteHabitacion.setFont(textFieldFont);
    txtDniPacienteHabitacion.setEditable(false);
    panelAsignarHabitacion.add(txtDniPacienteHabitacion, gbcHabitacion);

    // Patient Name (read-only)
    gbcHabitacion.gridx = 0;
    gbcHabitacion.gridy = 1;
    JLabel lblNombreHabitacion = new JLabel("Nombre Paciente:");
    lblNombreHabitacion.setFont(labelFont);
    lblNombreHabitacion.setForeground(labelFgColor);
    panelAsignarHabitacion.add(lblNombreHabitacion, gbcHabitacion);
    gbcHabitacion.gridx = 1;
    gbcHabitacion.gridy = 1;
    JTextField txtNombrePacienteHabitacion = new JTextField(20);
    txtNombrePacienteHabitacion.setFont(textFieldFont);
    txtNombrePacienteHabitacion.setEditable(false);
    panelAsignarHabitacion.add(txtNombrePacienteHabitacion, gbcHabitacion);

    // Habitación input
    gbcHabitacion.gridx = 0;
    gbcHabitacion.gridy = 2;
    JLabel lblHabitacion = new JLabel("Asignar Habitación:");
    lblHabitacion.setFont(labelFont);
    lblHabitacion.setForeground(labelFgColor);
    panelAsignarHabitacion.add(lblHabitacion, gbcHabitacion);
    gbcHabitacion.gridx = 1;
    gbcHabitacion.gridy = 2;
    txtAsignarHabitacion = new JTextField(20);
    txtAsignarHabitacion.setFont(textFieldFont);
    panelAsignarHabitacion.add(txtAsignarHabitacion, gbcHabitacion);

    // Buttons for Asignar Habitación
    gbcHabitacion.gridx = 0;
    gbcHabitacion.gridy = 3;
    gbcHabitacion.gridwidth = 2;
    gbcHabitacion.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesHabitacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesHabitacion.setBackground(formPanelBgColor);
    JButton btnGuardarHabitacion = new JButton("Guardar Habitación");
    styleGestionButton(btnGuardarHabitacion);
    JButton btnCancelarHabitacion = new JButton("Cancelar");
    styleGestionButton(btnCancelarHabitacion);
    panelBotonesHabitacion.add(btnGuardarHabitacion);
    panelBotonesHabitacion.add(btnCancelarHabitacion);
    panelAsignarHabitacion.add(panelBotonesHabitacion, gbcHabitacion);

    // Add all views to the CardLayout
    cardsPanel.add(panelTablaPacientesLocal, "Tabla");
    cardsPanel.add(panelFormularioPacienteLocal, "Formulario");
    cardsPanel.add(panelAgregarHistorial, "AgregarHistorial"); // NEW
    cardsPanel.add(panelAsignarHabitacion, "AsignarHabitacion"); // NEW
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnRegistrar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposPaciente();
      txtDNIpaciente.setEditable(true); // DNI should be editable when adding a new patient
      filaSeleccionadaPaciente = -1; // No row selected for adding
    });

    btnEditar.addActionListener(e -> {
      filaSeleccionadaPaciente = tablaPacientes.getSelectedRow();
      if (filaSeleccionadaPaciente != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
        txtDNIpaciente.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 0).toString());
        txtNombrePaciente.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 1).toString());
        txtFechaNacimientoPaciente.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 2).toString());
        txtTelefonoPaciente.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 3).toString());
        txtDireccionPaciente.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 4).toString());
        // Populate Historial and Habitacion when modifying (read-only)
        txtHistorialGeneral.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 5).toString());
        txtHabitacionGeneral.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 6).toString());
        txtDNIpaciente.setEditable(false); // DNI should not be editable when modifying
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un paciente para editar.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnEliminar.addActionListener(e -> {
      int fila = tablaPacientes.getSelectedRow();
      if (fila != -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este paciente?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          modeloPacientes.removeRow(fila);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un paciente para eliminar.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // Action listener for "Agregar Historial"
    btnAgreagrHistorial.addActionListener(e -> {
      filaSeleccionadaPaciente = tablaPacientes.getSelectedRow();
      if (filaSeleccionadaPaciente != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "AgregarHistorial");
        txtDniPacienteHistorial.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 0).toString());
        txtNombrePacienteHistorial.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 1).toString());
        txtHistorial.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 5).toString()); // Load existing
                                                                                                  // historial
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un paciente para agregar historial.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // Action listener for "Asignar Habitacion"
    btnAsignarHabitacion.addActionListener(e -> {
      filaSeleccionadaPaciente = tablaPacientes.getSelectedRow();
      if (filaSeleccionadaPaciente != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "AsignarHabitacion");
        txtDniPacienteHabitacion.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 0).toString());
        txtNombrePacienteHabitacion.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 1).toString());
        txtAsignarHabitacion.setText(modeloPacientes.getValueAt(filaSeleccionadaPaciente, 6).toString()); // Load
                                                                                                          // existing
                                                                                                          // room
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un paciente para asignar una habitación.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnGuardarPaciente.addActionListener(e -> {
      String dni = txtDNIpaciente.getText().trim();
      String nombre = txtNombrePaciente.getText().trim();
      String fechaNac = txtFechaNacimientoPaciente.getText().trim();
      String telefono = txtTelefonoPaciente.getText().trim();
      String direccion = txtDireccionPaciente.getText().trim();
      String historial = txtHistorialGeneral.getText().trim(); // Get from read-only field
      String habitacion = txtHabitacionGeneral.getText().trim(); // Get from read-only field

      if (dni.isEmpty() || nombre.isEmpty() || fechaNac.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos principales son obligatorios.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      if (filaSeleccionadaPaciente == -1) { // Agregar nuevo paciente
        // Add empty Historial and Habitacion when adding a new patient
        modeloPacientes.addRow(new Object[] { dni, nombre, fechaNac, telefono, direccion, "", "" });
      } else { // Editar paciente existente
        modeloPacientes.setValueAt(dni, filaSeleccionadaPaciente, 0);
        modeloPacientes.setValueAt(nombre, filaSeleccionadaPaciente, 1);
        modeloPacientes.setValueAt(fechaNac, filaSeleccionadaPaciente, 2);
        modeloPacientes.setValueAt(telefono, filaSeleccionadaPaciente, 3);
        modeloPacientes.setValueAt(direccion, filaSeleccionadaPaciente, 4);
        // Historial and Habitacion remain unchanged during general edit
        modeloPacientes.setValueAt(historial, filaSeleccionadaPaciente, 5);
        modeloPacientes.setValueAt(habitacion, filaSeleccionadaPaciente, 6);
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposPaciente();
    });

    btnCancelarPaciente.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposPaciente();
    });

    // Action listener for saving Historial
    btnGuardarHistorial.addActionListener(e -> {
      String historialContent = txtHistorial.getText().trim();
      if (filaSeleccionadaPaciente != -1) {
        modeloPacientes.setValueAt(historialContent, filaSeleccionadaPaciente, 5); // Update Historial column (index 5)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // Action listener for canceling Historial
    btnCancelarHistorial.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });

    // Action listener for saving Habitacion
    btnGuardarHabitacion.addActionListener(e -> {
      String habitacionNum = txtAsignarHabitacion.getText().trim();
      if (habitacionNum.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de Habitación no puede estar vacío.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (filaSeleccionadaPaciente != -1) {
        modeloPacientes.setValueAt(habitacionNum, filaSeleccionadaPaciente, 6); // Update Habitacion column (index 6)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // Action listener for canceling Habitacion
    btnCancelarHabitacion.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });
  }

  /**
   * Limpia los campos de texto del formulario de paciente y los campos de los
   * formularios de historial y habitación.
   */
  private void limpiarCamposPaciente() {
    txtDNIpaciente.setText("");
    txtNombrePaciente.setText("");
    txtFechaNacimientoPaciente.setText("");
    txtTelefonoPaciente.setText("");
    txtDireccionPaciente.setText("");
    txtHistorialGeneral.setText(""); // Clear historial display in general form
    txtHabitacionGeneral.setText(""); // Clear habitacion display in general form
    txtHistorial.setText(""); // Clear historial input field
    txtAsignarHabitacion.setText(""); // Clear habitacion input field
  }

  private void styleGestionButton(JButton button) {
    button.setBackground(gestionButtonBgColor);
    button.setForeground(gestionButtonFgColor);
    button.setFont(gestionButtonFont);
    button.setFocusPainted(false);
    button.setBorder(gestionButtonBorder);
    // Adjusted button preferred size for 5 buttons to fit well
    button.setPreferredSize(new Dimension(115, 35));
  }
}