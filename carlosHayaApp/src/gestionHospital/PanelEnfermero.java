// Archivo: PanelEnfermero.java
package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class PanelEnfermero extends JPanel {
    private final PanelImagen panelImagen;
    private Border border = BorderFactory.createLineBorder(Color.black, 1);
    Color colorbg = Color.decode("#212f3d");
    Color colorButton = Color.decode("#006D77");
    Color panelColor = Color.decode("#F4D35E");

    private CardLayout cardLayout = new CardLayout();
    private JPanel infoPanel;

    public PanelEnfermero(PanelImagen panelImagen) {
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

        // Cabecera
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(colorbg);
        JLabel label = new JLabel("ENFERMERO", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        label.setForeground(Color.decode("#F4D35E"));
        labelPanel.setPreferredSize(new Dimension(800, 100));
        labelPanel.add(label);

        // Contenido principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(colorbg);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de opciones
        JPanel opcionPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        opcionPanel.setPreferredSize(new Dimension(230, 550));
        opcionPanel.setBackground(colorbg);

        infoPanel = new JPanel(cardLayout);
        infoPanel.setBackground(Color.WHITE);

        String[] buttonLabels = {
            "Asignar cama a paciente",
            "Dar de alta a paciente",
            "Ver pacientes asignados",
            "|| Cerrar Sesión ||"
        };
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

        // Añadir los paneles internos
        infoPanel.add(new PanelAsignarCama(), "Panel1");
        infoPanel.add(new PanelDarAlta(),     "Panel2");
        infoPanel.add(new PanelVerPacientesAsignados(), "Panel3");
        infoPanel.add(new JPanel(),           "Panel4");

        // Espaciador
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10, 1));
        spacer.setBackground(colorbg);

        contentPanel.add(opcionPanel, BorderLayout.WEST);
        contentPanel.add(spacer, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        wrapperPanel.add(labelPanel, BorderLayout.NORTH);
        wrapperPanel.add(contentPanel, BorderLayout.CENTER);
        this.add(wrapperPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void cambiarAlInicio(JButton button) {
        button.addActionListener(e -> {
            button.setBackground(Color.decode("#FF6347"));
            panelImagen.cambiarPanel(new PanelLogin(panelImagen));
        });
    }

    private void switchPanel(String buttonLabel) {
        String panelName;
        switch (buttonLabel) {
            case "Asignar cama a paciente": panelName = "Panel1"; break;
            case "Dar de alta a paciente":   panelName = "Panel2"; break;
            case "Ver pacientes asignados": panelName = "Panel3"; break;
            default:                         panelName = "Panel4"; break;
        }
        cardLayout.show(infoPanel, panelName);
    }

    private void stylePanelButton(JButton button) {
        button.setBackground(colorButton);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(border);
    }
}