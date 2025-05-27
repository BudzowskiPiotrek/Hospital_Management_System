package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.*;

import clases.DBConnection;
import clases.Diagnostico;

public class PanelRegistrarDiagnostico extends JPanel {

    private Color colorbg = Color.decode("#212f3d"); // Color de fondo para el panel
    private JTextField textFieldPaciente, textFieldFecha;
    private JTextArea textAreaDescripcion;
    private DBConnection db;
    
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
        
        
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(Color.WHITE);
        textFieldFecha = new JTextField();
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Color.WHITE);
        textAreaDescripcion = new JTextArea(5, 20);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        textAreaDescripcion.setBackground(colorbg);
        textAreaDescripcion.setForeground(Color.WHITE);
        textAreaDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollDescripcion = new JScrollPane(textAreaDescripcion);
        
        //Conexión con base de datos
        db = new DBConnection();
        
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
                    	 try{
                        // Implementación para guardar el diagnóstico
                        String idPaciente = textFieldPaciente.getText();
                        String fecha = textFieldFecha.getText();
                        String descripcion = textAreaDescripcion.getText();
                        
                        // Separamos la fecha introducida, en dia, mes y año
                        int dia = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
                        int mes = Integer.parseInt(fecha.substring(fecha.indexOf("-") + 1, fecha.lastIndexOf("-")));
                        int anio = Integer.parseInt(fecha.substring(fecha.lastIndexOf("-") + 1));
                         LocalDate fechaTransformada = LocalDate.of(anio, mes, dia);
                        if (idPaciente.isEmpty() ||descripcion.isEmpty() || fecha.isEmpty() ) {
                            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        }else {
                        	Diagnostico d = new Diagnostico(idPaciente, Sesion.getUsuarioLogueado(), descripcion, fechaTransformada);
                        	if(db.agregarDiagnostico(d)) {
                        		JOptionPane.showMessageDialog(null, "Diagnóstico guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        	}else {
                        		JOptionPane.showMessageDialog(null, "Error al guardar el diagnóstico", "Error", JOptionPane.ERROR_MESSAGE);
                        	}
                            
                        }
                    	 }catch(DateTimeException e) {
                         	JOptionPane.showMessageDialog(null, "La fecha no existe", "Error", JOptionPane.ERROR_MESSAGE);
                         }
                    }
                });
                
                // Agregar los componentes al formulario
                formularioPanel.add(lblPaciente);
                formularioPanel.add(textFieldPaciente);
                formularioPanel.add(lblDescripcion);
                formularioPanel.add(scrollDescripcion);
                formularioPanel.add(lblFecha);
                formularioPanel.add(textFieldFecha);
                
                formularioPanel.add(new JLabel()); // Espacio vacío
                formularioPanel.add(btnGuardar);
        
                add(formularioPanel, BorderLayout.CENTER);
            }
        }
       