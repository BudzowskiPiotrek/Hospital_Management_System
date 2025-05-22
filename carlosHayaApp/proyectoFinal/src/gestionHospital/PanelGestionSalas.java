package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelGestionSalas extends JPanel {

  private JPanel cardsPanel;
  private DefaultTableModel modeloSalas;
  private JTable tablaSalas;
  private JTextField txtNumeroSala, txtTipoSala, txtCapacidadSala;
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

    String[] columnasSalas = { "Número", "Tipo", "Capacidad", "Disponibilidad", "Mantenimiento" };
    Object[][] datosSalas = {
        { "101", "Consulta General", 1 },
        { "203", "Quirófano", 5 },
        { "305", "Recuperación", 10 }
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

    JPanel panelBotonesTablaSalas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesTablaSalas.setBackground(tableButtonsPanelBgColor); // Fondo del panel de botones de tabla
    JButton btnAgregar = new JButton("Agregar Sala");
    styleGestionButton(btnAgregar);
    JButton btnEditar = new JButton("Editar Sala");
    styleGestionButton(btnEditar);
    JButton btnEliminar = new JButton("Eliminar Sala");
    styleGestionButton(btnEliminar);
    JButton btnConsultarDisponiblilidad = new JButton("Disponibilidad");
    styleGestionButton(btnConsultarDisponiblilidad);
    JButton btnMantenimientodelasala = new JButton("Mantenimiento");
    styleGestionButton(btnMantenimientodelasala);
    panelBotonesTablaSalas.add(btnAgregar);
    panelBotonesTablaSalas.add(btnEditar);
    panelBotonesTablaSalas.add(btnEliminar);
    panelBotonesTablaSalas.add(btnConsultarDisponiblilidad);
    panelBotonesTablaSalas.add(btnMantenimientodelasala);
    panelTablaSalasLocal.add(panelBotonesTablaSalas, BorderLayout.SOUTH);

    // --- Vista de Formulario de Sala ---
    JPanel panelFormularioSalaLocal = new JPanel(new GridBagLayout());
    panelFormularioSalaLocal.setBackground(formPanelBgColor); // Fondo del formulario
    panelFormularioSalaLocal.setBorder(new EmptyBorder(20, 50, 20, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel lblNumeroSala = new JLabel("Número:");
    lblNumeroSala.setFont(labelFont);
    lblNumeroSala.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioSalaLocal.add(lblNumeroSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    txtNumeroSala = new JTextField(20);
    txtNumeroSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtNumeroSala, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel lblTipoSala = new JLabel("Tipo:");
    lblTipoSala.setFont(labelFont);
    lblTipoSala.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioSalaLocal.add(lblTipoSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    txtTipoSala = new JTextField(20);
    txtTipoSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtTipoSala, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel lblCapacidadSala = new JLabel("Capacidad:");
    lblCapacidadSala.setFont(labelFont);
    lblCapacidadSala.setForeground(labelFgColor); // Aplicar color de texto de etiqueta
    panelFormularioSalaLocal.add(lblCapacidadSala, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    txtCapacidadSala = new JTextField(20);
    txtCapacidadSala.setFont(textFieldFont);
    panelFormularioSalaLocal.add(txtCapacidadSala, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JPanel panelBotonesFormularioSala = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panelBotonesFormularioSala.setBackground(formPanelBgColor); // Fondo del panel de botones del formulario
    JButton btnGuardarSala = new JButton("Guardar");
    styleGestionButton(btnGuardarSala);
    JButton btnCancelarSala = new JButton("Cancelar");
    styleGestionButton(btnCancelarSala);
    panelBotonesFormularioSala.add(btnGuardarSala);
    panelBotonesFormularioSala.add(btnCancelarSala);
    panelFormularioSalaLocal.add(panelBotonesFormularioSala, gbc);

    cardsPanel.add(panelTablaSalasLocal, "Tabla");
    cardsPanel.add(panelFormularioSalaLocal, "Formulario");
    this.add(cardsPanel, BorderLayout.CENTER);

    // --- Acciones de los botones ---
    btnAgregar.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
      limpiarCamposSala();
      filaSeleccionadaSala = -1;
    });

    btnEditar.addActionListener(e -> {
      filaSeleccionadaSala = tablaSalas.getSelectedRow();
      if (filaSeleccionadaSala != -1) {
        ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
        txtNumeroSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
        txtTipoSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());
        txtCapacidadSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 2).toString());
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

    btnGuardarSala.addActionListener(e -> {
      String numero = txtNumeroSala.getText().trim();
      String tipo = txtTipoSala.getText().trim();
      String capacidad = txtCapacidadSala.getText().trim();

      if (numero.isEmpty() || tipo.isEmpty() || capacidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error de Validación",
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

      if (filaSeleccionadaSala == -1) {
        modeloSalas.addRow(new Object[] { numero, tipo, capacidad });
      } else {
        modeloSalas.setValueAt(numero, filaSeleccionadaSala, 0);
        modeloSalas.setValueAt(tipo, filaSeleccionadaSala, 1);
        modeloSalas.setValueAt(capacidad, filaSeleccionadaSala, 2);
      }
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposSala();
    });

    btnCancelarSala.addActionListener(e -> {
      ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
      limpiarCamposSala();
    });
  }

  /**
   * Limpia los campos de texto del formulario de sala.
   */
  private void limpiarCamposSala() {
    txtNumeroSala.setText("");
    txtTipoSala.setText("");
    txtCapacidadSala.setText("");
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
    button.setPreferredSize(new Dimension(115, 35));
  }
}
