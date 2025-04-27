package gestionHospital;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelCardLayout extends JPanel {
    private final CardLayout cardLayout;
    private final String PANELLOGIN = "login";
    private final String PANELADMIN = "admin";
    private final String PANELREGISTRAR = "registrar";

    private JPanel wrapperGeneral;
    private Image img;

    public PanelCardLayout() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        propiedades();
    }

    private void propiedades() {
        this.setPreferredSize(new Dimension(900,650));
       img = new ImageIcon("Imagen/hospital_pic_dos.png").getImage();

        wrapperGeneral = new JPanel(new GridBagLayout());
        wrapperGeneral.setOpaque(false); // Hacer transparente
        wrapperGeneral.setPreferredSize(new Dimension(900, 650));
        this.add(wrapperGeneral, "wrapper");

        // Mostrar el login al inicio
        wrapperGeneral.add(new PanelLogin(this));
        cardLayout.show(this, "wrapper");
    }

    // Método para cambiar contenido dentro del wrapper
    public void cambiarPanel(String nombrePanel) {
        wrapperGeneral.removeAll(); // Borrar contenido anterior

        switch (nombrePanel) {
            case PANELLOGIN:
                wrapperGeneral.add(new PanelLogin(this));
                break;
            case PANELADMIN:
                wrapperGeneral.add(new PanelAdministrador(this));
                break;
            case PANELREGISTRAR:
                wrapperGeneral.add(new PanelRegistrar(this));
                break;
        }

        wrapperGeneral.revalidate(); // Actualizar cambios
        wrapperGeneral.repaint();    // Redibujar
    }

    public String getPANELLOGIN() {
        return PANELLOGIN;
    }

    public String getPANELADMIN() {
        return PANELADMIN;
    }

    public String getPANELREGISTRAR() {
        return PANELREGISTRAR;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, wrapperGeneral.getWidth(), wrapperGeneral.getHeight(), wrapperGeneral);
    }
}
