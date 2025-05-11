package gestionHospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelAdmin extends JPanel {
	private final PanelImagen panelImagen;
	private Border border = BorderFactory.createLineBorder(Color.black, 1);
	Color colorbg = Color.decode("#212f3d");
	Color colorButton =Color.decode("#006D77");

	public PanelAdmin(PanelImagen panelImagen) {
		this.panelImagen = panelImagen;
		propiedades();
		contenidos();
	}

	private void propiedades() {
		// Establecer el tamaño preferido del panel
		this.setBackground(colorbg);
		this.setPreferredSize(new Dimension(700, 600));
		
	}

	private void contenidos() {

		// Panel de título
		JPanel tittlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("ADMIN");
		title.setFont(new Font("Arial", Font.BOLD, 35));
		title.setForeground(Color.decode("#F4D35E"));
		tittlePanel.setBackground(colorbg);
		tittlePanel.setPreferredSize(new Dimension(700, 100));
		tittlePanel.add(title);
		this.add(tittlePanel, BorderLayout.NORTH);

		// Panel central con GridBagLayout
		JPanel gridbagpanel = new JPanel(new GridBagLayout());
		gridbagpanel.setPreferredSize(new Dimension(700, 300));
		gridbagpanel.setBackground(colorbg);

		// Create GridBagConstraints to place the buttons in the grid
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Adding gap between buttons
		gbc.fill = GridBagConstraints.BOTH; // Make buttons expand both horizontally and vertically
		gbc.weightx = 1.0; // Allow horizontal expansion
		gbc.weighty = 1.0; // Allow vertical expansion

		// First row, first button
		gbc.gridx = 0;
		gbc.gridy = 0;
		JButton botn1 = new JButton("Gestionar Empleados");
		stylePanelButton(botn1);
		gridbagpanel.add(botn1, gbc);

		// First row, second button
		gbc.gridx = 1;
		gbc.gridy = 0;
		JButton botn2 = new JButton("Gestionar Pacientes");
		stylePanelButton(botn2);
		gridbagpanel.add(botn2, gbc);

		// Second row, first button
		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton botn3 = new JButton("Gestionar Salas");
		stylePanelButton(botn3);
		gridbagpanel.add(botn3, gbc);

		// Second row, second button
		gbc.gridx = 1;
		gbc.gridy = 1;
		JButton botn4 = new JButton("Estadísticas");
		stylePanelButton(botn4);
		gridbagpanel.add(botn4, gbc);


		this.add(gridbagpanel, BorderLayout.CENTER);

		// Panel de botones (mantenemos el panel de abajo)
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		buttonPanel.setPreferredSize(new Dimension(700, 200));
		JButton botnInicio = new JButton("||  Cerrar Sesión  ||");
		buttonPanel.setBackground(colorbg);
		stylePanelButton(botnInicio);
		botnInicio.setPreferredSize(new Dimension(300,75));
		botnInicio.setBackground(Color.white);
		botnInicio.setForeground(Color.black);
		buttonPanel.add(botnInicio);
		this.add(buttonPanel, BorderLayout.SOUTH);
		cambiarAlInicio(botnInicio);
	}

	private void cambiarAlInicio(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelImagen.cambiarPanel(new PanelLogin(panelImagen));
			}
		});
	}

	

	private void stylePanelButton(JButton button) {
		button.setBackground(colorButton);
		button.setForeground(Color.white);
		button.setFont(new Font("Arial", Font.BOLD, 25));
		button.setFocusPainted(false);
		button.setBorder(border);
	}
}
