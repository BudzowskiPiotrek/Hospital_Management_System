package gestionHospital;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelRecetarMedicacion extends JPanel {

  // --- Paleta de Colores ---
  // Color de fondo principal para el panel, un azul grisáceo oscuro.
  private final Color primaryBackgroundColor = Color.decode("#2C3E50");
  // Color de acento, un azul vibrante para elementos importantes como botones.
  private final Color accentColor = Color.decode("#3498DB");
  // Color de texto claro para las etiquetas y títulos sobre fondos oscuros.
  private final Color textColor = Color.WHITE;
  // Color de texto para los campos de entrada, ahora será negro para mejor
  // contraste con fondo blanco.
  private final Color inputFieldTextColor = Color.BLACK;
  // Color del borde para los campos de entrada, un tono de gris azulado.
  private final Color inputFieldBorderColor = Color.decode("#5D6D7E");

  private JTextField textFieldPaciente, textFieldMedicamento, textFieldDosis; // Renombrado a textFieldDosis

  public PanelRecetarMedicacion() {
    // Configura el gestor de diseño principal del panel como BorderLayout.
    // Esto permite colocar el título en la parte superior y el formulario en el
    // centro.
    setLayout(new BorderLayout(10, 10)); // Espaciado entre componentes
    // Establece el color de fondo del panel.
    setBackground(primaryBackgroundColor);
    // Define el tamaño preferido del panel para una buena visualización.
    setPreferredSize(new Dimension(800, 600));
    // Llama al método que inicializa y organiza todos los componentes de la
    // interfaz.
    initComponents();
  }

  private void initComponents() {
    // --- Panel del Título ---
    // Contenedor para el título, lo centra y le añade relleno.
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(primaryBackgroundColor); // El fondo coincide con el del panel principal
    titlePanel.setBorder(new EmptyBorder(20, 20, 10, 20)); // Relleno alrededor del título
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centra el JLabel del título

    JLabel titulo = new JLabel("Recetar Medicación");
    // Fuente moderna, en negrita y de mayor tamaño para el título.
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
    titulo.setForeground(textColor); // Color del texto del título
    titlePanel.add(titulo);
    // Añade el panel del título a la región NORTE del BorderLayout.
    add(titlePanel, BorderLayout.NORTH);

    // --- Panel del Formulario ---
    // Utiliza GridBagLayout para un control preciso de la posición, tamaño y
    // alineación de los campos.
    JPanel formularioPanel = new JPanel(new GridBagLayout());
    formularioPanel.setBackground(primaryBackgroundColor); // Fondo del panel del formulario
    // Relleno alrededor del formulario para que no esté pegado a los bordes.
    formularioPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 5, 8, 5); // Relleno para cada componente (margen interno)
    gbc.fill = GridBagConstraints.HORIZONTAL; // Los campos de texto se expandirán horizontalmente

    // --- Campos del Formulario ---

    // ID Paciente
    JLabel lblPaciente = new JLabel("ID Paciente:");
    lblPaciente.setForeground(textColor);
    lblPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0; // Columna 0
    gbc.gridy = 0; // Fila 0
    gbc.anchor = GridBagConstraints.EAST; // Alinea la etiqueta a la derecha
    formularioPanel.add(lblPaciente, gbc);

    textFieldPaciente = new JTextField(20); // Tamaño preferido
    textFieldPaciente.setBackground(Color.WHITE); // Fondo blanco
    textFieldPaciente.setForeground(inputFieldTextColor); // Texto negro
    textFieldPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldPaciente.setCaretColor(inputFieldTextColor); // Color del cursor
    textFieldPaciente.setBorder(new LineBorder(inputFieldBorderColor, 1, true)); // Borde redondeado
    gbc.gridx = 1; // Columna 1
    gbc.gridy = 0; // Fila 0
    gbc.anchor = GridBagConstraints.WEST; // Alinea el campo a la izquierda
    gbc.weightx = 1.0; // Permite que el campo se expanda horizontalmente
    formularioPanel.add(textFieldPaciente, gbc);

    // Medicamento
    JLabel lblMedicamento = new JLabel("Medicamento:");
    lblMedicamento.setForeground(textColor);
    lblMedicamento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0; // Restablece el peso para la etiqueta
    formularioPanel.add(lblMedicamento, gbc);

    textFieldMedicamento = new JTextField(20);
    textFieldMedicamento.setBackground(Color.WHITE); // Fondo blanco
    textFieldMedicamento.setForeground(inputFieldTextColor); // Texto negro
    textFieldMedicamento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldMedicamento.setCaretColor(inputFieldTextColor); // Color del cursor
    textFieldMedicamento.setBorder(new LineBorder(inputFieldBorderColor, 1, true));
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0; // Permite que el campo se expanda
    formularioPanel.add(textFieldMedicamento, gbc);

    // Dosis (Anteriormente observaciones, ahora con un propósito más específico)
    JLabel lblDosis = new JLabel("Dosis:");
    lblDosis.setForeground(textColor);
    lblDosis.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0;
    formularioPanel.add(lblDosis, gbc);

    textFieldDosis = new JTextField(20); // Renombrado de textFieldObservaciones a textFieldDosis
    textFieldDosis.setBackground(Color.WHITE); // Fondo blanco
    textFieldDosis.setForeground(inputFieldTextColor); // Texto negro
    textFieldDosis.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    textFieldDosis.setCaretColor(inputFieldTextColor); // Color del cursor
    textFieldDosis.setBorder(new LineBorder(inputFieldBorderColor, 1, true));
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0;
    formularioPanel.add(textFieldDosis, gbc);

    // --- Botón Guardar ---
    JButton btnGuardar = new JButton("Guardar Receta");
    btnGuardar.setBackground(accentColor); // Color de fondo del botón
    btnGuardar.setForeground(Color.WHITE); // Color del texto del botón
    btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Fuente en negrita y más grande
    btnGuardar.setFocusPainted(false); // Elimina el borde de enfoque
    btnGuardar.setBorder(BorderFactory.createCompoundBorder(
        new LineBorder(accentColor.darker(), 1, true), // Borde exterior
        new EmptyBorder(10, 20, 10, 20) // Relleno interior
    ));

    // Añade un ActionListener para definir la acción al hacer clic en el botón.
    btnGuardar.addActionListener(e -> guardarReceta()); // Usa una expresión lambda para simplificar

    gbc.gridx = 0; // Columna 0
    gbc.gridy = 3; // Fila 3 (justo después del último campo)
    gbc.gridwidth = 2; // Ocupa ambas columnas
    gbc.fill = GridBagConstraints.NONE; // El botón no se expande horizontalmente
    gbc.anchor = GridBagConstraints.CENTER; // Centra el botón
    gbc.weighty = 0.0; // No permite que el botón se expanda verticalmente
    gbc.insets = new Insets(25, 5, 8, 5); // Más espacio superior para separar del último campo
    formularioPanel.add(btnGuardar, gbc);

    // Añade el panel del formulario a la región CENTRAL del BorderLayout del panel
    // principal.
    add(formularioPanel, BorderLayout.CENTER);
  }

  // Acción para guardar la receta
  private void guardarReceta() {
    // Obtiene el texto de los campos de entrada, eliminando espacios en blanco al
    // inicio y al final.
    String pacienteID = textFieldPaciente.getText().trim();
    String medicamento = textFieldMedicamento.getText().trim();
    String dosis = textFieldDosis.getText().trim(); // Usa el campo textFieldDosis

    // Valida que los campos obligatorios no estén vacíos.
    if (pacienteID.isEmpty() || medicamento.isEmpty() || dosis.isEmpty()) {
      // Muestra un mensaje de error si algún campo obligatorio está vacío.
      JOptionPane.showMessageDialog(this,
          "Por favor, complete todos los campos obligatorios (ID Paciente, Medicamento, Dosis).",
          "Error de Validación", JOptionPane.ERROR_MESSAGE);
      return; // Detiene la ejecución si hay un error
    }

    // Si los campos son válidos, muestra un mensaje de éxito.
    // Aquí es donde se añadiría la lógica real para guardar la receta en una base
    // de datos, etc.
    JOptionPane.showMessageDialog(this,
        "Receta guardada exitosamente para el paciente " + pacienteID + ": " + medicamento + " - " + dosis,
        "Éxito", JOptionPane.INFORMATION_MESSAGE);

    // Opcional: limpiar los campos después de guardar exitosamente.
    // textFieldPaciente.setText("");
    // textFieldMedicamento.setText("");
    // textFieldDosis.setText("");
  }
}