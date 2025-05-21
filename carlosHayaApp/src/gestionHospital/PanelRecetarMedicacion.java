package gestionHospital;

import java.awt.*;
import javax.swing.*;

public class PanelRecetarMedicacion extends JPanel {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel Recetar Medicación");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new PanelRecetarMedicacion().createPanel());
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private Color colorbg = Color.decode("#212f3d"); // Color de fondo para el panel
    private JTextField textFieldPaciente, textFieldMedicamento, textFieldObservaciones;

    public PanelRecetarMedicacion() {
        initComponents();
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorbg);
        // Removed unused and improperly defined initComponents method
        return panel;
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorbg);

        // Título
        JLabel titulo = new JLabel("Recetar Medicación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        this.add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setBackground(colorbg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // ID Paciente
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblPaciente = new JLabel("ID Paciente:");
        lblPaciente.setForeground(Color.WHITE);
        formularioPanel.add(lblPaciente, gbc);

        gbc.gridx = 1;
        textFieldPaciente = new JTextField();
        textFieldPaciente.setPreferredSize(new Dimension(200, 30));
        formularioPanel.add(textFieldPaciente, gbc);

        // Medicamento
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblMedicamento = new JLabel("Medicamento:");
        lblMedicamento.setForeground(Color.WHITE);
        formularioPanel.add(lblMedicamento, gbc);

        gbc.gridx = 1;
        textFieldMedicamento = new JTextField();
        textFieldMedicamento.setPreferredSize(new Dimension(200, 30));
        formularioPanel.add(textFieldMedicamento, gbc);

        // Botón guardar
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnGuardar = new JButton("Guardar Receta");
        btnGuardar.setBackground(Color.decode("#006D77"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(180, 35));
        btnGuardar.addActionListener(e -> guardarReceta());
        formularioPanel.add(btnGuardar, gbc);

        this.add(formularioPanel, BorderLayout.CENTER);
    }

    // Acción para guardar la receta
    private void guardarReceta() {
        String pacienteID = textFieldPaciente.getText().trim();
        String medicamento = textFieldMedicamento.getText().trim();
        String dosis = textFieldObservaciones.getText().trim();

        // Validate input fields
        if (pacienteID.isEmpty() || medicamento.isEmpty() || dosis.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the prescription (logic to save can be added here)
        JOptionPane.showMessageDialog(this, "Receta guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }}
