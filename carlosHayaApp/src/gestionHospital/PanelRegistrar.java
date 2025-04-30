package gestionHospital;

import java.awt.*;
import javax.swing.*;

public class PanelRegistrar extends JPanel {
    private PanelCardLayout cardLayoutPanel;

    public PanelRegistrar(PanelCardLayout cardLayoutPanel) {
        this.cardLayoutPanel = cardLayoutPanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.decode("#123456"));
        setLayout(new BorderLayout());

        // === TITLE at the very top ===
        JLabel titleLabel = new JLabel("REGISTRAR", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // === FORM PANEL ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#123456"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("SansSerif", Font.BOLD, 16);
        Color labelColor = Color.WHITE;

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(labelFont);
        nombreLabel.setForeground(labelColor);
        JTextField nombreField = new JTextField(20);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setFont(labelFont);
        apellidoLabel.setForeground(labelColor);
        JTextField apellidoField = new JTextField(20);

        JLabel usuarioLabel = new JLabel("Nombre de Usuario:");
        usuarioLabel.setFont(labelFont);
        usuarioLabel.setForeground(labelColor);
        JTextField usuarioField = new JTextField(20);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(labelFont);
        contrasenaLabel.setForeground(labelColor);
        JPasswordField contrasenaField = new JPasswordField(20);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(labelFont);
        dniLabel.setForeground(labelColor);
        JTextField dniField = new JTextField(20);

        // Row-by-row layout
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nombreLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nombreField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(apellidoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(apellidoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(usuarioLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usuarioField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(contrasenaLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(contrasenaField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(dniLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dniField, gbc);

        // Buttons
        JButton enviarBtn = new JButton("Enviar");
        JButton borrarBtn = new JButton("Borrar");
        JButton inicioBtn = new JButton("Inicio");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#123456"));
        buttonPanel.add(enviarBtn);
        buttonPanel.add(borrarBtn);
        buttonPanel.add(inicioBtn);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }
}
