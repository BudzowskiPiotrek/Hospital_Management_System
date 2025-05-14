package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelAdmin extends JPanel {
    private final PanelImagen panelImagen;
    private Border border = BorderFactory.createLineBorder(Color.black, 1);
    Color colorbg = Color.decode("#212f3d"); // Color de fondo para los paneles internos (paneles de botones, etc.)
    Color colorButton = Color.decode("#006D77"); // Color de los botones
    Color panelColor = Color.decode("#F4D35E"); // Color de fondo del panel envolvente

    // CardLayout para el panel infoPanel
    private CardLayout cardLayout = new CardLayout();
    private JPanel infoPanel; // Declarar infoPanel como una variable de clase para poder hacer referencia a ella más adelante

    public PanelAdmin(PanelImagen panelImagen) {
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
        // Panel Wrapper para controlar el tamaño
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setPreferredSize(new Dimension(800, 650));
        wrapperPanel.setBackground(panelColor); // El panel wrapper tiene el color claro (#F4D35E)

        // Panel de Etiqueta (Norte)
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(colorbg); // Establecer el color de fondo a #212f3d
        JLabel label = new JLabel("ADMIN", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        label.setForeground(Color.decode("#F4D35E"));
        labelPanel.setPreferredSize(new Dimension(800, 100));
        labelPanel.add(label);

        // Panel de Contenido (Centro)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(colorbg); // Establecer el color de fondo a #212f3d

        // Añadir un padding de 10px a contentPanel (arriba, derecha, abajo, izquierda)
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding de 10px en todos los lados

        // Panel Opcion (Oeste)
        JPanel opcionPanel = new JPanel(new GridLayout(5, 5, 10, 10)); // Establecer el espacio entre botones
        opcionPanel.setPreferredSize(new Dimension(230, 550));
        opcionPanel.setBackground(colorbg); // Establecer el color de fondo a #212f3d

        // Panel Info (Centro) con CardLayout
        infoPanel = new JPanel(cardLayout);
        infoPanel.setBackground(Color.WHITE); // Establecer el color de fondo a blanco (para cambios dinámicos)

        // Botones para el Panel Opcion
        String[] buttonLabels = {"Gestionar Empleados", "Gestionar Pacientes", "Gestionar Salas", "Estadísticas", "|| Cerrar Sesión ||"};
        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            stylePanelButton(button);
           
            if (buttonLabel.equals("|| Cerrar Sesión ||")) {
            	button.setBackground(Color.white);
            	button.setForeground(Color.black);
                cambiarAlInicio(button);
            } else {
                // Acción de botón para otros botones
                button.addActionListener(e -> {
                    switchPanel(buttonLabel); // Cambiar al panel correspondiente
                });
            }
            opcionPanel.add(button);
        }
        

        // Paneles de Información para el CardLayout
        for (int i = 1; i <= 5; i++) {
            JPanel panel = new JPanel(new BorderLayout()); // Usar BorderLayout para más control sobre los componentes
            panel.setBackground(Color.WHITE); // Los paneles de información tienen el fondo blanco inicialmente
            JLabel panelLabel = new JLabel("Info Panel " + i);
            panelLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar la etiqueta
            panelLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(panelLabel, BorderLayout.CENTER); // Añadir la etiqueta en el centro

            infoPanel.add(panel, "Panel" + i); // Añadir cada panel de información al CardLayout
        }

        // Añadir espacio entre el opcionPanel y el infoPanel (espacio de 10px)
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10, 1)); // Crear un pequeño panel de espacio con un ancho de 10px
        spacer.setBackground(colorbg); // Establecer el color de fondo para que coincida con el color principal
        
        contentPanel.add(opcionPanel, BorderLayout.WEST);
        contentPanel.add(spacer, BorderLayout.CENTER); // Añadir el espacio entre los paneles
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        // Añadir algo de espacio en la parte inferior del contentPanel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Añadir un padding en la parte inferior

        wrapperPanel.add(labelPanel, BorderLayout.NORTH);
        wrapperPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(wrapperPanel, BorderLayout.CENTER);

        // Refrescar el diseño
        revalidate();
        repaint();
    }

    private void cambiarAlInicio(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(Color.decode("#FF6347")); // Cambiar color al hacer clic
                panelImagen.cambiarPanel(new PanelLogin(panelImagen));
            }
        });
    }

    // Cambiar entre los paneles de información según el botón presionado
    private void switchPanel(String buttonLabel) {
        // Mapear los nombres de los botones a los nombres de los paneles
        String panelName = "";
        switch (buttonLabel) {
            case "Gestionar Empleados":
                panelName = "Panel1"; // Panel correspondiente a "Gestionar Empleados"
                break;
            case "Gestionar Pacientes":
                panelName = "Panel2"; // Panel correspondiente a "Gestionar Pacientes"
                break;
            case "Gestionar Salas":
                panelName = "Panel3"; // Panel correspondiente a "Gestionar Salas"
                break;
            case "Estadísticas":
                panelName = "Panel4"; // Panel correspondiente a "Estadísticas"
                break;
            default:
                panelName = "Panel5"; // Panel por defecto para "Cerrar Sesión"
                break;
        }

        // Ahora mostrar el panel correcto basado en el nombre
        cardLayout.show(infoPanel, panelName);
    }

    private void stylePanelButton(JButton button) {
        button.setBackground(colorButton);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setFocusPainted(false);
        button.setBorder(border);
    }
}
