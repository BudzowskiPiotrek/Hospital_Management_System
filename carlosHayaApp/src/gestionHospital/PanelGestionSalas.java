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
    private JTextField txtNumeroSala, txtTipoSala; // Campos para agregar/editar sala

    // Campos específicos para el formulario de Mantenimiento
    private JComboBox<String> cmbDisponibilidad; // RESTAURADO: ComboBox para Disponibilidad
    private JTextField txtDNIEmpleadoMantenimiento;
    private JFormattedTextField txtFechaMantenimiento;

    // Campos de solo lectura para mostrar en el formulario de Mantenimiento
    private JTextField txtNumeroSalaMantenimiento;
    private JTextField txtTipoSalaMantenimiento;

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

        // RESTAURADO: Columnas de la tabla: "Número", "Tipo", "Disponibilidad"
        String[] columnasSalas = { "Número", "Tipo", "Disponibilidad" };
        Object[][] datosSalas = {
                { "101", "Consulta General", "Disponible" },
                { "203", "Quirófano", "Ocupada" },
                { "305", "Recuperación", "En Limpieza" }
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
        JButton btnMantenimiento = new JButton("Mantenimiento"); // Nombre del botón se mantiene
        styleGestionButton(btnMantenimiento);

        panelBotonesTablaSalas.add(btnAgregar);
        panelBotonesTablaSalas.add(btnEditar);
        panelBotonesTablaSalas.add(btnEliminar);
        panelBotonesTablaSalas.add(btnMantenimiento);
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

        // Número de Sala
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

        // Tipo de Sala
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

        // Botones del formulario de sala (Guardar/Cancelar)
        gbc.gridx = 0;
        gbc.gridy = 2;
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

        // --- Vista de Formulario para Mantenimiento de Sala ---
        JPanel panelMantenimientoSala = new JPanel(new GridBagLayout());
        panelMantenimientoSala.setBackground(formPanelBgColor);
        panelMantenimientoSala.setBorder(new EmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbcMantenimiento = new GridBagConstraints();
        gbcMantenimiento.insets = new Insets(10, 5, 10, 5);
        gbcMantenimiento.fill = GridBagConstraints.HORIZONTAL;

        // Número de Sala (solo lectura, pre-seleccionable de la tabla)
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 0;
        JLabel lblNumeroSalaMantenimiento = new JLabel("Número de Sala:");
        lblNumeroSalaMantenimiento.setFont(labelFont);
        lblNumeroSalaMantenimiento.setForeground(labelFgColor);
        panelMantenimientoSala.add(lblNumeroSalaMantenimiento, gbcMantenimiento);
        gbcMantenimiento.gridx = 1;
        gbcMantenimiento.gridy = 0;
        txtNumeroSalaMantenimiento = new JTextField(20);
        txtNumeroSalaMantenimiento.setFont(textFieldFont);
        txtNumeroSalaMantenimiento.setEditable(false); // No se edita directamente aquí
        panelMantenimientoSala.add(txtNumeroSalaMantenimiento, gbcMantenimiento);

        // Tipo de Sala (solo lectura)
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 1;
        JLabel lblTipoSalaMantenimiento = new JLabel("Tipo de Sala:");
        lblTipoSalaMantenimiento.setFont(labelFont);
        lblTipoSalaMantenimiento.setForeground(labelFgColor);
        panelMantenimientoSala.add(lblTipoSalaMantenimiento, gbcMantenimiento);
        gbcMantenimiento.gridx = 1;
        gbcMantenimiento.gridy = 1;
        txtTipoSalaMantenimiento = new JTextField(20);
        txtTipoSalaMantenimiento.setFont(textFieldFont);
        txtTipoSalaMantenimiento.setEditable(false); // No se edita directamente aquí
        panelMantenimientoSala.add(txtTipoSalaMantenimiento, gbcMantenimiento);

        // RESTAURADO: Disponibilidad JComboBox
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 2;
        JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
        lblDisponibilidad.setFont(labelFont);
        lblDisponibilidad.setForeground(labelFgColor);
        panelMantenimientoSala.add(lblDisponibilidad, gbcMantenimiento);
        gbcMantenimiento.gridx = 1;
        gbcMantenimiento.gridy = 2;
        String[] disponibilidadOptions = { "Disponible", "Ocupada", "En Limpieza", "En Mantenimiento" };
        cmbDisponibilidad = new JComboBox<>(disponibilidadOptions); // Inicializado aquí
        cmbDisponibilidad.setFont(textFieldFont);
        panelMantenimientoSala.add(cmbDisponibilidad, gbcMantenimiento);

        // DNI de Empleado
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 3;
        JLabel lblDNIEmpleadoMantenimiento = new JLabel("DNI de Empleado:");
        lblDNIEmpleadoMantenimiento.setFont(labelFont);
        lblDNIEmpleadoMantenimiento.setForeground(labelFgColor);
        panelMantenimientoSala.add(lblDNIEmpleadoMantenimiento, gbcMantenimiento);
        gbcMantenimiento.gridx = 1;
        gbcMantenimiento.gridy = 3;
        txtDNIEmpleadoMantenimiento = new JTextField(20);
        txtDNIEmpleadoMantenimiento.setFont(textFieldFont);
        panelMantenimientoSala.add(txtDNIEmpleadoMantenimiento, gbcMantenimiento);

        // Fecha de Mantenimiento (día, mes, año)
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 4;
        JLabel lblFechaMantenimiento = new JLabel("Fecha (YYYY-MM-DD):");
        lblFechaMantenimiento.setFont(labelFont);
        lblFechaMantenimiento.setForeground(labelFgColor);
        panelMantenimientoSala.add(lblFechaMantenimiento, gbcMantenimiento);
        gbcMantenimiento.gridx = 1;
        gbcMantenimiento.gridy = 4;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        txtFechaMantenimiento = new JFormattedTextField(new DefaultFormatterFactory(dateFormatter));
        txtFechaMantenimiento.setFont(textFieldFont);
        txtFechaMantenimiento.setColumns(10);
        panelMantenimientoSala.add(txtFechaMantenimiento, gbcMantenimiento);

        // Botones del formulario de Mantenimiento
        gbcMantenimiento.gridx = 0;
        gbcMantenimiento.gridy = 5;
        gbcMantenimiento.gridwidth = 2;
        gbcMantenimiento.anchor = GridBagConstraints.CENTER;
        JPanel panelBotonesMantenimiento = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotonesMantenimiento.setBackground(formPanelBgColor);
        JButton btnGuardarMantenimiento = new JButton("Guardar Mantenimiento");
        styleGestionButton(btnGuardarMantenimiento);
        JButton btnCancelarMantenimiento = new JButton("Cancelar");
        styleGestionButton(btnCancelarMantenimiento);
        panelBotonesMantenimiento.add(btnGuardarMantenimiento);
        panelBotonesMantenimiento.add(btnCancelarMantenimiento);
        panelMantenimientoSala.add(panelBotonesMantenimiento, gbcMantenimiento);

        // Añadir todas las vistas al CardLayout
        cardsPanel.add(panelTablaSalasLocal, "Tabla");
        cardsPanel.add(panelFormularioSalaLocal, "Formulario");
        cardsPanel.add(panelMantenimientoSala, "Mantenimiento");
        this.add(cardsPanel, BorderLayout.CENTER);

        
        
        
        
        
        
        
        
        
        
        
        // --- Acciones de los botones ---
        btnAgregar.addActionListener(e -> {
            ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
            limpiarCamposSala();
            txtNumeroSala.setEditable(true); // El número de sala es editable al agregar
            filaSeleccionadaSala = -1; // No hay fila seleccionada para agregar
        });

        btnEditar.addActionListener(e -> {
            filaSeleccionadaSala = tablaSalas.getSelectedRow();
            if (filaSeleccionadaSala != -1) {
                ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Formulario");
                txtNumeroSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
                txtTipoSala.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());
                txtNumeroSala.setEditable(false); // El número de sala no es editable al editar
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
                    JOptionPane.showMessageDialog(this, "Sala eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una sala para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Action listener para el botón "Mantenimiento"
        btnMantenimiento.addActionListener(e -> {
            filaSeleccionadaSala = tablaSalas.getSelectedRow();
            if (filaSeleccionadaSala != -1) {
                ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Mantenimiento");
                txtNumeroSalaMantenimiento.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString());
                txtTipoSalaMantenimiento.setText(modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString());
                // RESTAURADO: Cargar la disponibilidad existente en el JComboBox
                cmbDisponibilidad.setSelectedItem(modeloSalas.getValueAt(filaSeleccionadaSala, 2).toString());
                txtDNIEmpleadoMantenimiento.setText(""); // Limpiar el campo DNI para nueva entrada
                txtFechaMantenimiento.setValue(null); // Limpiar el campo de fecha
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una sala para gestionar el mantenimiento.", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Action listener para guardar una sala (desde "Formulario")
        btnGuardarSala.addActionListener(e -> {
            String numero = txtNumeroSala.getText().trim();
            String tipo = txtTipoSala.getText().trim();

            if (numero.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Los campos Número y Tipo son obligatorios.",
                        "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (filaSeleccionadaSala == -1) { // Agregar nueva sala
                // RESTAURADO: Agrega la disponibilidad por defecto para una nueva sala
                modeloSalas.addRow(new Object[] { numero, tipo, "Disponible" });
                JOptionPane.showMessageDialog(this, "Sala agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else { // Editar sala existente
                modeloSalas.setValueAt(numero, filaSeleccionadaSala, 0);
                modeloSalas.setValueAt(tipo, filaSeleccionadaSala, 1);
                // La disponibilidad NO se edita desde este formulario, se mantiene la existente
                // modeloSalas.setValueAt(modeloSalas.getValueAt(filaSeleccionadaSala, 2), filaSeleccionadaSala, 2);
                JOptionPane.showMessageDialog(this, "Sala actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
            limpiarCamposSala();
        });

        // Action listener para cancelar el formulario de sala
        btnCancelarSala.addActionListener(e -> {
            ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
            limpiarCamposSala();
        });

        // Action listener para guardar el mantenimiento (desde "Mantenimiento")
        btnGuardarMantenimiento.addActionListener(e -> {
            String selectedDisponibilidad = (String) cmbDisponibilidad.getSelectedItem(); // Obtener la disponibilidad seleccionada
            String dniEmpleado = txtDNIEmpleadoMantenimiento.getText().trim();
            Date fechaMantenimiento = (Date) txtFechaMantenimiento.getValue();

            if (selectedDisponibilidad == null || selectedDisponibilidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una opción de disponibilidad.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (dniEmpleado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El DNI de Empleado es obligatorio.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (fechaMantenimiento == null) {
                JOptionPane.showMessageDialog(this, "Debe introducir una fecha válida (YYYY-MM-DD).", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (filaSeleccionadaSala != -1) {
                // RESTAURADO: Actualizar la columna de Disponibilidad (índice 2)
                modeloSalas.setValueAt(selectedDisponibilidad, filaSeleccionadaSala, 2);

                // Aquí, en una aplicación real, guardarías el DNI del empleado y la fecha
                // en tu modelo de datos o base de datos asociados a la sala seleccionada.
                // Como no tenemos una base de datos aquí, solo mostramos un mensaje.
                String numeroSala = modeloSalas.getValueAt(filaSeleccionadaSala, 0).toString();
                String tipoSala = modeloSalas.getValueAt(filaSeleccionadaSala, 1).toString();
                JOptionPane.showMessageDialog(this,
                        "Mantenimiento y Disponibilidad actualizados para la Sala " + numeroSala + " (" + tipoSala + "):\n" +
                                "Disponibilidad: " + selectedDisponibilidad + "\n" +
                                "DNI Empleado: " + dniEmpleado + "\n" +
                                "Fecha: " + dateFormat.format(fechaMantenimiento),
                        "Mantenimiento Registrado", JOptionPane.INFORMATION_MESSAGE);
            }
            ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
            limpiarCamposSala();
        });

        btnCancelarMantenimiento.addActionListener(e -> {
            ((CardLayout) cardsPanel.getLayout()).show(cardsPanel, "Tabla");
            limpiarCamposSala();
        });
    }


    private void limpiarCamposSala() {
        txtNumeroSala.setText("");
        txtTipoSala.setText("");
        txtNumeroSalaMantenimiento.setText("");
        txtTipoSalaMantenimiento.setText("");
        if (cmbDisponibilidad != null) {
            cmbDisponibilidad.setSelectedItem("Disponible");
        }
        txtDNIEmpleadoMantenimiento.setText("");
        txtFechaMantenimiento.setValue(null); 
    }

    private void styleGestionButton(JButton button) {
        button.setBackground(gestionButtonBgColor);
        button.setForeground(gestionButtonFgColor);
        button.setFont(gestionButtonFont);
        button.setFocusPainted(false);
        button.setBorder(gestionButtonBorder);
        button.setPreferredSize(new Dimension(130, 35));
    }
}