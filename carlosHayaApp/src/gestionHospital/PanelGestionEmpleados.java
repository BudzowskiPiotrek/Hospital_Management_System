package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelGestionEmpleados extends JPanel {

  private JPanel cardsPanel; // Panel con CardLayout para vistas de tabla/formulario
  private DefaultTableModel modeloEmpleados;
  private JTable tablaEmpleados;
  private JTextField txtNombreEmpleado, txtDNIEmpleado, txtTelefonoEmpleado, txtPuestoEmpleado;

  // New TextFields for Asignar Sala and Asignar Turnos Forms
  private JTextField txtAsignarSala; // For assigning a room
  private JTextField txtAsignarTurno; // For assigning a shift

  private int filaSeleccionadaEmpleado = -1; // Para saber qué fila se está editando

  // Colores y estilos extraídos directamente del PanelAdmin original para
  // consistencia
  private Color mainPanelBgColor = Color.decode("#E3242B"); // Fondo del panel principal de gestión
  private Color cardsPanelBgColor = Color.decode("#B0E0E6"); // Fondo del panel con CardLayout (tabla/formulario)
  private Color formPanelBgColor = Color.decode("#24e3dc"); // Fondo del panel del formulario
  private Color tableButtonsPanelBgColor = Color.decode("#212f3d"); // Fondo del panel de botones de la tabla

  private Color gestionButtonBgColor = Color.decode("#CD7F32"); // Color de fondo de los botones de gestión
  private Color gestionButtonFgColor = Color.white; // Color de texto de los botones de gestión

  private Font gestionButtonFont = new Font("Arial", Font.BOLD, 11); // Further reduced font size to 11

  private Border gestionButtonBorder = BorderFactory.createLineBorder(Color.decode("#CD7F32"), 1); // Borde de los
                                                                                                   // botones de
                                                                                                   // gestión

  private Color titleFgColor = Color.white; // Color del texto del título principal del panel
  private Color labelFgColor = Color.DARK_GRAY; // Color del texto de las etiquetas del formulario

  private Color tableHeaderBg = Color.decode("#f2f2f2"); // Fondo del encabezado de la tabla
  private Color tableHeaderFg = Color.decode("#333"); // Color de texto del encabezado de la tabla

  public PanelGestionEmpleados() {
    this.setLayout(new BorderLayout());
    this.setBackground(mainPanelBgColor); // Aplicar el color de fondo principal

    JLabel titulo = new JLabel("Gestión de Empleados", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 30));
    titulo.setForeground(titleFgColor); // Aplicar color de texto del título
    titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
    this.add(titulo, BorderLayout.NORTH);

    // Panel para las "cards" (tabla y formulario)
    cardsPanel = new JPanel(new CardLayout());
    cardsPanel.setBackground(cardsPanelBgColor); // Aplicar color de fondo de las cards

    // --- Vista de Tabla de Empleados ---
    JPanel panelTablaEmpleadosLocal = new JPanel(new BorderLayout());
    panelTablaEmpleadosLocal.setBackground(cardsPanelBgColor); // Fondo de la tabla (dentro de cardsPanel)

    // MODIFIED: Added "Sala" and "Turno" columns
    String[] columnasEmpleados = { "DNI", "Nombre", "Teléfono", "Puesto", "Sala", "Turno" };
    Object[][] datosEmpleados = { { "12345678A", "Juan Pérez", "600111222", "Médico", "", "" },
        { "87654321B", "María López", "600333444", "Enfermera", "", "" },
        { "11223344C", "Carlos Ruiz", "600555666", "Administrativo", "", "" } };
    modeloEmpleados = new DefaultTableModel(datosEmpleados, columnasEmpleados) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Las celdas de la tabla no son editables directamente
      }
    };
    tablaEmpleados = new JTable(modeloEmpleados);
    tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 14));
    tablaEmpleados.setRowHeight(25);
    tablaEmpleados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    tablaEmpleados.setBackground(Color.WHITE);
    tablaEmpleados.setForeground(Color.BLACK);
    tablaEmpleados.getTableHeader().setBackground(tableHeaderBg);
    tablaEmpleados.getTableHeader().setForeground(tableHeaderFg);
    JScrollPane scrollTablaEmpleados = new JScrollPane(tablaEmpleados);
    scrollTablaEmpleados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panelTablaEmpleadosLocal.add(scrollTablaEmpleados, BorderLayout.CENTER);

    JPanel panelBotonesTablaEmpleados = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10)); // Further reduced
                                                                                              // hgap to 5
    panelBotonesTablaEmpleados.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla

    JButton btnAgregar = new JButton("Añadir Empleado");
    styleGestionButton(btnAgregar);

    JButton btnModificar = new JButton("Modificar Empleado");
    styleGestionButton(btnModificar);

    JButton btnBorrar = new JButton("Borrar Empleado");
    styleGestionButton(btnBorrar);

    JButton btnAsignarTurnos = new JButton("Asignar Turnos");
    styleGestionButton(btnAsignarTurnos);

    JButton btnAsignarSalas = new JButton("Asignar Salas");
    styleGestionButton(btnAsignarSalas);

    panelBotonesTablaEmpleados.add(btnAgregar);
    panelBotonesTablaEmpleados.add(btnModificar);
    panelBotonesTablaEmpleados.add(btnBorrar);
    panelBotonesTablaEmpleados.add(btnAsignarTurnos);
    panelBotonesTablaEmpleados.add(btnAsignarSalas);
    panelTablaEmpleadosLocal.add(panelBotonesTablaEmpleados, BorderLayout.SOUTH);

    // --- Vista de Formulario de Empleado (Add/Modify) ---
    JPanel panelFormularioEmpleadoLocal = new JPanel(new GridBagLayout());
    panelFormularioEmpleadoLocal.setBackground(formPanelBgColor);
    panelFormularioEmpleadoLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    // DNI
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblDNI = new JLabel("DNI:");
    lblDNI.setFont(labelFont);
    lblDNI.setForeground(labelFgColor);
    panelFormularioEmpleadoLocal.add(lblDNI, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    txtDNIEmpleado = new JTextField(20);
    txtDNIEmpleado.setFont(textFieldFont);
    panelFormularioEmpleadoLocal.add(txtDNIEmpleado, gbc);

    // Nombre
    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(labelFont);
    lblNombre.setForeground(labelFgColor);
    panelFormularioEmpleadoLocal.add(lblNombre, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    txtNombreEmpleado = new JTextField(20);
    txtNombreEmpleado.setFont(textFieldFont);
    panelFormularioEmpleadoLocal.add(txtNombreEmpleado, gbc);

    // Teléfono
    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblTelefono = new JLabel("Teléfono:");
    lblTelefono.setFont(labelFont);
    lblTelefono.setForeground(labelFgColor);
    panelFormularioEmpleadoLocal.add(lblTelefono, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    txtTelefonoEmpleado = new JTextField(20);
    txtTelefonoEmpleado.setFont(textFieldFont);
    panelFormularioEmpleadoLocal.add(txtTelefonoEmpleado, gbc);

    // Puesto
    gbc.gridx = 0;
    gbc.gridy = 3;
    JLabel lblPuesto = new JLabel("Puesto:");
    lblPuesto.setFont(labelFont);
    lblPuesto.setForeground(labelFgColor);
    panelFormularioEmpleadoLocal.add(lblPuesto, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    txtPuestoEmpleado = new JTextField(20);
    txtPuestoEmpleado.setFont(textFieldFont);
    panelFormularioEmpleadoLocal.add(txtPuestoEmpleado, gbc);

    // Botones del formulario de empleado
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormulario = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormulario.setBackground(formPanelBgColor);
    JButton btnGuardarEmpleado = new JButton("Guardar");
    styleGestionButton(btnGuardarEmpleado);
    JButton btnCancelarEmpleado = new JButton("Cancelar");
    styleGestionButton(btnCancelarEmpleado);
    panelBotonesFormulario.add(btnGuardarEmpleado);
    panelBotonesFormulario.add(btnCancelarEmpleado);
    panelFormularioEmpleadoLocal.add(panelBotonesFormulario, gbc);

    // --- NEW: Vista de Formulario para Asignar Sala ---
    JPanel panelAsignarSala = new JPanel(new GridBagLayout());
    panelAsignarSala.setBackground(formPanelBgColor);
    panelAsignarSala.setBorder(new EmptyBorder(50, 50, 50, 50)); // Adjusted padding
    GridBagConstraints gbcSala = new GridBagConstraints();
    gbcSala.insets = new Insets(10, 5, 10, 5);
    gbcSala.fill = GridBagConstraints.HORIZONTAL;

    // Employee DNI (read-only)
    gbcSala.gridx = 0;
    gbcSala.gridy = 0;
    JLabel lblDniSala = new JLabel("DNI Empleado:");
    lblDniSala.setFont(labelFont);
    lblDniSala.setForeground(labelFgColor);
    panelAsignarSala.add(lblDniSala, gbcSala);
    gbcSala.gridx = 1;
    gbcSala.gridy = 0;
    JTextField txtDniEmpleadoSala = new JTextField(20);
    txtDniEmpleadoSala.setFont(textFieldFont);
    txtDniEmpleadoSala.setEditable(false);
    panelAsignarSala.add(txtDniEmpleadoSala, gbcSala);

    // Employee Name (read-only)
    gbcSala.gridx = 0;
    gbcSala.gridy = 1;
    JLabel lblNombreSala = new JLabel("Nombre Empleado:");
    lblNombreSala.setFont(labelFont);
    lblNombreSala.setForeground(labelFgColor);
    panelAsignarSala.add(lblNombreSala, gbcSala);
    gbcSala.gridx = 1;
    gbcSala.gridy = 1;
    JTextField txtNombreEmpleadoSala = new JTextField(20);
    txtNombreEmpleadoSala.setFont(textFieldFont);
    txtNombreEmpleadoSala.setEditable(false);
    panelAsignarSala.add(txtNombreEmpleadoSala, gbcSala);

    // Sala input
    gbcSala.gridx = 0;
    gbcSala.gridy = 2;
    JLabel lblSala = new JLabel("Asignar Sala:");
    lblSala.setFont(labelFont);
    lblSala.setForeground(labelFgColor);
    panelAsignarSala.add(lblSala, gbcSala);
    gbcSala.gridx = 1;
    gbcSala.gridy = 2;
    txtAsignarSala = new JTextField(20);
    txtAsignarSala.setFont(textFieldFont);
    panelAsignarSala.add(txtAsignarSala, gbcSala);

    // Buttons for Asignar Sala
    gbcSala.gridx = 0;
    gbcSala.gridy = 3;
    gbcSala.gridwidth = 2;
    gbcSala.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesAsignarSala = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesAsignarSala.setBackground(formPanelBgColor);
    JButton btnGuardarSala = new JButton("Guardar Sala");
    styleGestionButton(btnGuardarSala);
    JButton btnCancelarSala = new JButton("Cancelar");
    styleGestionButton(btnCancelarSala);
    panelBotonesAsignarSala.add(btnGuardarSala);
    panelBotonesAsignarSala.add(btnCancelarSala);
    panelAsignarSala.add(panelBotonesAsignarSala, gbcSala);

    // --- NEW: Vista de Formulario para Asignar Turno ---
    JPanel panelAsignarTurno = new JPanel(new GridBagLayout());
    panelAsignarTurno.setBackground(formPanelBgColor);
    panelAsignarTurno.setBorder(new EmptyBorder(50, 50, 50, 50)); // Adjusted padding
    GridBagConstraints gbcTurno = new GridBagConstraints();
    gbcTurno.insets = new Insets(10, 5, 10, 5);
    gbcTurno.fill = GridBagConstraints.HORIZONTAL;

    // Employee DNI (read-only)
    gbcTurno.gridx = 0;
    gbcTurno.gridy = 0;
    JLabel lblDniTurno = new JLabel("DNI Empleado:");
    lblDniTurno.setFont(labelFont);
    lblDniTurno.setForeground(labelFgColor);
    panelAsignarTurno.add(lblDniTurno, gbcTurno);
    gbcTurno.gridx = 1;
    gbcTurno.gridy = 0;
    JTextField txtDniEmpleadoTurno = new JTextField(20);
    txtDniEmpleadoTurno.setFont(textFieldFont);
    txtDniEmpleadoTurno.setEditable(false);
    panelAsignarTurno.add(txtDniEmpleadoTurno, gbcTurno);

    // Employee Name (read-only)
    gbcTurno.gridx = 0;
    gbcTurno.gridy = 1;
    JLabel lblNombreTurno = new JLabel("Nombre Empleado:");
    lblNombreTurno.setFont(labelFont);
    lblNombreTurno.setForeground(labelFgColor);
    panelAsignarTurno.add(lblNombreTurno, gbcTurno);
    gbcTurno.gridx = 1;
    gbcTurno.gridy = 1;
    JTextField txtNombreEmpleadoTurno = new JTextField(20);
    txtNombreEmpleadoTurno.setFont(textFieldFont);
    txtNombreEmpleadoTurno.setEditable(false);
    panelAsignarTurno.add(txtNombreEmpleadoTurno, gbcTurno);

    // Turno input
    gbcTurno.gridx = 0;
    gbcTurno.gridy = 2;
    JLabel lblTurno = new JLabel("Asignar Turno:");
    lblTurno.setFont(labelFont);
    lblTurno.setForeground(labelFgColor);
    panelAsignarTurno.add(lblTurno, gbcTurno);
    gbcTurno.gridx = 1;
    gbcTurno.gridy = 2;
    txtAsignarTurno = new JTextField(20);
    txtAsignarTurno.setFont(textFieldFont);
    panelAsignarTurno.add(txtAsignarTurno, gbcTurno);

    // Buttons for Asignar Turno
    gbcTurno.gridx = 0;
    gbcTurno.gridy = 3;
    gbcTurno.gridwidth = 2;
    gbcTurno.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesAsignarTurno = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesAsignarTurno.setBackground(formPanelBgColor);
    JButton btnGuardarTurno = new JButton("Guardar Turno");
    styleGestionButton(btnGuardarTurno);
    JButton btnCancelarTurno = new JButton("Cancelar");
    styleGestionButton(btnCancelarTurno);
    panelBotonesAsignarTurno.add(btnGuardarTurno);
    panelBotonesAsignarTurno.add(btnCancelarTurno);
    panelAsignarTurno.add(panelBotonesAsignarTurno, gbcTurno);

    // Añadir las nuevas vistas al CardLayout
    cardsPanel.add(panelTablaEmpleadosLocal, "Tabla");
    cardsPanel.add(panelFormularioEmpleadoLocal, "Formulario");
    cardsPanel.add(panelAsignarSala, "AsignarSala"); // NEW
    cardsPanel.add(panelAsignarTurno, "AsignarTurno"); // NEW
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnAgregar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposEmpleado();
      filaSeleccionadaEmpleado = -1; // No hay fila seleccionada para agregar
    });

    btnModificar.addActionListener(e -> {
      filaSeleccionadaEmpleado = tablaEmpleados.getSelectedRow();
      if (filaSeleccionadaEmpleado != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
        txtDNIEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 0).toString());
        txtNombreEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 1).toString());
        txtTelefonoEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 2).toString());
        txtPuestoEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 3).toString());
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para modificar.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnBorrar.addActionListener(e -> {
      int fila = tablaEmpleados.getSelectedRow();
      if (fila != -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea borrar este empleado?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          modeloEmpleados.removeRow(fila);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para borrar.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // MODIFIED: Action listener for "Asignar Turnos"
    btnAsignarTurnos.addActionListener(e -> {
      filaSeleccionadaEmpleado = tablaEmpleados.getSelectedRow();
      if (filaSeleccionadaEmpleado != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "AsignarTurno");
        // Pre-fill DNI and Nombre
        txtDniEmpleadoTurno.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 0).toString());
        txtNombreEmpleadoTurno.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 1).toString());
        // Clear previous shift if any
        txtAsignarTurno.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 5).toString());
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para asignar un turno.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // MODIFIED: Action listener for "Asignar Salas"
    btnAsignarSalas.addActionListener(e -> {
      filaSeleccionadaEmpleado = tablaEmpleados.getSelectedRow();
      if (filaSeleccionadaEmpleado != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "AsignarSala");
        // Pre-fill DNI and Nombre
        txtDniEmpleadoSala.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 0).toString());
        txtNombreEmpleadoSala.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 1).toString());
        // Clear previous room if any
        txtAsignarSala.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 4).toString());
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para asignar una sala.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnGuardarEmpleado.addActionListener(e -> {
      String dni = txtDNIEmpleado.getText().trim();
      String nombre = txtNombreEmpleado.getText().trim();
      String telefono = txtTelefonoEmpleado.getText().trim();
      String puesto = txtPuestoEmpleado.getText().trim();

      if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || puesto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      if (filaSeleccionadaEmpleado == -1) { // Agregar nuevo empleado
        // NEW: Add empty Sala and Turno when adding a new employee
        modeloEmpleados.addRow(new Object[] { dni, nombre, telefono, puesto, "", "" });
      } else { // Editar empleado existente
        modeloEmpleados.setValueAt(dni, filaSeleccionadaEmpleado, 0);
        modeloEmpleados.setValueAt(nombre, filaSeleccionadaEmpleado, 1);
        modeloEmpleados.setValueAt(telefono, filaSeleccionadaEmpleado, 2);
        modeloEmpleados.setValueAt(puesto, filaSeleccionadaEmpleado, 3);
        // Sala and Turno columns remain unchanged during general edit
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposEmpleado(); // Limpiar campos después de guardar
    });

    btnCancelarEmpleado.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposEmpleado();
    });

    // NEW: Action listener for saving Sala
    btnGuardarSala.addActionListener(e -> {
      String sala = txtAsignarSala.getText().trim();
      if (sala.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de Sala no puede estar vacío.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (filaSeleccionadaEmpleado != -1) {
        modeloEmpleados.setValueAt(sala, filaSeleccionadaEmpleado, 4); // Update Sala column (index 4)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // NEW: Action listener for canceling Sala assignment
    btnCancelarSala.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });

    // NEW: Action listener for saving Turno
    btnGuardarTurno.addActionListener(e -> {
      String turno = txtAsignarTurno.getText().trim();
      if (turno.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de Turno no puede estar vacío.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (filaSeleccionadaEmpleado != -1) {
        modeloEmpleados.setValueAt(turno, filaSeleccionadaEmpleado, 5); // Update Turno column (index 5)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // NEW: Action listener for canceling Turno assignment
    btnCancelarTurno.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });
  }

  /**
   * Limpia los campos de texto del formulario de empleado.
   */
  private void limpiarCamposEmpleado() {
    txtDNIEmpleado.setText("");
    txtNombreEmpleado.setText("");
    txtTelefonoEmpleado.setText("");
    txtPuestoEmpleado.setText("");
    // No need to clear Sala/Turno fields here as they have their own forms
  }

  /**
   * Aplica un estilo consistente a los botones de gestión (Agregar, Editar,
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

    // Further reduced button preferred size for 5 buttons to fit
    button.setPreferredSize(new Dimension(115, 35)); // Reduced from (115, 40) to (100, 35)
  }
}