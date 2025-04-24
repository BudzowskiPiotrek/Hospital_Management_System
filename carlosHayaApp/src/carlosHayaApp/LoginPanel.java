package carlosHayaApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	private App app;

	public LoginPanel(App app) {
		this.app = app;
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel loginLabelUsuario = new JLabel("Usuario:");
		JTextField loginCampoUsuario = new JTextField(15);

		JLabel loginLabelClave = new JLabel("Contraseña:");
		JPasswordField loginCampoClave = new JPasswordField(15);

		JButton loginBotonLogin = new JButton("Iniciar sesión");

		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(loginLabelUsuario, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(loginCampoUsuario, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(loginLabelClave, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(loginCampoClave, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		add(loginBotonLogin, gbc);

		loginBotonLogin.addActionListener(e -> {
			app.mostrarInicio();
			/*
			String usuario = loginCampoUsuario.getText();
			String clave = new String(loginCampoClave.getPassword());
			
			try (Connection conn = ConexionBD.conectar()) {
				
				
				
				
				
		        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		    */
		});

	}
}
