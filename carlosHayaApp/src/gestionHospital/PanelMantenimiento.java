package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class PanelMantenimiento extends JPanel {
    private final PanelImagen panelImagen;
    private Border border = BorderFactory.createLineBorder(Color.black, 1);
    Color colorbg = Color.decode("#212f3d"); // Fondo paneles internos
    Color colorButton = Color.decode("#006D77"); // Color botones
    Color panelColor = Color.decode("#F4D35E"); // Fondo panel envolvente

    private CardLayout cardLayout = new CardLayout();
    private JPanel infoPanel;

    public PanelMantenimiento(PanelImagen panelImagen) {
        this.panelImagen = panelImagen;
        setLayout(new BorderLayout());
        propiedades();
        contenidos();
    }

    private void propiedades() {
        this.setBackground(colorbg);
        this.setPreferredSize(new Dimension(800, 650));
    }

    private void contenidos() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setPreferredSize(new Dimension(800, 650));
        wrapperPanel.setBackground(panelColor);

        // Panel de etiqueta superior
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(colorbg);
        JLabel label = new JLabel("PERSONAL DE MANTENIMIENTO", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.decode("#F4D35E"));
        labelPanel.setPreferredSize(new Dimension(800, 100));
        labelPanel.add(label);

        // Panel contenido principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(colorbg);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel opciones (botones)
        JPanel opcionPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        opcionPanel.setPreferredSize(new Dimension(230, 550));
        opcionPanel.setBackground(colorbg);

        String[] buttonLabels = { "Salas", "Habitaciones", "|| Cerrar Sesión ||" };
        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            stylePanelButton(button);

            if (buttonLabel.equals("|| Cerrar Sesión ||")) {
                button.setBackground(Color.white);
                button.setForeground(Color.black);
                cambiarAlInicio(button);
            } else {
                button.addActionListener(e -> switchPanel(buttonLabel));
            }
            opcionPanel.add(button);
        }

        // Panel info con CardLayout
        infoPanel = new JPanel(cardLayout);
        infoPanel.setBackground(Color.WHITE);

        infoPanel.add(panelSalas(), "Salas");
        infoPanel.add(panelHabitaciones(), "Habitaciones");
        infoPanel.add(new JPanel(), "Cerrar");

        // Panel espacio entre botones y contenido
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10, 1));
        spacer.setBackground(colorbg);

        contentPanel.add(opcionPanel, BorderLayout.WEST);
        contentPanel.add(spacer, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        wrapperPanel.add(labelPanel, BorderLayout.NORTH);
        wrapperPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(wrapperPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JPanel panelSalas() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.WHITE);
    JLabel titulo = new JLabel("Estado de Limpieza - Salas", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 22));
    titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(titulo, BorderLayout.NORTH);

    JPanel listaSalas = new JPanel();
    listaSalas.setLayout(new BoxLayout(listaSalas, BoxLayout.Y_AXIS));
    listaSalas.setBackground(Color.WHITE);

    String[] salas = { "Sala 101", "Sala 102", "Sala 103", "Sala 104", "Sala 105" };

    for (String sala : salas) {
        JPanel salaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        salaPanel.setBackground(Color.WHITE);
        JLabel salaLabel = new JLabel(sala);
        salaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        salaPanel.add(salaLabel);

        JRadioButton rbSi = new JRadioButton("Sí");
        JRadioButton rbNo = new JRadioButton("No");
        rbSi.setBackground(Color.WHITE);
        rbNo.setBackground(Color.WHITE);
        rbSi.setFont(new Font("Arial", Font.PLAIN, 16));
        rbNo.setFont(new Font("Arial", Font.PLAIN, 16));

        // Agrupar para que solo uno se pueda seleccionar
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbSi);
        grupo.add(rbNo);

        // Por defecto, no limpia (No seleccionado es "No")
        rbNo.setSelected(true);

        salaPanel.add(rbSi);
        salaPanel.add(rbNo);

        listaSalas.add(salaPanel);
    }

    JScrollPane scrollPane = new JScrollPane(listaSalas);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}

private JPanel panelHabitaciones() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.WHITE);
    JLabel titulo = new JLabel("Estado de Limpieza - Habitaciones", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 22));
    titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(titulo, BorderLayout.NORTH);

    JPanel listaHabitaciones = new JPanel();
    listaHabitaciones.setLayout(new BoxLayout(listaHabitaciones, BoxLayout.Y_AXIS));
    listaHabitaciones.setBackground(Color.WHITE);

    String[] habitaciones = { "Habitación 201", "Habitación 202", "Habitación 203", "Habitación 204", "Habitación 205" };

    for (String habitacion : habitaciones) {
        JPanel habitacionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        habitacionPanel.setBackground(Color.WHITE);
        JLabel habitacionLabel = new JLabel(habitacion);
        habitacionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        habitacionPanel.add(habitacionLabel);

        JRadioButton rbSi = new JRadioButton("Sí");
        JRadioButton rbNo = new JRadioButton("No");
        rbSi.setBackground(Color.WHITE);
        rbNo.setBackground(Color.WHITE);
        rbSi.setFont(new Font("Arial", Font.PLAIN, 16));
        rbNo.setFont(new Font("Arial", Font.PLAIN, 16));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbSi);
        grupo.add(rbNo);

        rbNo.setSelected(true);

        habitacionPanel.add(rbSi);
        habitacionPanel.add(rbNo);

        listaHabitaciones.add(habitacionPanel);
    }

    JScrollPane scrollPane = new JScrollPane(listaHabitaciones);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}

    private void cambiarAlInicio(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(Color.decode("#FF6347"));
                panelImagen.cambiarPanel(new PanelLogin(panelImagen));
            }
        });
    }

    private void switchPanel(String buttonLabel) {
        cardLayout.show(infoPanel, buttonLabel);
    }

    private void stylePanelButton(JButton button) {
        button.setBackground(colorButton);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(border);
    }
}
