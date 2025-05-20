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
  private int filaSeleccionadaPaciente = -1;

  // Colores y estilos extraídos directamente del PanelAdmin original para
  // consistencia
  private Color mainPanelBgColor = Color.decode("#E3242B"); // Fondo del panel principal de gestión
  private Color cardsPanelBgColor = Color.decode("#B0E0E6"); // Fondo del panel con CardLayout (tabla/formulario)
  private Color formPanelBgColor = Color.decode("#24e3dc"); // Fondo del panel del formulario
  private Color tableButtonsPanelBgColor = Color.decode("#212f3d"); // Fondo del panel de botones de la tabla

  private Color gestionButtonBgColor = Color.decode("#CD7F32"); // Color de fondo de los botones de gestión
  private Color gestionButtonFgColor = Color.white; // Color de texto de los botones de gestión
  private Font gestionButtonFont = new Font("Arial", Font.BOLD, 15); // Fuente de los botones de gestión
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

    String[] columnasPacientes = { "DNI", "Nombre", "F. Nacimiento", "Teléfono", "Dirección" };
    Object[][] datosPacientes = {
        { "98765432X", "Ana García", "1990-05-10", "611223344", "C/ Falsa 123" },
        { "11223344Y", "Pedro Ruíz", "1985-11-20", "622334455", "Av. Siempre Viva 45" }
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

    JPanel panelBotonesTablaPacientes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesTablaPacientes.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla
    JButton btnRegistrar = new JButton("Registrar Paciente");
    styleGestionButton(btnRegistrar);
    JButton btnEditar = new JButton("Editar Paciente");
    styleGestionButton(btnEditar);
    JButton btnEliminar = new JButton("Eliminar Paciente");
    styleGestionButton(btnEliminar);
    panelBotonesTablaPacientes.add(btnRegistrar);
    panelBotonesTablaPacientes.add(btnEditar);
    panelBotonesTablaPacientes.add(btnEliminar);
    panelTablaPacientesLocal.add(panelBotonesTablaPacientes, BorderLayout.SOUTH);

    // --- Vista de Formulario de Paciente ---
    JPanel panelFormularioPacienteLocal = new JPanel(new GridBagLayout());
    panelFormularioPacienteLocal.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioPacienteLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblDNIpaciente = new JLabel("DNI:");
    lblDNIpaciente.setFont(labelFont);
    lblDNIpaciente.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioPacienteLocal.add(lblDNIpaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    txtDNIpaciente = new JTextField(20);
    txtDNIpaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtDNIpaciente, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblNombrePaciente = new JLabel("Nombre:");
    lblNombrePaciente.setFont(labelFont);
    lblNombrePaciente.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioPacienteLocal.add(lblNombrePaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    txtNombrePaciente = new JTextField(20);
    txtNombrePaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtNombrePaciente, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblFechaNacimientoPaciente = new JLabel("F. Nacimiento:");
    lblFechaNacimientoPaciente.setFont(labelFont);
    lblFechaNacimientoPaciente.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioPacienteLocal.add(lblFechaNacimientoPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    txtFechaNacimientoPaciente = new JTextField(20);
    txtFechaNacimientoPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtFechaNacimientoPaciente, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    JLabel lblTelefonoPaciente = new JLabel("Teléfono:");
    lblTelefonoPaciente.setFont(labelFont);
    lblTelefonoPaciente.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioPacienteLocal.add(lblTelefonoPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    txtTelefonoPaciente = new JTextField(20);
    txtTelefonoPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtTelefonoPaciente, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    JLabel lblDireccionPaciente = new JLabel("Dirección:");
    lblDireccionPaciente.setFont(labelFont);
    lblDireccionPaciente.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioPacienteLocal.add(lblDireccionPaciente, gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    txtDireccionPaciente = new JTextField(20);
    txtDireccionPaciente.setFont(textFieldFont);
    panelFormularioPacienteLocal.add(txtDireccionPaciente, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormularioPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormularioPaciente.setBackground(formPanelBgColor); // Fondo del panel de botones del formulario
    JButton btnGuardarPaciente = new JButton("Guardar");
    styleGestionButton(btnGuardarPaciente);
    JButton btnCancelarPaciente = new JButton("Cancelar");
    styleGestionButton(btnCancelarPaciente);
    panelBotonesFormularioPaciente.add(btnGuardarPaciente);
    panelBotonesFormularioPaciente.add(btnCancelarPaciente);
    panelFormularioPacienteLocal.add(panelBotonesFormularioPaciente, gbc);

    cardsPanel.add(panelTablaPacientesLocal, "Tabla");
    cardsPanel.add(panelFormularioPacienteLocal, "Formulario");
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnRegistrar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposPaciente();
      filaSeleccionadaPaciente = -1;
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

    btnGuardarPaciente.addActionListener(e -> {
      String dni = txtDNIpaciente.getText().trim();
      String nombre = txtNombrePaciente.getText().trim();
      String fechaNac = txtFechaNacimientoPaciente.getText().trim();
      String telefono = txtTelefonoPaciente.getText().trim();
      String direccion = txtDireccionPaciente.getText().trim();

      if (dni.isEmpty() || nombre.isEmpty() || fechaNac.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      if (filaSeleccionadaPaciente == -1) {
        modeloPacientes.addRow(new Object[] { dni, nombre, fechaNac, telefono, direccion });
      } else {
        modeloPacientes.setValueAt(dni, filaSeleccionadaPaciente, 0);
        modeloPacientes.setValueAt(nombre, filaSeleccionadaPaciente, 1);
        modeloPacientes.setValueAt(fechaNac, filaSeleccionadaPaciente, 2);
        modeloPacientes.setValueAt(telefono, filaSeleccionadaPaciente, 3);
        modeloPacientes.setValueAt(direccion, filaSeleccionadaPaciente, 4);
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposPaciente();
    });

    btnCancelarPaciente.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposPaciente();
    });
  }

  /**
   * Limpia los campos de texto del formulario de paciente.
   */
  private void limpiarCamposPaciente() {
    txtDNIpaciente.setText("");
    txtNombrePaciente.setText("");
    txtFechaNacimientoPaciente.setText("");
    txtTelefonoPaciente.setText("");
    txtDireccionPaciente.setText("");
  }

  /**
   * Aplica un estilo consistente a los botones de gestión (Registrar, Editar,
   * Eliminar, Guardar, Cancelar).
   * 
   * @param button El JButton al que se aplicará el estilo.
   */
  private void styleGestionButton(JButton button) {
    button.setBackground(gestionButtonBgColor);
    button.setForeground(gestionButtonFgColor);
    button.setFont(gestionButtonFont);
    button.setFocusPainted(false);
    button.setBorder(gestionButtonBorder);
    button.setPreferredSize(new Dimension(180, 45));
  }
}
