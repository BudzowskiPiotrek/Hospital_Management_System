package carlosHayaApp;

import javax.swing.*;

public class App extends JFrame {
	private JDesktopPane escritorio = new JDesktopPane();

	public App() {
		// NOMBRE Y AJUSTES GENERALES DE LA APP
		setTitle("Hospital Carlos Haya");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		mostrarLogin();
	}

	// METODO PARA CAMBIAR DE PANELES
	public void cambiarPanel(JPanel nuevo) {
		getContentPane().removeAll();
		getContentPane().add(nuevo);
		revalidate();
		repaint();
	}
	public void abrirMarcoInterno(JInternalFrame frame) {
	    escritorio.add(frame);
	    frame.setVisible(true);
	}

	public JDesktopPane getEscritorio() {
	    return escritorio;
	}

	
	// CADA UNO DE LAS FRAMES TENDRA AQUI SU METODO PARA INVOCAR
	// SE HACE DESDE OTRA CLASE PONIENDO app.mostrarInicio();
	// PAGINA PRIMERA QUE SE CARGA.
	public void mostrarLogin() {
		cambiarPanel(new LoginPanel(this));
	}

	// PAGINA DE INICIO
	public void mostrarInicio() {
		cambiarPanel(new HomePanel(this));
	}
		
	// EL MAIN CON INVOCAR PAPI!!!!!!
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			new App();
		});
	}
}
