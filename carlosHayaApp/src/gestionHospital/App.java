package gestionHospital;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App extends JFrame {

    public App() {
        propiedades();
        crearPanelImagen();
    }

    private void crearPanelImagen() {
        PanelImagen panelImagen = new PanelImagen(this);
        this.add(panelImagen);
    }

    private void propiedades() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        this.setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
