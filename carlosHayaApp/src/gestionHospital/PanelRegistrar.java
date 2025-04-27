package gestionHospital;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class PanelRegistrar extends JPanel {
    private PanelCardLayout cardLayoutPanel;

    public PanelRegistrar(PanelCardLayout cardLayoutPanel) {
        this.cardLayoutPanel = cardLayoutPanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLUE); // Color azul para registrar
    }
}
