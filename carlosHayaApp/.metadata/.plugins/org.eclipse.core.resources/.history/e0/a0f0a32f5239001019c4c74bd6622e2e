package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelRegistrarDiagnostico extends JPanel {

    private Color colorbg = Color.decode("#212f3d"); // Color de fondo para el panel
    private JTextField textFieldPaciente, textFieldDiagnostico, textFieldTratamiento;
    private JTextArea textAreaNotas;
    
    public PanelRegistrarDiagnostico() {
        setLayout(new BorderLayout());
        setBackground(colorbg);
        initComponents();
    }

    private void initComponents() {
        // Titulo del panel
        JLabel titulo = new JLabel("Registrar Diagnóstico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas
        formularioPanel.setBackground(colorbg);

        // Campos del formulario
        JLabel lblPaciente = new JLabel("ID Paciente:");
        lblPaciente.setForeground(Color.WHITE);
        textFieldPaciente = new JTextField();
        
        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        lblDiagnostico.setForeground(Color.WHITE);
        textFieldDiagnostico = new JTextField();
        
        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setForeground(Color.WHITE);
        textFieldTratamiento = new JTextField();
        
        JLabel lblNotas = new JLabel("Notas adicionales:");
        lblNotas.setForeground(Color.WHITE);
        textAreaNotas = new JTextArea(5, 20);
        textAreaNotas.setLineWrap(true);
        textAreaNotas.setWrapStyleWord(true);
        textAreaNotas.setBackground(colorbg);
        textAreaNotas.setForeground(Color.WHITE);
        textAreaNotas.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollNotas = new JScrollPane(textAreaNotas);
        
        // Botón de guardar
        JButton btnGuardar = new JButton("Guardar Diagnóstico");
        btnGuardar.setBackground(Color.decode("#006D77"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDiagnostico();
            }

                    private void guardarDiagnostico() {
                        // Implementación para guardar el diagnóstico
                        String paciente = textFieldPaciente.getText();
                        String diagnostico = textFieldDiagnostico.getText();
                        String tratamiento = textFieldTratamiento.getText();
                        String notas = textAreaNotas.getText();
        
                        if (paciente.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Diagnóstico guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                
                // Agregar los componentes al formulario
                formularioPanel.add(lblPaciente);
                formularioPanel.add(textFieldPaciente);
                formularioPanel.add(lblDiagnostico);
                formularioPanel.add(textFieldDiagnostico);
                formularioPanel.add(lblTratamiento);
                formularioPanel.add(textFieldTratamiento);
                formularioPanel.add(lblNotas);
                formularioPanel.add(scrollNotas);
                formularioPanel.add(new JLabel()); // Espacio vacío
                formularioPanel.add(btnGuardar);
        
                add(formularioPanel, BorderLayout.CENTER);
            }
        }
       
