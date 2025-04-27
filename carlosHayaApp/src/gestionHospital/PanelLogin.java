package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelLogin extends JPanel {

    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton loginButton, salirButton;
    private PanelCardLayout cardLayoutPanel;
    private static Border border= BorderFactory.createLineBorder(Color.black, 2);

    public PanelLogin(PanelCardLayout cardLayoutPanel) {
        this.cardLayoutPanel = cardLayoutPanel;
        setPreferredSize(new Dimension(400,400));
        setBorder(border);
        contenidos();
    }

    private void contenidos() {
        setBackground(new Color(0x123456)); // Fondo azul oscuro

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = Color.WHITE;

        // Panel título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(getBackground());
        JLabel titleLabel = new JLabel("INICIAR SESIÓN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(labelColor);
        titlePanel.add(titleLabel);

        // Panel usuario
        JPanel usuarioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usuarioPanel.setBackground(getBackground());
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(labelFont);
        usuarioLabel.setForeground(labelColor);
        usuarioField = new JTextField(15);
        usuarioPanel.add(usuarioLabel);
        usuarioPanel.add(usuarioField);

        // Panel contraseña
        JPanel contrasenaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contrasenaPanel.setBackground(getBackground());
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(labelFont);
        contrasenaLabel.setForeground(labelColor);
        contrasenaField = new JPasswordField(15);
        contrasenaPanel.add(contrasenaLabel);
        contrasenaPanel.add(contrasenaField);

        // Panel botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonesPanel.setBackground(getBackground());
        loginButton = new JButton("Iniciar");
        salirButton = new JButton("Registrar");

        styleButton(loginButton);
        styleButton(salirButton);

        botonesPanel.add(loginButton);
        botonesPanel.add(salirButton);

        // Añadir todo al panel principal
        setLayout(new GridLayout(4, 1, 10, 0));
        add(titlePanel);
        add(usuarioPanel);
        add(contrasenaPanel);
        add(botonesPanel);

        // Acciones botones
        loginButton.addActionListener(e -> cardLayoutPanel.cambiarPanel(cardLayoutPanel.getPANELADMIN()));
        salirButton.addActionListener(e -> cardLayoutPanel.cambiarPanel(cardLayoutPanel.getPANELREGISTRAR()));
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.white);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100,30));
        button.setBorder(border);
    }
}
