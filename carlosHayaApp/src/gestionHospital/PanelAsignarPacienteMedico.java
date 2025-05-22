package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent; // Se mantiene por si se usa en otro contexto, aunque ya no para el btnAsignarPaciente
import javax.swing.event.ListSelectionListener; // Se mantiene por si se usa en otro contexto

@SuppressWarnings("serial")
public class PanelAsignarPacienteMedico extends JPanel {

  private JPanel cardsPanel; // Panel con CardLayout para alternar entre tabla de médicos y formulario de
                             // asignación
  private DefaultTableModel modeloMedicos;
  private JTable tablaMedicos;
  private JButton btnAsignarPaciente;

  // Componentes del formulario de asignación de paciente
  private JLabel lblMedicoDNI, lblMedicoNombre, lblMedicoEspecialidad;
  private JTextField txtPacienteDNI, txtPacienteNombre;
  private JButton btnGuardarAsignacion, btnCancelarAsignacion;

  // Datos del médico seleccionado para pasar al formulario
  private String medicoDNISeleccionado;
  private String medicoNombreSeleccionado;
  private String medicoEspecialidadSeleccionada;

  // Colores y estilos consistentes con el resto de la aplicación
  private Color mainPanelBgColor = Color.decode("#E3242B"); // Fondo del panel principal
  private Color cardsPanelBgColor = Color.decode("#B0E0E6"); // Fondo del panel con CardLayout
  private Color formPanelBgColor = Color.decode("#24e3dc"); // Fondo del formulario de asignación

  private Color gestionButtonFgColor = Color.white; // Color de texto de los botones de gestión
  private Font gestionButtonFont = new Font("Arial", Font.BOLD, 15); // Fuente de los botones de gestión
  private Border gestionButtonBorder = BorderFactory.createLineBorder(Color.decode("#006D77"), 1); // Borde de los
                                                                                                   // botones de
                                                                                                   // gestión

  private Color titleFgColor = Color.white; // Color del texto del título principal
  private Color labelFgColor = Color.DARK_GRAY; // Color del texto de las etiquetas del formulario

  public PanelAsignarPacienteMedico() {
    this.setLayout(new BorderLayout());
    this.setBackground(mainPanelBgColor); // Aplicar el color de fondo principal

    JLabel titulo = new JLabel("Asignar Paciente a Médico", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 30));
    titulo.setForeground(titleFgColor);
    titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
    this.add(titulo, BorderLayout.NORTH);

    cardsPanel = new JPanel(new CardLayout());
    cardsPanel.setBackground(cardsPanelBgColor); // Fondo del panel de cards
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Vista de Tabla de Médicos ---
    JPanel panelTablaMedicos = new JPanel(new BorderLayout());
    panelTablaMedicos.setBackground(cardsPanelBgColor); // Fondo de la tabla de médicos

    String[] columnasMedicos = { "DNI", "Nombre", "Especialidad" };
    Object[][] datosMedicos = { { "11111111M", "Dr. Juan Pérez", "Cardiología" },
        { "22222222N", "Dra. Elena Soto", "Pediatría" },
        { "33333333P", "Dr. Miguel Ángel", "Medicina General" } };
    modeloMedicos = new DefaultTableModel(datosMedicos, columnasMedicos) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Las celdas de la tabla no son editables directamente
      }
    };
    tablaMedicos = new JTable(modeloMedicos);
    tablaMedicos.setFont(new Font("Arial", Font.PLAIN, 14));
    tablaMedicos.setRowHeight(25);
    tablaMedicos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    tablaMedicos.setBackground(Color.WHITE);
    tablaMedicos.setForeground(Color.BLACK);
    tablaMedicos.getTableHeader().setBackground(Color.decode("#006D77"));
    tablaMedicos.getTableHeader().setForeground(Color.white);
    JScrollPane scrollTablaMedicos = new JScrollPane(tablaMedicos);
    scrollTablaMedicos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panelTablaMedicos.add(scrollTablaMedicos, BorderLayout.CENTER);

    JPanel panelBotonesTablaMedicos = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesTablaMedicos.setBackground(Color.decode("#728C69")); // Fondo del panel de botones de tabla
    btnAsignarPaciente = new JButton("Asignar Paciente");
    styleGestionButton(btnAsignarPaciente);
    // Anteriormente: btnAsignarPaciente.setEnabled(false);
    // ¡Ahora el botón siempre está habilitado!
    btnAsignarPaciente.setBackground(Color.decode("#006D77"));
    panelBotonesTablaMedicos.add(btnAsignarPaciente);
    panelTablaMedicos.add(panelBotonesTablaMedicos, BorderLayout.SOUTH);

    // --- Listener para la selección de fila en la tabla de médicos ---
    // Este listener ya NO se utiliza para habilitar/deshabilitar el botón "Asignar
    // Paciente"
    // Se ha eliminado su funcionalidad previa de cambiar el estado del botón.
    // Si se necesita para otra lógica en el futuro, se puede añadir aquí.
    tablaMedicos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        // Lógica adicional que dependa de la selección de fila puede ir aquí,
        // pero el botón 'Asignar Paciente' ya no se controla desde aquí.
      }
    });

    // --- Vista de Formulario de Asignación de Paciente ---
    JPanel panelFormularioAsignacion = new JPanel(new GridBagLayout());
    panelFormularioAsignacion.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioAsignacion.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font dataFont = new Font("Arial", Font.PLAIN, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    // Datos del Médico Seleccionado
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblDNI = new JLabel("DNI Médico:");
    lblDNI.setFont(labelFont);
    lblDNI.setForeground(labelFgColor);
    panelFormularioAsignacion.add(lblDNI, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    lblMedicoDNI = new JLabel("");
    lblMedicoDNI.setFont(dataFont);
    lblMedicoDNI.setForeground(Color.BLACK);
    panelFormularioAsignacion.add(lblMedicoDNI, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblNombre = new JLabel("Nombre Médico:");
    lblNombre.setFont(labelFont);
    lblNombre.setForeground(labelFgColor);
    panelFormularioAsignacion.add(lblNombre, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    lblMedicoNombre = new JLabel("");
    lblMedicoNombre.setFont(dataFont);
    lblMedicoNombre.setForeground(Color.BLACK);
    panelFormularioAsignacion.add(lblMedicoNombre, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblEspecialidad = new JLabel("Especialidad:");
    lblEspecialidad.setFont(labelFont);
    lblEspecialidad.setForeground(labelFgColor);
    panelFormularioAsignacion.add(lblEspecialidad, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    lblMedicoEspecialidad = new JLabel("");
    lblMedicoEspecialidad.setFont(dataFont);
    lblMedicoEspecialidad.setForeground(Color.BLACK);
    panelFormularioAsignacion.add(lblMedicoEspecialidad, gbc);

    // Separador
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    panelFormularioAsignacion.add(new JSeparator(), gbc);

    // Datos del Paciente a Asignar
    gbc.gridx = 0;
    gbc.gridy = 4;
    JLabel lblPacienteDNI = new JLabel("DNI Paciente:");
    lblPacienteDNI.setFont(labelFont);
    lblPacienteDNI.setForeground(labelFgColor);
    panelFormularioAsignacion.add(lblPacienteDNI, gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    txtPacienteDNI = new JTextField(20);
    txtPacienteDNI.setFont(textFieldFont);
    panelFormularioAsignacion.add(txtPacienteDNI, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    JLabel lblPacienteNombre = new JLabel("Nombre Paciente:");
    lblPacienteNombre.setFont(labelFont);
    lblPacienteNombre.setForeground(labelFgColor);
    panelFormularioAsignacion.add(lblPacienteNombre, gbc);
    gbc.gridx = 1;
    gbc.gridy = 5;
    txtPacienteNombre = new JTextField(20);
    txtPacienteNombre.setFont(textFieldFont);
    panelFormularioAsignacion.add(txtPacienteNombre, gbc);

    // Botones del formulario
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormulario = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormulario.setBackground(formPanelBgColor); // Fondo del panel de botones del formulario
    btnGuardarAsignacion = new JButton("Guardar");
    styleGestionButton(btnGuardarAsignacion);
    btnGuardarAsignacion.setPreferredSize(new Dimension(120, 45)); // Ajustado para un botón más ancho

    btnCancelarAsignacion = new JButton("Cancelar");
    styleGestionButton(btnCancelarAsignacion);
    btnCancelarAsignacion.setPreferredSize(new Dimension(120, 45)); // Ajustado para un botón más ancho

    panelBotonesFormulario.add(btnGuardarAsignacion);
    panelBotonesFormulario.add(btnCancelarAsignacion);
    panelFormularioAsignacion.add(panelBotonesFormulario, gbc);

    // Añadir las vistas al CardLayout
    cardsPanel.add(panelTablaMedicos, "TablaMedicos");
    cardsPanel.add(panelFormularioAsignacion, "FormularioAsignacion");

    // --- Acciones de los botones ---
    btnAsignarPaciente.addActionListener(e -> {
      int fila = tablaMedicos.getSelectedRow();
      if (fila != -1) { // ¡Verificación de selección aquí!
        medicoDNISeleccionado = modeloMedicos.getValueAt(fila, 0).toString();
        medicoNombreSeleccionado = modeloMedicos.getValueAt(fila, 1).toString();
        medicoEspecialidadSeleccionada = modeloMedicos.getValueAt(fila, 2).toString();

        // Actualizar los labels del formulario con los datos del médico
        lblMedicoDNI.setText(medicoDNISeleccionado);
        lblMedicoNombre.setText(medicoNombreSeleccionado);
        lblMedicoEspecialidad.setText(medicoEspecialidadSeleccionada);

        // Limpiar campos del paciente
        txtPacienteDNI.setText("");
        txtPacienteNombre.setText("");

        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "FormularioAsignacion");
      } else {
        // Mensaje JOptionPane si no hay médico seleccionado
        JOptionPane.showMessageDialog(this,
            "Por favor, seleccione un médico de la tabla para asignar un paciente.", "Error de Selección",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnGuardarAsignacion.addActionListener(e -> {
      String pacienteDNI = txtPacienteDNI.getText().trim();
      String pacienteNombre = txtPacienteNombre.getText().trim();

      if (pacienteDNI.isEmpty() || pacienteNombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el DNI y el nombre del paciente.",
            "Error de Validación", JOptionPane.WARNING_MESSAGE);
        return;
      }

      // Aquí iría la lógica para guardar la asignación en una base de datos
      // Por ahora, solo mostramos un mensaje de confirmación.
      JOptionPane.showMessageDialog(this,
          "Paciente " + pacienteNombre + " (DNI: " + pacienteDNI + ") asignado a:\n" + "Médico: "
              + medicoNombreSeleccionado + " (DNI: " + medicoDNISeleccionado + ", Especialidad: "
              + medicoEspecialidadSeleccionada + ")",
          "Asignación Exitosa", JOptionPane.INFORMATION_MESSAGE);

      // Volver a la tabla de médicos después de guardar
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "TablaMedicos");
    });

    btnCancelarAsignacion.addActionListener(e -> { // Listener para el botón Cancelar
      // Simplemente vuelve a la tabla de médicos sin guardar cambios
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "TablaMedicos");
    });
  }

  private void styleGestionButton(JButton button) {
    button.setBackground(Color.decode("#006D77"));
    button.setForeground(gestionButtonFgColor);
    button.setFont(gestionButtonFont); // Usa la fuente definida en la clase (Arial, BOLD, 15)
    button.setFocusPainted(false);
    button.setBorder(gestionButtonBorder);
    button.setPreferredSize(new Dimension(200, 45)); // Ajustado para un botón más ancho
  }
}