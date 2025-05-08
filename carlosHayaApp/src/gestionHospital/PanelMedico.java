package gestionHospital;

import java.awt.*;
import javax.swing.*;

public class PanelMedico extends JPanel {

  private PanelImagen panelImagen;

  public PanelMedico(PanelImagen panelImagen) {
    this.panelImagen = panelImagen;
    setBackground(Color.decode("#1c2833"));
    setLayout(new BorderLayout());

    // Título
    JLabel titleLabel = new JLabel("Panel del Médico", SwingConstants.CENTER);
    titleLabel.setForeground(Color.orange);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    add(titleLabel, BorderLayout.NORTH);

    // Panel de botones
    JPanel botonesPanel = new JPanel(new GridLayout(5, 1, 20, 20));
    botonesPanel.setBackground(getBackground());
    botonesPanel.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

    // Botones
    String[] opciones = {
        "Ver pacientes asignados",
        "Ver/Agregar historial médico",
        "Registrar diagnóstico",
        "Recetar medicación",
        "Cerrar sesión"
    };

    for (String texto : opciones) {
      JButton boton = crearBoton(texto);
      boton.addActionListener(e -> manejarAccion(texto));
      botonesPanel.add(boton);
    }

    add(botonesPanel, BorderLayout.CENTER);
  }

  private JButton crearBoton(String texto) {
    JButton boton = new JButton(texto);
    boton.setBackground(Color.WHITE);
    boton.setForeground(Color.BLACK);
    boton.setFont(new Font("Arial", Font.BOLD, 18));
    boton.setFocusPainted(false);
    boton.setPreferredSize(new Dimension(300, 60));
    return boton;
  }

  private void manejarAccion(String accion) {
    switch (accion) {
      case "Ver pacientes asignados":
        // Cambiar a panel de pacientes
        break;
      case "Ver/Agregar historial médico":
        // Mostrar historial médico
        break;
      case "Registrar diagnóstico":
        // Panel para diagnóstico
        break;
      case "Recetar medicación":
        // Panel para recetas
        break;
      case "Cerrar sesión":
        panelImagen.cambiarPanel(new PanelLogin(panelImagen));
        break;
    }
  }
}