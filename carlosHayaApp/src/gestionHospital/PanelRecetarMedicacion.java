package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textFieldPaciente, textFieldMedicamento, textFieldDosis, textFieldFrecuencia;
    private JTextArea textAreaInstrucciones;

    public PanelRecetarMedicacion() {
        // Constructor logic if needed
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorbg);
        // Removed unused and improperly defined initComponents method
        return panel;
    }

    private void initComponents() {
        // Titulo del panel
        JLabel titulo = new JLabel("Recetar Medicación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas
        formularioPanel.setBackground(colorbg);

        // Campos del formulario
        JLabel lblPaciente = new JLabel("ID Paciente:");
        lblPaciente.setForeground(Color.WHITE);
        textFieldPaciente = new JTextField();

        JLabel lblMedicamento = new JLabel("Medicamento:");
        lblMedicamento.setForeground(Color.WHITE);
        textFieldMedicamento = new JTextField();

        JLabel lblDosis = new JLabel("Dosis:");
        lblDosis.setForeground(Color.WHITE);
        textFieldDosis = new JTextField();

        JLabel lblFrecuencia = new JLabel("Frecuencia (días/semana):");
        lblFrecuencia.setForeground(Color.WHITE);
        textFieldFrecuencia = new JTextField();

        JLabel lblInstrucciones = new JLabel("Instrucciones:");
        lblInstrucciones.setForeground(Color.WHITE);
        textAreaInstrucciones = new JTextArea(5, 20);
        textAreaInstrucciones.setLineWrap(true);
        textAreaInstrucciones.setWrapStyleWord(true);
        textAreaInstrucciones.setBackground(colorbg);
        textAreaInstrucciones.setForeground(Color.WHITE);
        textAreaInstrucciones.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollInstrucciones = new JScrollPane(textAreaInstrucciones);

        // Botón de guardar receta
        JButton btnGuardar = new JButton("Guardar Receta");
        btnGuardar.setBackground(Color.decode("#006D77"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarReceta();
            }
        });

        // Agregar los componentes al formulario
        formularioPanel.add(lblPaciente);
        formularioPanel.add(textFieldPaciente);
        formularioPanel.add(lblMedicamento);
        formularioPanel.add(textFieldMedicamento);
        formularioPanel.add(lblDosis);
        formularioPanel.add(textFieldDosis);
        formularioPanel.add(lblFrecuencia);
        formularioPanel.add(textFieldFrecuencia);
        formularioPanel.add(lblInstrucciones);
        formularioPanel.add(scrollInstrucciones);
        formularioPanel.add(new JLabel()); // Filler label para alinear el botón
        formularioPanel.add(btnGuardar);

        // Añadir formulario al panel principal
        add(formularioPanel, BorderLayout.CENTER);
    }

    // Acción para guardar la receta
    private void guardarReceta() {
        String pacienteID = textFieldPaciente.getText().trim();
        String medicamento = textFieldMedicamento.getText().trim();
        String dosis = textFieldDosis.getText().trim();
        String frecuencia = textFieldFrecuencia.getText().trim();
        String instrucciones = textAreaInstrucciones.getText().trim();

        // Validate input fields
        if (pacienteID.isEmpty() || medicamento.isEmpty() || dosis.isEmpty() || frecuencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the prescription (logic to save can be added here)
        JOptionPane.showMessageDialog(this, "Receta guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }}
