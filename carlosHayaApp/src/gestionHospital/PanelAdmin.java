package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelAdmin extends JPanel {
    Color colorbg = Color.decode("#212f3d");
    Color colorButton = Color.decode("#006D77");
    private Border border = BorderFactory.createLineBorder(Color.black, 1);
    @SuppressWarnings("unused")
    private final PanelImagen panelImagen;
    private JPanel panelMedio;
    private JPanel panelMenu;
    private JPanel panelDisplay;
    private JPanel panelTitulo;

    // Paneles para cada funcionalidad
    private JPanel panelGestionEmpleados;
    private JPanel panelGestionPacientes;
    private JPanel panelGestionSalas;
    private JPanel panelVerReportes;

    // Componentes para el panel de Ver Reportes (Estadísticas)
    private JTable tablaEstadisticas;
    private DefaultTableModel modeloEstadisticas;
    private JTextField textFieldFiltro; // Campo para filtrar (si es necesario)
    private TableRowSorter<DefaultTableModel> sorterEstadisticas;

    public PanelAdmin(PanelImagen panelImagen) {
        this.panelImagen = panelImagen;
        this.setBackground(colorbg);
        this.setPreferredSize(new Dimension(900, 600));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelTitle();
        panelMenuYdisplay();
        mostrarPanelInicio(); // Mostrar un panel de inicio al principio
    }

    private void panelMenuYdisplay() {
        panelMedio = new JPanel(new BorderLayout());
        panelIzquierda();
        panelDerecho();
        panelMedio.setBackground(colorbg);
        this.add(panelMedio, BorderLayout.CENTER);
    }

    private void panelIzquierda() {
        panelMenu = new JPanel(new GridLayout(5, 1, 5, 5));
        panelMenu.setPreferredSize(new Dimension(200, 500));
        panelMenu.setBackground(colorbg);
        panelMenu.setBorder(new EmptyBorder(0, 0, 0, 10));
        panelMedio.add(panelMenu, BorderLayout.WEST);
        String[] buttonLabels = { "Gestionar Empleados", "Gestionar Pacientes", "Gestionar Salas", "Estadísticas",
                "Cerrar Sesión" };
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            stylePanelButton(button);
            panelMenu.add(button);
            final String label = buttonLabels[i]; // Necesario para usar en el ActionListener

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("Gestionar Empleados")) {
                        mostrarPanel(panelGestionEmpleados);
                    } else if (label.equals("Gestionar Pacientes")) {
                        mostrarPanel(panelGestionPacientes);
                    } else if (label.equals("Gestionar Salas")) {
                        mostrarPanel(panelGestionSalas);
                    } else if (label.equals("Estadísticas")) {
                        mostrarPanel(panelVerReportes);
                    } else if (label.equals("Cerrar Sesión")) {
                        button.setBackground(Color.decode("#FF6347")); // Cambiar color al hacer clic
                        panelImagen.cambiarPanel(new PanelLogin(panelImagen));
                    }
                }
            });

            if (buttonLabels[i].equals("Cerrar Sesión")) {
                button.setBackground(Color.decode("#C08080"));
                button.setForeground(Color.white);
            }
        }
    }

    private void panelDerecho() {
        panelDisplay = new JPanel(new BorderLayout());
        panelDisplay.setBackground(Color.decode("#ECF0F1"));
        panelMedio.add(panelDisplay, BorderLayout.CENTER);

        // Inicializar los paneles de funcionalidad
        panelGestionEmpleados = crearPanelGestionEmpleados();
        panelGestionPacientes = crearPanelGestionPacientes();
        panelGestionSalas = crearPanelGestionSalas();
        panelVerReportes = crearPanelVerReportes(); // Inicializar el panel de reportes
    }

    private void panelTitle() {
        panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(colorbg);
        JLabel title = new JLabel("ADMIN");
        title.setForeground(Color.decode("#F4D35E"));
        title.setFont(new Font("Roboto", Font.BOLD, 35));
        panelTitulo.add(title);
        this.add(panelTitulo, BorderLayout.NORTH);
    }

    private void stylePanelButton(JButton button) {
        button.setBackground(colorButton);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorder(border);
        button.setPreferredSize(new Dimension(180, 40)); // Aumentar el tamaño de los botones
    }

    // Modificado para el panel de gestión de empleados
    private JPanel crearPanelGestionEmpleados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#B0E0E6")); // Nuevo color de fondo (era el color del botón)

        JLabel titulo = new JLabel("Gestión de Empleados", SwingConstants.CENTER); // Centrar el texto del título
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.darkGray);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0)); // Añadir un poco de espacio alrededor del título
        panel.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10)); // GridLayout para los botones
        panelBotones.setBackground(Color.decode("#B0E0E6")); // Mantener el mismo color de fondo
        panelBotones.setBorder(new EmptyBorder(20, 50, 20, 50)); // Añadir espacio alrededor de los botones

        JButton btnAgregar = new JButton("Agregar Empleado");
        stylePanelButtonGestion(btnAgregar); // Usamos el estilo modificado
        panelBotones.add(btnAgregar);

        JButton btnEditar = new JButton("Editar Empleado");
        stylePanelButtonGestion(btnEditar); // Usamos el estilo modificado
        panelBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Empleado");
        stylePanelButtonGestion(btnEliminar); // Usamos el estilo modificado
        panelBotones.add(btnEliminar);

        panel.add(panelBotones, BorderLayout.CENTER);

        return panel;
    }

    // Nuevo método para el estilo de los botones dentro de Gestión
    private void stylePanelButtonGestion(JButton button) {
        button.setBackground(Color.decode("#CD7F32")); // Nuevo color de fondo (era el color del panel)
        button.setForeground(Color.white); // Asegurar la legibilidad del texto (era gris oscuro)
        button.setFont(new Font("Arial", Font.BOLD, 25)); // Mantener la fuente
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#0056b3"), 1)); // Mantener el borde
        button.setPreferredSize(new Dimension(180, 45)); // Mantener el tamaño
    }

    // Nuevo método para el panel de gestión de pacientes
    private JPanel crearPanelGestionPacientes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#B0E0E6"));// Nuevo color de fondo

        JLabel titulo = new JLabel("Gestión de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.darkGray);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBackground(Color.decode("#B0E0E6")); // Mantener el mismo color de fondo
        panelBotones.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton btnRegistrar = new JButton("Registrar Paciente");
        stylePanelButtonGestion(btnRegistrar);
        panelBotones.add(btnRegistrar);

        JButton btnEditar = new JButton("Editar Paciente");
        stylePanelButtonGestion(btnEditar);
        panelBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Paciente");
        stylePanelButtonGestion(btnEliminar);
        panelBotones.add(btnEliminar);

        panel.add(panelBotones, BorderLayout.CENTER);

        return panel;
    }

    // Nuevo método para el panel de gestión de salas
    private JPanel crearPanelGestionSalas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#B0E0E6")); // Nuevo color de fondo

        JLabel titulo = new JLabel("Gestión de Salas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.darkGray);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBackground(Color.decode("#B0E0E6")); // Mantener el mismo color de fondo
        panelBotones.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton btnAgregar = new JButton("Agregar Sala");
        stylePanelButtonGestion(btnAgregar);
        panelBotones.add(btnAgregar);

        JButton btnEditar = new JButton("Editar Sala");
        stylePanelButtonGestion(btnEditar);
        panelBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Sala");
        stylePanelButtonGestion(btnEliminar);
        panelBotones.add(btnEliminar);

        panel.add(panelBotones, BorderLayout.CENTER);

        return panel;
    }

    // Nuevo método para el panel de Ver Reportes (Estadísticas) usando una tabla
    private JPanel crearPanelVerReportes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.red);

        JLabel titulo = new JLabel("Estadísticas Generales", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        // Panel para el filtro (si es necesario en el futuro)
        JPanel filtroPanel = new JPanel();
        filtroPanel.setBackground(colorbg);
        filtroPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel lblFiltro = new JLabel("Filtrar por:"); // Etiqueta para el campo de filtro
        lblFiltro.setForeground(Color.WHITE);
        lblFiltro.setFont(new Font("Arial", Font.BOLD, 18)); // Aumentar tamaño de "Filtrar por:"
        filtroPanel.add(lblFiltro);

        textFieldFiltro = new JTextField(15); // Aumentar el tamaño del campo de texto
        textFieldFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        filtroPanel.add(textFieldFiltro);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(Color.decode("#008000")); // Nuevo color de fondo (era el color del panel)
        btnBuscar.setForeground(Color.white); // Asegurar la legibilidad del texto (era gris oscuro)
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15)); // Mantener la fuente
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createLineBorder(Color.decode("#0056b3"), 1));
        btnBuscar.setPreferredSize(new Dimension(85, 30));
        filtroPanel.add(btnBuscar);

        panel.add(filtroPanel, BorderLayout.SOUTH);

        // Columnas de la tabla para mostrar las estadísticas
        String[] columnas = { "Estadística", "Valor" };

        // Datos de ejemplo de las estadísticas
        Object[][] datos = {
                { "Pacientes Atendidos Hoy", 85 },
                { "Ingresos Totales del Mes", "$125,000" },
                { "Promedio de Citas por Día", 32.5 },
                { "Ocupación de Camas", "75%" },
                { "Empleados Activos", 55 }
        };

        modeloEstadisticas = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        tablaEstadisticas = new JTable(modeloEstadisticas);
        tablaEstadisticas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEstadisticas.setRowHeight(25);
        tablaEstadisticas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        sorterEstadisticas = new TableRowSorter<>(modeloEstadisticas);
        tablaEstadisticas.setRowSorter(sorterEstadisticas);

        JScrollPane scrollPane = new JScrollPane(tablaEstadisticas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        panel.add(scrollPane, BorderLayout.CENTER);

        // Acción del botón buscar (adaptado para estadísticas)
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarTablaEstadisticas();
            }
        });

        return panel;
    }

    private void filtrarTablaEstadisticas() {
        String filtro = textFieldFiltro.getText().trim();
        if (filtro.isEmpty()) {
            sorterEstadisticas.setRowFilter(null);
        } else {
            // Adaptar el filtro según las columnas de la tabla de estadísticas
            sorterEstadisticas.setRowFilter(RowFilter.regexFilter(filtro, 0)); // Filtra por la columna "Estadística"
        }
    }

    private JPanel crearPanel(String texto) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F08080")); // Un color diferente para distinguirlos
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(etiqueta);
        return panel;
    }

    private void mostrarPanel(JPanel panel) {
        panelDisplay.removeAll();
        panelDisplay.add(panel, BorderLayout.CENTER);
        panelDisplay.revalidate();
        panelDisplay.repaint();
    }

    private void mostrarPanelInicio() {
        JPanel panelInicio = new JPanel();
        panelInicio.setBackground(Color.decode("#ADD8E6")); // Un color para el panel de inicio
        JLabel etiquetaInicio = new JLabel("Bienvenido al Panel de Administración");
        etiquetaInicio.setFont(new Font("Arial", Font.ITALIC, 24));
        panelInicio.add(etiquetaInicio);
        mostrarPanel(panelInicio);
    }
}
