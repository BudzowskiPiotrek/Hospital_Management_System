package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelRegistrarDiagnostico extends JPanel {

  // --- Paleta de Colores ---
  // Color de fondo principal, un azul grisáceo oscuro para una apariencia
  // profesional.
  private final Color primaryBackgroundColor = Color.decode("#2C3E50");
  // Color de acento, un azul vibrante para botones y bordes destacados.
  private final Color accentColor = Color.decode("#3498DB");
  // Color de texto claro, ideal para contrastar con fondos oscuros.
  private final Color textColor = Color.WHITE;
  // Color de texto para los campos de entrada, ahora será NEGRO.
  private final Color inputFieldTextColor = Color.BLACK; // ¡Cambiado a NEGRO!
  // Color del borde para los campos de entrada.
  private final Color inputFieldBorderColor = Color.decode("#5D6D7E"); // Un gris azulado más claro

  private JTextField textFieldPaciente, textFieldDiagnostico, textFieldTratamiento;
  private JTextArea textAreaNotas;

  public PanelRegistrarDiagnostico() {
    // Establece el gestor de diseño principal del panel a BorderLayout.
    setLayout(new BorderLayout(10, 10)); // Añade un pequeño espacio entre las regiones
    // Establece el color de fondo de todo el panel.
    setBackground(primaryBackgroundColor);
    // Define el tamaño preferido del panel para una mejor visualización.
    setPreferredSize(new Dimension(800, 600));
    // Llama al método para inicializar y configurar todos los componentes de la
    // interfaz.
    initComponents();
  }

  private void initComponents() {
    // --- Panel del Título ---
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(primaryBackgroundColor); // Fondo del panel del título
    // Añade un relleno alrededor del título para darle espacio.
    titlePanel.setBorder(new EmptyBorder(20, 20, 10, 20));
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centra el JLabel del título

    JLabel titulo = new JLabel("Registrar Diagnóstico");
    // Fuente moderna, en negrita y de mayor tamaño para el título.
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
    titulo.setForeground(textColor); // Color del texto del título
    titlePanel.add(titulo);
    // Coloca el panel del título en la región NORTE del BorderLayout.
    add(titlePanel, BorderLayout.NORTH);

    // --- Panel del Formulario ---
    // Usamos GridBagLayout para un control más preciso sobre la disposición de los
    // componentes
    JPanel formularioPanel = new JPanel(new GridBagLayout());
    formularioPanel.setBackground(primaryBackgroundColor); // Fondo del panel del formulario
    // Añade un relleno alrededor del formulario para que no esté pegado a los
    // bordes del panel principal.
    formularioPanel.setBorder(new EmptyBorder(20, 50, 20, 50)); // Más padding lateral

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 5, 8, 5); // Relleno (padding) para cada componente
    gbc.fill = GridBagConstraints.HORIZONTAL; // Los campos de texto se expandirán horizontalmente

    // --- Campos del Formulario ---

    // ID Paciente
    JLabel lblPaciente = new JLabel("ID Paciente:");
    lblPaciente.setForeground(textColor);
    lblPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fuente para la etiqueta
    gbc.gridx = 0; // Columna 0
    gbc.gridy = 0; // Fila 0
    gbc.anchor = GridBagConstraints.EAST; // Alinea la etiqueta a la derecha
    formularioPanel.add(lblPaciente, gbc);

    textFieldPaciente = new JTextField(20); // Tamaño preferido para el campo
    textFieldPaciente.setBackground(Color.WHITE); // ¡Fondo BLANCO!
    textFieldPaciente.setForeground(inputFieldTextColor); // ¡Texto NEGRO!
    textFieldPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldPaciente.setCaretColor(Color.BLACK); // Color del cursor (cambiado a negro)
    textFieldPaciente.setBorder(new LineBorder(inputFieldBorderColor, 1, true)); // Borde redondeado
    gbc.gridx = 1; // Columna 1
    gbc.gridy = 0; // Fila 0
    gbc.anchor = GridBagConstraints.WEST; // Alinea el campo a la izquierda
    gbc.weightx = 1.0; // Permite que el campo se expanda
    formularioPanel.add(textFieldPaciente, gbc);

    // Diagnóstico
    JLabel lblDiagnostico = new JLabel("Diagnóstico:");
    lblDiagnostico.setForeground(textColor);
    lblDiagnostico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0; // Restablece el peso para la etiqueta
    formularioPanel.add(lblDiagnostico, gbc);

    textFieldDiagnostico = new JTextField(20);
    textFieldDiagnostico.setBackground(Color.WHITE); // ¡Fondo BLANCO!
    textFieldDiagnostico.setForeground(inputFieldTextColor); // ¡Texto NEGRO!
    textFieldDiagnostico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldDiagnostico.setCaretColor(Color.BLACK); // Color del cursor
    textFieldDiagnostico.setBorder(new LineBorder(inputFieldBorderColor, 1, true));
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0; // Permite que el campo se expanda
    formularioPanel.add(textFieldDiagnostico, gbc);

    // Tratamiento
    JLabel lblTratamiento = new JLabel("Tratamiento:");
    lblTratamiento.setForeground(textColor);
    lblTratamiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0;
    formularioPanel.add(lblTratamiento, gbc);

    textFieldTratamiento = new JTextField(20);
    textFieldTratamiento.setBackground(Color.WHITE); // ¡Fondo BLANCO!
    textFieldTratamiento.setForeground(inputFieldTextColor); // ¡Texto NEGRO!
    textFieldTratamiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldTratamiento.setCaretColor(Color.BLACK); // Color del cursor
    textFieldTratamiento.setBorder(new LineBorder(inputFieldBorderColor, 1, true));
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0;
    formularioPanel.add(textFieldTratamiento, gbc);

    // Notas adicionales (JTextArea)
    JLabel lblNotas = new JLabel("Notas adicionales:");
    lblNotas.setForeground(textColor);
    lblNotas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.NORTHEAST; // Alinea la etiqueta arriba a la derecha
    gbc.weightx = 0;
    formularioPanel.add(lblNotas, gbc);

    textAreaNotas = new JTextArea(5, 20); // Filas y columnas preferidas
    textAreaNotas.setLineWrap(true); // Ajuste de línea automático
    textAreaNotas.setWrapStyleWord(true); // Ajuste de línea por palabra
    textAreaNotas.setBackground(Color.WHITE); // ¡Fondo BLANCO!
    textAreaNotas.setForeground(inputFieldTextColor); // ¡Texto NEGRO!
    textAreaNotas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textAreaNotas.setCaretColor(Color.BLACK); // Color del cursor
    textAreaNotas.setBorder(new LineBorder(inputFieldBorderColor, 1, true));
    // Envuelve el JTextArea en un JScrollPane para permitir el desplazamiento si el
    // texto es largo.
    JScrollPane scrollNotas = new JScrollPane(textAreaNotas);
    scrollNotas.setBorder(new LineBorder(inputFieldBorderColor, 1, true)); // Borde para el scroll pane
    scrollNotas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra de desplazamiento
                                                                                      // vertical

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0; // Permite que el área de texto se expanda verticalmente
    gbc.fill = GridBagConstraints.BOTH; // El scroll pane se expandirá en ambas direcciones
    formularioPanel.add(scrollNotas, gbc);

    // --- Botón de Guardar ---
    JButton btnGuardar = new JButton("Guardar Diagnóstico");
    btnGuardar.setBackground(accentColor); // Color de fondo del botón
    btnGuardar.setForeground(Color.WHITE); // Color del texto del botón
    btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Fuente en negrita y más grande
    btnGuardar.setFocusPainted(false); // Elimina el borde de enfoque
    btnGuardar.setBorder(BorderFactory.createCompoundBorder(
        new LineBorder(accentColor.darker(), 1, true), // Borde exterior
        new EmptyBorder(10, 20, 10, 20) // Relleno interior
    ));

    // Añade un ActionListener para manejar el clic del botón.
    btnGuardar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        guardarDiagnostico(); // Llama al método para guardar el diagnóstico
      }

      private void guardarDiagnostico() {
        // Obtiene el texto de los campos de entrada.
        String paciente = textFieldPaciente.getText();
        String diagnostico = textFieldDiagnostico.getText();
        String tratamiento = textFieldTratamiento.getText();
        String notas = textAreaNotas.getText();

        // Validación básica: comprueba si los campos obligatorios están vacíos.
        if (paciente.trim().isEmpty() || diagnostico.trim().isEmpty() || tratamiento.trim().isEmpty()) {
          // Muestra un mensaje de error si faltan campos.
          JOptionPane.showMessageDialog(PanelRegistrarDiagnostico.this, // Usa 'this' del panel como padre
              "Por favor, complete todos los campos obligatorios (ID Paciente, Diagnóstico, Tratamiento).",
              "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } else {
          // Muestra un mensaje de éxito si los datos son válidos.
          JOptionPane.showMessageDialog(PanelRegistrarDiagnostico.this,
              "Diagnóstico guardado exitosamente para el paciente: " + paciente,
              "Éxito", JOptionPane.INFORMATION_MESSAGE);
          // Aquí es donde iría la lógica real para guardar los datos en una base de
          // datos, etc.
          // Opcionalmente, puedes limpiar los campos después de guardar:
          // textFieldPaciente.setText("");
          // textFieldDiagnostico.setText("");
          // textFieldTratamiento.setText("");
          // textAreaNotas.setText("");
        }
      }
    });

    // Añade el botón al formulario.
    gbc.gridx = 0; // Columna 0
    gbc.gridy = 4; // Fila 4
    gbc.gridwidth = 2; // Ocupa 2 columnas
    gbc.fill = GridBagConstraints.NONE; // No expande el botón
    gbc.anchor = GridBagConstraints.CENTER; // Centra el botón
    gbc.weighty = 0.0; // No permite que el botón se expanda verticalmente
    gbc.insets = new Insets(25, 5, 8, 5); // Más espacio superior para el botón
    formularioPanel.add(btnGuardar, gbc);

    // Añade el panel del formulario a la región CENTRAL del BorderLayout del panel
    // principal.
    add(formularioPanel, BorderLayout.CENTER);
  }
}