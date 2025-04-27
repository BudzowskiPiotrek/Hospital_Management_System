package gestionHospital;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel {
    private Image img;
    private App app;

    public PanelImagen(App app) {
        this.app = app;
        propiedades();
        crearBackgroungImagen();
        crearPanelCardLayout();
    }

    private void propiedades() {
        setLayout(new GridBagLayout()); // Centrado de contenido
    }

    private void crearBackgroungImagen() {
        img = new ImageIcon("Imagen/Hospital_Carlos_Haya.jpg").getImage(); // Imagen fondo
    }

    private void crearPanelCardLayout() {
        PanelCardLayout panelCardLayout = new PanelCardLayout();
        this.add(panelCardLayout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // Pintar imagen de fondo
    }
}
