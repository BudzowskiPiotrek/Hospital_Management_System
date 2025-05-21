package gestionHospital;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

@SuppressWarnings("serial")
public class PanelGestionSalas extends JPanel {

  private JPanel cardsPanel;
  private DefaultTableModel modeloSalas;
  private JTable tablaSalas;
  private JTextField txtNumeroSala, txtTipoSala, txtCapacidadSala;

  // Fields for Availability and Maintenance forms
  private JComboBox<String> cmbDisponibilidad; // For updating room availability
  private JFormattedTextField txtUltimaLimpieza; // NEW: For last cleaned date

  // TextFields for general modification form (Availability and Maintenance
  // display)
  private JTextField txtDisponibilidadGeneral;
  private JTextField txtUltimaLimpiezaGeneral; // NEW: For last cleaned date display

  private int filaSeleccionadaSala = -1;

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

  public PanelGestionSalas() {
    this.setLayout(new BorderLayout());
    this.setBackground(mainPanelBgColor); // Aplicar el color de fondo principal

    JLabel titulo = new JLabel("Gestión de Salas", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 30));
    titulo.setForeground(titleFgColor); // Aplicar color de texto del título
    titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
    this.add(titulo, BorderLayout.NORTH);

    cardsPanel = new JPanel(new CardLayout());
    cardsPanel.setBackground(cardsPanelBgColor); // Aplicar color de fondo de las cards

    // --- Vista de Tabla de Salas ---
    JPanel panelTablaSalasLocal = new JPanel(new BorderLayout());
    panelTablaSalasLocal.setBackground(cardsPanelBgColor); // Fondo de la tabla (dentro de cardsPanel)

    // MODIFIED: Added "Disponibilidad" and "Mantenimiento" columns (Mantenimiento
    // will store dates)
    String[] columnasSalas = { "Número", "Tipo", "Capacidad", "Disponibilidad", "Última Limpieza" };
    Object[][] datosSalas = {
        { "101", "Consulta General", 1, "Disponible", "2024-05-15" },
        { "203", "Quirófano", 5, "Ocupada", "2024-05-10" },
        { "305", "Recuperación", 10, "En Limpieza", "2024-05-20" }
    };
    modeloSalas = new DefaultTableModel(datosSalas, columnasSalas) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tablaSalas = new JTable(modeloSalas);
    tablaSalas.setFont(new Font("Arial", Font.PLAIN, 14));
    tablaSalas.setRowHeight(25);
    tablaSalas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    tablaSalas.setBackground(Color.WHITE);
    tablaSalas.setForeground(Color.BLACK);
    tablaSalas.getTableHeader().setBackground(tableHeaderBg);
    tablaSalas.getTableHeader().setForeground(tableHeaderFg);
    JScrollPane scrollTablaSalas = new JScrollPane(tablaSalas);
    scrollTablaSalas.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panelTablaSalasLocal.add(scrollTablaSalas, BorderLayout.CENTER);

    JPanel panelBotonesTablaSalas = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10)); // Reduced hgap
    panelBotonesTablaSalas.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla

    JButton btnAgregar = new JButton("Agregar Sala");
    styleGestionButton(btnAgregar);
    JButton btnEditar = new JButton("Editar Sala");
    styleGestionButton(btnEditar);
    JButton btnEliminar = new JButton("Eliminar Sala");
    styleGestionButton(btnEliminar);
    JButton btnConsultarDisponiblilidad = new JButton("Disponibilidad");
    styleGestionButton(btnConsultarDisponiblilidad);
    JButton btnMantenimientodelasala = new JButton("Última Limpieza"); // Renamed button
    styleGestionButton(btnMantenimientodelasala);

    panelBotonesTablaSalas.add(btnAgregar);
    panelBotonesTablaSalas.add(btnEditar);
    panelBotonesTablaSalas.add(btnEliminar);
    panelBotonesTablaSalas.add(btnConsultarDisponiblilidad);
    panelBotonesTablaSalas.add(btnMantenimientodelasala);
    panelTablaSalasLocal.add(panelBotonesTablaSalas, BorderLayout.SOUTH);

    // --- Vista de Formulario de Sala (Add/Modify) ---
    JPanel panelFormularioSalaLocal = new JPanel(new GridBagLayout());
    panelFormularioSalaLocal.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioSalaLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    // Número
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblNumeroSala = new JLabel("Número:");
    lblNumeroSala.setFont(labelFont);
    lblNumeroSala.setForeground(labelFgColor);
    panelFormularioSalaLocal.add(lblNumeroSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    txtNumeroSala = new JTextField(20);
    txtNumeroSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtNumeroSala, gbc);

    // Tipo
    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblTipoSala = new JLabel("Tipo:");
    lblTipoSala.setFont(labelFont);
    lblTipoSala.setForeground(labelFgColor);
    panelFormularioSalaLocal.add(lblTipoSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    txtTipoSala = new JTextField(20);
    txtTipoSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtTipoSala, gbc);

    // Capacidad
    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblCapacidadSala = new JLabel("Capacidad:");
    lblCapacidadSala.setFont(labelFont);
    lblCapacidadSala.setForeground(labelFgColor);
    panelFormularioSalaLocal.add(lblCapacidadSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    txtCapacidadSala = new JTextField(20);
    txtCapacidadSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtCapacidadSala, gbc);

    // Disponibilidad (read-only for general modification)
    gbc.gridx = 0;
    gbc.gridy = 3;
    JLabel lblDisponibilidadGeneral = new JLabel("Disponibilidad (solo lectura):");
    lblDisponibilidadGeneral.setFont(labelFont);
    lblDisponibilidadGeneral.setForeground(labelFgColor);
    panelFormularioSalaLocal.add(lblDisponibilidadGeneral, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    txtDisponibilidadGeneral = new JTextField(20); // Make it read-only
    txtDisponibilidadGeneral.setFont(textFieldFont);
    txtDisponibilidadGeneral.setEditable(false);
    panelFormularioSalaLocal.add(txtDisponibilidadGeneral, gbc);

    // Última Limpieza (read-only for general modification)
    gbc.gridx = 0;
    gbc.gridy = 4;
    JLabel lblUltimaLimpiezaGeneral = new JLabel("Última Limpieza (solo lectura):"); // Renamed label
    lblUltimaLimpiezaGeneral.setFont(labelFont);
    lblUltimaLimpiezaGeneral.setForeground(labelFgColor);
    panelFormularioSalaLocal.add(lblUltimaLimpiezaGeneral, gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    txtUltimaLimpiezaGeneral = new JTextField(20); // Make it read-only
    txtUltimaLimpiezaGeneral.setFont(textFieldFont);
    txtUltimaLimpiezaGeneral.setEditable(false);
    panelFormularioSalaLocal.add(txtUltimaLimpiezaGeneral, gbc);

    // Botones del formulario de sala
    gbc.gridx = 0;
    gbc.gridy = 5; // Adjusted gridy
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormularioSala = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormularioSala.setBackground(formPanelBgColor);
    JButton btnGuardarSala = new JButton("Guardar");
    styleGestionButton(btnGuardarSala);
    JButton btnCancelarSala = new JButton("Cancelar");
    styleGestionButton(btnCancelarSala);
    panelBotonesFormularioSala.add(btnGuardarSala);
    panelBotonesFormularioSala.add(btnCancelarSala);
    panelFormularioSalaLocal.add(panelBotonesFormularioSala, gbc);

    // --- Vista de Formulario para Consultar/Actualizar Disponibilidad ---
    JPanel panelConsultarDisponibilidad = new JPanel(new GridBagLayout());
    panelConsultarDisponibilidad.setBackground(formPanelBgColor);
    panelConsultarDisponibilidad.setBorder(new EmptyBorder(50, 50, 50, 50));
    GridBagConstraints gbcDisp = new GridBagConstraints();
    gbcDisp.insets = new Insets(10, 5, 10, 5);
    gbcDisp.fill = GridBagConstraints.HORIZONTAL;

    // Sala Number (read-only)
    gbcDisp.gridx = 0;
    gbcDisp.gridy = 0;
    JLabel lblNumeroSalaDisp = new JLabel("Número de Sala:");
    lblNumeroSalaDisp.setFont(labelFont);
    lblNumeroSalaDisp.setForeground(labelFgColor);
    panelConsultarDisponibilidad.add(lblNumeroSalaDisp, gbcDisp);
    gbcDisp.gridx = 1;
    gbcDisp.gridy = 0;
    JTextField txtNumeroSalaDisp = new JTextField(20);
    txtNumeroSalaDisp.setFont(textFieldFont);
    txtNumeroSalaDisp.setEditable(false);
    panelConsultarDisponibilidad.add(txtNumeroSalaDisp, gbcDisp);

    // Sala Type (read-only)
    gbcDisp.gridx = 0;
    gbcDisp.gridy = 1;
    JLabel lblTipoSalaDisp = new JLabel("Tipo de Sala:");
    lblTipoSalaDisp.setFont(labelFont);
    lblTipoSalaDisp.setForeground(labelFgColor);
    panelConsultarDisponibilidad.add(lblTipoSalaDisp, gbcDisp);
    gbcDisp.gridx = 1;
    gbcDisp.gridy = 1;
    JTextField txtTipoSalaDisp = new JTextField(20);
    txtTipoSalaDisp.setFont(textFieldFont);
    txtTipoSalaDisp.setEditable(false);
    panelConsultarDisponibilidad.add(txtTipoSalaDisp, gbcDisp);

    // Disponibilidad JComboBox
    gbcDisp.gridx = 0;
    gbcDisp.gridy = 2;
    JLabel lblDisponibilidad = new JLabel("Actualizar Disponibilidad:");
    lblDisponibilidad.setFont(labelFont);
    lblDisponibilidad.setForeground(labelFgColor);
    panelConsultarDisponibilidad.add(lblDisponibilidad, gbcDisp);
    gbcDisp.gridx = 1;
    gbcDisp.gridy = 2;
    String[] disponibilidadOptions = { "Disponible", "Ocupada", "En Limpieza", "En Mantenimiento" };
    cmbDisponibilidad = new JComboBox<>(disponibilidadOptions);
    cmbDisponibilidad.setFont(textFieldFont);
    panelConsultarDisponibilidad.add(cmbDisponibilidad, gbcDisp);

    // Buttons for Disponibilidad
    gbcDisp.gridx = 0;
    gbcDisp.gridy = 3;
    gbcDisp.gridwidth = 2;
    gbcDisp.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesDisponibilidad = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesDisponibilidad.setBackground(formPanelBgColor);
    JButton btnGuardarDisponibilidad = new JButton("Guardar Disponibilidad");
    styleGestionButton(btnGuardarDisponibilidad);
    JButton btnCancelarDisponibilidad = new JButton("Cancelar");
    styleGestionButton(btnCancelarDisponibilidad);
    panelBotonesDisponibilidad.add(btnGuardarDisponibilidad);
    panelBotonesDisponibilidad.add(btnCancelarDisponibilidad);
    panelConsultarDisponibilidad.add(panelBotonesDisponibilidad, gbcDisp);

    // --- Vista de Formulario para Última Limpieza de Sala --- // NEW PANEL
    JPanel panelUltimaLimpiezaSala = new JPanel(new GridBagLayout());
    panelUltimaLimpiezaSala.setBackground(formPanelBgColor);
    panelUltimaLimpiezaSala.setBorder(new EmptyBorder(50, 50, 50, 50));
    GridBagConstraints gbcLimpieza = new GridBagConstraints();
    gbcLimpieza.insets = new Insets(10, 5, 10, 5);
    gbcLimpieza.fill = GridBagConstraints.HORIZONTAL; // Keep horizontal fill

    // Sala Number (read-only)
    gbcLimpieza.gridx = 0;
    gbcLimpieza.gridy = 0;
    JLabel lblNumeroSalaLimpieza = new JLabel("Número de Sala:");
    lblNumeroSalaLimpieza.setFont(labelFont);
    lblNumeroSalaLimpieza.setForeground(labelFgColor);
    panelUltimaLimpiezaSala.add(lblNumeroSalaLimpieza, gbcLimpieza);
    gbcLimpieza.gridx = 1;
    gbcLimpieza.gridy = 0;
    JTextField txtNumeroSalaLimpieza = new JTextField(20);
    txtNumeroSalaLimpieza.setFont(textFieldFont);
    txtNumeroSalaLimpieza.setEditable(false);
    panelUltimaLimpiezaSala.add(txtNumeroSalaLimpieza, gbcLimpieza);

    // Sala Type (read-only)
    gbcLimpieza.gridx = 0;
    gbcLimpieza.gridy = 1;
    JLabel lblTipoSalaLimpieza = new JLabel("Tipo de Sala:");
    lblTipoSalaLimpieza.setFont(labelFont);
    lblTipoSalaLimpieza.setForeground(labelFgColor);
    panelUltimaLimpiezaSala.add(lblTipoSalaLimpieza, gbcLimpieza);
    gbcLimpieza.gridx = 1;
    gbcLimpieza.gridy = 1;
    JTextField txtTipoSalaLimpieza = new JTextField(20);
    txtTipoSalaLimpieza.setFont(textFieldFont);
    txtTipoSalaLimpieza.setEditable(false);
    panelUltimaLimpiezaSala.add(txtTipoSalaLimpieza, gbcLimpieza);

    // Última Limpieza Date Input (JFormattedTextField)
    gbcLimpieza.gridx = 0;
    gbcLimpieza.gridy = 2;
    JLabel lblUltimaLimpieza = new JLabel("Fecha Última Limpieza (YYYY-MM-DD):"); // Renamed label
    lblUltimaLimpieza.setFont(labelFont);
    lblUltimaLimpieza.setForeground(labelFgColor);
    panelUltimaLimpiezaSala.add(lblUltimaLimpieza, gbcLimpieza);
    gbcLimpieza.gridx = 1;
    gbcLimpieza.gridy = 2;
    gbcLimpieza.weightx = 1.0; // Allow horizontal expansion
    gbcLimpieza.gridwidth = 1; // Explicitly set gridwidth for the text field
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormatter dateFormatter = new DateFormatter(dateFormat);
    txtUltimaLimpieza = new JFormattedTextField(new DefaultFormatterFactory(dateFormatter));
    txtUltimaLimpieza.setFont(textFieldFont);
    txtUltimaLimpieza.setColumns(10); // Give it a hint for preferred width
    panelUltimaLimpiezaSala.add(txtUltimaLimpieza, gbcLimpieza);

    // Reset gridwidth and weightx for subsequent components if needed
    gbcLimpieza.gridwidth = 1;
    gbcLimpieza.weightx = 0.0;

    // Buttons for Última Limpieza
    gbcLimpieza.gridx = 0;
    gbcLimpieza.gridy = 3;
    gbcLimpieza.gridwidth = 2; // Span two columns for buttons
    gbcLimpieza.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesUltimaLimpieza = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesUltimaLimpieza.setBackground(formPanelBgColor);
    JButton btnGuardarUltimaLimpieza = new JButton("Guardar Fecha");
    styleGestionButton(btnGuardarUltimaLimpieza);
    btnGuardarUltimaLimpieza.setFont(new Font("Arial", Font.BOLD, 12));// Renamed button
    JButton btnCancelarUltimaLimpieza = new JButton("Cancelar");
    styleGestionButton(btnCancelarUltimaLimpieza);
    btnCancelarUltimaLimpieza.setFont(new Font("Arial", Font.BOLD, 12));// Renamed button
    panelBotonesUltimaLimpieza.add(btnGuardarUltimaLimpieza);
    panelBotonesUltimaLimpieza.add(btnCancelarUltimaLimpieza);
    panelUltimaLimpiezaSala.add(panelBotonesUltimaLimpieza, gbcLimpieza);

    // Add all views to the CardLayout
    cardsPanel.add(panelTablaSalasLocal, "Tabla");
    cardsPanel.add(panelFormularioSalaLocal, "Formulario");
    cardsPanel.add(panelConsultarDisponibilidad, "Disponibilidad");
    cardsPanel.add(panelUltimaLimpiezaSala, "UltimaLimpieza"); // NEW card name
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnAgregar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposSala();
      txtNumeroSala.setEditable(true); // Numero de Sala should be editable when adding a new one
      filaSeleccionadaSala = -1; // No row selected for adding
    });

    btnEditar.addActionListener(e -> {
      filaSeleccionadaSala = tablaSalas.getSelectedRow();
      if (filaSeleccionadaSala != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
        txtNumeroSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
        txtTipoSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());
        txtCapacidadSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 2).toString());
        // Populate Disponibilidad and Última Limpieza when modifying (read-only)
        txtDisponibilidadGeneral.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 3).toString());
        txtUltimaLimpiezaGeneral.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 4).toString());
        txtNumeroSala.setEditable(false); // Sala Number should not be editable when modifying
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione una sala para editar.", "Error", JOptionPane.WARNING_MESSAGE);
      }
    });

    btnEliminar.addActionListener(e -> {
      int fila = tablaSalas.getSelectedRow();
      if (fila != -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta sala?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          modeloSalas.removeRow(fila);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione una sala para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
      }
    });

    // Action listener for "Disponibilidad"
    btnConsultarDisponiblilidad.addActionListener(e -> {
      filaSeleccionadaSala = tablaSalas.getSelectedRow();
      if (filaSeleccionadaSala != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Disponibilidad");
        txtNumeroSalaDisp.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
        txtTipoSalaDisp.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());
        cmbDisponibilidad.setSelectedItem(modeloSalas.getValueAt(filaSeleccionadaSala, 3).toString()); // Load existing
                                                                                                       // availability
      } else {
        JOptionPane.showMessageDialog(this, "Seleccione una sala para consultar/actualizar disponibilidad.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // Action listener for "Última Limpieza"
    btnMantenimientodelasala.addActionListener(e -> {
      filaSeleccionadaSala = tablaSalas.getSelectedRow();
      if (filaSeleccionadaSala != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "UltimaLimpieza"); // Show new card
        txtNumeroSalaLimpieza.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
        txtTipoSalaLimpieza.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());

        // Load existing date, handle potential null/empty string
        String dateString = "";
        if (modeloSalas.getValueAt(filaSeleccionadaSala, 4) != null) {
          dateString = modeloSalas.getValueAt(filaSeleccionadaSala, 4).toString();
        }

        try {
          if (!dateString.isEmpty()) {
            txtUltimaLimpieza.setValue(dateFormat.parse(dateString)); // Set the date object
          } else {
            txtUltimaLimpieza.setValue(null); // Clear if empty
          }
        } catch (java.text.ParseException ex) {
          System.err.println("Error parsing date: " + dateString + " - " + ex.getMessage());
          txtUltimaLimpieza.setValue(null); // Clear on parse error
          JOptionPane.showMessageDialog(this,
              "Fecha de última limpieza inválida en la tabla. Formato esperado: YYYY-MM-DD.", "Error de Datos",
              JOptionPane.ERROR_MESSAGE);
        }

      } else {
        JOptionPane.showMessageDialog(this, "Seleccione una sala para gestionar la última limpieza.", "Error",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    btnGuardarSala.addActionListener(e -> {
      String numero = txtNumeroSala.getText().trim();
      String tipo = txtTipoSala.getText().trim();
      String capacidad = txtCapacidadSala.getText().trim();
      String disponibilidad = txtDisponibilidadGeneral.getText().trim(); // Get from read-only field
      String ultimaLimpieza = txtUltimaLimpiezaGeneral.getText().trim(); // Get from read-only field

      if (numero.isEmpty() || tipo.isEmpty() || capacidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Los campos Número, Tipo y Capacidad son obligatorios.",
            "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      // Validación simple para que capacidad sea un número
      try {
        Integer.parseInt(capacidad);
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La capacidad debe ser un número entero.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      if (filaSeleccionadaSala == -1) { // Agregar nueva sala
        // Add default/empty Disponibilidad and Última Limpieza when adding a new room
        modeloSalas.addRow(new Object[] { numero, tipo, capacidad, "Disponible", "" }); // Default empty date for new
                                                                                        // room
      } else { // Editar sala existente
        modeloSalas.setValueAt(numero, filaSeleccionadaSala, 0);
        modeloSalas.setValueAt(tipo, filaSeleccionadaSala, 1);
        modeloSalas.setValueAt(capacidad, filaSeleccionadaSala, 2);
        // Disponibilidad and Última Limpieza remain unchanged during general edit
        modeloSalas.setValueAt(disponibilidad, filaSeleccionadaSala, 3);
        modeloSalas.setValueAt(ultimaLimpieza, filaSeleccionadaSala, 4);
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposSala();
    });

    btnCancelarSala.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposSala();
    });

    // Action listener for saving Disponibilidad
    btnGuardarDisponibilidad.addActionListener(e -> {
      String selectedDisponibilidad = (String) cmbDisponibilidad.getSelectedItem();
      if (selectedDisponibilidad == null || selectedDisponibilidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una opción de disponibilidad.", "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (filaSeleccionadaSala != -1) {
        modeloSalas.setValueAt(selectedDisponibilidad, filaSeleccionadaSala, 3); // Update Disponibilidad column (index
                                                                                 // 3)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // Action listener for canceling Disponibilidad
    btnCancelarDisponibilidad.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });

    // Action listener for saving Última Limpieza
    btnGuardarUltimaLimpieza.addActionListener(e -> {
      Date cleanedDate = (Date) txtUltimaLimpieza.getValue();
      if (cleanedDate == null) {
        JOptionPane.showMessageDialog(this, "Debe introducir una fecha válida para la última limpieza (YYYY-MM-DD).",
            "Error de Validación",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (filaSeleccionadaSala != -1) {
        modeloSalas.setValueAt(dateFormat.format(cleanedDate), filaSeleccionadaSala, 4); // Update Última Limpieza
                                                                                         // column (index 4)
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      }
    });

    // Action listener for canceling Última Limpieza
    btnCancelarUltimaLimpieza.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
    });
  }

  /**
   * Limpia los campos de texto del formulario de sala y los campos de los
   * formularios de disponibilidad y última limpieza.
   */
  private void limpiarCamposSala() {
    txtNumeroSala.setText("");
    txtTipoSala.setText("");
    txtCapacidadSala.setText("");
    txtDisponibilidadGeneral.setText(""); // Clear display field
    txtUltimaLimpiezaGeneral.setText(""); // Clear display field
    cmbDisponibilidad.setSelectedItem("Disponible"); // Reset JComboBox
    txtUltimaLimpieza.setValue(null); // Clear JFormattedTextField (sets to empty string visually)
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