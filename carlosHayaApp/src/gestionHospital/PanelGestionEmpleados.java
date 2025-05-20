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
  private int filaSeleccionadaEmpleado = -1; // Para saber qué fila se está editando

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

    String[] columnasEmpleados = { "DNI", "Nombre", "Teléfono", "Puesto" };
    Object[][] datosEmpleados = {
        { "12345678A", "Juan Pérez", "600111222", "Médico" },
        { "87654321B", "María López", "600333444", "Enfermera" },
        { "11223344C", "Carlos Ruiz", "600555666", "Administrativo" }
    };
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

    JPanel panelBotonesTablaEmpleados = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesTablaEmpleados.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla
    JButton btnAgregar = new JButton("Agregar Empleado");
    styleGestionButton(btnAgregar);
    JButton btnEditar = new JButton("Editar Empleado");
    styleGestionButton(btnEditar);
    JButton btnEliminar = new JButton("Eliminar Empleado");
    styleGestionButton(btnEliminar);
    panelBotonesTablaEmpleados.add(btnAgregar);
    panelBotonesTablaEmpleados.add(btnEditar);
    panelBotonesTablaEmpleados.add(btnEliminar);
    panelTablaEmpleadosLocal.add(panelBotonesTablaEmpleados, BorderLayout.SOUTH);

    // --- Vista de Formulario de Empleado ---
    JPanel panelFormularioEmpleadoLocal = new JPanel(new GridBagLayout());
    panelFormularioEmpleadoLocal.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioEmpleadoLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5); // Padding alrededor de los componentes
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    // DNI
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblDNI = new JLabel("DNI:");
    lblDNI.setFont(labelFont);
    lblDNI.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
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
    lblNombre.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
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
    lblTelefono.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
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
    lblPuesto.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioEmpleadoLocal.add(lblPuesto, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    txtPuestoEmpleado = new JTextField(20);
    txtPuestoEmpleado.setFont(textFieldFont);
    panelFormularioEmpleadoLocal.add(txtPuestoEmpleado, gbc);

    // Botones del formulario
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2; // Ocupa dos columnas
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormulario = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormulario.setBackground(formPanelBgColor); // Fondo del panel de botones del formulario
    JButton btnGuardarEmpleado = new JButton("Guardar");
    styleGestionButton(btnGuardarEmpleado);
    JButton btnCancelarEmpleado = new JButton("Cancelar");
    styleGestionButton(btnCancelarEmpleado);
    panelBotonesFormulario.add(btnGuardarEmpleado);
    panelBotonesFormulario.add(btnCancelarEmpleado);
    panelFormularioEmpleadoLocal.add(panelBotonesFormulario, gbc);

    // Añadir las vistas al CardLayout
    cardsPanel.add(panelTablaEmpleadosLocal, "Tabla");
    cardsPanel.add(panelFormularioEmpleadoLocal, "Formulario");
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnAgregar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposEmpleado();
      filaSeleccionadaEmpleado = -1; // No hay fila seleccionada para agregar
    });

    btnEditar.addActionListener(e -> {
      filaSeleccionadaEmpleado = tablaEmpleados.getSelectedRow();
      if (filaSeleccionadaEmpleado != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
        txtDNIEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 0).toString());
        txtNombreEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 1).toString());
        txtTelefonoEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 2).toString());
        txtPuestoEmpleado.setText(modeloEmpleados.getValueAt(filaSeleccionadaEmpleado, 3).toString());
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para editar.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnEliminar.addActionListener(e -> {
      int fila = tablaEmpleados.getSelectedRow();
      if (fila != -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este empleado?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          modeloEmpleados.removeRow(fila);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.", "Error",
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
        modeloEmpleados.addRow(new Object[] { dni, nombre, telefono, puesto });
      } else { // Editar empleado existente
        modeloEmpleados.setValueAt(dni, filaSeleccionadaEmpleado, 0);
        modeloEmpleados.setValueAt(nombre, filaSeleccionadaEmpleado, 1);
        modeloEmpleados.setValueAt(telefono, filaSeleccionadaEmpleado, 2);
        modeloEmpleados.setValueAt(puesto, filaSeleccionadaEmpleado, 3);
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposEmpleado(); // Limpiar campos después de guardar
    });

    btnCancelarEmpleado.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposEmpleado();
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
    button.setPreferredSize(new Dimension(180, 45));
  }
}
