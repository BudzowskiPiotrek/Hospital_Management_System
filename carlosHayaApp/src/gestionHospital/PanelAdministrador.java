package gestionHospital;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class PanelAdministrador extends JPanel {
    private PanelCardLayout cardLayoutPanel;

    public PanelAdministrador(PanelCardLayout cardLayoutPanel) {
        this.cardLayoutPanel = cardLayoutPanel;
        setPreferredSize(new Dimension(400, 200));
        setBackground(Color.ORANGE); // Color de fondo para distinguir
    }
}
