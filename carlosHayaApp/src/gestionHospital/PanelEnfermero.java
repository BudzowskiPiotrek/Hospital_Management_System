package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelEnfermero extends JPanel {
    private final PanelImagen panelImagen;

    // Tus colores originales de PanelAdmin
  
    Color colorbg = Color.decode("#212f3d"); // Fondo general del panel principal y menú izquierdo
    Color colorButton = Color.decode("#006D77"); // Color de los botones del menú izquierdo
    Color panelColor = Color.decode("#F4D35E"); // Color de fondo del panel envolvente

    private CardLayout cardLayout = new CardLayout();
    private JPanel infoPanel;

    public PanelEnfermero(PanelImagen panelImagen) {
        this.panelImagen = panelImagen;
        setLayout(new BorderLayout()); // Layout principal del PanelEnfermero, sin espaciado aquí
        setBackground(colorbg); // Fondo del PanelEnfermero
        setPreferredSize(new Dimension(800, 650)); // Tamaño original del PanelEnfermero, para que el wrapper se ajuste
        initComponents();
    }

    private void initComponents() {
        // --- Panel Wrapper ---
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(panelColor); // Fondo amarillo para el wrapper

        // Panel de Etiqueta (Cabecera)
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(colorbg); // Fondo oscuro #212f3d
        labelPanel.setBorder(new EmptyBorder(20, 20, 10, 20)); // Padding interno
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("ENFERMERO", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        label.setForeground(Color.decode("#F4D35E")); // Color amarillo
        labelPanel.setPreferredSize(new Dimension(800, 100)); // Altura preferida para el título
        labelPanel.add(label);

        // Contenido principal (contiene el menú de botones y el área de display)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10)); // Espaciado entre componentes
        contentPanel.setBackground(colorbg); // Fondo oscuro #212f3d
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding

        // Panel de opciones (menú lateral izquierdo)
        JPanel opcionPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna, espaciado de 10px
        opcionPanel.setBackground(colorbg); // Fondo oscuro #212f3d
        opcionPanel.setPreferredSize(new Dimension(230, 550)); // Ancho preferido para los botones

        // Espacio a la izquierda del opcionPanel
        opcionPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // 20px de padding a la izquierda

        // Panel Info (área de visualización) con CardLayout
        infoPanel = new JPanel(cardLayout);
        infoPanel.setBackground(Color.decode("#ECF0F1")); // Fondo gris muy claro, fiel al PanelAdmin original

        // He cambiado los nombres de los botones para que coincidan con tu última versión.
        // Asegúrate de que estos nombres sean exactamente los que quieres.
        String[] buttonLabels = { "Asignar Cama", "Dar Alta", "Ver pacientes Asignados", "Cerrar Sesión" };

        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            stylePanelButton(button, buttonLabel.equals("Cerrar Sesión"));

            if (buttonLabel.equals("Cerrar Sesión")) {
                button.addActionListener(e -> cambiarAlInicio(button));
            } else {
                button.addActionListener(e -> switchPanel(buttonLabel));
            }
            opcionPanel.add(button);
        }

        // Añadir los paneles internos (asumiendo que existen estas clases)
        infoPanel.add(new PanelAsignarCama(), "AsignarCama");
        infoPanel.add(new PanelDarAlta(),     "DarAlta");
        infoPanel.add(new PanelVerPacientesAsignados(), "VerPacientes");

        // Espaciador (se mantiene igual que tu PanelAdmin)
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10, 1));
        spacer.setBackground(colorbg); // Color de fondo oscuro para el separador

        contentPanel.add(opcionPanel, BorderLayout.WEST);
        contentPanel.add(spacer, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        contentPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); // Padding inferior

        // Añadir el labelPanel y el contentPanel al wrapperPanel
        wrapperPanel.add(labelPanel, BorderLayout.NORTH);
        wrapperPanel.add(contentPanel, BorderLayout.CENTER);

        // Finalmente, añadir el wrapperPanel al PanelEnfermero principal
        this.add(wrapperPanel, BorderLayout.CENTER);

        revalidate();
        repaint();

        // Mostrar el primer panel por defecto
        cardLayout.show(infoPanel, "AsignarCama");
    }

    private void cambiarAlInicio(JButton button) {
        button.addActionListener(e -> {
            button.setBackground(Color.decode("#FF6347")); // Color de confirmación al hacer clic
            panelImagen.cambiarPanel(new PanelLogin(panelImagen));
        });
    }

    private void switchPanel(String buttonLabel) {
        String panelName = "";
        switch (buttonLabel) {
            case "Asignar Cama": // Corregido: Coincide con la etiqueta actual del botón
                panelName = "AsignarCama";
                break;
            case "Dar Alta":
                panelName = "DarAlta";
                break;
            case "Ver pacientes Asignados":
                panelName = "VerPacientes";
                break;
            default:
                panelName = "DefaultPanel";
                break;
        }
        cardLayout.show(infoPanel, panelName);
    }

    // Método para aplicar estilo a los botones, incluyendo el padding horizontal
    private void stylePanelButton(JButton button, boolean isLogoutButton) {
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Tu fuente original
        button.setFocusPainted(false); // Sin resaltado de foco

        // Clave: Usar EmptyBorder para el padding interno (horizontal y vertical)
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1), // Borde exterior negro (como tu original)
            new EmptyBorder(10, 20, 10, 20) // 10px arriba/abajo, 20px izquierda/derecha
        ));

        if (isLogoutButton) {
            button.setBackground(Color.decode("#C08080")); // Color original del botón Cerrar Sesión
            button.setForeground(Color.white);
        } else {
            button.setBackground(colorButton); // Color original de los botones normales #006D77
            button.setForeground(Color.white);
        }
    }
}
